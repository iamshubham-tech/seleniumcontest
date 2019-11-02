package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class Example1 {

 @Test
	public void exp1() throws IOException
	{
		
		String home="https://agiletestingalliance.org";
		String url= "";
		HttpURLConnection huc = null;
        int respCode = 200;
		
	String NavigationURL="https://agiletestingalliance.org/";
	
	FileInputStream file = new FileInputStream("G:\\SeleniumComp\\SeleniumProject\\config.properties");
	
	Properties prop = new Properties();
	
	prop.load(file);
	
	System.setProperty("webdriver.chrome.driver","G:\\seleniumm\\chromedriver_win32(1)\\chromedriver.exe");
	
	WebDriver driver = new ChromeDriver();
	
	driver.get(prop.getProperty("CertificateURL"));
	
	driver.findElement(By.xpath("//a[contains(text(),'Certifications')]")).click();
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	WebElement lookInto = driver.findElement(By.cssSelector("map[name='image-map']>area[target='_self'"));
	
	List<WebElement> links = lookInto.findElements(By.tagName("area"));
	
	System.out.println("Total number of links are :"+links.size());
	
	Iterator<WebElement> it = links.iterator();
	
	while(it.hasNext())
	{
		url= it.next().getAttribute("href");
		
		System.out.println(url);
		
		if(url==null || url.isEmpty())
		{
			System.out.println("URL is either not configured for anchor tag or it is empty");
            continue;
		}
		if(!url.startsWith(home)){
            System.out.println("URL correct.");
            continue;
        }
		
		 try {
             huc = (HttpURLConnection)(new URL(url).openConnection());
             
             huc.setRequestMethod("HEAD");
             
             huc.connect();
             
             respCode = huc.getResponseCode();
             
             if(respCode >= 400){
                 System.out.println(url+" is a broken link");
             }
             else{
                 System.out.println(url+" is a valid link");
             }
		 } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {

             e.printStackTrace();
         }
	}
	
	}}