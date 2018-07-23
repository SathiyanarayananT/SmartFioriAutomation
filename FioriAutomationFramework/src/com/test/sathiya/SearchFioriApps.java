package com.test.sathiya;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFioriApps {

	public void ManageApps(WebDriver driver){
		try{
			// TODO Auto-generated method stub
			// Page to get load
			Utilities ul = new Utilities();
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Go']")));

			// Capture input fields
			List<WebElement> list = ul.Captureinput();
			System.out.println();
//			ul.Capturebutton(driver);
			
			// Enter the values
			ul.WebDyn_Enterinputs(list);

			driver.findElement(By.xpath("//button//following::*[text()='Go']")).click();
			
			//Get the table values
			//Get columns
			List<WebElement> col = driver.findElements(By.xpath("//table/thead/tr/th"));
			for(WebElement cols : col){
				System.out.print(cols.getText());
				System.out.print("\t");
			}
			System.out.println();
			//Get rows
			List<WebElement> rowvalue = driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			for(WebElement rowvalues : rowvalue){
				System.out.print(rowvalues.getText());
				System.out.print("\t");
			}
			// Custom Actions
			
			
		}catch(Exception e){
			System.out.println("Exception occured : " + e);
	        e.printStackTrace();
		}
	}
}
