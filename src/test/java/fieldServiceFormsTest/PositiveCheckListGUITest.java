/*************************************************************************************************
 *   COPYRIGHT (C) 2022 Tonal Systems, Inc.
 *   The reproduction, transmission or use of this document/file or its
 *   contents is not permitted without  written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *------------------------------------------------------------------------------------------------
 *   Purpose:  CheckListGUITest.java file
 *   Project:  Tonal(Test Automation of Field service forms using selenium scripts)
 *   Platform: Cloud Service Provider Independent
 *   Compiler: JavaSE-11
 *   IDE:	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *			   Version: 2021-09 (4.21.0)
 *             Build id: 20210910-1417
 *************************************************************************************************/
package fieldServiceFormsTest;
import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.CheckListLocatorsGUI;
import resources.BrowserConfigGUI;

/*****************************************************************************************************************
 * This class is used to test the Installation Checklist form by passing url in Chrome Browser(WebApp testing)
 * Calling all methods of BrowserConfig class to Checklisttest class using extends keyword(inheritance)
 *****************************************************************************************************************/
public class PositiveCheckListGUITest extends BrowserConfigGUI {
	
	public WebDriver driver; 															//Defining the local driver	
	
	private static Logger log =LogManager.getLogger(BrowserConfigGUI.class.getName()); 	//Used to print validation messages in log4j2 file
																						//LogManager.getLogger - API Function
																						//BrowserConfig.class.getName() - class name(get entire path of that particular class
	
	private CheckListLocatorsGUI locate;     											//Defining the locate object
	
