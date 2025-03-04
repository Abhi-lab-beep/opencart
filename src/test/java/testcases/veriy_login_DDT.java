package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobject.Myaccountpage;
import Pageobject.homepage;
import Pageobject.loginpage;
import Utilities.DataProviders;

public class veriy_login_DDT extends testbase{
	
	@Test(dataProvider = "LoginData",dataProviderClass=DataProviders.class)
	public void eriy_login_DDT(String email , String Pass, String exp) {
	
		try {
		
		homepage hp = new homepage(driver);
	hp.setmyaccount();
	hp.setclick();
	
	
	loginpage lp =new loginpage(driver);
	lp.setemail(email);
	lp.setpassword(Pass);
	lp.setbutton();
	
	
	Myaccountpage mcc =new Myaccountpage(driver);
	boolean targetpage=mcc.MyAccountexists();

 
	if(exp.equalsIgnoreCase("valid"))
	{
		if(targetpage=true)
		{
			mcc.clicklogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("invalid"))
	{
		if(targetpage=true)
		{
			mcc.clicklogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
		}catch(Exception e) {
			Assert.fail();
		}
	
	}

}
