package com.amazon.steps;

import com.amazon.pages.CartPage;
import com.google.inject.Inject;

import cucumber.api.java.en.When;

public class CartPageSteps {

	@Inject
	private CartPage cartPage;

	@When("^moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart() {
		cartPage.clickOn_ContinueToCheckout();
	}

}