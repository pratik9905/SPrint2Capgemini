package com.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.Utility;
import com.manager.PageObjectManager;
import com.pages.SearchPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchSteps extends Utility{
	
	PageObjectManager manager;
	SearchPage searchPage;	
	String search_keyword;
	
	@BeforeClass
	@Given("User is on snapdeal page")
	public void user_is_on_snapdeal_page() {
		// Configure the driver and Launch the browser
		getDriver();
		
		// To get specific url		  
		loadUrl("https://www.snapdeal.com/");
		
		// Verifying the URL
		String actualUrl="https://www.snapdeal.com/";
		String expectedUrl= getCurrentPageUrl();
		System.out.println(expectedUrl+"\n");
		System.out.println(getCurrentPageTitle()+"\n");
		Assert.assertEquals(expectedUrl,actualUrl);
		
	}
	
	
	@Test(priority=1)
	@Parameters({"search keyword"})
	@When("User enters {string}")
	public void user_enters(@Optional("Bags under 2000")String searchKeyword) throws InterruptedException, IOException {
		search_keyword = searchKeyword;
		manager = new PageObjectManager();
        searchPage = manager.getSearchPage();
        // waiting for page whole page to load
        implicitWait(500);

		//Verifying whether search bar is present
    	boolean  isSearchBoxDisplayed = searchPage.getSearchBox().isDisplayed();
    	boolean  isSearchBoxEnabled= searchPage.getSearchBox().isEnabled();
    	if(isSearchBoxDisplayed==true && isSearchBoxEnabled==true)
    	{
    		btnClick(searchPage.getSearchBox());
    		type(searchPage.getSearchBox(),searchKeyword); 
    		getScreenShot("Search1 Entered Search Keyword");
    		System.out.println("Entered Search Keyword :"+searchKeyword);
	}
    	
	}
	
	@Test(priority=2)
	@Then("User should click search button")
	public void user_should_click_search_button() throws AWTException, InterruptedException, IOException {
		// pressing enter key
		pressEnterKey();
		
		// waiting for taking screen shot
		Thread.sleep(500);
		getScreenShot("Search2 Cliked Search Button");
		getScreenShot("Search3 Search Results");
		
	}
	
	@Test(priority=3)
	@Then("Verify and display the search results")
	public void verify_and_display_the_search_results()  throws IOException, InterruptedException{
		
		String validSearchTitle = "Snapdeal.com - Online shopping India- Discounts - shop Online Perfumes, Watches, sunglasses etc";
		String invalidSearchTitle = "Shop Online for Men, Women & Kids Clothing, Shoes, Home Decor Items";
		String expected = getCurrentPageTitle();
		
		// Verifying a valid search keyword
		if(validSearchTitle.equals(expected)) {
			Assert.assertTrue(true, "Valid search keyword");
			
			
			// Scroll down for more search results 
			scrollDown(searchPage.getScrollDownElem());
			getScreenShot("Search4 Scrolled Down for Valid Search Keyword");
			
			
			// Fetch All the Products Text
			List<WebElement> list_of_products = searchPage.getListOfProducts();
			System.out.println("Total no of products for "+search_keyword+" are "+list_of_products.size()+"\n");
			
			File loc = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\Search Results for "+search_keyword+".xlsx");
			Workbook w = new XSSFWorkbook();
			Sheet s = w.createSheet("Sheet1");	
			
			// Use of HashMaop to store Products 
			for(int i=0;i<list_of_products.size();i++) {
				s.createRow(i).createCell(0).setCellValue(list_of_products.get(i).getText()); //Iterate and fetch product name
				System.out.println(list_of_products.get(i).getText());
			}
			
			// write the output
			FileOutputStream f = new FileOutputStream(loc);
			w.write(f);
		} 	

		// Verifying a invalid search keyword
		if(invalidSearchTitle.equals(expected)) {
			Assert.assertTrue(true, "Invalid search keyword");
			System.out.println("No results found for "+search_keyword);
		} 
		}
		
		
	//close the browser
	@AfterTest
	public void afterTest() {
		closeWindow();

	}

}
