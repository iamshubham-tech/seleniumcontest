package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class Example3 {
	public static WebDriver driver;
	FileInputStream file;
	Properties prop;
	
	@Test
	public void exp3() throws IOException
	{
	file = new FileInputStream("G:\\SeleniumComp\\SeleniumProject\\config.properties");
	
	prop = new Properties();
	
	prop.load(file);
	
	System.setProperty("webdriver.chrome.driver","G:\\seleniumm\\chromedriver_win32(1)\\chromedriver.exe");
	
	driver = new ChromeDriver();
	
	driver.get(prop.getProperty("URL"));
	
	driver.findElement(By.cssSelector("input[id='keyword']")).sendKeys(prop.getProperty("CompanyName"));
	
	//driver.findElement(By.cssSelector("input[id='keyword']")).sendKeys(Keys.RETURN);
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class='selected']>a"))).click();
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	this.TakeScreenshot(driver, "D://test.png");
	
	String faceValue = driver.findElement(By.cssSelector("li[id='face']>span")).getText();
	System.out.println("Face value is: "+faceValue);
	
	String weeksHigh = driver.findElement(By.cssSelector("span[id='high52']")).getText();
	System.out.println("52 weeks high :"+weeksHigh);
	
	String weeksLow = driver.findElement(By.cssSelector("span[id='low52']>font")).getText();
	System.out.println("52 weeks low: "+weeksLow);
	
	}
	
	public static void TakeScreenshot(WebDriver driver, String filePath) throws IOException
	{
		

		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		 
		File destFile = new File(filePath);
		
		FileUtils.copyFile(srcFile, destFile);
	}
}
