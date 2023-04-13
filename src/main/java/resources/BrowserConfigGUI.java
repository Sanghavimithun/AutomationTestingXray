/*************************************************************************************************
 *   COPYRIGHT (C) 2022 Tonal Systems, Inc.
 *   The reproduction, transmission or use of this document/file 
 *   or its contents is not permitted without  written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *------------------------------------------------------------------------------------------------
 *   Purpose:  BrowserConfigGUI.java file
 *   Project:  Logistics
 *   Platform: Cloud Service Provider Independent
 *   Compiler: JavaSE-11
 *   IDE:	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *			   Version: 2021-09 (4.21.0)
 *             Build id: 20210910-1417
 *************************************************************************************************/
package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**************************************************************************************
 * This class is called when user wants to run the test case in different browsers
 **************************************************************************************/
public class BrowserConfigGUI {

    public  WebDriver driver; 			//defining webdriver(Creating driver object globally)
    public Properties prop; 			//defining properties file
    
    //public ChromeOptions options; 	//Used to run the selenium script in headless mode
    
   /***********************************************************************
     * This function is called for browser invocation for all the test case
     * @return driver
     ***********************************************************************/
    public WebDriver initializeDriver() throws IOException{
    
    prop= new Properties(); 			//Creating object of the class to read .properties file
    
    //options = new ChromeOptions();  	//- for headlessmode
    
    FileInputStream inputStream = new FileInputStream("C:\\Users\\DELL\\git\\repository\\Logistics\\src\\main\\java\\resources\\GuiUrl.properties"); 	//To tell properties class where exactly where file is present 
    																																//creating object of file by class
    													   																			//FileInputStream - To read data
    
    prop.load(inputStream);  									//Load is to load that file in reading mode   

    String browserName = prop.getProperty("browser"); 			//To get the property of the browser
   
    System.out.println(browserName); 							//To print it in output
   
    //If loop is used to execute the testcases in particular browser depends upon the browser mentioned inside the test case
    if(browserName.equals("chrome")){
    	
	   System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\chromedriver.exe"); 	//System.setProperty is used to tell where the browser.exe file is placed in system and telling it to open the browser
	   
	   //options.setHeadless(true);
	   //driver= new ChromeDriver(options);
	   
	   driver= new ChromeDriver(); 																 	//Launching the chrome browser and storing the values of the brower in reference variable called as "driver"
    }
    else if (browserName.equals("firefox")){
    	
    	System.setProperty("webdriver.firefox.driver","C:\\Program Files (x86)\\firefoxdriver.exe"); 
	   	driver= new FirefoxDriver();
	}
    else if (browserName.equals("IE")){
    	
    	System.setProperty("webdriver.IE.driver","C:\\Program Files (x86)\\IE.exe"); 
	   	driver= new InternetExplorerDriver();
   	}
    return driver;
    
}
    

/****************************************************************************************
 * This function is called when user wants to takes screenshot of the failed testcase.
 * @param String testCaseName(Used to get the name of the testcases written)
 * @param WebDriver driver(Used to call the Chrome Driver).
 * @return destinationfile
*****************************************************************************************/
public String gUIgetScreenShotPath(String testCaseName,WebDriver driver) throws IOException{
    
    TakesScreenshot ts=(TakesScreenshot) driver; 	   											//TypeCasting from webdriver to Takescreenshot
    File source = ts.getScreenshotAs(OutputType.FILE);										    //Taking screenshot and storing the path in Srcfile, OutputType is an interface and File is a constant
    
    String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";  //Passing the destination file path of screenshot
    
    FileUtils.copyFile(source,new File(destinationFile));                                       //Creating a new file in order to copy and paste the screenshot which are taken, in order to do this copy paste work we have a method copyfile
    													                                        //Copyfile - It is a static method of FileUtils class
    return destinationFile;

}

/**************************************************************
  * This function is used to decode the password for login page
  * @param String password
  * @return decodedstring value
  **************************************************************/
protected static String gUIdecodePwd(String password){
	
	byte[] decodedString=Base64.decodeBase64(password); 		//This method is used to decode the original password
	return new String(decodedString); 				   			//returning the decoded password as a string
	
	}
}


