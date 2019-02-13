package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.world.World;
import com.google.inject.Inject;

public class CartPage extends BasePage {

	@Inject
	public CartPage(World world) {
		super(world);
	}

	@FindBy(how = How.ID, using = "hlb-ptc-btn-native")
	public WebElement btn_ContinueToCheckout;

	public void clickOn_ContinueToCheckout() {
		btn_ContinueToCheckout.click();
	}

}