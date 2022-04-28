package leaftaps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DeleteLead extends BaseClass{

		@Test(priority=4)
		void deleteLead() throws InterruptedException {
		
		/// select first leadid
		Thread.sleep(10000);
		WebElement leadid = driver.findElement(By.xpath(
				"//table[contains(@class,'table')]//tr//div[contains(@class,'Id')]/a[contains(@href,'partyId')]"));
		String text = leadid.getText();
		System.out.println("Print lead id" + text);
		leadid.click();
		//// delete selected lead id
		driver.findElement(By.className("subMenuButtonDangerous")).click();
		///// check whether lead id is deleted or not
		driver.findElement(By.xpath("//a[contains(text(),'Catalog')]/preceding::a[text()='Find Leads']")).click();
		driver.findElement(By.name("id")).sendKeys(text);
		driver.findElement(By.xpath("//button[contains(text(),'Find Leads')]")).click();
		Thread.sleep(5000);
		String text2 = driver.findElement(By.xpath("//div[@class='x-paging-info']")).getText();
		System.out.println("page info" + text2);

		if (text2.contains("No records")) {

			System.out.println(" Lead id is deleted sucessfully!!");
		} else {
			System.out.println(" Lead id is not deleted");
		}

		
	}

}