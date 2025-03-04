package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaccountpage extends basepage {

	public Myaccountpage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgheading;
	

   @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
   WebElement btn_logout;
	
	
	
	public boolean MyAccountexists() {
		try {
		
	return(	msgheading.isDisplayed());
		}catch(Exception e) {
			return false;
		}
		
		
	}
	
	public void clicklogout() {
		btn_logout.click();
	}
	
	

}
