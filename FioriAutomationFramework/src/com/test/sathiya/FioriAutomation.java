package com.test.sathiya;


import org.openqa.selenium.WebDriver;

public class FioriAutomation {

	public static void main(String[] args) {
		// Initialize driver
		WebDriver driver;
		
		Initialization in = new Initialization();
		
//		//Login
//		in.Initialize();
//		in.Login("URL");
//		driver = in.getDriver();
//		
//		// Create Apps
//		CreateFioriApps cr = new CreateFioriApps();
//		cr.CreateApps(driver);
//		
//		// Close the browser
//		in.Quit();
		
		// Login
		in.Initialize();
		in.Login("URL1");
		driver = in.getDriver();
		
		// Manage Apps
		ManageFioriApps ma = new ManageFioriApps();
		ma.ManageApps(driver);
		
		// Close browser
//		in.Quit();
	}

}
