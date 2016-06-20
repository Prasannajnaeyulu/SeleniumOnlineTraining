package com.google.gmail.pageobject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.gmail.AbstractPageObject;

public class LoginPage extends AbstractPageObject{

	//@FindBy(how=How.ID, id="reauthEmail")
	@FindBy(how=How.ID, id="email-display")
	WebElement existingaccount;
	
	@FindBy(how=How.ID, id="Passwd")
	WebElement pwd;
	
	@FindBy(how=How.ID, id="signIn")
	WebElement sigin;
	
	@FindBy(how=How.ID, id="Email")
	WebElement email;
	
	@FindBy(how=How.ID, id="next")
	WebElement btnnext;
	
	public LoginPage() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean loginwithexistingaccount(String username, String password){
		//if(existingaccount.getText().trim().equalsIgnoreCase(username.trim())){
			typeEditbox(email, username);
			clickButton(btnnext);
			/*pwd.clear();
			pwd.sendKeys(password);*/
			typeEditbox(pwd, password);
			clickButton(sigin);
			return true;
		/*}
		else{
			System.out.println("Unable to match given user account: "+username+"with existing account: "+existingaccount.getText().trim());
			return false;
	    }*/
	}
	
	public String getLoggedinUserTitle(String username){
		String unamelink  = objectrepo.getProperty("usernamelink"); //  //a[contains(@title,'{0}')]
		String unamelinkxpath = MessageFormat.format(unamelink, username);
		return getAttribute(By.xpath("//a[contains(@title,'"+username+"')]"), "title");
	}
	
	public void assertInPage(){
		waitForElementVisibility(By.xpath(objectrepo.getProperty("inboxlink")));
	}

/*	public boolean loginwithdifferentaccount(String username, String pwd){

	}*/
}
