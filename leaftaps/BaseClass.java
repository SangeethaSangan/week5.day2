package leaftaps;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public  ChromeDriver driver;
	
public String excelFileName;

	@DataProvider(name="fetchData")//read data from excel sheet
	public String[][] fetchData() throws IOException {
		
		
		return ReadExcel.readData(excelFileName);//pass excel sheet name
	}
	
	@Parameters({"url","username","password"})//pass static parameters
	
	@BeforeMethod
	void preCondition(String url,String username,String password) {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();// chrome driver initialization
		driver.get(url);// load leaftaps URL
		driver.manage().window().maximize();// maximize browser
		driver.findElement(By.id("username")).sendKeys(username);// locate User name and enter value
		driver.findElement(By.id("password")).sendKeys(password);// locate password and enter value
		driver.findElement(By.className("decorativeSubmit")).click();// locate and click submit button
		driver.findElement(By.linkText("CRM/SFA")).click();// locate and click CRM link
		driver.findElement(By.linkText("Leads")).click();// locate lead and click
	}
	
	


	@AfterMethod
	void postCondition() {
		driver.close();// close browser
	}
	
}
