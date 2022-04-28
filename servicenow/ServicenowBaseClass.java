package servicenow;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import leaftaps.ReadExcel;

public class ServicenowBaseClass {

	public ChromeDriver driver;// declare driver variable
	public String excelFileName;// declare file name

	//dynamic parameters
	@DataProvider(name = "fetchData") // read data from excel sheet
	public String[][] fetchData() throws IOException {
		return ReadExcel.readData(excelFileName);// pass excel sheet name
	}
	
	// static parameter
	@Parameters({ "url", "username", "password" }) 
	
	// login into application
	@BeforeMethod
	void preConditions(String url, String username, String password) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);// load URL
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));// locate frame element
		driver.switchTo().frame(frame);// switch to frame
		driver.findElement(By.id("user_name")).sendKeys(username);// enter user name
		driver.findElement(By.id("user_password")).sendKeys(password);// enter password
		driver.findElement(By.id("sysverb_login")).click();// click login
		driver.switchTo().defaultContent();// switch from frame
		driver.findElement(By.id("filter")).sendKeys("incident", Keys.ENTER);// enter 'incidents' in search bar
		driver.findElement(By.xpath(
				"//a[@id='b55b4ab0c0a80009007a9c0f03fb4da9']//div[@class='sn-widget-list-title'][normalize-space()='All']"))
				.click();// click 'All' incidents
		}

	@AfterMethod
	void postConditions() {
		driver.close();//closing instance
	}
}
