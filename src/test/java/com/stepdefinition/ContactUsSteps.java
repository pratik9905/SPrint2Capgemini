package com.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.Utility;
import com.manager.PageObjectManager;
import com.pages.ContactUsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ContactUsSteps extends Utility{
	String search_query;
	PageObjectManager manager;
	ContactUsPage contactUsPage;
	
	@BeforeClass
	@Given("User is on snapdeal Homepage")
	public void user_is_on_snapdeal_Homepage() {
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

	@Test(priority = 1)
	@When("User should scrolls down")
	public void user_should_scrolls_down() throws InterruptedException, IOException {
		manager = new PageObjectManager();
		contactUsPage = new ContactUsPage();
		// waiting for page whole page to load
        implicitWait(200);

		// Scrolling page to footer section
		scrollDown(contactUsPage.getFooter());
		
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("ContactUs1 Scrolled down to footer");
	}

	@Test(priority = 2)
	@When("Uer should clicks on contact us")
	public void uer_should_clicks_on_contact_us() throws InterruptedException, IOException {
		explicitiWaitClickable(500,contactUsPage.getContactUs());
		System.out.println("Cliked on Contact Us");
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("ContactUs2 Cliked on Contact Us");
	}

	@Test(priority = 3)
	@Parameters({ "search query" })
	@When("User enters {string} on search menu")
	public void user_enters_on_search_menu(@Optional("How can I check my order status?")String fAQ) throws  InterruptedException, IOException {
		search_query = fAQ;
		
		//Verifying whether search bar is present
    	boolean  isSearchBoxDisplayed = contactUsPage.getSearchMenu().isDisplayed();
    	boolean  isSearchBoxEnabled= contactUsPage.getSearchMenu().isEnabled();
    	if(isSearchBoxDisplayed==true && isSearchBoxEnabled==true)
    	{
    		explicitiWaitClickable(3000,contactUsPage.getSearchMenu());
    		type(contactUsPage.getSearchMenu(),fAQ);
    		System.out.println("Entered query in search box: "+fAQ);
    		// waiting for taking screen shot
    		Thread.sleep(200);
    		getScreenShot("ContactUs3 Entered query in search box");
    	}

		
		
	}
	
	@Test(priority = 4)
	@When("User can click on FAQs")
	public void user_can_click_on_FAQs() throws InterruptedException, IOException, AWTException {
		
		pressDownKey();
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("ContactUs4 Selected the query");
		
		pressEnterKey();
		// waiting for taking screen shot
		Thread.sleep(300);
		getScreenShot("ContactUs5 Search Results");
		
	}
	
	@Test(priority = 5)
	@When("User can click on any other query from FAQs List")
	public void user_can_click_on_any_other_query_from_FAQs_List() throws IOException, InterruptedException {
		explicitiWaitClickable(3000,contactUsPage.getFaq1());
		getScreenShot("ContactUs5 Clicked on another query");
		System.out.println("Clicked on another query");
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("ContactUs7 Another query search results");
		
		explicitiWaitClickable(3000,contactUsPage.getViewAll());
		
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("ContactUs8 Cliked on view all");

		// Scrolling page to view less 
		scrollDown(contactUsPage.getFaq2());
		
		// waiting for taking screen shot
		Thread.sleep(200);
		getScreenShot("ContactUs9 Scrolled down to view less");
		
	}
	
	@Test(priority = 6)
	@Then("Display searched query results")
	public void display_searched_query_results() throws IOException {
		String question = contactUsPage.getQuestion().getText();
		String answer = contactUsPage.getAnswer().getText();
		String topFAQ= contactUsPage.getTopFAQ().getText();
		
		File loc = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\ContactUs Results for "+ question.replace("?", "") + ".xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("Sheet1");

		System.out.println("The searched query is: "+question);
		System.out.println("The Answer of the query is: "+answer);
		System.out.println("The "+topFAQ+" for " + search_query + " are \n");
		System.out.println(topFAQ+"\n");
	
		s.createRow(0).createCell(0).setCellValue("Question:"+question);
		s.createRow(1).createCell(0).setCellValue("Answer:"+answer);
		s.createRow(2).createCell(0).setCellValue(topFAQ);
		
		// Fetch All the FAQ link and Text
		List<WebElement> list_of_query = contactUsPage.getQuestions();
		List<WebElement> list_of_links = contactUsPage.getFaqLinks();
		for (int i = 3; i < list_of_query.size()+3; i++) {
			s.createRow(i).createCell(0).setCellValue(list_of_query.get(i-3).getAttribute("title")); // Iterate and fetch product
			s.getRow(i).createCell(1).setCellValue(list_of_links.get(i-3).getAttribute("href"));																			// name
			System.out.println(list_of_query.get(i-3).getAttribute("title"));
			System.out.println(list_of_links.get(i-3).getAttribute("href")+"\n");
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

