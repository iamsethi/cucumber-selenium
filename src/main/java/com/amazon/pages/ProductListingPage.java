package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.world.World;
import com.google.inject.Inject;

public class ProductListingPage extends BasePage {

	@Inject
	public ProductListingPage(World world) {
		super(world);
	}

	@FindBy(how = How.ID, using = "add-to-cart-button")
	public WebElement btn_AddToCart;

	@FindBy(how = How.XPATH, using = "(//a[@class='a-link-normal a-text-normal'])[2]")
	public WebElement prd_List;

	public void clickOn_AddToCart() {
		btn_AddToCart.click();
	}

	public void select_Product(int productNumber) {
		prd_List.click();
	}

}