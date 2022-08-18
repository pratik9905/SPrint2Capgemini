package com.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Utility;
import com.manager.PageObjectManager;
import com.pages.CartPage;
import com.pages.SortFilterPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SortFilterSteps extends Utility{
	PageObjectManager manager;
	SortFilterPage sortFilterPage;	
	
	@BeforeClass
	@Given("User is on Snapdeal page")
	public void user_is_on_Snapdeal_page() {
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
	@When("User enters Ncert Textbook")
	public void user_enters_Ncert_Textbook() throws IOException, InterruptedException {
		manager = new PageObjectManager();
		sortFilterPage = manager.getSortFilterPage();
		// waiting for page whole page to load
        implicitWait(200);

		//Verifying whether search bar is present
    	boolean  isSearchBoxDisplayed = sortFilterPage.getSearchBox().isDisplayed();
    	boolean  isSearchBoxEnabled= sortFilterPage.getSearchBox().isEnabled();
    	if(isSearchBoxDisplayed==true && isSearchBoxEnabled==true)
    	{
    		btnClick(sortFilterPage.getSearchBox());
    		type(sortFilterPage.getSearchBox(),getData(0,0,"C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\SortFilterSearchData.xlsx")); 
    		getScreenShot("SortFilter1 Entered Search Keyword");
	}
	}
	
	@Test(priority=2)
	@Then("User should clicks on search button")
	public void user_should_clicks_on_search_button() throws IOException, InterruptedException, AWTException {
		// pressing enter key
		pressEnterKey();
		
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("SortFilter2 Cliked Search Button");
		getScreenShot("SortFilter3 Search Results");
	}

	@Test(priority=3)
	@When("User click on sort by")
	public void user_click_on_sort_by() throws InterruptedException, IOException {
    	boolean  isSortByDisplayed = sortFilterPage.getSortBy().isDisplayed();
    	boolean  isSortByEnabled = sortFilterPage.getSortBy().isEnabled();
    	if(isSortByDisplayed==true && isSortByEnabled == true)
    	{
    		// waiting for sort by  to be clickable 
    		explicitiWaitClickable(500,sortFilterPage.getSortBy());
    		
    		// waiting for taking screen shot
    		Thread.sleep(200);
    		getScreenShot("SortFilter4 Clicked on Sort By");
    	}
	}
	
	@Test(priority=4)
	@Then("Checks the counts of Options")
	public void checks_the_counts_of_Options() {
		//count the number of elements present in sort by
		List<WebElement> sortByList = sortFilterPage.getSortByList();
		System.out.println("The number of elements present = "+sortByList.size()+" \n ");
		  
		System.out.println("The List of options present : ");
		WebElement sortByOptions = sortFilterPage.getSortByOptions();
	    System.out.println(sortByOptions.getText());
	}
	
	@Test(priority=5)
	@When("User selects the option fresh arrivals")
	public void user_selects_the_option_fresh_arrivals() throws IOException, InterruptedException {
		// waiting for fresh arrivals to be clickable 
		explicitiWaitClickable(500,sortFilterPage.getFreshArrivals());
		  
  		// waiting for taking screen shot
  		Thread.sleep(200);
  		getScreenShot("SortFilter5 Fresh Arrivals is selected");
  		System.out.println("Fresh Arrivals is clicked \n");
  		
		// waiting for page whole page to load
		implicitWait(300);
		 
	}
	
	
	@Test(priority=6)
	@Then("verify the fresh arrival is selected")
	public void verify_the_fresh_arrival_is_selected() {
		// verify the fresh arrival is selected
		boolean  isFreshArivalsDisplayed= sortFilterPage.getSortSelected().isDisplayed();
    	boolean  isFreshArivalsEnabled= sortFilterPage.getSortSelected().isEnabled();
    	if( isFreshArivalsEnabled == true && isFreshArivalsDisplayed == true) {
    		if( sortFilterPage.getSortSelected().getText().equals("Fresh Arrivals") ) {
    			System.out.println("Fresh Arrivals is selected \n");
    			Assert.assertTrue(true);
    		} else {
    			Assert.assertFalse(true);
    		}
    	}
	}
	
	@Test(priority=7)
	@When("User clicks on apply filter options")
	public void user_clicks_on_apply_filter_options() throws InterruptedException, IOException {
		// Handling price range slider
		// Decreasing price range slider
		dragAndDrop(sortFilterPage.getPriceSlider2(), -80, 0);
		
		System.out.println("Price range is selected");
		
  		// waiting for taking screen shot
		Thread.sleep(100);
  		getScreenShot("SortFilter6 Price bar is moved");
  		
		// selecting customer rating
		chceckedJs(sortFilterPage.getRating());
		System.out.println("Customer Rating is selected");
		
  		// waiting for taking screen shot
  		getScreenShot("SortFilter7 Customer rating is selected");
  		
		// Scroll down to subjects
  		Thread.sleep(200);
		scrollDown(sortFilterPage.getScrollDownElem());
  		
  		// selecting customer rating
  		chceckedJs(sortFilterPage.getTopDeals());
  		System.out.println("Top Deals is selected");
  		
  		// selecting Vayu Education of india
  		chceckedJs(sortFilterPage.getVayu());
  		System.out.println("Vayu Education of india is selected");
  		
  		
  		// selecting JBC Press
  		chceckedJs(sortFilterPage.getJbcPress());
  		System.out.println("JBC Press is selected");

  		
  		// selecting Ncert
  		chceckedJs(sortFilterPage.getNcert());
  		System.out.println("NCERT is selected");
 		
  	    // taking screen shot
  		getScreenShot("SortFilter8 Quick Picks are selected");
  		
  		// selecting math subject
  		chceckedJs(sortFilterPage.getMaths());
  		System.out.println("Mathematics is selected");
  		
  		// selecting chemistry subject
  		chceckedJs(sortFilterPage.getChemistry());
  		System.out.println("Chemistry is selected");
  		
  		// selecting chemistry subject
  		chceckedJs(sortFilterPage.getPhysics());
  		System.out.println("Physics is selected\n");
  		
  	    // waiting for taking screen shot
  		Thread.sleep(100);
  		getScreenShot("SortFilter9 Subjects are selected");
  		
  		
		// Scroll down to Customer rating
  		Thread.sleep(2000);
		scrollDown(sortFilterPage.getScrollDownElem2());
		
  		// waiting for taking screen shot
  		Thread.sleep(200);
		getScreenShot("SortFilter10 Scrolled Down for more Filtered Results");
  		
	}
	
	@Test(priority=8)
	@Then("Dispaly the filtered results")
	public void dispaly_the_filtered_results() throws IOException {
		// Fetch All the Products Text
		List<WebElement> list_of_products = sortFilterPage.getListOfProducts();
		System.out.println("Total no of filtered products for "+getData(0,0,"C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\SortFilterSearchData.xlsx")+" are "+list_of_products.size()+"\n");
		
		File loc = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\Filtered Products.xlsx");
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
	
	//close the browser
	@AfterTest
	public void afterMethod() {
		closeWindow();

	}
}
