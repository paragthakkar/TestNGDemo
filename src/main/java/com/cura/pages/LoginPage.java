package com.cura.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cura.testbase.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(id="btn-make-appointment")
	WebElement makeApptBtn;
	
	@FindBy(xpath = "//*[@id='txt-username']")
	WebElement username;
	
	@FindBy(xpath = "//*[@id='txt-password']")
	WebElement password;
	
	@FindBy(xpath = "//*[@id='btn-login']")
	WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String usrname, String pssword) {
		makeApptBtn.click();
		username.sendKeys(usrname);
		password.sendKeys(pssword);
		loginBtn.click();
		return new HomePage();
	}
	
}
