package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	public static WebDriver driver;
	
	
	public static WebDriver getDriver(){
		// Configure the driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\driver\\chromedriver.exe");
		
		// Launch the browser
		driver =  new ChromeDriver();
		return driver;
	}
	
	public static void maximize() {
		driver.manage().window().maximize();
	}
	
	public static void switchToWindow(String winHandle) {
		driver.switchTo().window(winHandle);
	}
	
	public static void loadUrl(String url) {
	    driver.get(url);
	    maximize();
	 }
	
	 public static void type(WebElement element, String data) {
	    element.sendKeys(data);
	  }
	 
	 public static void btnClick(WebElement element) {
	    element.click();
	  }
	  
	 public static void closeBrowser() {
	    driver.quit();
	 }
	 
	 public static void closeWindow() {
		    driver.close();
		 }
	 
	 public static void dragAndDrop(WebElement element, int x, int y) {
		 Actions action = new Actions(driver);
		 // Decreasing the slider
		 action.dragAndDropBy(element, -80, 0).perform();
	}
	 
	 public static void hover(WebElement element) {
		 Actions action = new Actions(driver);
		 // Decreasing the slider
		 action.moveToElement(element).perform();
	}
	 
	public static void typeJs(WebElement element, String data) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].setAttribute('value'," + data +")", element);
	}
	
	public static void chceckedJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('checked','checked')",element);
	    
	}
	
	public static void btnClick3(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click", element);
	}
	
	
	public static String getValueByText(WebElement element) {
	    return element.getText();
	}
	  
	public static String getAttributeValue(WebElement element) {
	    return driver.getCurrentUrl();
	}
	
	public static void getUrl(String url) {
	    driver.get(url);
	 }
	
	public static void getScreenShot(String fileName) throws IOException, InterruptedException {
		// Typecast 
		TakesScreenshot tk = (TakesScreenshot) driver;
		 File src= tk.getScreenshotAs(OutputType.FILE);
		
		 File des = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Screenshots\\Scenarios Steps\\"+fileName+".png");
		
		// copy file from source to destination
		FileUtils.copyFile(src,des);
	}
	
	public static void implicitWait(int seconds) {	
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	

	public static String getCurrentPageTitle() {	
		return driver.getTitle();
	}
	
	public static String getCurrentPageUrl() {	
		return driver.getCurrentUrl();
	}
	
	public static void scrollDown(WebElement element) {	
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public static String getData(int row, int cell,String fileName) throws IOException {
		// location
		File loc = new File(fileName);
		FileInputStream f = new FileInputStream(loc);
		Workbook w = new XSSFWorkbook(f);
		Sheet s = w.getSheet("Sheet1");				
		Row r = s.getRow(row);
		Cell c = r.getCell(cell);
		// 1= String, 0 - number and date
		int type = c.getCellType();
		String value = null;
		
		
		if(type == 1) {
			value = c.getStringCellValue();
		} 
		else if(type == 0) {
			if(DateUtil.isCellDateFormatted(c)) {
				Date getDate = c.getDateCellValue();
				SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
				value = sim.format(getDate);
			}
			else {
				double getNum = c.getNumericCellValue();
				long l = (long) getNum;
				value = String.valueOf(l);
			}
		}
		return value;
		
	}
	
	public static void explicitiWaitClickable(int seconds, WebElement element) {
		  WebDriverWait w = new WebDriverWait(driver,seconds);
		  WebElement loc = w.until(ExpectedConditions.elementToBeClickable(element));
		  loc.click();
	}
	
	public static void fluentWaitVisisbilityOf(int seconds, int pollingTime, WebElement element) {
		  Wait w1 = new FluentWait(driver).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(Throwable.class);
		  Object ob = w1.until(ExpectedConditions.visibilityOf(element));

	}
	
	public static void pressEnterKey() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	 }
	
	public static void pressDownKey() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
	 }
}