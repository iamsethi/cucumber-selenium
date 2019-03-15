package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.amazon.world.World;

public abstract class BasePage {

	protected WebDriver driver;

	protected World world;

	// Initiate the Page Factory and create as abstract class, so
	// you can use for all the other Page Objects
	public BasePage(World world) {
		this.driver = world.driver.get("firefox").get().getDriver();
		this.world = world;
		PageFactory.initElements(driver, this);
	}

}
