package com.amazon.steps;

import com.amazon.pages.HomePage;
import com.google.inject.Inject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class HomePageSteps {

	@Inject
	private HomePage homePage;

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() {
		homePage.navigateTo_HomePage();
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String product) {
		homePage.perform_Search(product);
	}
	
	@When("^he shop by category$")
	public void he_shop_by_category() {
		homePage.txtbx_shop_by_category.click();
	}

}