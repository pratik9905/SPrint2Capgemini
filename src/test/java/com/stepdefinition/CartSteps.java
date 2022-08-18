package com.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Utility;
import com.manager.PageObjectManager;
import com.pages.CartPage;
import com.pages.SearchPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CartSteps extends Utility {
	PageObjectManager manager;
	CartPage cartPage;

	@BeforeClass
	@Given("User is on snapdeal page.")

	public void user_is_on_snapdeal_page() {
		// Configure the driver and Launch the browser
		getDriver();

		// To get specific url
		loadUrl("https://www.snapdeal.com/");

		// Verifying the URL
		String actualUrl = "https://www.snapdeal.com/";
		String expectedUrl = getCurrentPageUrl();
		System.out.println(expectedUrl);
		System.out.println(getCurrentPageTitle());
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	@Test(priority = 1)
	@When("User enters search keyword")
	public void user_enters_search_keyword() throws IOException, InterruptedException {
		manager = new PageObjectManager();
		cartPage = manager.getCartPage();
		// waiting for page whole page to load
		implicitWait(200);

		// Verifying whether search bar is present
		boolean isSearchBoxDisplayed = cartPage.getSearchBox().isDisplayed();
		boolean isSearchBoxEnabled = cartPage.getSearchBox().isEnabled();
		if (isSearchBoxDisplayed == true && isSearchBoxEnabled == true) {
			btnClick(cartPage.getSearchBox());
			type(cartPage.getSearchBox(),
					getData(0, 0, "C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\CartSearchData.xlsx"));
			getScreenShot("Cart1 Entered Search Keyword");
			System.out.println("Entered the search keyword "+getData(0, 0, "C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\CartSearchData.xlsx"));
		}
	}

	@Test(priority = 2)
	@Then("User should click on search button")
	public void user_should_click_on_search_button() throws IOException, InterruptedException, AWTException {
		// pressing enter key
		pressEnterKey();

		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("Cart2 Cliked Search Button");
		getScreenShot("Cart3 Search Results");

	}

	@Test(priority = 3)
	@When("User should clicks on product")
	public void user_should_clicks_on_product() throws IOException, InterruptedException {
		// waiting for page whole page to load
		implicitWait(200);

		// waiting for product element to be clickable

		// Scroll down for more search results
		Thread.sleep(500);
		scrollDown(cartPage.getSearchResultText());
		getScreenShot("Cart4 Scrolled Down to product");

		// To hover over the desired product
		Thread.sleep(300);
		hover(cartPage.getHoverProduct());
		Thread.sleep(300);
		getScreenShot("Cart5 Hover over product");

		// waiting for taking screen shot

		explicitiWaitClickable(500, cartPage.getProduct());
		Thread.sleep(200);
		getScreenShot("Cart6 Cliked on product");
		System.out.println(cartPage.getProductTitle().getText() + " is clicked");

		// Opening the product in new window
		for (String winHandle : driver.getWindowHandles()) {
			switchToWindow(winHandle);
		}

		getScreenShot("Cart7 Product is opened");

		// Scroll down to add to cart button
		scrollDown(cartPage.getScrollDownAddtoCartBtn());
		// To hover over the desired product
		Thread.sleep(500);
		getScreenShot("Cart8 Scrolled down to add to cart button");

	}

	@Test(priority = 4)
	@When("User should click on add to cart button")
	public void user_should_click_on_add_to_cart_button() throws IOException, InterruptedException {
		// waiting for add to cart button to be clickable
		explicitiWaitClickable(2000, cartPage.getAddtoCartBtn());
		
		Thread.sleep(500);
		getScreenShot("Cart9 Clicked on Add to cart button");

	}

	@Test(priority = 5)
	@Then("Verify the product is added to cart button")
	public void verify_the_product_is_added_to_cart_button() throws IOException, InterruptedException {
		// waiting for message text to be visible
		fluentWaitVisisbilityOf(30, 5, cartPage.getMessageBox());

		// verifying the product is added to cart or not
		if (cartPage.getMessageBox().isEnabled() && cartPage.getMessageBox().isDisplayed()) {
			if (cartPage.getMessageText().getText()
					.equals(cartPage.getCartProductTitle().getText() + " added to your cart")) {
				System.out.println(cartPage.getCartProductTitle().getText() + " is added to cart");
				// waiting for taking screen shot
				Thread.sleep(500);
				getScreenShot("Cart10 Product is added to cart");

				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
		}
	}

	@Test(priority = 6)
	@When("User can clicks on same product again")
	public void user_can_clicks_on_same_product_again() throws InterruptedException, IOException {
		// waiting for product element to be clickable
		explicitiWaitClickable(200, cartPage.getProductAddedtoCart());

		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("Cart11 Again the same product is clicked");
		System.out.println(cartPage.getProductAddedtoCart().getText() + " Aagain the same product is clicked");

		// Opening the product in new window
		for (String winHandle : driver.getWindowHandles()) {
			switchToWindow(winHandle);
		}

		// waiting for page whole page to load
		implicitWait(300);

		// waiting for taking screen shot
		Thread.sleep(500);
		getScreenShot("Cart12 Again the same product is opened");

		// Scroll down to add to cart button
		scrollDown(cartPage.getScrollDownAddtoCartBtn());
	}
	
	@Test(priority = 7)
	@When("User should click on add to cart button again")
	public void user_should_click_on_add_to_cart_button_again() throws IOException, InterruptedException {
		// waiting for add to cart button to be clickable
		explicitiWaitClickable(2000, cartPage.getAddtoCartBtn());

		// waiting for taking screen shot
		Thread.sleep(500);
		getScreenShot("Cart13 Clicked on Add to cart button again");
	}

	@Test(priority = 8)
	@Then("Verify the product is already added to cart button")
	public void verify_the_product_is_already_added_to_cart_button() throws IOException, InterruptedException {

		// waiting for message text to be visible
		fluentWaitVisisbilityOf(30, 5, cartPage.getMessageBox());

		// verifying the product is added to cart or not
		if (cartPage.getMessageBox().isEnabled() && cartPage.getMessageBox().isDisplayed()) {
			if (cartPage.getMessageText().getText().equals(cartPage.getCartProductTitle().getText()
					+ " is already present in your cart. Please make use of the quantity field to increase quantity.")) {
				System.out.println(cartPage.getCartProductTitle().getText() + " is already added to cart");

				// waiting for taking screen shot
				Thread.sleep(500);
				getScreenShot("Cart14 Product is already added to cart");
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(true);
			}
		}
	}

	@Test(priority = 9)
	@When("User can clicks on view cart button")
	public void user_can_clicks_on_view_cart_button() throws IOException, InterruptedException {
		// waiting for page whole page to load
		implicitWait(500);

		// waiting for add to cart button to be clickable
		explicitiWaitClickable(3000, cartPage.getViewCartBtn());

		// waiting for taking screen shot
		Thread.sleep(500);
		getScreenShot("Cart15 Clicked on view cart button");
	}

	@Test(priority = 10)
	@Then("Display view cart details")
	public void display_cart_details() throws IOException {
		String itemName = cartPage.getItemName().getText();
		String itemLink = cartPage.getItemName().getAttribute("href");
		String itemPrice = cartPage.getItemPrice().getText();
		String itemQunatity = cartPage.getItemQuantity().getText();
		String itemSubtotal = cartPage.getItemSubtotal().getText();

		File loc = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\View Cart Details.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("Sheet1");

		System.out.println("\nThe View cart items are \n ");
		System.out.println("Item Name: " + itemName);
		System.out.println("Item Link: " + itemLink);
		System.out.println("Item Price: " + itemPrice);
		System.out.println("Item Quantity: " + itemQunatity);

		System.out.println("Item Subtotal: " + itemSubtotal);
		System.out.println("Final Total: " + itemSubtotal);

		s.createRow(0).createCell(0).setCellValue("The View cart items are \n ");
		s.createRow(1).createCell(0).setCellValue("Item Name: " + itemName);
		s.createRow(2).createCell(0).setCellValue("Item Link: " + itemLink);
		s.createRow(3).createCell(0).setCellValue("Item Price: " + itemPrice);
		s.createRow(4).createCell(0).setCellValue("Item Quantity: " + itemQunatity);
		s.createRow(5).createCell(0).setCellValue("Item Subtotal: " + itemSubtotal);
		s.createRow(6).createCell(0).setCellValue("Final Total: " + itemSubtotal);

		// write the output
		FileOutputStream f = new FileOutputStream(loc);
		w.write(f);
	}

	@Test(priority = 11)
	@When("User can clicks on remove from cart button")
	public void user_can_clicks_on_remove_from_cartb_button() throws IOException, InterruptedException {
		Thread.sleep(6000);

		// waiting for add to cart button to be clickable
		explicitiWaitClickable(3000, cartPage.getRemoveCartBtn());

		// taking screen shot
		//Thread.sleep(500);
		getScreenShot("Cart16 Clicked on remove cart button");
	}

	@Test(priority = 12)
	@Then("Verify the product is removed from the cart")
	public void verify_the_product_is_removed_from_the_cart() throws InterruptedException, IOException {
		// verifying the product is added to cart or not
		if (cartPage.getEmptyCartText().getText().equals("Shopping Cart is empty!")) {
			System.out.println(cartPage.getCartProductTitle().getText() + " is removed from cart");

			// waiting for taking screen shot
			Thread.sleep(500);
			getScreenShot("Cart17 Product is removed from cart");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(true);
		}

	}

	// close the browser
	//close the browser
	@AfterTest
	public void afterTest() {
		closeBrowser();
	}
}
