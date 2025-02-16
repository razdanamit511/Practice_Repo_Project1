package com.qa.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.qa.base.BaseTest;
import com.qa.pages.CreateAccountPage;
import com.qa.utils.ConfigReader;

public class TS001 extends BaseTest {

	/* Object creation/ instantiation of page objects */
	CreateAccountPage capage;

	@Test(description = "Verify that the Create an Account form contains below mentioned fields : -\r\n" + "\r\n"
			+ "Labels: -\r\n" + "\"First Name\"\r\n" + "\"Last Name\"\r\n" + "\"Email\"\r\n" + "\"Password\"\r\n"
			+ "\"Confirm Password\"\r\n" + "\r\n" + "Textboxes:-\r\n" + "\"First Name\" textbox\r\n"
			+ "\"Last Name\" textbox\r\n" + "\"Email\" textbox\r\n" + "\"Password\" textbox\r\n"
			+ "\"Confirm Password\" textbox\r\n" + "\r\n" + "Buttons:-\r\n"
			+ "\"Create an account\" button", priority = 0)
	public void TS001_TC001() {

		capage = new CreateAccountPage(driver);
		
		driver.get(ConfigReader.GetCreateAccountPageUrl());
        extentTest.log(Status.INFO, "User opens Create an Account webpage.");
        
		Assert.assertEquals(capage.GetFirstNameLabel(), "First Name");
        extentTest.log(Status.INFO, "fetches the label of first name");

		Assert.assertEquals(capage.GetLastNameLabel(), "Last Name");
        extentTest.log(Status.INFO, "fetches the label of last name");

		Assert.assertEquals(capage.GetEmailLabel(), "Email");
        extentTest.log(Status.INFO, "fetches the label of email");
        
		Assert.assertEquals(capage.GetPasswordLabel(), "Password");
        extentTest.log(Status.INFO, "fetches the label of password");
        
		Assert.assertEquals(capage.GetConfirmPasswordLabel(), "Confirm Password");
        extentTest.log(Status.INFO, "fetches the label of confirm password");
        
		Assert.assertEquals(capage.GetCreateAccountButtonLabel(), "Create an Account");
        extentTest.log(Status.INFO, "fetches the label of Create An Account button");
	}

	@Test(description = "Verify presence of valid placeholder under \"Password\" label / Textbox.Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.\"", priority = 0)
	public void TS001_TC002() {
		
		capage = new CreateAccountPage(driver);
		driver.get(ConfigReader.GetCreateAccountPageUrl());
        extentTest.log(Status.INFO, "User opens Create an Account webpage.");

		capage.EnterPassword("Demo");
        extentTest.log(Status.INFO, "User enters any random non-valid string in Password textbox");


	}

	@Test(description = "Verify that system generates a validation message when clicking on submit button without filling all the mandatory fields.", priority = 3)
	public void TS001_TC003() {
		
		capage = new CreateAccountPage(driver);
		SoftAssert assert1 = new SoftAssert();

		driver.get(ConfigReader.GetCreateAccountPageUrl());
        extentTest.log(Status.INFO, "User opens Create an Account webpage.");

		capage.ClickCreateAccountButton();
        extentTest.log(Status.INFO, "User clicked on Create An Account button");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='firstname-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under first name textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='lastname-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under last name textbox");
        
		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='email_address-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under email address textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='password-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under password textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='password-confirmation-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under password confirmation textbox");

		assert1.assertAll();
	}

	@Test(description = "Verify that entering blank spaces on mandatory fields lead to validation error", priority = 4)
	public void TS001_TC004() {
		
		capage = new CreateAccountPage(driver);
		SoftAssert assert1 = new SoftAssert();

		driver.get(ConfigReader.GetCreateAccountPageUrl());
        extentTest.log(Status.INFO, "User opens Create an Account webpage.");

		capage.EnterFirstName(" ");
        extentTest.log(Status.INFO, "User enters white-spaces in first name textbox");

		capage.EnterLastName(" ");
        extentTest.log(Status.INFO, "User enters white-spaces in last name textbox");

		capage.EnterEmail("    ");
        extentTest.log(Status.INFO, "User enters white-spaces in email-address textbox");

		capage.EnterPassword("  ");
        extentTest.log(Status.INFO, "User enters white-spaces in password textbox");

		capage.EnterConfirmPassword("  ");
        extentTest.log(Status.INFO, "User enters white-spaces in confirmation-password textbox");

		capage.ClickCreateAccountButton();
        extentTest.log(Status.INFO, "User clicks on Create An Account button");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='firstname-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under first name textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='lastname-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under last name textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='email_address-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under email-address textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='password-error']")).getText(),
				"This is a required field.");
        extentTest.log(Status.INFO, "User got warning message under password textbox");

		assert1.assertEquals(driver.findElement(By.xpath("//*[@id='password-confirmation-error']")).getText(),
				"This is a required field.11");
        extentTest.log(Status.INFO, "User got warning message under password confirmation textbox");

		assert1.assertAll();

	}

	@Test(description = "Verify that the password is in encrypted form when entered", priority = 5)
	public void TS001_TC005() {
		
		capage = new CreateAccountPage(driver);
	
		driver.get(ConfigReader.GetCreateAccountPageUrl());
        extentTest.log(Status.INFO, "User opens Create an Account webpage.");

		capage.EnterPassword("demo");
        extentTest.log(Status.INFO, "User enters any random string in password textbox");

		Assert.assertTrue(capage.CheckEncryptedPassword("type"));
        extentTest.log(Status.INFO, "User validates entered string is converted in its encrypted form in password textbox");


	}

}
