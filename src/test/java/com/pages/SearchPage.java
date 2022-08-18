package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Utility;

public class SearchPage extends Utility{
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBy(xpath="//input[@id='inputValEnter']")
	private WebElement searchBox;
	

	@FindBy(xpath="//button[@class='searchformButton col-xs-4 rippleGrey' ]")
	private WebElement searchBtn;

	@FindBy(xpath="(//*[@class='col-xs-6  favDp product-tuple-listing js-tuple ' ])[12]")
	private WebElement scrollDownElem;

	@FindBy(xpath="//span[@class='nnn']")
	private WebElement searchResultText;
	
	@FindBy(xpath="//*[@class='product-title' ]")
	private List<WebElement> listOfProducts;

	public WebElement getSearchBox() {
		return searchBox;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	public WebElement getScrollDownElem() {
		return scrollDownElem;
	}

	public List<WebElement> getListOfProducts() {
		return listOfProducts;
	}
	
	public WebElement getSearchResultText() {
		return searchResultText;
	}

}
