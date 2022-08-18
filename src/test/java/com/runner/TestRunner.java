
package com.runner;

//import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.base.JVMReport;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src\\test\\resources\\Features"},glue={"com\\stepdefinition"},
monochrome=true, dryRun=false, strict=true, plugin= {"pretty","json:src\\test\\resources\\Reports\\Cucumber.json",
		"rerun:src\\test\\resources\\Features\\Failed.txt"})



public class TestRunner{
	
	   @AfterClass
	   
	    public static void afterClass() {
		   JVMReport.generateJVMReport(System.getProperty("user.dir")+
				   "\\src\\test\\resources\\Reports\\Cucumber.json");
}
}





