package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Example2 {

	public static WebDriver driver;
	FileInputStream file;
	Properties prop;
	
	@Test
	public void exp2() throws IOException
	{
		
		file = new FileInputStream("G:\\SeleniumComp\\SeleniumProject\\config.properties");
		
		prop = new Properties();
		
		prop.load(file);
		
		System.setProperty("webdriver.chrome.driver","G:\\seleniumm\\chromedriver_win32(1)\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get(prop.getProperty("URL"));
		
		String Advances = driver.findElement(By.cssSelector("li[id='advances']>span")).getText();
		
		String Declines = driver.findElement(By.cssSelector("li[id='declines']>span")).getText();
		
		String Unchanged = driver.findElement(By.cssSelector("li[id='unchanged']>span")).getText();
		
		
		int AdvanceNum = Integer.parseInt(Advances);
		
		System.out.println("Advances:"+AdvanceNum);
		
		
		int DeclinesNum = Integer.parseInt(Declines);
		
		System.out.println("Declines:"+DeclinesNum);
		
		
		
		int UnchangedNum = Integer.parseInt(Unchanged);
		
		System.out.println("Unchange:"+UnchangedNum);
		
		
		if(AdvanceNum <  DeclinesNum && AdvanceNum <UnchangedNum)
		{
			System.out.println("Advance is lowest");
		}
		else if(DeclinesNum < AdvanceNum && DeclinesNum < UnchangedNum)
		{
			System.out.println("Decline is lowest");
		}
		else {
			System.out.println("Unchange is lowest");
		}
		
		
	}
	
	
}
