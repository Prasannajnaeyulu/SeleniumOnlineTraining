package com.google.gmail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageObject {
	protected static WebDriver driver;	
	protected static WebDriverWait wait;
	protected static EventFiringWebDriver event_webdriver;
	protected static Properties objectrepo;

	public AbstractPageObject() throws FileNotFoundException, IOException{
		if(driver==null){
			driver = new FirefoxDriver();
			event_webdriver=new EventFiringWebDriver(driver);
			event_webdriver.register(new WebDriverListener());
			driver = event_webdriver;
			driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#password");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 30);
			objectrepo = new Properties();
			objectrepo.load(new FileInputStream("ObjectRepository.properties"));
		}
		PageFactory.initElements(driver, this);
	}

	public static WebDriver getDriver(){
		return driver;
	}
	
	
	public void typeEditbox(WebElement editbox, String texttotype){	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editbox.clear();
		editbox.sendKeys(texttotype);
	}
	
	public void clickButton(WebElement btn){
		if(btn.isDisplayed() && btn.isEnabled())
			btn.click();
		
		if(driver instanceof InternetExplorerDriver){
			Actions actions = new Actions(driver);
			actions.sendKeys(btn, Keys.ENTER);
			actions.build().perform();
		}
	}
	
	public static WebElement waitForElementVisibility(By by){
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static String getAttribute(By by, String attrname){
		WebElement el = driver.findElement(by);
		return el.getAttribute(attrname);
	}
}
