package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accountregistrationpage  extends basepage{

	public accountregistrationpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath="//input[@id='input-firstname']")
    WebElement text_fistname;
    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement text_lastname;
    @FindBy(xpath="//input[@id='input-email']")
    WebElement text_email;
    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement text_telephone;
    @FindBy(xpath="//input[@id='input-password']")
    WebElement text_password;
    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement text_confirmpass;
    @FindBy(xpath="//input[@name='agree']")
    WebElement chk_policy;
    @FindBy(xpath="//input[@value='Continue']")
    WebElement bttn_continue;
    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confimationmessage;
    
    public void setfirstname(String fname) {
    	text_fistname.sendKeys(fname);
    }
    public void setlastname(String lname) {
    	text_lastname.sendKeys(lname);
    }
    public void setemail(String email) {
    	text_email.sendKeys(email);
    }
    public void settelephone(String telephone) {
    	text_telephone.sendKeys(telephone);
    }
    public void setpassword(String pass) {
    	text_password.sendKeys(pass);
    }
    public void setconfirmpass(String cpass) {
    	text_confirmpass.sendKeys(cpass);
    }
    public void setpolicy() {
    	chk_policy.click();
    }
    public void setcontinue() {
    	bttn_continue.click();
    }
    public String getconfirmationmsg() {
    	try {
    		return(confimationmessage.getText());
    }catch(Exception e) {
    	return(e.getMessage());
    }

}
}
