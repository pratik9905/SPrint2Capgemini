package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Utility;

public class ComparePage extends Utility {

	public ComparePage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBy(xpath = "//input[@id='inputValEnter']")
	private WebElement searchBox;

	@FindBy(xpath = "//button[@class='searchformButton col-xs-4 rippleGrey' ]")
	private WebElement searchBtn;

	@FindBy(xpath = "//div[@class='product-tuple-image ']//child::img")
	private WebElement selctProduct;

	public WebElement getAddToCompre() {
		return addToCompre;
	}

	@FindBy(xpath = "(//div[@class='product-relative col-xs-5']//child::img)[1]")
	private WebElement selctOthrProduct;

	@FindBy(xpath = "(//div[@class='compare-icon-wrapper']//child::span)[3]")
	private WebElement addToCompre;

	@FindBy(xpath = "//div[@class='col-xs-5 btn-cont pad5']//child::a")
	private WebElement letscompare;

	@FindBy(xpath = "(//div[@class='prod-cont']//p)[1]")
	private WebElement AbtProdct1;

	@FindBy(xpath = "(//div[@class='prod-cont']//p)[3]")
	private WebElement AbtProdct2;

	@FindBy(xpath = "(//div[@class='prod-price alignleft'])[1]")
	private WebElement price1;

	@FindBy(xpath = "(//div[@class='prod-price alignleft'])[2]")
	private WebElement price2;

	@FindBy(xpath = "(//div[@class='compare-cont']//child::tr)[3]")
	private WebElement type;

	@FindBy(xpath = "(//div[@class='product-relative col-xs-5']//child::img)[1]")
	private WebElement scDown1;
	
	@FindBy(xpath = "//div[@class='attr-img']")
	private WebElement scDown2;
	
	@FindBy(xpath = "(//div[@class='compare-cont']//tr//td[ 2 ])[ 5 ]")
	private WebElement scDown3;

	public WebElement getAbtProdct1() {
		return AbtProdct1;
	}

	public WebElement getAbtProdct2() {
		return AbtProdct2;
	}

	public WebElement getPrice1() {
		return price1;
	}

	public WebElement getPrice2() {
		return price2;
	}

	public WebElement getType() {
		return type;
	}

	public WebElement getLetscompare() {
		return letscompare;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSelctProduct() {
		return selctProduct;
	}

	public WebElement getSelctOthrProduct() {
		return selctOthrProduct;
	}
	
	public WebElement getScDown1() {
		return scDown1;
	}

	public WebElement getScDown2() {
		return scDown2;
	}

	public WebElement getScDown3() {
		return scDown3;
	}

}
