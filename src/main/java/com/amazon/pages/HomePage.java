package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.world.World;
import com.google.inject.Inject;

public class HomePage extends BasePage {

	@Inject
	public HomePage(World world) {
		super(world);
	}

	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	public WebElement txtbx_Srch;

	@FindBy(how = How.CSS, using = "input[type='submit']")
	public WebElement txtbx_Go;

	@FindBy(how = How.CSS, using = "a[aria-label='Shop by Category - Shop now']")
	public WebElement txtbx_shop_by_category;

	public void perform_Search(String search) {
		txtbx_Srch.sendKeys(search);
		txtbx_Go.click();
	}

	public void navigateTo_HomePage() {
		world.driver.get(world.config.getURL());
		world.db.getConnection();
	}

}