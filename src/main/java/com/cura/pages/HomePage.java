package com.cura.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cura.testbase.TestBase;

public class HomePage extends TestBase{

	@FindBy(id="txt_visit_date")
	WebElement dateSelector;
	
	@FindBy(id="btn-book-appointment")
	WebElement bookApptBtn;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void bookAppointment(String d) {
		dateSelector.sendKeys(d);
		bookApptBtn.click();
	}
}