	/**********************************************************************************************************************
	 *  This function is used to open the Installation Checklist form url in Chrome Browser and for maximizing the browser
	 **********************************************************************************************************************/
	@BeforeTest
	public void initialize() throws IOException, InterruptedException 
	{    
		driver=initializeDriver(); 						//This is used to open the chrome browser
		log.debug("Driver is initialized"); 			//Printing the success message in log4j2 file 
	   
		driver.get(prop.getProperty("url01")); 			//This method is used to load the Installation Checklist form Application url
	    log.debug("Navigated to Checklist page");  		//Printing the success message in log4j2 file
	    
	    driver.manage().window().maximize(); 			//This method is used to maximize the Chrome browser window
		log.debug("chrome window is maximized");    	//Printing the success message in log4j2 file
		
		locate = new CheckListLocatorsGUI(driver);  	//Creating the object of CheckListLocatorsGUI form page to access PageObject locators of the Installation Checklist form
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));		//Adding implicit wait for the application to fetch the elements 
	}
	
	/*******************************************************************************************************************************************************************************
	 * This method is used to test every field of the installation checklist page by selecting Yes radio button and execute by filling all the fields and clicking on submit button
	 *******************************************************************************************************************************************************************************/
	@Test(priority=1, invocationCount=1)
	public void gUITestInstallChkradioButtonOptionYes() throws InterruptedException, IOException 
	{
		JavascriptExecutor Email = (JavascriptExecutor)driver; 						
		Email.executeScript ("arguments[0].value='vikram@gmail.com';", locate.EmailAddress()); // this user is an FS_Admin for field service form project 
		
		JavascriptExecutor Password = (JavascriptExecutor)driver; 						
		Password.executeScript ("arguments[0].value='Welcome#001';", locate.Password());
		
		locate.LoginButton().click();
		Thread.sleep(3000);
		
		locate.ToggleButton().click(); // Locating an element and selecting the ToggleButton options
		
		Thread.sleep(2000);
		
		locate.ToggleForms().click(); // Locating an element and selecting the ToggleButton options
		
		Thread.sleep(2000);
		locate.ChecklistForm().click(); // Locating an element and selecting the ToggleButton options
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("SalesForce")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		//Generating and sending unique SalesForce id using current time method
		String text = String.valueOf(System.currentTimeMillis()); 					//method returns the current time in milliseconds
		JavascriptExecutor sales = (JavascriptExecutor)driver; 						
		sales.executeScript ("arguments[0].value="+text, locate.salesForce()); 		//Finding the element and setting the attribute for sending text value
		
		
		
		//Sending random character varying(max length 50) to the Installername text field
		JavascriptExecutor name = (JavascriptExecutor)driver; 						
		name.executeScript ("arguments[0].value='There were acoupl2176471865786156187781561 7635213';", locate.installerName());  
		
		locate.installationPerformedYes().click();   //This is used to click on "Can Installation Be Performed?" radio button 
		
		Select confirmLocation= new Select(locate.confirmLocation()); 				
		confirmLocation.selectByValue("Secondary"); 								//selecting the dropdown option of confirmLocation field using selectByvalue method
				
		Select wallTypeConfirmation= new Select(locate.wallTypeConfirmation()); 	
		wallTypeConfirmation.selectByValue("Concrete"); 							//selecting the dropdown option of confirmLocation field using selectByvalue method
		
		Select studSpaceConfirmation= new Select(locate.studSpaceConfirmation()); 	
		studSpaceConfirmation.selectByValue("17\"- 24\""); 								//selecting the dropdown option using selectByvalue method
		
		Select studTypeConfirmation= new Select(locate.studTypeConfirmation()); 	
		studTypeConfirmation.selectByValue("Not applicable"); 						//selecting the dropdown option using selectByvalue method
		
		//Sending random character varying(Max length 100) to the Scan SN text field
		JavascriptExecutor scan = (JavascriptExecutor)driver; 				
		scan.executeScript ("arguments[0].value='There were acoupl2176471865786156187781561 7635213';", locate.scanUnpacking());  
	  
		Select bottomBracket= new Select(locate.bottomBracket()); 			 
		bottomBracket.selectByValue("Yes"); 								//selecting the dropdown option using selectByvalue method
		
		Select bracketLevel= new Select(locate.bracketLevel());  			 
		bracketLevel.selectByValue("Yes");						 			//selecting the dropdown option using selectByvalue method
		
		Select securedStuds= new Select(locate.securedStuds()); 			 
		securedStuds.selectByValue("Yes"); 									//selecting the dropdown option using selectByvalue method
		
		Select dropdown= new Select(locate.tetherInstalled()); 		   
		dropdown.selectByVisibleText("No (State reason in the comments section)");
		
		Select securedWall= new Select(locate.securedWall());  				
		securedWall.selectByValue("Yes"); 									//selecting the dropdown option using selectByvalue method
		
		Select batteriesInstalled= new Select(locate.batteriesInstalled());	
		batteriesInstalled.selectByValue("Yes"); 						 	//selecting the dropdown option using selectByvalue method
		
		Select pluggedIn= new Select(locate.pluggedin()); 					
		pluggedIn.selectByValue("Yes"); 									//selecting the dropdown option using selectByvalue method
		
		Select armRotation= new Select(locate.armrotation()); 				
		armRotation.selectByValue("Yes"); 									//selecting the dropdown option using selectByvalue method
		
		Select connectedWiFi= new Select(locate.connectedWiFi()); 			
		connectedWiFi.selectByValue("Yes"); 								//selecting the dropdown option using selectByvalue method
		
		Select accessoriesUnboxed= new Select(locate.accessoriesUnboxed()); 
		accessoriesUnboxed.selectByValue("Yes"); 							//selecting the dropdown option using selectByvalue method
		   
		Select accessoriesUnboxed1= new Select(locate.batteriesInserted()); 	
		accessoriesUnboxed1.selectByValue("Yes"); 							//selecting the dropdown option using selectByvalue method
		
		Select pairedSuccessfully= new Select(locate.pairedSuccessfully()); 
		pairedSuccessfully.selectByValue("Yes"); 							//selecting the dropdown option using selectByvalue method
		
		Select benchAssembly= new Select(locate.benchAssembly()); 		
		benchAssembly.selectByValue("Yes");					 				//selecting the dropdown option using selectByvalue method
		
		//Sending random character varying(Max length 100) to the capturedSn text field
		JavascriptExecutor capture = (JavascriptExecutor)driver; 				
		capture.executeScript ("arguments[0].value='A while back I needed to count the amount of letters that a piece of text in151626262346897267238628';", locate.capturedSN()); //Finding the element and setting the attribute for sending text value
		
		Select packagingPlaced= new Select(locate.packagingPlaced()); 		
		packagingPlaced.selectByValue("Yes"); 								//selecting the dropdown option using selectByvalue method
		
		Select vacuum= new Select(locate.vacuum()); 					
		vacuum.selectByValue("Yes"); 										//selecting the dropdown option using selectByvalue method
		
		JavascriptExecutor scroll = (JavascriptExecutor)driver; 				//Scrolling using JavascriptExecutor
		scroll.executeScript("window.scrollBy(0,380)");							//Scroll Down to file upload button (+ve)
		
		locate.file().sendKeys("C:\\Users\\DELL\\Downloads\\signature (5).png" +"\n" +"C:\\Users\\DELL\\Downloads\\signature (6) (1).png" +"\n" +"C:\\Users\\DELL\\Downloads\\signature_1650526927.png"); //sending keys using local location of the files to file upload field
		
		//Sending random lengthy varchar to the comments text field
		JavascriptExecutor comments = (JavascriptExecutor)driver; 						 
		comments.executeScript ("arguments[0].value='Online Character Count Tool A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any character limits). Unfortunately, I could not think of a quick way to do so on my macbook and I therefore turned to the Internet There were a couple of tools out there, but none of them met my standards and since I am a web designer I thought: why not do it myself and help others along the way? And... here is the result, hope it helps out! What is this? Character Count Online is a free online character and word counting tool. All results are immediately shown and it is ridiculously easy to use and of course, the service is completely free.How is it used? You can copy and paste your text with the characters to count in the';", locate.comments()); //Finding the element and setting the attribute for sending text value
		WebElement submit=driver.findElement(By.id("submitButton"));				//Finding locator of save Button
	    submit.click(); 															//clicking on the save Button 
			   
	    WebElement back=driver.findElement(By.id("submitSuccessChecklist"));		//Finding locator of Back Button 
		back.click();  																//clicking on the "Back to Installation checklist" Button
	
	}
	/*******************************************************************************************************************************************************************************
	 * This method is used to test every field of the installation checklist page by selecting No radio button and execute by filling all the fields and clicking on submit button
	 *******************************************************************************************************************************************************************************/
	@Test(priority=2, invocationCount=1)
	public void gUITestInstlChkRadiButtonOptionNo() throws InterruptedException, IOException
	{
		//Generating and sending unique SalesForce id using current time method
		String text = String.valueOf(System.currentTimeMillis()); 					//method returns the current time in milliseconds
		JavascriptExecutor sales = (JavascriptExecutor)driver; 						
		sales.executeScript ("arguments[0].value="+text, locate.salesForce()); 		//Finding the element and setting the attribute for sending text value
			
		//Sending random character varying(max length 50) to the Installername text field
		JavascriptExecutor name = (JavascriptExecutor)driver; 						
		name.executeScript ("arguments[0].value='There were acoupl2176471865786156187781561 7635213';", locate.installerName());
		
		locate.installationPerformedNo().click();		//This is used to click on performed No radio button 
		
		Select  installationException = new Select(locate.installationException());   
		installationException.selectByValue("Other (Please fill out the comment below)"); 				//selecting the installationException dropdown option using selectByvalue method
		
		//Sending random lengthy varchar to the comments text field
		JavascriptExecutor comments = (JavascriptExecutor)driver; 						
		comments.executeScript ("document.getElementById('Comments').value='Online Character Count Tool A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any character limits). Unfortunately, I could not think of a quick way to do so on my macbook and I therefore turned to the Internet There were a couple of tools out there, but none of them met my standards and since I am a web designer I thought: why not do it myself and help others along the way? And... here is the result, hope it helps out! What is this? Character Count Online is a free online character and word counting tool. All results are immediately shown and it is ridiculously easy to use and of course, the service is completely free.How is it used? You can copy and paste your text with the characters to count in the'"); //Finding the element and setting the attribute for sending text value
		
		WebElement submit=driver.findElement(By.id("submitButton")); 				//Finding locator of save Button
	    submit.click();																//Clicking on save button
	    
	    WebElement back=driver.findElement(By.id("submitSuccessChecklist")); 		//Finding locator of Back Button 
		back.click();  																//clicking on the "Back to Installation checklist" Button
	}
	
}
