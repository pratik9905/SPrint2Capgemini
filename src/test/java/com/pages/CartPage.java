package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Utility;

public class CartPage extends Utility{
	public CartPage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBy(xpath="//input[@id='inputValEnter']")
	private WebElement searchBox;
	
	@FindBy(xpath="//button[@class='searchformButton col-xs-4 rippleGrey' ]")
	private WebElement searchBtn;

	@FindBy(xpath="(//*[@class='col-xs-6  favDp product-tuple-listing js-tuple ' ])[1]")
	private WebElement product;
	
	@FindBy(xpath="(//p[@class='product-title'])[1]")
	private  WebElement productTitle;

	@FindBy(xpath="(//a[@class='cart-link'])[2]")
	private  WebElement cartProductTitle;

	@FindBy(xpath="//span[contains(text(),'add to cart')]")
	private  WebElement addtoCartBtn;
	
	@FindBy(xpath="//h1[@class='pdp-e-i-head']")
	private   WebElement scrollDownAddtoCartBtn;
	
	@FindBy(xpath="//div[@class='mess-container']")
	private  WebElement messageBox;
	
	@FindBy(xpath="//span[@class='mess-text']")
	private  WebElement messageText;
	
	@FindBy(xpath="//div[@class='btn btn-theme-secondary open-cart']")
	private  WebElement viewCartBtn;
	
	@FindBy(xpath="(//div[@class='remove-item-div'])[1]")
	private  WebElement removeCartBtn;
	
	@FindBy(xpath="//span[@class='product-name']//a")
	private  WebElement productAddedtoCart;

	@FindBy(xpath="//div[@class='cart-heading clearfix']//h3")
	private  WebElement emptyCartText;
	
	@FindBy(xpath="//span[@class='nnn']")
	private WebElement searchResultText;
	
	@FindBy(xpath="//div[@class='col-xs-21 reset-padding height-inherit']")
	private WebElement hoverImage;

	@FindBy(xpath="(//a[@class='item-name'])[1]")
	private WebElement itemName;
	
	@FindBy(xpath="(//span[@class='item-price'])[1]")
	private WebElement itemPrice;
	
	@FindBy(xpath="//div[@class='styledSelect sd-icon-expand-arrow item-quantity customized']")
	private WebElement itemQuantity;
	
	@FindBy(xpath="(//span[@class='item-subtotal-black'])[1]")
	private WebElement itemSubtotal;
	
	
	@FindBy(xpath="(//div[@class='col-xs-6  favDp product-tuple-listing js-tuple ']//child::div)[3]")
	private WebElement hoverProduct;
	
	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getProduct() {
		return product;
	}

	public WebElement getScrollDownAddtoCartBtn() {
		return scrollDownAddtoCartBtn;
	}

	public  WebElement getProductTitle() {
		return productTitle;
	}
	
	public WebElement getAddtoCartBtn() {
		return addtoCartBtn;
	}
	
	public  WebElement getMessageBox() {
		return messageBox;
	}
	
	public WebElement getMessageText() {
		return messageText;
	}
	
	public WebElement getCartProductTitle() {
		return cartProductTitle;
	}

	public  WebElement getViewCartBtn() {
		return viewCartBtn;
	}
	
	public  WebElement getRemoveCartBtn() {
		return removeCartBtn;
	}
	
	public WebElement getProductAddedtoCart() {
		return productAddedtoCart;
	}
	
	public WebElement getEmptyCartText() {
		return emptyCartText;
	}
	
	public WebElement getSearchResultText() {
		return searchResultText;
	}

	public WebElement getHoverProduct() {
		return hoverProduct;
	}
	
	
	public WebElement getItemName() {
		return itemName;
	}

	public WebElement getItemPrice() {
		return itemPrice;
	}

	public WebElement getItemQuantity() {
		return itemQuantity;
	}
	

	public WebElement getItemSubtotal() {
		return itemSubtotal;
	}

	
	
}
