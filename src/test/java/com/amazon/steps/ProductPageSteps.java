package com.amazon.steps;

import com.amazon.pages.ProductListingPage;
import com.google.inject.Inject;

import cucumber.api.java.en.When;

public class ProductPageSteps {

	@Inject
	private ProductListingPage productListingPage;

	@When("^choose to buy the first item$")
	public void choose_to_buy_the_first_item() {
		productListingPage.select_Product(0);
		productListingPage.clickOn_AddToCart();
	}
}