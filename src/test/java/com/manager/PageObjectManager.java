package com.manager;

import com.pages.CartPage;
import com.pages.ComparePage;
import com.pages.ContactUsPage;
import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import com.pages.SortFilterPage;

public class PageObjectManager {
	
	private CartPage cartPage;
	private ComparePage comparePage;
	private ContactUsPage contactUsPage;
	private ProductDetailsPage productDetailsPage;
	private SearchPage searchPage;
	private SortFilterPage sortFilterPage;	
	
	public CartPage getCartPage() {
		return (cartPage==null)? cartPage = new CartPage(): cartPage;
	}
	
	public ComparePage getComparePage() {
		return (comparePage==null)? comparePage = new ComparePage(): comparePage;
	}
	
	public ContactUsPage getContactUsPage() {
		return (contactUsPage==null)? contactUsPage = new ContactUsPage(): contactUsPage;
	}
	
	public ProductDetailsPage getProductDetailsPage() {
		return (productDetailsPage==null)? productDetailsPage = new ProductDetailsPage(): productDetailsPage;
	}
	
	public SearchPage getSearchPage() {
		return (searchPage==null)? searchPage = new SearchPage(): searchPage;
	}
	
	public SortFilterPage getSortFilterPage() {
		return (sortFilterPage==null)? sortFilterPage = new SortFilterPage(): sortFilterPage;
	}
}
