package com.test.sathiya;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateFioriFromManageApps {
	
	public void CreateApps(){
		
		// Login
		WebDriver driver = Init.getDriver();
		Utilities ul = new Utilities();
		
		// Wait for Go button
		ul.WaitForElement("Go");
		
		// Click on Add button
		driver.findElement(By.xpath("//*[contains(@title,'Create')]/span/span")).click();
		
		// Wait for save button appear
		ul.WaitForElement("Save");
		
		// Inputs fields
		List<WebElement> list1 = ul.Captureinput();
		
		// Enter values
		ul.WebDyn_Enterinputs(list1);

		
		List<WebElement> list = driver.findElements(By.xpath("//section[@role='region']/div[@role='heading']/div"));
		for(WebElement e : list){
			ReadWriteExcel rx = new ReadWriteExcel();
			System.out.println(e.getText());
			String section = e.getText().trim();
			for(int j=0;j<rx.noOfColumns(section);j++){
				driver.findElement(By.xpath("//div[text()='" + section + "']/following::div/following::button[@title='Add']")).click();
			}
			// Getting header
			List<WebElement> Ls = driver.findElements(By.xpath("//div[text()='" + section+ "']/following::th/span"));
			
			for(int i=0 ;i<Ls.size();i++){
				System.out.println(Ls.get(i).getAttribute("id"));
				
				String TableHeader = "//td[@headers='"+Ls.get(i).getAttribute("id")+"']//child::input";
				
				if(driver.findElements(By.xpath(TableHeader)).size()>0){
					List<WebElement> in = driver.findElements(By.xpath(TableHeader));				
					List<String> val = rx.ReadArray(section , Ls.get(i).getText().trim());
					
					System.out.println(val);
					if(!val.isEmpty()){
						for(int l=0;l<in.size();l++){
							System.out.println(val.get(l));
							// Clear default values
							ul.clearValues(in.get(l));
							in.get(l).sendKeys(val.get(0));
						}
					}
				}
			}
		}
		
		
		
	}

}
