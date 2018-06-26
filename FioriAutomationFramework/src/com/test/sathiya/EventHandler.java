package com.test.sathiya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventHandler implements WebDriverEventListener {

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
		// Error Handling - Field check
		if(arg1.findElements(By.xpath("//*[text()='Error']")).size() > 0){
			System.err.print("Error Occured in page : ");
			System.out.print(arg1.findElement(By.xpath("//*[text()='Error']/following::span")).getText());
			System.exit(0);
		}
		
		if(arg1.findElements(By.xpath("//*[contains(@aria-label,'Error')]")).size() > 0){
			System.err.print("Error Occured in page : ");
			System.out.print(arg1.findElement(By.xpath("//*[contains(@aria-label,'Error')]//following::*")).getText());
			System.exit(0);
		}
		
		if(arg1.findElements(By.xpath("//*[contains(@aria-label,'Success')]")).size() > 0){
			ReadWriteExcel rd = new ReadWriteExcel();
			System.out.println(arg1.findElement(By.xpath("//*[text()='ID']//following::span[4]")).getText());
			rd.Write( "ID", arg1.findElement(By.xpath("//*[text()='ID']//following::span[4]")).getText());
			WebDriverWait wait3 = new WebDriverWait(arg1, 20);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@aria-label,'Error')] | //*[contains(@aria-label,'Success')]")));
			System.out.println(arg1.findElement(By.xpath("//*[contains(@aria-label,'Success')]//following::*")).getText());

		}
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}
