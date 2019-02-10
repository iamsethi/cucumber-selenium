package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.support.World;
import com.google.inject.Inject;

public class HomePage extends BasePage {
	WebDriver driver;

	@Inject
	public HomePage(World world) {
		super(world);
	}

	@FindBy(how = How.NAME, using = "field-keywords")
	public WebElement txtbx_Srch;

	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	public WebElement txtbx_Go;

	public void perform_Search(String search) {
		txtbx_Srch.sendKeys(search);
		txtbx_Go.click();
	}

	public void navigateTo_HomePage() {
		world.driver.get(world.property.getApplicationUrl());
	}

}