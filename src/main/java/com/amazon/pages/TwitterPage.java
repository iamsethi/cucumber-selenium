package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.world.World;
import com.google.inject.Inject;

public class TwitterPage extends BasePage {

	@Inject
	public TwitterPage(World world) {
		super(world);
	}

	@FindBy(how = How.NAME, using = "email")
	public WebElement txtbx_Email;

	@FindBy(how = How.NAME, using = "password")
	public WebElement txtbx_Password;

	@FindBy(how = How.ID, using = "continue")
	public WebElement txtbx_Continue;

	@FindBy(how = How.ID, using = "signInSubmit")
	public WebElement txtbx_SignInSubmit;

	public void enter_Email(String email) {
		txtbx_Email.sendKeys(email);
	}

	public void enter_Password(String pswd) {
		txtbx_Password.sendKeys(pswd);
	}

	public void perform_SignIn() {

		enter_Email("testbrianlee@gmail.com");
		// txtbx_Continue.click();
		enter_Password("Login@123");
		txtbx_SignInSubmit.click();

	}

}