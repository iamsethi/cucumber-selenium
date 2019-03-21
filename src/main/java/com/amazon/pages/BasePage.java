package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.amazon.world.World;

public abstract class BasePage {
	Logger log = Logger.getLogger(this.getClass());

	protected World world;

	public BasePage(World world) {
		log.info("User on this page");
		this.world = world;
		PageFactory.initElements(world.driver, this);
	}

}
