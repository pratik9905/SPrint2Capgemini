package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Utility;

public class ContactUsPage extends Utility{
	public ContactUsPage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBy(id="sdFooter")
	private WebElement footer;
	
	@FindBy(linkText="Contact Us")
	private WebElement contactUs;
	
	@FindBy(xpath="//input[@id='faq_search_text']")
	private WebElement searchMenu;
	
	@FindBy(xpath="//div[@class='row']//child::li")
	private WebElement faq1;
	
	@FindBy(xpath="(//div[@class='row']//child::li)[18]")
	private WebElement faq2;

	@FindBy(xpath="//button[@class='view-all view-all-faq']")
	private WebElement viewAll;

	@FindBy(xpath="//button[@class='view-less view-less-faq']")
	private WebElement viewLess;

	@FindBy(xpath="//div[@class='row answer']")
	private WebElement answer;

	@FindBy(xpath="//div[@class='row question']")
	private WebElement question;

	@FindBy(xpath="//div[@class='row primary_heading']")
	private WebElement topFAQ;
	
	@FindBy(xpath="//ul[@class='row helpcenter_queries_list']//li//span//a")
	private List<WebElement> faqLinks;
	
	@FindBy(xpath="//ul[@class='row helpcenter_queries_list']//li")
	private List<WebElement> faqQuestions;

	public WebElement getFooter() {
		return footer;
	}

	public WebElement getContactUs() {
		return contactUs;
	}

	public WebElement getSearchMenu() {
		return searchMenu;
	}

	public WebElement getFaq1() {
		return faq1;
	}

	public WebElement getFaq2() {
		return faq2;
	}
	
	public WebElement getViewAll() {
		return viewAll;
	}

	public WebElement getViewLess() {
		return viewLess;
	}
	
	public WebElement getQuestion() {
		return question;
	}

	public WebElement getAnswer() {
		return answer;
	}

	public WebElement getTopFAQ() {
		return topFAQ;
	}

	public List<WebElement> getFaqLinks() {
		return faqLinks;
	}
	
	public List<WebElement> getQuestions() {
		return faqQuestions;
	}

}
