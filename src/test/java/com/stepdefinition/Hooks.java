package com.stepdefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.Utility;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Utility{

	@Before
	public void beforeScenario() {
		System.out.println("Scenario Starts");

	}
	
	@After
	public void afterScenario(Scenario sc) throws IOException, InterruptedException{

		if (sc.isFailed()) {
			TakesScreenshot tk = (TakesScreenshot) driver;
            byte[] bk = tk.getScreenshotAs(OutputType.BYTES);
            sc.embed(bk, "image/png");
            
    		File src= tk.getScreenshotAs(OutputType.FILE);
    		File des = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Screenshots\\Failed Scenarios\\"+sc.getName()+".png");
    		// copy file from source to destination
    		FileUtils.copyFile(src,des);
        } else {
    		TakesScreenshot tk1 = (TakesScreenshot) driver;
    		File src= tk1.getScreenshotAs(OutputType.FILE);
    		File des = new File("C:\\Users\\Pratik\\eclipse-workspace2\\SnapdealProject\\Screenshots\\Passed Scenarios\\"+sc.getName()+".png");
    		// copy file from source to destination
    		FileUtils.copyFile(src,des);
        }
		Thread.sleep(300);
		driver.quit();
		System.out.println("Scenario Ends");
	}
}
