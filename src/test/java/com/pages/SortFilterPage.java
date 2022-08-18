package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Utility;

public class SortFilterPage extends Utility{
	public SortFilterPage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBy(xpath="//input[@id='inputValEnter']")
	private WebElement searchBox;
	

	@FindBy(xpath="//button[@class='searchformButton col-xs-4 rippleGrey' ]")
	private WebElement searchBtn;

	@FindBy(xpath="(//*[@class='col-xs-6  favDp product-tuple-listing js-tuple ' ])[5]")
	private WebElement scrollDownElem;	
	
	@FindBy(xpath="(//*[@class='col-xs-6  favDp product-tuple-listing js-tuple ' ])[8]")
	private WebElement scrollDownElem2;
	
	@FindBy(xpath="//*[@class='product-title' ]")
	private List<WebElement> listOfProducts;
	
	@FindBy(xpath="//div[@class='sort-drop clearfix']")
	private WebElement sortBy;
	
	@FindBy(xpath="//div[@class='sorting-sec animBounce']//ul//li")
	private List<WebElement> sortByList;

	@FindBy(xpath="//div[@class='sorting-sec animBounce']/ul")
	private WebElement sortByOptions;
	
	@FindBy(xpath="//li[@class='search-li'][5]")
	private WebElement freshArrivals;

	@FindBy(xpath="//div[@class='sort-selected']")
	private WebElement sortSelected;
	
	@FindBy(xpath="//a[@class='price-slider-scroll left-handle ui-slider-handle ui-state-default ui-corner-all hashAdded']")
	private WebElement priceSlider1;
	
	@FindBy(xpath="//a[@class='price-slider-scroll right-handle ui-slider-handle ui-state-default ui-corner-all hashAdded']")
	private WebElement priceSlider2;

	@FindBy(xpath="//input[@id='avgRating-4.0']")
	private WebElement rating;
	
	@FindBy(xpath="//input[@id='TopPicksBooks_s-TopDeals']")
	private WebElement topDeals;
	
	@FindBy(xpath="//input[@id='TopPicksBooks_s-Vayu%20Education%20of%20India']")
	private WebElement vayu;
	
	@FindBy(xpath="//input[@id='Quick_Picks_s-JBC%20Press']")
	private WebElement jbcPress;

	@FindBy(xpath="//input[@id='Quick_Picks_s-NCERT']")
	private WebElement Ncert;

	@FindBy(xpath="//input[@id='Subject_s-Mathematics']")
	private WebElement maths;
	
	@FindBy(xpath="//input[@id='Subject_s-Physics']")
	private WebElement physics;
	
	@FindBy(xpath="//input[@id='Subject_s-Chemistry']")
	private WebElement chemistry;

	
	public WebElement getSearchBox() {
		return searchBox;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getScrollDownElem() {
		return scrollDownElem;
	}
	
	public WebElement getScrollDownElem2() {
		return scrollDownElem;
	}


	public List<WebElement> getListOfProducts() {
		return listOfProducts;
	}
	
	public WebElement getSortBy() {
		return sortBy;
	}


	public List<WebElement> getSortByList() {
		return sortByList;
	}

	public WebElement getFreshArrivals() {
		return freshArrivals;
	}
	
	public WebElement getSortByOptions() {
		return sortByOptions;
	}
	
	public WebElement getSortSelected() {
		return sortSelected;
	}

	public WebElement getPriceSlider1() {
		return priceSlider1;
	}

	public WebElement getPriceSlider2() {
		return priceSlider2;
	}
	
	public WebElement getRating() {
		return rating;
	}
	
	public WebElement getTopDeals() {
		return topDeals;
	}

	public WebElement getVayu() {
		return vayu;
	}

	public WebElement getJbcPress() {
		return jbcPress;
	}
	
	public WebElement getNcert() {
		return Ncert;
	}
	
	
	public WebElement getMaths() {
		return maths;
	}


	public WebElement getPhysics() {
		return physics;
	}


	public WebElement getChemistry() {
		return chemistry;
	}
	
}
