
   
package leaftaps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditLead extends BaseClass {
	@BeforeTest
	public void setFileName() {
		excelFileName = "EditLead";
	}
	@Test(priority=2,dataProvider="fetchData")//dependsOnMethods="CreateLead"

	
		void editLead(String companyname,String firstname,String lastname,String fNameLocal,String lNameLocal,String Dept,String Description,String email,String UpdateDesc,String SourceId,String Country ){
			driver.findElement(By.linkText("Create Lead")).click();//locate and click create lead
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyname);//locate company name and enter value
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys(firstname);//locate first name and enter value
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastname);//locate last name and enter value

			WebElement element = driver.findElement(By.id("createLeadForm_dataSourceId"));//select 'employee' from source dropdown
			Select dd = new Select(element);
			dd.selectByVisibleText(SourceId);

			driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys(fNameLocal);//locate first local name and enter value
			driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys(lNameLocal);//locate last local name and enter value
			driver.findElement(By.id("createLeadForm_departmentName")).sendKeys(Dept);//locate dept name and enter value
			driver.findElement(By.id("createLeadForm_description")).sendKeys(Description);//locate description and enter value
			driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys(email);//locate email and enter value

			WebElement ele = driver.findElement(By.id("createLeadForm_generalCountryGeoId"));//select 'New York' from country dropdown
			Select dd1 = new Select(ele);
			dd1.selectByVisibleText(Country);
			driver.findElement(By.className("smallSubmit")).click();//locate and click create lead button

			String title = driver.getTitle();//get current title of the page
			System.out.println(title);

			String text = driver.findElement(By.id("viewLead_firstName_sp")).getText();//get lead first name
			System.out.println(text);
		driver.findElement(By.linkText("Edit")).click();//click edit button
		driver.findElement(By.id("updateLeadForm_description")).clear();//clear already present desc
		driver.findElement(By.id("updateLeadForm_importantNote")).sendKeys(Description);//update desc
		driver.findElement(By.name("submitButton")).click();//click submit button

		String Title = driver.getTitle();//get current title of the page
		System.out.println(Title);

	}

}
