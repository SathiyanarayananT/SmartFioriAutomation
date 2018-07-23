package com.test.sathiya;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Init {
	
	public static WebDriver webdriver;
	public static EventFiringWebDriver driver;
	
	//Driver initialise
	public static void Initialize(){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\i343453\\Desktop\\Selenium\\geckodriver.exe");
		webdriver = new FirefoxDriver();
		driver = new EventFiringWebDriver(webdriver);
		EventHandler handler = new EventHandler();
		driver.register(handler);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	//Login
	public static void Login(String URL){
		ReadData rd = new ReadData();
		driver.get(rd.readDetails(URL));
		driver.findElement(By.id("USERNAME_FIELD-inner")).sendKeys(rd.readDetails("Username"));
		driver.findElement(By.id("PASSWORD_FIELD-inner")).sendKeys(rd.readDetails("Password"));
		driver.findElement(By.id("LOGIN_LINK")).click();	
	}
	
	// Driver quit
	public static void Quit(){
		driver.quit();

	}
	//Get WebDriver
	public static WebDriver getDriver(){
		return driver;	
	}
	

}
