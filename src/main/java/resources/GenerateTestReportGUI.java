/*************************************************************************************************
 *   COPYRIGHT (C) 2022 Tonal Systems, Inc.
 *   The reproduction, transmission or use of this document/file 
 *   or its contents is not permitted without  written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *------------------------------------------------------------------------------------------------
 *   Purpose:  GenerateTestReportGUI.java file
 *   Project:  Tonal(Test Automation of Field service forms using selenium scripts)
 *   Platform: Cloud Service Provider Independent
 *   Compiler: JavaSE-11
 *   IDE:	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *			   Version: 2021-09 (4.21.0)
 *             Build id: 20210910-1417
 *************************************************************************************************/
package resources;
import java.sql.Date;
import java.text.SimpleDateFormat;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/*********************************************************************************************************
 * Creating class for generating html report and defining report configuration for the generated html file
 *********************************************************************************************************/
public class GenerateTestReportGUI {
	
	static ExtentReports extent; 			//declaring extent report globally to handle in all subsequents method
	
	/***************************************************************************************
	* This function is called after the test case execution completes to generate the report
	* @return extent
	****************************************************************************************/
	public static ExtentReports getReportObject() {
		 
		//String fileName = System.currentTimeMillis() + ".html"; 							
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh-mm-ss");					//To print the report name using time and date format
		Date date = new Date(System.currentTimeMillis());
		String fileName = simpleDateFormat.format(date)+ ".html"; 
		System.out.println(fileName); 																	//To print the report name in console using current time milli seconds
		String path = System.getProperty("user.dir")+"\\reports\\Error"+ fileName; 	//Getting project path dynamically to create a test report file inside the project and storing it in "path" variable
	
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);   								//Creating object for extentsparkreporter class and giving path where report should be created
		
		//This method creates HTML file and do some configurations															  						
		reporter.config().setReportName("API Automation Results"); 										//Setting report name configuration for html file
		reporter.config().setDocumentTitle("Test Results"); 	   										//Setting document title for html report
		extent = new ExtentReports(); 							   										//creating extent reports object to drive all reporting execution
		extent.attachReporter(reporter);                           										//Attaching reports for the main class(ExtentReports)
		extent.setSystemInfo("Tester", "Sanghavi"); 			   												//defining properties for the report(Giving system information to the test report)
		return extent; 
	}
}

