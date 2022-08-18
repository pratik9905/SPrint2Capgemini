package com.stepdefinition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.Utility;
import com.manager.PageObjectManager;
import com.pages.ProductDetailsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductDetailsSteps extends Utility {

	PageObjectManager manager;
	ProductDetailsPage productDetailsPage;
	String search_keyword;

	@BeforeClass
	@Given("user is on snapdeal home page")
	public void user_is_on_snapdeal_home_page() {

		// Configure the driver and Launch the browser
		getDriver();

		// To get specific url
		loadUrl("https://www.snapdeal.com/");

		// Verifying the URL
		String actualUrl = "https://www.snapdeal.com/";
		String expectedUrl = getCurrentPageUrl();
		System.out.println(expectedUrl + "\n");
		System.out.println(getCurrentPageTitle() + "\n");
		Assert.assertEquals(expectedUrl, actualUrl);

	}

	@Test(priority = 1)
	@Parameters({ "search keyword" })
	@When("user enters {string}")
	public void user_enters(@Optional("RD Sharma maths class10")String text) throws InterruptedException, IOException {
		search_keyword = text;
		productDetailsPage = new ProductDetailsPage();

		//Verifying whether search bar is present
    	boolean  isSearchBoxDisplayed = productDetailsPage.getSearchBox().isDisplayed();
    	boolean  isSearchBoxEnabled= productDetailsPage.getSearchBox().isEnabled();
    	if(isSearchBoxDisplayed==true && isSearchBoxEnabled==true)
    	{
		
		// To enter the Search Keyword
		type(productDetailsPage.getSearchBox(), text);
		// To take screenshot
		getScreenShot("ProductDetails1 Entered Search Keyword");

		
		// To Click on the Search Button
		explicitiWaitClickable(2000, productDetailsPage.getSearchBtn());
		// To take screenshot
		getScreenShot("ProductDetails2 Click on Search button");
    	}
	}

	@Test(priority = 2)
	@When("hover over the desired product and click on QUICK VIEW and then on VIEW DETAILS")
	public void hover_over_the_desired_product_and_click_on_QUICK_VIEW_and_then_on_VIEW_DETAILS()
			throws InterruptedException, IOException {
		// To take screenshot
		getScreenShot("ProductDetails3 Search results");
		
		scrollDown(productDetailsPage.getSearchResultText());

		// To hover over the desired product
		
		Thread.sleep(5000);
		hover(productDetailsPage.getHoverProduct());
		System.out.println("User hovers on the desired product.\n");
		// To take screenshot
		getScreenShot("ProductDetails4 Hover over product"); 

		
		// To take screenshot
		getScreenShot("ProductDetails5 QUICK VIEW"); 
		// To click on QUICK LINK
		explicitiWaitClickable(5000, productDetailsPage.getQuickView());
		System.out.println("User clicks on QUICK VIEW.\n");

		
		// To take screenshot
		Thread.sleep(500); // Wait time to take screenshot
		getScreenShot("ProductDetails6 VIEW DETAILS"); 
		// To click on VIEW DETAILS
		explicitiWaitClickable(5000, productDetailsPage.getViewDetails());
		System.out.println("User clicks on VIEW DETAILS.\n");
		
	}

	@Test(priority = 3)
	@When("expand all offers and hover over the image")
	public void expand_all_offers_and_hover_over_the_image() throws InterruptedException, IOException {

		// To take screenshot
		getScreenShot("ProductDetails7 Selected Product Page");
		
		
		// To expand all offers
		explicitiWaitClickable(5000, productDetailsPage.getMoreOffers());
		System.out.println("User clicks on the button to expand more offers.\n");
		// To take screenshot
		getScreenShot("ProductDetails8 Expand More Offers");

		// To hover on the image
		
		Thread.sleep(5000);
		hover(productDetailsPage.getHoverImage());
		System.out.println("User hovers on the image to zoom it.\n");
		// To take screenshot
		Thread.sleep(500); // Wait time to take screenshot
		getScreenShot("ProductDetails9 Hover over image");
	}

	@Test(priority = 4)
	@Then("clicks on View all item details")
	public void clicks_on_View_all_item_details() throws InterruptedException, IOException {

		// To navigate to Item Details
		btnClick(productDetailsPage.getItemDetails());
		System.out.println("User clicks on View all Items.\n");
		System.out.println("____________________________________________________");
		System.out.println("____________________________________________________");
		// To take screenshot
		Thread.sleep(1000); // Wait time to take screenshot
		getScreenShot("ProductDetails10 Item Details");

		// Fetch All the Products Details
		List<WebElement> list_of_products = productDetailsPage.getProductDetailsList();
		System.out.println("The Product Details for " + search_keyword + " are \n");

		File loc = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\Product Details for"+search_keyword+".xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("Sheet1");
		String productName = productDetailsPage.getProductName().getText();
		String productPrice = productDetailsPage.getProductPrice().getText();
		String productDiscount = productDetailsPage.getProductDiscount().getText();

		s.createRow(0).createCell(0).setCellValue("Product Name:" + productName);
		s.createRow(1).createCell(0).setCellValue("Product Price:" + productPrice);
		s.createRow(2).createCell(0).setCellValue("Product Discount:" + productDiscount);

		System.out.println("Product Name: " + productName);
		System.out.println("Product Price: Rs " + productPrice);
		System.out.println("Product Discount: " + productDiscount + "%");
		System.out.println("____________________________________________________");

		// Use of HashMap to store Products
		for (int i = 3; i < list_of_products.size()+3; i++) {
			s.createRow(i).createCell(0).setCellValue(list_of_products.get(i-3).getText()); // Iterate and fetch product
																							// name
			System.out.println(list_of_products.get(i-3).getText());
		}

		// write the output
		FileOutputStream f = new FileOutputStream(loc);
		w.write(f);
	}

	//close the browser
	@AfterTest
	public void afterTest() {
		closeBrowser();

	}

}