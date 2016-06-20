import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bsh.Capabilities;


public class GoogleWebDriverTest {
	WebDriver webdriver;
	
	Map<String,Integer> mapTeamPoints = new HashMap<String, Integer>();
	
	@BeforeTest
	public void setup(){
		//webdriver = new FirefoxDriver();
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, "true");
		//System.setProperty("webdriver.ie.driver", "D:\\Selenium\\IEDriverServer.exe");
		//webdriver = new InternetExplorerDriver(capabilities);
		webdriver = new FirefoxDriver();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//webdriver.get("https://www.google.com");
		//webdriver.get("https://inet.idbibank.co.in");
		//webdriver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		//webdriver.get("file:///D:/SeleniumTest/SeleniumTest/home.html");
		webdriver.get("http://www.espncricinfo.com/indian-premier-league-2016/content/series/968923.html");
		webdriver.manage().window().maximize();
	}
	
	@Test(enabled=false)
	public void googleAllTest() throws InterruptedException{
	/*	//Browser Commands
		webdriver.close(); //close the current browser window opened by webdriver
		webdriver.get(""); //load webbrowser window with this URL
		webdriver.navigate().back(); //browser backward action
		webdriver.navigate().forward(); //browser forward action
		webdriver.getTitle(); //get the current open window title
		webdriver.getPageSource(); //get the html source of currently opened broswer window by webdriver
		webdriver.getCurrentUrl(); //with which url the browser got opened it returns that
		webdriver.getWindowHandle(); //Current window handle id in string form; Window opened by webbrowser
		
		//interactive/information commands 
		WebElement we = webdriver.findElement(By.id("textbox"));
		we.clear();
		we.sendKeys("abcd");
		we.click();
		we.getAttribute("style");
		Point p = we.getLocation();
		int x = p.getX();
		int y = p.getY();
		Alert alert = webdriver.switchTo().alert();
		webdriver.switchTo().frame(2);
		webdriver.switchTo().window("");
		
		logger.info("Found an unwanted alert: "+alert.getText());
		alert.accept();
		alert.dismiss();
		
		Actions action = new Actions(webdriver);
		action.moveToElement(we).click();
		action.build().perform();
		
			
		try{
			webdriver.findElement(By.id("mayexist")).click();
		}
		catch(UnhandledAlertException alertexception)){
			System.out.println("This element exists sometimes. But not all the times so we can ignore it!!!!");
		}
		
		List<WebElement> lsdivels = webdriver.findElements(By.id("//div"));
		if(lsdivels!=null && !lsdivels.isEmpty()){
			for(WebElement element:lsdivels){
				element.clear();
				element.sendKeys("apple");
			}
		}
		else
		{
			new ElementsNotFoundException("No Element is found with the given identifier //div");
		}*/
		//implicitwait
		//explicitwait
		
		doSearch("Selenium Tutorial");
		WebDriverWait wait = new WebDriverWait(webdriver, 60);
		WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium Tutorial")));
		
		//WebElement link = webdriver.findElement(By.linkText("Selenium Tutorial"));
		System.out.println("Present URL:"+ webdriver.getCurrentUrl());
		/*Actions actions = new Actions(webdriver);
		actions.contextClick(link);*/
		link.click();
		wait.until(ExpectedConditions.titleContains("Selenium Tutorial"));
		String actual = webdriver.getCurrentUrl();
		System.out.println(actual);
		Assert.assertEquals("http://www.tutorialspoint.com/selenium/", actual);
		//Free Selenium Tutorials - Guru99
	}
	
	@Test(enabled=false)
	public void googleNewsTest() throws InterruptedException{
		Thread.sleep(5000);
		doSearch("Apple");
		Thread.sleep(5000);
		WebElement link = webdriver.findElement(By.linkText("Apple"));
		link.click();
	}
	
	@Test(enabled=false)
	public void popupWindowHandle(){
		webdriver.findElement(By.cssSelector("img[src$='Phishing_12.jpg']")).click();
		String parentwin = webdriver.getWindowHandle();
		webdriver.findElement(By.linkText("Know more")).click();
		Set<String> setwindowhandles = webdriver.getWindowHandles();
		//iterate over windows until you find a child window
		for(String s:setwindowhandles){
			if(s.equalsIgnoreCase(parentwin))
				continue;
			webdriver.switchTo().window(s);
			break;
		}
		
		webdriver.findElement(By.linkText("How to Apply")).click();
		WebElement howtoapplyheader = webdriver.findElement(By.cssSelector(".subject-org"));
		Assert.assertEquals("How to Apply", howtoapplyheader.getText());
		webdriver.close();
		webdriver.switchTo().window(parentwin);
		String securityinfo = webdriver.findElement(By.xpath("//div[contains(.,'You may have received a spam mail asking you')]")).getText();
		System.out.println(securityinfo);
	}
	
	@Test(enabled=false)
	public void rediffmailLoginAlertTest(){
		webdriver.findElement(By.name("proceed")).click();
		webdriver.switchTo().alert().dismiss();
		/*Alert a  = webdriver.switchTo().alert();
		Assert.assertEquals("Please enter a valid user name", a.getText());
		a.accept();*/
	}
	
	@Test(enabled=false)
	public void handleframes()
	{
		/*int frames = webdriver.findElements(By.xpath("//frame")).size();
		for(int i=0;i<frames;i++){
			webdriver.switchTo().frame(i);
		}*/
		webdriver.switchTo().frame(1);
		webdriver.findElement(By.linkText("tree")).click();
		webdriver.switchTo().defaultContent(); //this will take your webdriver to a window
		webdriver.switchTo().frame(0);
		System.out.println(webdriver.findElement(By.xpath("//h3")).getText());
		webdriver.switchTo().defaultContent();
		webdriver.switchTo().frame("subFrame");
		webdriver.switchTo().frame("frameDetail");
		webdriver.findElement(By.id("myBtn2")).click();
		webdriver.switchTo().defaultContent();
		webdriver.switchTo().frame("subFrame");
		webdriver.switchTo().frame("frameHeader");
		System.out.println(webdriver.findElement(By.xpath("//h3")).getText());
	}
	
	@Test
	public void handleTables(){
		List<String> ls = TableHandler.getTableColumnNames(webdriver, By.cssSelector("section[class='intermediate'] .ch-points-table table th"));
		System.out.println(ls);
		
		String kkrpointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'KKR')]/following-sibling::td[6]"));
		String SRHpointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'SRH')]/following-sibling::td[6]"));
		String RCBpointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'RCB')]/following-sibling::td[6]"));
		String MIpointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'MI')]/following-sibling::td[6]"));
		String GLpointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'GL')]/following-sibling::td[6]"));
		String DDpointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'DD')]/following-sibling::td[6]"));
		String rpspointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'RPS')]/following-sibling::td[6]"));
		String kxippointsipl2016 = TableHandler.getTableColumnData(webdriver, By.xpath("//section[@class='intermediate']//table//td[contains(.,'KXIP')]/following-sibling::td[6]"));
		
		System.out.println("KKR Points in current edition of IPL 2016: "+kkrpointsipl2016); 
		mapTeamPoints.put("KKR", Integer.parseInt(kkrpointsipl2016));
		mapTeamPoints.put("SRH", Integer.parseInt(SRHpointsipl2016));
		mapTeamPoints.put("RCB", Integer.parseInt(RCBpointsipl2016));
		mapTeamPoints.put("MI", Integer.parseInt(MIpointsipl2016));
		mapTeamPoints.put("GL", Integer.parseInt(GLpointsipl2016));
		mapTeamPoints.put("DD", Integer.parseInt(DDpointsipl2016));
		mapTeamPoints.put("RPS", Integer.parseInt(rpspointsipl2016));
		mapTeamPoints.put("KXIP", Integer.parseInt(kxippointsipl2016));
	}
	
	@Test(dependsOnMethods="handleTables")
	public void verifyTeamPoints(){
		int expectedpoints = 14; 
		String teamname = "GL";
		
		int actualpoints = mapTeamPoints.get(teamname);
		
		Assert.assertEquals(expectedpoints, actualpoints);
	}
	
	public void doSearch(String searchtext){
		WebElement ele = webdriver.findElement(By.id("lst-ib"));
		ele.sendKeys(searchtext);
		WebElement btn = webdriver.findElement(By.name("btnG"));
		btn.click();
	}
	
	@AfterTest
	public void teardown(){
		webdriver.close();
	}
}
