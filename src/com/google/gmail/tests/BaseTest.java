package com.google.gmail.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.google.gmail.AbstractPageObject;
import com.google.gmail.WebDriverListener;
import com.google.gmail.common.TestStep;


public class BaseTest {
	/*WebDriver driver;// = new FirefoxDriver();
	WebDriverWait wait; //= new WebDriverWait(driver,30);*/
	
	WebDriver driver;
	Map<String, List<TestStep>> mapTestSteps = new HashMap<String, List<TestStep>>();
	protected String filename;
   
	public BaseTest(){
		
	}
	
	public BaseTest(String filename, String drivertype){
		this.filename = filename;
		if(drivertype.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		if(drivertype.equalsIgnoreCase("internetexplorer")){
			System.setProperty("wedriver.ie.driver", "D:\\Selenium\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		registerListeners(driver);
	}
	
	/*public void setup(){
		driver = AbstractPageObject.getDriver();
		wait = new WebDriverWait(driver,30);
	}*/
	
	private void registerListeners(WebDriver driver) {
		EventFiringWebDriver webdriver = new EventFiringWebDriver(driver);
		webdriver.register(new WebDriverListener());
		this.driver = webdriver;
	}

	@BeforeClass
	public void readTestCases() throws BiffException, FileNotFoundException, IOException{
		File file = new File(filename);
		if(file.exists()){
			Workbook workbook = Workbook.getWorkbook(new FileInputStream(file));
			Sheet sheet = workbook.getSheet(0);
			/*Cell[] cell = sheet.getRow(1);
		String methodname = cell[0].getContents();
		String keyword = cell[1].getContents();
		String paramname = cell[2].getContents();
		String value = cell[3].getContents();*/

			int rows = sheet.getRows();
			List<TestStep> lsTestStep = new ArrayList<TestStep>();
			String prevmethodname=null;
			for(int i=1;i<rows;i++){
				Cell[] cells = sheet.getRow(i);
				String methodname = cells[0].getContents();

				if(prevmethodname!=null && !methodname.equalsIgnoreCase(prevmethodname)){
					mapTestSteps.put(prevmethodname, lsTestStep);
					lsTestStep = new ArrayList<TestStep>();
				}

				String keyword = cells[1].getContents();
				String elementlocator = cells[2].getContents();
				String value = "";
				if(cells.length>3) //read only if data exists for a firth cell
					value = cells[3].getContents();

				TestStep teststep = new TestStep();
				teststep.setKeyword(keyword);
				teststep.setElementlocator(elementlocator);
				teststep.setValue(value);

				lsTestStep.add(teststep);

				prevmethodname = methodname;
			}
			System.out.println("Map");
			mapTestSteps.put(prevmethodname, lsTestStep);
		}
	}
	
	@AfterClass
	public void teardown(){
		driver.quit();
	}
}
