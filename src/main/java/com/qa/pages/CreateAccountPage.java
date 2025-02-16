package com.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

	WebDriver driver;

	/* Page elements */

	@FindBy(xpath = "//label[@for='firstname']//span")
	WebElement FirstNameLabel;

	@FindBy(xpath = "//label[@for='lastname']//span")
	WebElement LastNameLabel;

	@FindBy(xpath = "//label[@for='email_address']//span")
	WebElement EmailLabel;

	@FindBy(xpath = "//label[@for='password']//span")
	WebElement PasswordLabel;

	@FindBy(xpath = "//label[@for='password-confirmation']//span")
	WebElement ConfirmPasswordLabel;

	@FindBy(xpath = "//*[@id='firstname']")
	WebElement FirstNameTextbox;

	@FindBy(xpath = "//*[@id='lastname']")
	WebElement LastNameTextbox;

	@FindBy(xpath = "//*[@id='email_address']")
	WebElement EmailAddressTextbox;

	@FindBy(xpath = "//*[@id='password']")
	WebElement PasswordTextbox;

	@FindBy(xpath = "//*[@id='password-confirmation']")
	WebElement ConfirmPasswordTextbox;

	@FindBy(xpath = "//*[@title='Create an Account']//span")
	WebElement CreateAccountButton;

	/* Constructor */
	public CreateAccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	/* Action Methods */

	public String GetFirstNameLabel() {

		if (FirstNameLabel.isDisplayed())
			return FirstNameLabel.getText();
		else
			throw new NoSuchElementException("First Name label is not displayed");
	}

	public String GetLastNameLabel() {
		if (LastNameLabel.isDisplayed())
			return LastNameLabel.getText();
		else
			throw new NoSuchElementException("Last Name label is not displayed");
	}

	public String GetEmailLabel() {
		if (EmailLabel.isDisplayed())
			return EmailLabel.getText();
		else
			throw new NoSuchElementException("Email textbox is not displayed");
	}

	public String GetPasswordLabel() {
		if (PasswordLabel.isDisplayed())
			return PasswordLabel.getText();
		else
			throw new NoSuchElementException("Password textbox is not displayed");
	}

	public String GetConfirmPasswordLabel() {

		if (ConfirmPasswordLabel.isDisplayed())
			return ConfirmPasswordLabel.getText();
		else
			throw new NoSuchElementException("Confirm password textbox is not displayed");
	}

	public void EnterFirstName(String value) {

		if (FirstNameTextbox.isDisplayed())
			FirstNameTextbox.sendKeys(value);
		else
			throw new NoSuchElementException("Not able to enter any value in First Name textbox");
	}

	public void EnterLastName(String value) {

		if (LastNameTextbox.isDisplayed())
			LastNameTextbox.sendKeys(value);
		else
			throw new NoSuchElementException("Not able to enter any value in Last Name textbox");
	}

	public void EnterPassword(String value) {
		if (PasswordTextbox.isDisplayed())
			PasswordTextbox.sendKeys(value);
		else
			throw new NoSuchElementException("Not able to enter any value in Password textbox");
	}

	public void EnterConfirmPassword(String value) {
		if (ConfirmPasswordTextbox.isDisplayed())
			ConfirmPasswordTextbox.sendKeys(value);
		else
			throw new NoSuchElementException("Not able to enter any value in Confirm Password textbox");
	}

	public void EnterEmail(String value) {
		if (EmailAddressTextbox.isDisplayed())
			EmailAddressTextbox.sendKeys(value);
		else
			throw new NoSuchElementException("Not able to enter any value in Email textbox");
	}

	public String GetCreateAccountButtonLabel() {

		if (CreateAccountButton.isDisplayed())
			return CreateAccountButton.getText();
		else
			throw new NoSuchElementException("Not able to enter any value in Create An Account textbox");
	}

	public void ClickCreateAccountButton() {

		if (CreateAccountButton.isDisplayed())
			CreateAccountButton.click();
		else
			throw new NoSuchElementException("Not able to enter any value in Create An Account button");
	}

	public boolean CheckEncryptedPassword(String AttributeName) {

		if (PasswordTextbox.getAttribute("type").equalsIgnoreCase("password"))
			return true;
		else
			return false;
	}

}
