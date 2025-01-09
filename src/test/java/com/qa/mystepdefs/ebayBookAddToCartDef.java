package com.qa.mystepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


import pages.EbayPage;

public class ebayBookAddToCartDef {

	private WebDriver driver;
	private EbayPage ebayPage;

	@Before("@ui")
	public void setup(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@After("@ui")
	public void tearDown(){
		if(driver!=null){
			driver.quit();
		}
	}
	@AfterStep("@ui")
	public void attachScreenShot(Scenario scenario) {

		if(scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName()); 
		}   
	}

	@Given("Launch ebay portal")
	public void launch_ebay_portal() {
		driver.get("https://www.ebay.com/");
		ebayPage = new EbayPage(driver);
	}
	@When("Search {string} on the search bar")
	public void search_on_the_search_bar(String book) {
		ebayPage.search("book");
		ebayPage.clickSearchButton();

	}

	@When("Click on the first book in the list")
	public void click_on_the_first_book_in_the_list() {
		ebayPage.clickBook();
	}

	@When("Click to added to cart")
	public void click_to_added_to_cart() {
		ebayPage.clickAddToCart();
	}

	@Then("Verify the cart has been updated and displays the number of items in the cart")
	public void verify_the_cart_has_been_updated_and_displays_the_number_of_items_in_the_cart() {
		int value =  ebayPage.verifyCart();
		Assert.assertEquals(value, 1);

	}
}

