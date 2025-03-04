package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage extends basepage {

	public loginpage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_pass;
	@FindBy(xpath="//input[@value='Login']")
	WebElement bttn_login;
	
	public void setemail(String email) {
		txt_email.sendKeys(email);
	}
	
	public void setpassword(String pass) {
		txt_pass.sendKeys(pass);
	}
	
	public void setbutton() {
		bttn_login.click();
	}
	
	
	
	
	

}
