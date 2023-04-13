/*************************************************************************************************
 *   COPYRIGHT (C) 2022 Tonal Systems, Inc.
 *   The reproduction, transmission or use of this document/file 
 *   or its contents is not permitted without  written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *------------------------------------------------------------------------------------------------
 *   Purpose:  Listeners.java file
 *   Project:  Logistics
 *   Platform: Cloud Service Provider Independent
 *   Compiler: JavaSE-11
 *   IDE:	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *			   Version: 2021-09 (4.21.0)
 *             Build id: 20210910-1417
 *************************************************************************************************/

package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.BrowserConfigGUI;
import resources.GenerateTestReportGUI;


/***************************************************************************************************************
 * ITestListener is an interface which implements testNg listeners
 * FormsGUIListeners function is used to execute a specific event depends on the test execution(OnTestPass/Fail)
 ***************************************************************************************************************/
public class Listeners extends BrowserConfigGUI implements ITestListener{
	
	ExtentTest test; 										  			//declaring test keyword for using it globally
	ExtentReports extent = GenerateTestReportGUI.getReportObject(); 	//Calling Extentreporterng class object where extent method is present and assigning to one variable 
																		//Extentreports is a return type for extent method
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); 	//To maintain thread safe for the class to avoid overridding
	
	/*****************************************************************************************
	 * This method is called on test start to generate method name and method in test reports
	 * @param passing result object 
	 *****************************************************************************************/
	@Override
	public void onTestStart(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName()); 	//Creating the test using extent object 
																		//And getting the method and method name will be created in test reports
		extentTest.set(test); 									   		//setting the extenttest to threadlocal
	}

	/*************************************************************************************************
	 * This method is called on test success to log the status of the test in report when it is passed
	 * @param passing result object to hold the log of passed test case
	 *************************************************************************************************/
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed"); //To log the status of the test in report when it is passed
	}
	
	/*********************************************************************************************************************************
	 * This method is called on test failure to capture the name of the test that got failed and storing it in testMethodName variable
	 * @param passing result object to hold the log of failed test case
	 *********************************************************************************************************************************/
	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable()); 
		//extentTest.get().log(Status.FAIL, "Test Failed");
		//This is used to retrive the failure log and it will send back to this method will be shown in reports
//		WebDriver driver=null; 											//Creating Webdriver object
//		String testMethodName =result.getMethod().getMethodName(); 		//To capture the name of the test that got failed and storing it in testMethodName variable
//		
//		try {
//			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()); //To get access to fields of any class using testng listeners
//		} catch (Exception e) {
//			
//		}
//		try {
//			
//        	extentTest.get().addScreenCaptureFromPath(gUIgetScreenShotPath(testMethodName,driver), result.getMethod().getMethodName()); 	//To capture the screenshot and gives the method and methodname in reports 
//        																																//Selenium take the screenshot from destination file and attach to the reports
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
     }

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush(); //Used for flush the test
	}

}
