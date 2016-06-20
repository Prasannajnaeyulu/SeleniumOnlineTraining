package com.google.gmail.common;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.thoughtworks.selenium.Wait;

public class SeleniumDriver {
	
	String parentwindowid;

	public void runTestSteps(WebDriver driver, List<TestStep> lsteststep){
			
		for(TestStep step: lsteststep){
			if(step.getKeyword().equalsIgnoreCase("openbrowser")){
				driver.get(step.getValue());
				driver.manage().window().maximize();
			}

			String elementlocator = step.getElementlocator();
			String identifier = elementlocator.substring(elementlocator.indexOf("=")+1);
			By locator=null;
			if(elementlocator.startsWith("id"))
				locator = By.id(identifier);
			if(elementlocator.startsWith("name"))
				locator = By.name(identifier);
			if(elementlocator.startsWith("css"))
				locator = By.cssSelector(identifier);
			if(elementlocator.startsWith("xpath"))
				locator = By.xpath(identifier);
			if(elementlocator.startsWith("link"))
				locator = By.linkText(identifier);
			if(elementlocator.startsWith("partiallink"))
				locator = By.partialLinkText(identifier);
			
			if(elementlocator.startsWith("switchtowindow")){
				parentwindowid = driver.getWindowHandle();
				Set<String> windowhandles = driver.getWindowHandles();
				for(String window: windowhandles){
					 if(!window.equalsIgnoreCase(parentwindowid)){
						 driver.switchTo().window(window);
						 break;
					 }
				}
			}

			if(step.getKeyword().equalsIgnoreCase("type")){
				WebElement textelement = waitForElement(locator, driver);
				textelement.sendKeys(step.getValue());
			}
			if(step.getKeyword().equalsIgnoreCase("click")){
				WebElement btn = waitForElement(locator, driver);
				btn.click();
			}
		}
	}
	public WebElement waitForElement(final By locator, WebDriver driver){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).
											pollingEvery(5, TimeUnit.SECONDS).
											ignoring(NoSuchElementException.class).
											ignoring(ElementNotVisibleException.class);
		
		WebElement webelement = wait.until(new Function<WebDriver, WebElement>(){

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				WebDriverWait wait = new WebDriverWait(driver, 1);
				
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
			
		});
		return webelement;
	}
	
	public void waitForCondition(final String expectedtitle, WebDriver driver){
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).
		pollingEvery(5, TimeUnit.SECONDS);
		
		wait.until(new Predicate<WebDriver>(){
			@Override
			public boolean apply(WebDriver driver) {
				String currentpagetitle = driver.getTitle();
				return currentpagetitle.equalsIgnoreCase(expectedtitle);
			}
		});
	}
}
