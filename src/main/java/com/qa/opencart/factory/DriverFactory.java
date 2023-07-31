package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory 
{
	WebDriver driver;
	public Properties prop;
	OptionsManager options;
	public static Boolean highlight;
	public static ThreadLocal<WebDriver>tlDriver=new ThreadLocal<WebDriver>();
	
	
	public WebDriver initializeDriver(Properties prope)
	{
		String browserName=prope.getProperty("browser").toLowerCase().trim();
		highlight=Boolean.parseBoolean(prope.getProperty("highlight"));
		 options=new OptionsManager(prope);
		
		if(browserName.trim().equals("chrome"))
		{
			//driver=new ChromeDriver(options.getChromeOptions());
			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
		}
		else if (browserName.trim().equals("safari")) 
		{
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else if (browserName.trim().equals("firefox")) 
		{
			//driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		else
		{
			System.out.println("enter correct browser");
			throw new FrameworkException("Enter correct browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
	return getDriver();
	}
	
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	public Properties initProp()
	{
		String env=System.getProperty("env");		
		prop=new Properties();
		FileInputStream fp=null;
		try 
		{
		if(env==null)
		{
			
				fp=new FileInputStream("./src/test/resources/config/qa.config.properties");
				System.out.println("RUNNING ON QA");
		}
		else
		{
			switch (env.toLowerCase().trim()) {
			case "qa":fp=new FileInputStream("./src/test/resources/config/qa.config.properties");
			System.out.println("RUNNING ON QA");
				break;
			case "stage":fp=new FileInputStream("./src/test/resources/config/stage.config.properties");
			System.out.println("RUNNING ON STAGE");
				break;
			case "prod":fp=new FileInputStream("./src/test/resources/config/config.properties");
			System.out.println("RUNNING ON PROD");
				break;
			default:
				System.out.println("wrong env");
				throw new FrameworkException("wrong environment");
			}
		}
		
	}
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		try {
			prop.load(fp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	



}
