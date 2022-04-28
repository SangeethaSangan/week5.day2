package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateIncident extends ServicenowBaseClass {
	@BeforeTest
	public void setFileName() {
		excelFileName = "CreateIncident";// set excel file name
	}
	@Test(dataProvider="fetchData")
	void createIncident(String name,String desc) throws InterruptedException {
		WebElement frame1 = driver.findElement(By.id("gsft_main"));// locate frame element
		driver.switchTo().frame(frame1);// switch to frame
		driver.findElement(By.xpath("//button[text()='New']")).click();// click 'new ' incident

		String incidentNo = driver.findElement(By.id("incident.number")).getAttribute("value");// get incident no
		System.out.println("incident no:" + incidentNo);// print incident no
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).sendKeys(name,Keys.ENTER);//enter user name
		Thread.sleep(2000);
		driver.findElement(By.id("incident.short_description")).sendKeys(desc);// enter short desc
		driver.findElement(By.xpath("//button[text()='Submit']")).click();//click submit button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='form-control'][@placeholder='Search']")).sendKeys(incidentNo,
				Keys.ENTER);// enter incident no in search filter and press enter key

		String resultIncidentNo = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();// get result
		if (resultIncidentNo.equals(incidentNo))// check whether incident no is listed in search results
		{
			System.out.println("incident no:" + incidentNo + " is created successfully");
		} else {

			System.out.println("incident no:" + incidentNo + " is not created ");
		}
	}

}
