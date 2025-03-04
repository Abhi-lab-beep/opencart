package testcases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class testbase {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity,Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		FileReader file =new FileReader("./src//test/resources//properties");
		p= new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities  capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("winacdows"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching browser");
				return;
			}
			//browser
			switch(br.toLowerCase())
			{
			 case "chrome" : capabilities.setBrowserName("chrome"); break;
			 case "edge" : capabilities.setBrowserName("edge"); break;
			 case "Firefox" : capabilities.setBrowserName("Firefox"); break;
			 default :System.out.println("Invalid browser");
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.2:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome" :driver=new ChromeDriver(); break;
		case "edge"  :driver =new EdgeDriver(); break;	
		case "Firefox":driver = new FirefoxDriver(); break;
		default :System.out.println("Invalid browser");
		}
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl"));
	}
	

	@AfterClass(groups= {"Sanity,Regression","Master"})
	public void teardown() {
		driver.quit();
	}
	
	public String randomstring() {
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
		public String randomnumber() {
			String generatenumber = RandomStringUtils.randomNumeric(10);
			return generatenumber;
		}
		public String randomalpanumeric() {
			String generatedstring=RandomStringUtils.randomAlphabetic(3);
			String generatednumber = RandomStringUtils.randomNumeric(10);
			return (generatedstring+"@"+generatednumber);
		}
		
		 public static String captureScreen(String tname) throws IOException {
		        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		        
		        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		        
		        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		        File targetFile = new File(targetFilePath);
		        
		        sourceFile.renameTo(targetFile);
		        
		        return targetFilePath;
		    }

}
