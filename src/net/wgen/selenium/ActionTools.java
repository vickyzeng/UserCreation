package net.wgen.selenium;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ActionTools {
	
	public static WebDriver nevigatetoWebpage(WebDriver driver, String url) {
		driver.get(url);
		return driver;
	}
	
	public static WebDriver switchtoFrame(WebDriver driver, String frameName) {
		driver.switchTo().frame(frameName);
		return driver;
	}
	
	public static WebDriver navigateWin(WebDriver driver, String action) {
		if (action.equalsIgnoreCase("forward")) {
			driver.navigate().forward();
		} else {
			driver.navigate().back();
		}
		return driver;
	}
	
	public static WebDriver closePopup(WebDriver driver, Alert alert) {
		Alert popupwin = driver.switchTo().alert();
		popupwin.accept();
		return driver;
	}
	
	public static WebDriver clickButton(WebDriver driver, String identifyBy, String locator){
		switch (identifyBy.toLowerCase()) {
			case "xpath":
				driver.findElement(By.xpath(locator)).click();
				break;
			case "id":
				driver.findElement(By.id(locator)).click();
				break;
			case "name":
				driver.findElement(By.name(locator)).click();
				break;
		}
		return driver;
	}

	public static WebDriver clickLink(WebDriver driver, String identifyBy, String locator){
		switch (identifyBy.toLowerCase()) {
			case "xpath":
				driver.findElement(By.xpath(locator)).click();
				break;
			case "id":
				driver.findElement(By.id(locator)).click();
				break;
			case "name":
				driver.findElement(By.name(locator)).click();
				break;
			case "linkText":
				driver.findElement(By.linkText(locator)).click();
				break;
			case "partialLinkText":
				driver.findElement(By.partialLinkText(locator)).click();
				break;
		}
		return driver;
	}
	 
	public static WebDriver typeinEditbox(WebDriver driver, String identifyBy, String locator, String value) {
		switch(identifyBy.toLowerCase()) {
			case "name":
				driver.findElement(By.name(locator)).clear();
				driver.findElement(By.name(locator)).sendKeys(value);
				break;
			case "id":
				driver.findElement(By.id(locator)).clear();
				driver.findElement(By.id(locator)).sendKeys(value);
				break;
			case "xpath":
				driver.findElement(By.xpath(locator)).clear();
				driver.findElement(By.xpath(locator)).sendKeys(value);
				break;	
		}
		return driver;
	}
	
	public static WebDriver selectRadiobutton(WebDriver driver, String identifyBy, String locator){
		switch(identifyBy.toLowerCase()) {
		case "name":
			driver.findElement(By.name(locator)).click();
			break;
		case "id":
			driver.findElement(By.id(locator)).click();
			break;
		case "xpath":
			driver.findElement(By.xpath(locator)).click();
			break;	
		}
		return driver;
	}

	public static WebDriver selectCheckbox(WebDriver driver, String identifyBy, String locator, String checkFlag){
		switch (identifyBy.toLowerCase()) {
		case "xpath":
			if ((checkFlag).equalsIgnoreCase("ON")){
				if (!(driver.findElement(By.xpath(locator)).isSelected())){
					driver.findElement(By.xpath(locator)).click();
				}
			}
			break;
		case "id":
			if ((checkFlag).equalsIgnoreCase("ON")){
				if (!(driver.findElement(By.id(locator)).isSelected())){
					driver.findElement(By.id(locator)).click();
				}
			}
			break;
		case "name":
			if ((checkFlag).equalsIgnoreCase("ON")){
				if (!(driver.findElement(By.name(locator)).isSelected())){
					driver.findElement(By.name(locator)).click();
				}
			}
			break;
		}
		return driver;
	}

	public static void selectValue(WebDriver driver, String locator, String valToBeSelected){
		 new Select (driver.findElement(By.id(locator))).selectByVisibleText(valToBeSelected);
	}
	
	public static void ImplicitWait(WebDriver driver, int sec){
	    driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	
	public static void forcetoWait (int seconds) {
        long end = System.currentTimeMillis() + seconds * 1000;
        while (System.currentTimeMillis() < end) {}
	}
	
	public static String readFromSystemInput() throws IOException {
		String value = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		value = br.readLine();
		int timer = 0;
		while (value.isEmpty()) {
			value = br.readLine();
			timer ++ ;
			if (timer > 3) {
				break;
			}
		}
		return value;
	}
}