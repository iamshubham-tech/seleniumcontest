package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Example4 {
	
	@Test
	public void exp4() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","G:\\seleniumm\\chromedriver_win32(1)\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		FileInputStream file = new FileInputStream("G:\\SeleniumComp\\SeleniumProject\\config.properties");
		
		Properties prop = new Properties();
		
		prop.load(file);
		
		File excelFile = new File("G:\\SeleniumComp\\SeleniumComp\\SeleniumProject\\Companies.xlsx");
		
		FileInputStream fin = new FileInputStream(excelFile);

	XSSFWorkbook work = new XSSFWorkbook(fin);
	
	
	XSSFSheet sheet = work.getSheetAt(0);
	
	XSSFCell cell;
	
	driver.get(prop.getProperty("URL"));
	
	for (int i=1; i< sheet.getLastRowNum();i++)
	{
		cell = sheet.getRow(i).getCell(0);
		
		driver.findElement(By.cssSelector("input[id='keyword']")).sendKeys(cell.getStringCellValue());
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class='selected']>a"))).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		this.TakeScreenshot(driver, "G://test"+i+".png");
		
		String faceValue = driver.findElement(By.cssSelector("li[id='face']>span")).getText();
		System.out.println("Face value for"+cell.getStringCellValue()+" is: "+faceValue);
		
		String weeksHigh = driver.findElement(By.cssSelector("span[id='high52']")).getText();
		System.out.println("52 weeks for "+cell.getStringCellValue()+" high :"+weeksHigh);
		
		String weeksLow = driver.findElement(By.cssSelector("span[id='low52']>font")).getText();
		System.out.println("52 weeks for"+cell.getStringCellValue()+" low: "+weeksLow);
		
		
	}
		
	}
	
	public static void TakeScreenshot(WebDriver driver, String filePath) throws IOException
	{
		

		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		 
		File destFile = new File(filePath);
		
		FileUtils.copyFile(srcFile, destFile);
	}
}
