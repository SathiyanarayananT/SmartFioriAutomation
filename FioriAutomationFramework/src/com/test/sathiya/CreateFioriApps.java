package com.test.sathiya;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CreateFioriApps {
	
	public void CreateApps(WebDriver driver){
		try{
			Utilities ul = new Utilities();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
			Thread.sleep(3000);
			
			// Frame handling
			int size = driver.findElements(By.tagName("//iframe")).size();
			System.out.println(size);
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			String fid = "";
			for(WebElement element:frames)
			{
				fid = element.getAttribute("id");
				
			}
			driver.switchTo().frame(fid);
			
			// Wait for the page to load
			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Continue']")));
			
//			WebDriverWait wai = new WebDriverWait(driver, 100);
//			wai.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//span/input"))));

			new WebDriverWait(driver, 20)
	        .ignoring(StaleElementReferenceException.class)
	        .until((WebDriver d) -> {
	            d.findElement(By.xpath("//span/input")).click();
	            return true;
	        });
			
			// Get Inputs fields
			List<WebElement> list =ul.Captureinput(driver);
			System.out.println();
			
			// Enter values
			ul.Enterinputs(list , driver);

			// Continue button
			driver.findElement(By.xpath("//*[text()='Continue']/ancestor::div[@role='button']")).click();
			
			// Wait for save button
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Save']")));
			
			new WebDriverWait(driver, 20)
	        .ignoring(StaleElementReferenceException.class)
	        .until((WebDriver d) -> {
	            d.findElement(By.xpath("//span/input")).getText();
	            return true;
	        });
			
			// Inputs fields
			List<WebElement> list1 = ul.Captureinput(driver);
			// Enter values
			ul.Enterinputs(list1 , driver);
			
			// Find Tables and enter values
			System.out.println("Tables present");
			ul.EnterValuesTables(driver);
			
			//tabs
			List<WebElement> tabs = driver.findElements(By.xpath("//span[@role='tab']"));
			List<String> tabnames = new ArrayList<String>();
			for(WebElement tab : tabs){
				tabnames.add(tab.getText());
			}
			for(String tab : tabnames){
				boolean result = true;
			    while(result) {
			        try {
			            driver.findElement(By.xpath("//span[(@role='tab') and (text()='"+ tab.trim()+"')]")).click();
			            result = false;
			            break;
			        } catch(StaleElementReferenceException e) {
			        	result = true;
			        }
			    }
			}
			
			// Save
			driver.findElement(By.xpath("//*[text()='Save']/ancestor::div[@role='button']")).click();

		}catch(Exception e){
			System.out.println("Exception occured : " + e);
	        e.printStackTrace();
		}
	}
}
