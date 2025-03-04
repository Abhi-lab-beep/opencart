package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobject.Myaccountpage;
import Pageobject.homepage;
import Pageobject.loginpage;

public class verify_login_Myaccount extends testbase{
	
	@Test(groups= {"Sanity","Master"})
	public void verifymyaccountexists() {
		
		logger.info("Test is started");
		homepage hp =new homepage (driver);
		hp.setmyaccount();
		hp.setclick();
		
		
		loginpage lg =new loginpage(driver);
		lg.setemail(p.getProperty("email"));
		lg.setpassword(p.getProperty("password"));
		
		lg.setbutton();
		
		Myaccountpage mcc =new Myaccountpage(driver);
		boolean targetpage=mcc.MyAccountexists();
		Assert.assertEquals(targetpage, true);
		
		
	}

}
