import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TableHandler {
	//section[class='intermediate'] .ch-points-table table th
	public static List<String> getTableColumnNames(WebDriver driver, By tableheaderidentifier){
		
		waitandmovetoelement(driver, tableheaderidentifier);
		List<WebElement> lsHeaders = driver.findElements(tableheaderidentifier);
		List<String> ls = new ArrayList<String>();
		for(WebElement header:lsHeaders){
		   ls.add(header.getText().trim());
		}
		return ls;
	}
	
	public static String getTableColumnData(WebDriver driver, By columnidentifier){
		waitandmovetoelement(driver, columnidentifier);
		
		return driver.findElement(columnidentifier).getText();
	}
	
	public static void waitandmovetoelement(WebDriver driver, By elementidentifier){
		//wait until table header element appears
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(elementidentifier));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(elementidentifier));
	}
}
