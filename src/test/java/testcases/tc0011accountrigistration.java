package testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobject.accountregistrationpage;
import Pageobject.homepage;
import jdk.internal.org.jline.utils.Log;

public class tc0011accountrigistration extends testbase {
	@Test(groups= {"Regression","Master"})
	public void verify_accountregistration(){
		
		logger.info("Test Execution is started");
	
		try {
		
		homepage hp =new homepage(driver);
		
		hp.setmyaccount();
		logger.info("Click on the my Account");
		hp.setregister();
		logger.info("Click on the Register");
		
		accountregistrationpage arp =new  accountregistrationpage(driver);
		logger.info("Provided personal information");
		arp.setfirstname(randomstring().toUpperCase());
		arp.setlastname(randomstring().toUpperCase());
		arp.setemail(randomstring().toUpperCase()+"@gmail.com");
		arp.settelephone(randomnumber());
		
		String password = randomalpanumeric();
		arp.setpassword(password);
		arp.setconfirmpass(password);
		arp.setpolicy();
		arp.setcontinue();
		
		logger.info("Validating the confirmation message");
		String confmsg = arp.getconfirmationmsg();
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}catch(Exception e) {
			logger.error("Test is failed");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("Test is fineshed");	
		}
		

}
