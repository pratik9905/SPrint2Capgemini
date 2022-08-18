package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Utility;

public class ProductDetailsPage extends Utility{
	
	public ProductDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(id="inputValEnter")
	private WebElement searchBox;
	
	@FindBy(xpath="//span[text()='Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath="(//div[@class='col-xs-6  favDp product-tuple-listing js-tuple ']//child::div)[3]")
	private WebElement hoverProduct;
	
	@FindBy(xpath="//div[@class='clearfix row-disc']//child::div")
	private WebElement quickView;

	@FindBy(xpath="(//div[@class='comp-quickview']//child::a)[4]")
	private WebElement viewDetails;
	
	@FindBy(xpath="//div[@class='moreOffers']")
	private WebElement moreOffers;

	@FindBy(xpath="//div[@class='bx-viewport']")
	private WebElement hoverImage;

	@FindBy(xpath="//span[@class='h-content viewAllDetails']")
	private WebElement itemDetails;

	@FindBy(xpath="//h1[@class='pdp-e-i-head']")
	private WebElement productName;
	
	@FindBy(xpath="//span[@class='payBlkBig']")
	private WebElement productPrice;
	
	@FindBy(xpath="//span[@class='pdpDiscount ']//span")
	private WebElement productDiscount;
	
	@FindBy(xpath="//ul[@class='dtls-list clear']//li")
	private List<WebElement> productDetailsList;
	
	@FindBy(xpath="//span[@class='nnn']")
	private WebElement searchResultText;

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getHoverProduct() {
		return hoverProduct;
	}

	public WebElement getQuickView() {
		return quickView;
	}

	public WebElement getViewDetails() {
		return viewDetails;
	}

	public WebElement getMoreOffers() {
		return moreOffers;
	}

	public WebElement getHoverImage() {
		return hoverImage;
	}

	public WebElement getItemDetails() {
		return itemDetails;
	}
	
	public WebElement getProductName() {
		return productName;
	}

	public WebElement getProductPrice() {
		return productPrice;
	}

	public WebElement getProductDiscount() {
		return productDiscount;
	}

	public List<WebElement> getProductDetailsList() {
		return productDetailsList;
	}
	
	public WebElement getSearchResultText() {
		return searchResultText;
	}
	
	
	
}
