package com.test.sathiya;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	public static final int WaitTimeinSECONDS = 50;

	public List<WebElement> Captureinput(){
		WebDriver driver = Init.getDriver();
		boolean result = true;
		while(result) {
	        try {
	    		List<WebElement> list = driver.findElements(By.xpath("//label"));
	    		
	    		System.out.println("Total input fields : "+list.size());
	    		for(WebElement element:list)
	    		{
	    			System.out.println(element.getText());
	    		}
	    		result = false;
	    		return list;
	        }catch(StaleElementReferenceException e) {
	        	result = true;
	        }
		}
		List<WebElement> list = driver.findElements(By.xpath("//label"));
		
		System.out.println("Total input fields : "+list.size());
		
		for(WebElement element:list)
		{
			System.out.println(element.getText());
		}
		return list;
	}
	
	//Capture buttons
	public List<WebElement> Capturebutton(WebDriver driver){
		List<WebElement> list = driver.findElements(By.xpath("//button"));
		System.out.println("Total input fields : "+list.size());
		for(WebElement element:list)
		{
			System.out.println(element.getText());
		}
		return list;
	}
	
	//Provide input value
	public void WebDyn_Enterinputs(List<WebElement> list) {
		// TODO Auto-generated method stub
		WebDriver driver = Init.getDriver();
		for(int i=0;i<list.size();i++){
			ReadWriteExcel rx = new ReadWriteExcel();
			String value = rx.Read(list.get(i).getText());
			
			if( !value.equals("") ){
				ReadData rd = new ReadData();
				String n = "";
				if(driver.findElements(By.xpath("//span[text()='"+list.get(i).getText()+"']/preceding-sibling::input")).size() > 0){
					n="//span[text()='"+list.get(i).getText()+"']/preceding-sibling::input";
				}else{
					// fall back
					n = "//*[text()='" +list.get(i).getText()+ "']/following::input";
				}
				
				WebElement wb = driver.findElement(By.xpath(n));
				if(wb.isDisplayed()){
					if(wb.getAttribute("aria-readonly")== null){
						if(wb.getAttribute("class").equals(rd.readDetails("class"))){
							String a = "//label/bdi[text()='"+ list.get(i).getText() +"']/following::span";
							driver.findElement(By.xpath(a)).click();
						    WebElement drop= driver.findElement(By.xpath("//ul//ancestor::div"));
						    List<WebElement> droplist=drop.findElements(By.tagName("li"));
						    
						    for (WebElement li : droplist) {
						    if (li.getText().equals(value)) {
						         li.click();
						       }
						    }
						}else{
							wb.clear();
							wb.sendKeys(value);
							String val = wb.getAttribute("aria-invalid");
							if(val != null){
								System.err.println("Invalid entry");
							}
						}
					}
				}
			}
		}
	}
	
	// Get inputs fields and provide input for table
	public void WebDyn_EnterTables(WebDriver driver){
		
		List<WebElement> tableinCurrentpage = driver.findElements(By.xpath("//tbody[contains(@id,'content')]"));
		for(WebElement element : tableinCurrentpage){
			String Table = "";
			if(driver.findElements(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::thead//child::*[@role='heading']/span")).size() >0){
				System.out.println("Table :"+driver.findElement(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::thead//child::*[@role='heading']/span")).getText());
				Table=driver.findElement(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::thead//child::*[@role='heading']/span")).getText();
			}else if(driver.findElements(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::tbody//child::*[@role='heading']/span")).size()>0){
				System.out.println("Table :"+driver.findElement(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::tbody//child::*[@role='heading']/span")).getText());
				Table=driver.findElement(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::tbody//child::*[@role='heading']/span")).getText();
			}else if(driver.findElements(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::thead//child::*[@role='heading']")).size()>0){
				System.out.println("Table :"+driver.findElement(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::thead//child::*[@role='heading']")).getText());
				Table=driver.findElement(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//preceding::thead//child::*[@role='heading']")).getText();
			}
			
			
			System.out.println("Fields present inside the Table views :");
			List<WebElement> columns = driver.findElements(By.xpath("//tbody[@id='"+ element.getAttribute("id") +"']//ancestor::div[@role='columnheader']/span/span"));
			for(WebElement Col : columns){
				ReadWriteExcel rx = new ReadWriteExcel();				
				List<String> val = rx.ReadArray(Table, Col.getText());
				List<WebElement> li = driver.findElements(By.xpath("//span[text()='"+Col.getText().trim()+"']/preceding-sibling::input"));
				for(int i=0;i<val.size();i++){
					driver.findElement(By.id(li.get(i).getAttribute("id"))).clear();
					driver.findElement(By.id(li.get(i).getAttribute("id"))).sendKeys(val.get(i));
					String val1 = driver.findElement(By.id(li.get(i).getAttribute("id"))).getAttribute("aria-invalid");
					if(val1 != null){
						System.err.println("Invalid entry");
					}
				}
			}
			
		}
	}
	
	// Hard Clear values 
	public void clearValues(WebElement webElement) {
		// TODO Auto-generated method stub
		webElement.sendKeys(Keys.CONTROL + "a");
		webElement.sendKeys(Keys.DELETE);
	}
	
	// Wait for Elements
	public void WaitForElement(String Element) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(Init.getDriver(), WaitTimeinSECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+ Element +"']")));
	}
	
	// Wait for the frame to load
	public void WaitForFrame() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(Init.getDriver(), WaitTimeinSECONDS);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
	}
	public void actionClick() {
		// TODO Auto-generated method stub
		
	}
	public void inputValues() {
		// TODO Auto-generated method stub
		
	}
}
