package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homepage extends  basepage{
      
	public homepage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myaccount;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement register;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement login;
	
	
	public void setmyaccount() {
		myaccount.click();
	}
	
	public void setregister() {
		register.click();
	}
	public void setclick() {
		login.click();
	}
}
