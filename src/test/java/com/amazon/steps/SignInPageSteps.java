package com.amazon.steps;

import com.amazon.pages.SignInPage;
import com.google.inject.Inject;

import cucumber.api.java.en.When;

public class SignInPageSteps {

	@Inject
	private SignInPage signInPage;

	@When("^I sign in on application$")
	public void i_sign_in_on_application() {

		signInPage.perform_SignIn();

	}

}