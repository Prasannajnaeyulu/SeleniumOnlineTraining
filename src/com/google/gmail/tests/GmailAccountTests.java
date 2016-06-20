package com.google.gmail.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gmail.common.SeleniumDriver;
import com.google.gmail.common.TestStep;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class GmailAccountTests extends BaseTest{
	
	SeleniumDriver sedriver;
	Logger log = Logger.getLogger(GmailAccountTests.class);
	
	public GmailAccountTests() {
		super("D:\\Login.xls", "firefox");
		sedriver = new SeleniumDriver();
	}
		/*public void runTestCase(String testmethodname){
			 = lsTestSteps.get(testmethodname);
		}*/

		/*@DataProvider(name="logindata")
	public Object[][] readLoginData() throws FileNotFoundException, IOException, BiffException{
		Properties props = new Properties();
		props.load(new FileReader("LoginTestData.properties"));

		String username="";
		String password="";
		File file = new File("D:\\LoginTestData.xls");
		if(file.exists()){

		Workbook workbook = Workbook.getWorkbook(new FileInputStream(file));
		Sheet sheet = workbook.getSheet(0);
		Cell[] cell = sheet.getRow(1);
		username = cell[0].getContents();
		password = cell[1].getContents();
		}

		String[] str= new String[2];
		str[0] = username;
		str[1] = password;

		String[][] str2 = new String[1][2];
		str2[0] = str;

		return str2;
	}*/

		/*@Test(dataProvider="logindata")
	public void loginwithexistingaccount(String username, String password) throws FileNotFoundException, IOException{
		//Declare test data
		String username="anji.it26@gmail.com";
		String password = "abcdef";

		Properties props = new Properties();
		props.load(new FileReader("LoginTestData.properties"));

		//Do a required action
		LoginPage loginpage = new LoginPage();
		loginpage.loginwithexistingaccount(username, password);

		//Verify and Validate
		loginpage.assertInPage();
		String title = loginpage.getLoggedinUserTitle(username);

		WebElement composemailbtn = waitForElementVisibility(By.xpath("//*[@gh='cm']"));
		String btntext = composemailbtn.getText();

		Validate.validateStringEquals(username, title.substring(title.indexOf("(")+1, title.indexOf(")")), true);
		Validate.validateStringEquals("COMPOSE", btntext, false);	
	}*/
	@Test
	public void loginTest(){
		/*Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:MM:SS"); 
		String currentdatetime = format.format(d);
		
		System.out.println(this.getClass()+"  "+currentdatetime+"   Inside LoginTest Method");*/
		log.info("Inside LoginTest Method...");
		log.error("Inside LoginTest Method...");
		log.warn("Inside LoginTest Method...");
		log.debug("Inside LoginTest Method...");
		/*List<TestStep> lsteststep = mapTestSteps.get("loginTest");
		sedriver.runTestSteps(driver, lsteststep);*/
	}
	
	/*@Test
	public void registerTest(){
		List<TestStep> lsteststep = mapTestSteps.get("RegisterTest");
		sedriver.runTestSteps(driver, lsteststep);
	}*/
}
