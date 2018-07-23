package com.test.sathiya;

import org.openqa.selenium.WebDriver;

public class FioriAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver;
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
		Init.Initialize();
		Init.Login("URL1");
		driver = Init.getDriver();
		
		// Manage Apps
//		SearchFioriApps ma = new SearchFioriApps();
//		ma.ManageApps(driver);
		CreateFioriFromManageApps cr = new CreateFioriFromManageApps();
		cr.CreateApps();
		
		// Close browser
//		in.Quit();		

	}

}
