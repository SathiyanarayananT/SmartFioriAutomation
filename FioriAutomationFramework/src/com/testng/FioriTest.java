package com.testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.sathiya.Init;
import com.test.sathiya.SearchFioriApps;

public class FioriTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void Login(){
		
		// Login
		Init.Initialize();
		Init.Login("URL1");
		driver = Init.getDriver();
		
	}
	
	@AfterMethod
	public void Logout(){
		
		Init.Quit();
		
	}
	
	@Test
	public void SearchApps() {
		  
		SearchFioriApps ma = new SearchFioriApps();
		ma.SearchApps();
		
	}
}
