package leaftaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MergeLead extends BaseClass {

	@BeforeTest
	public void setFileName() {
		excelFileName = "MergeLead";// set excel file name
	}

	@Test(dataProvider = "fetchData")
	void mergeLead(String firstname, String lastname) throws InterruptedException {

		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Merge Leads")).click();// locate and click merge lead button
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();// click icon on FROM Lead
		Set<String> allWindows = driver.getWindowHandles();// get all window references
		List<String> allhandles = new ArrayList<String>(allWindows);// convert into list from set
		driver.switchTo().window(allhandles.get(1));// switch to newwindow from parent window
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstname);// enter name in first name
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();// click on find lead
		Thread.sleep(1000);
		String leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"))
				.getText();// select first listed lead id
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(allhandles.get(0));// switch back to parent window

		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();// click on To Lead
		Set<String> allWindows2 = driver.getWindowHandles();/// get all window references
		List<String> allhandles2 = new ArrayList<String>(allWindows2);// convert into list from set
		driver.switchTo().window(allhandles2.get(1));// switch to newwindow from parent window
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastname);// enter name in first name
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();// click on find lead
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();// select first
																		// lead id
		driver.switchTo().window(allhandles2.get(0));// switch back to parent window
		driver.findElement(By.xpath("//a[text()='Merge']")).click();// click merge lead
		driver.switchTo().alert().accept();// handle alert
		driver.findElement(By.linkText("Find Leads")).click();//click merge lead
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		String text = driver.findElement(By.className("x-paging-info")).getText();
		if (text.equals("No records to display")) {
			System.out.println("Text matched");
		} else {
			System.out.println("Text not matched");
		}

	}
}
