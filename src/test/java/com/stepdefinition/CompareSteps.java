package com.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.Utility;
import com.manager.PageObjectManager;
import com.pages.ComparePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class CompareSteps extends Utility {

	PageObjectManager manager;
	ComparePage comparePage;

	@BeforeClass
	@Given("User is on Snapdeal home Page")
	public void user_is_on_snapdeal_home_page() throws IOException, InterruptedException {

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

		//Thread.sleep(2000);
		// To take screenshot
		//getScreenShot("ComparePage Launch Browser");
	}

	@Test(priority = 1)
	@Parameters({ "search keyword" })
	@When("Enter search the {string}")
	public void enter_search_the(@Optional("Boat Headphone under 3000") String string)
			throws IOException, InterruptedException {
		manager = new PageObjectManager();
		comparePage = manager.getComparePage();
		// Verifying whether search bar is present
		boolean isSearchBoxDisplayed = comparePage.getSearchBox().isDisplayed();
		boolean isSearchBoxEnabled = comparePage.getSearchBox().isEnabled();
		if (isSearchBoxDisplayed == true && isSearchBoxEnabled == true) {
		// To enter the Search Keyword
		type(comparePage.getSearchBox(), string);

		// To take screenshot
		getScreenShot("ComparePage1 Entered Search Keyword");
		}
	}

	@Test(priority = 2)
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException, AWTException, IOException {

		// Pressing Enter to search
		pressEnterKey();
		// To take screenshot
		getScreenShot("ComparePage2 Clicked on Search button");
	}

	@Test(priority = 3)
	@When("select the product")
	public void select_the_product() throws InterruptedException, IOException {

		// select First Product

		explicitiWaitClickable(2000, comparePage.getSelctProduct());
		// To take screenshot
		getScreenShot("ComparePage3 select First Product");

	}

	@Test(priority = 4)
	@When("click on add to compare")
	public void click_on_add_to_compare() throws InterruptedException, IOException {

		// Opening the product in new window

		String pwid = driver.getWindowHandle();
		// System.out.println(pwid);
		Set<String> all = driver.getWindowHandles();
		// System.out.println(all);
		for (String winHandle : all) {
			if (!pwid.equals(winHandle))
				switchToWindow(winHandle);
		}

		Thread.sleep(2000);
		btnClick(comparePage.getAddToCompre());

		// To take screenshot
		getScreenShot("ComparePage4 click add to compare");

		// Type casting WebDriver to JavaScript

		scrollDown(comparePage.getScDown1());
		// To take screenshot
		getScreenShot("ComparePage5 scroll down");

		Thread.sleep(2000);

	}

	@Test(priority = 5)
	@When("select another product")
	public void select_another_product() throws InterruptedException, IOException {

		explicitiWaitClickable(5000, comparePage.getSelctOthrProduct());

		// To take screenshot
		getScreenShot("ComparePage6 select another product");

		// Type casting WebDriver to JavaScript

		scrollDown(comparePage.getScDown2());

		Thread.sleep(2000);

		Thread.sleep(2000);
		btnClick(comparePage.getAddToCompre());

		// To take screenshot
		getScreenShot("ComparePage7 add to compare2");

		Thread.sleep(2000);

	}

	@Test(priority = 6)
	@Then("click on btn Lets compare")
	public void click_on_btn_Lets_compare() throws InterruptedException, IOException {

		System.out.println("complete");

		// click on lets compare
		explicitiWaitClickable(5000, comparePage.getLetscompare());

		// Opening the product in new window
		Thread.sleep(2000);
		String pwid = driver.getWindowHandle();
		// System.out.println(pwid);
		Set<String> all = driver.getWindowHandles();
		// System.out.println(all);
		for (String winHandle : all) {
			if (!pwid.equals(winHandle))
				switchToWindow(winHandle);
		}

		Thread.sleep(2000);
		// To take screenshot
		getScreenShot("ComparePage8 click lets compare");

		scrollDown(comparePage.getScDown3());

		// Fetch All the Products Text

		System.out.println("The Product Details for are \n");

		File loc = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Excel\\Compare Results.xlsx");
		Workbook w = new XSSFWorkbook();

		Sheet s = w.createSheet("Sheet1");
		int r = 3;
		int c = 0;

		String abt_prdt_1 = comparePage.getAbtProdct1().getText();
		String abt_prdt_2 = comparePage.getAbtProdct2().getText();
		String productPrice1 = comparePage.getPrice1().getText();
		String productPrice2 = comparePage.getPrice2().getText();

		s.createRow(0).createCell(1).setCellValue("About product1: " + abt_prdt_1);

		s.getRow(0).createCell(2).setCellValue("About product2: " + abt_prdt_2);

		s.createRow(1).createCell(1).setCellValue("Product Price1: " + productPrice1);

		s.getRow(1).createCell(2).setCellValue("Product Price2: " + productPrice2);

		for (int i = 1; i <= 5; i++) {

			for (int j = 3; j <= 7; j++) {
				WebElement findElements = driver.findElement(By.xpath("(//div[@class='compare-cont']//tr//td[" + i + "])[" + j + "]"));
				String value = findElements.getText();
				System.out.println(value);

				s.createRow(r).createCell(c).setCellValue(value);

				r++;

			}
			c++;

		}
		// // write the output
		FileOutputStream f = new FileOutputStream(loc);
		w.write(f);

		System.out.println("completed");
	}

	
	//close the browser
	@AfterTest
	public void afterTest() {
		closeBrowser();
	}
	
	

}
