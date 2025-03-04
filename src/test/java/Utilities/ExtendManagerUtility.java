package Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.testbase;

public class ExtendManagerUtility implements ITestListener {
    
    public ExtentSparkReporter sparker; // UI of the reports
    public ExtentReports extent; // Populate common info on the report
    public ExtentTest test; // Creating the test case entries and updating the status
    
    public void onStart(ITestContext context) {
        
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timestamp + ".html";
        
        sparker = new ExtentSparkReporter(".\\Reports\\" + repName);
        
        sparker.config().setDocumentTitle("Open Cart Automation Report");
        sparker.config().setReportName("OpenCart Functional Testing");
        sparker.config().setTheme(Theme.DARK);
        
        extent = new ExtentReports();
        extent.attachReporter(sparker);
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Corrected syntax: using `context` instead of `testContext`
        String OS = context.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("Operating System", OS);
        
        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser Name", OS);
        
        List <String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includedGroups.toString());
        }
        
        
    }
    
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+" Got Successfully Executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,result.getName()+" Test failed ");
        test.log(Status.INFO, result.getThrowable().getMessage()); // Improved exception logging
        
        testbase tb = new testbase();
        
        try {
        	String imgpath = testbase.captureScreen(result.getName());
        	test.addScreenCaptureFromPath(imgpath);
        }catch(IOException e1) {
        	e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,  result.getName() + "Test is skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
    
    
    
}


