package com.test.sathiya;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Initialization {
	
	WebDriver webdriver;
	EventFiringWebDriver driver;
	//Driver initialise
	public void Initialize(){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\i343453\\Desktop\\Selenium\\geckodriver.exe");
		webdriver = new FirefoxDriver();
		driver = new EventFiringWebDriver(webdriver);
		EventHandler handler = new EventHandler();
		driver.register(handler);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	//Login
	public void Login(String URL){
		ReadData rd = new ReadData();
		driver.get(rd.readDetails(URL));
		driver.findElement(By.id("USERNAME_FIELD-inner")).sendKeys(rd.readDetails("Username"));
		driver.findElement(By.id("PASSWORD_FIELD-inner")).sendKeys(rd.readDetails("Password"));
		driver.findElement(By.id("LOGIN_LINK")).click();
//		WebDriverWait wait = new WebDriverWait(driver, 25);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));		
	}
	// Driver quit
	public void Quit(){
		driver.quit();

	}
	//Get WebDriver
	public WebDriver getDriver(){
		return driver;	
	}
	

}
