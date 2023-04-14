/*************************************************************************************************
 *   COPYRIGHT (C) 2022 Tonal Systems, Inc.
 *   The reproduction, transmission or use of this document/file 
 *   or its contents is not permitted without  written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *------------------------------------------------------------------------------------------------
 *   Purpose:  CheckListLocatorsGUI.java file
 *   Project:  Tonal(Test Automation of Field service forms using selenium scripts)
 *   Platform: Cloud Service Provider Independent
 *   Compiler: JavaSE-11
 *   IDE:	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *			   Version: 2021-09 (4.21.0)
 *             Build id: 20210910-1417
 *************************************************************************************************/
package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 

/**********************************************************************************************************************
 * This class is called when user wants to identify the elements of all the fields in Installation Checklist Form page
 **********************************************************************************************************************/

	public class CheckListLocatorsGUI {
		public WebDriver driver; 									//Defining the local driver
		public CheckListLocatorsGUI(WebDriver driver2) {			//Creating constructor with WebDriver arguments to pass the driver object information
			this.driver=driver2;									//Assigning local driver to test case driver with "This" keyword to represent it has local	
		}  

		
		

		By First= By.id("email");				//Passing ID to identify "Email" field in Installation Checklist form using "By" class
		/*********************************************************
		 * Creating SalesForce field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
		public WebElement EmailAddress()
		{
			return driver.findElement(First); //Finding element of the Email field and returning it
		}
		
		By Second= By.id("loginPassword");				//Passing ID to identify "Password" field in Installation Checklist form using "By" class
		/*********************************************************
		 * Creating SalesForce field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
		public WebElement Password()
		{
			return driver.findElement(Second); //Finding element of the Password field and returning it
		}
		
		By Third= By.id("saveLogin");				//Passing ID to identify "Password" field in Installation Checklist form using "By" class
		/*********************************************************
		 * Creating SalesForce field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
		public WebElement LoginButton()
		{
			return driver.findElement(Third); //Finding element of the Password field and returning it
		}
		
		By togglebutton = By.id("openBtn");//Passing ID to identify "Tonal's ToggleButton ID" field in OrderableParts form using "By" class
		/*************************************************************
		 * Creating OrderableParts field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *************************************************************/
		public WebElement ToggleButton()
		{
			return driver.findElement(togglebutton);//Finding element of the ToggleButton field and returning it
		}
		
		
		By formtogglebutton = By.xpath("//li /a[@href='#works']");//Passing ID to identify "Tonal's ToggleButton ID" field in OrderableParts form using "By" class
		/*************************************************************
		 * Creating OrderableParts field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *************************************************************/
		public WebElement ToggleForms()
		{
			return driver.findElement(formtogglebutton);//Finding element of the ToggleButton field and returning it
		}
		
		By Checklist = By.xpath("//li /a[@href='/checklist/showchecklistpage']");//Passing ID to identify "Tonal's OrderableParts itemNumber" field in OrderableParts form using "By" class
		/*************************************************************
		 * Creating OrderableParts field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *************************************************************/
		public WebElement ChecklistForm()
		{
			return driver.findElement(Checklist); //Finding element of the CreateOrderableParts field and returning it
		}
		
		By one = By.id("SalesForce"); 		//Passing ID to identify "Tonal's SalesForce Case Number" field in Installation Checklist form using "By" class
		/*********************************************************
		 * Creating SalesForce field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
		public WebElement salesForce()
		{
			return driver.findElement(one); //Finding element of the SalesForce field and returning it
		}
				

		By two = By.id("InstallerName"); 	//Passing ID to identify "Installer Name" field in Installation Checklist form using "By" class
		/************************************************************
		 * Creating InstallerName field method to pass LOCATOR OBJECT
		 * @return the WebElement
		*************************************************************/
		public WebElement installerName()
		{
			return driver.findElement(two); //Finding element of the InstallerName field and returning it
		}
		
		By yes = By.id("InstallationPerformedYes");	//Passing ID to identify "Can Installation Be Performed?" field in Installation Checklist form using "By" class
		/*****************************************************************
		 * Creating installationPerformedYes method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *****************************************************************/
		public WebElement installationPerformedYes()
		{
			return driver.findElement(yes); 		//Finding element of the Can InstallationPerformedYes field and returning it
		}
		   
		By confirmLocation = By.id("ConfirmLocation");  //Passing ID to identify "Confirm location*" field in Installation Checklist form using "By" class
		/***************************************************************
		 * Creating confirmLocation field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ***************************************************************/
		public WebElement confirmLocation()
		{
			return driver.findElement(confirmLocation);  //Finding element of the ConfirmLocation field and returning it
		}  
		  
		By wallTypeConfirmation = By.id("WallTypeConfirmation");  	//Passing ID to identify " WallTypeConfirmation" field in Installation Checklist form using "By" class
		/********************************************************************
		 * Creating  WallTypeConfirmation field method to pass LOCATOR OBJECT
		 * @return the WebElement
	     ********************************************************************/
		public WebElement wallTypeConfirmation()
		{
			return driver.findElement(wallTypeConfirmation); 		//Finding element of the WallTypeConfirmation field and returning it
		}
		   
		By studSpaceConfirmation = By.id("StudSpaceConfirmation");  //Passing ID to identify "StudSpaceConfirmation" field in Installation Checklist form using "By" class
		/*********************************************************************
		 * Creating StudSpaceConfirmation field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 **********************************************************************/
		public WebElement studSpaceConfirmation()
		{
			return driver.findElement(studSpaceConfirmation); 		//Finding element of the StudSpaceConfirmation field and returning it
		} 
		
		By studTypeConfirmation = By.id("StudTypeConfirmation");  	//Passing ID to identify "StudTypeConfirmation" field in Installation Checklist form using "By" class
		/********************************************************************
		 * Creating StudTypeConfirmation field method to pass LOCATOR OBJECT
		 * @return the WebElement
	    ********************************************************************/
		public WebElement studTypeConfirmation()
		{
			 return driver.findElement(studTypeConfirmation);	 	//Finding element of the StudTypeConfirmation field and returning it
		} 
		 
		By scanUnpacking = By.id("ScanUnpacking");   				//Passing ID to identify "ScanUnpacking" field in Installation Checklist form using "By" class				
		/*************************************************************
		 * Creating ScanUnpacking field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *************************************************************/
	    public WebElement scanUnpacking()
		{
			return driver.findElement(scanUnpacking); 		 		//Finding element of the ScanUnpacking field and returning it
		}
		   
	    By bottomBracket = By.id("Bottombracket");  				//Passing ID to identify "Bottombracket" field in Installation Checklist form using "By" class
	    /*************************************************************
	     * Creating Bottombracket field method to pass LOCATOR OBJECT
	     * @return the WebElement
	     ************************************************************/
		public WebElement bottomBracket()
		{
			return driver.findElement(bottomBracket); 				//Finding element of the Bottombracket field and returning it
		}

		By bracketLevel = By.id("Bracketlevel");  					//Passing ID to identify "Bracketlevel" field in Installation Checklist form using "By" class
		/************************************************************
		 * Creating Bracketlevel field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ***********************************************************/
		public WebElement bracketLevel()
		{
		   return driver.findElement(bracketLevel); 				//Finding element of the Bracketlevel field and returning it
		}


		By securedStuds = By.id("Securedstuds"); 					//Passing ID to identify "securedStuds" field in Installation Checklist form using "By" class
		/************************************************************
		 * Creating Securedstuds field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ***********************************************************/
		public WebElement securedStuds()
		{
		   return driver.findElement(securedStuds); 				//Finding element of the Securedstuds field and returning it
		}

		By securedWall = By.id("Securedwall");  					//Passing ID to identify "securedwall" field in Installation Checklist form using "By" class
		/***********************************************************
		 * Creating Securedwall field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 **********************************************************/
		public WebElement securedWall()
		{
		   return driver.findElement(securedWall); 					//Finding element of the Securedwall field and returning it
		}

		By batteriesInstalled = By.id("Batteriesinstalled");  		//Passing ID to identify "Batteriesinstalled" field in Installation Checklist form using "By" class
		/******************************************************************
		 * Creating Batteriesinstalled field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *****************************************************************/
		public WebElement batteriesInstalled()
		{
		   return driver.findElement(batteriesInstalled); 			//Finding element of the Batteriesinstalled field and returning it
		}

	    By pluggedIn = By.id("Pluggedin");   						//Passing ID to identify "Pluggedin" field in Installation Checklist form using "By" class
		/*********************************************************
		 * Creating Pluggedin field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ********************************************************/
		 public WebElement pluggedin()
		 {
		   return driver.findElement(pluggedIn); 					//Finding element of the Pluggedin field and returning it
		 }

	     By armrotation = By.id("Armrotation");  					//Passing ID to identify "Armrotation" field in Installation Checklist form using "By" class
	     /***********************************************************
		 * Creating Armrotation field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 **********************************************************/
	     public WebElement armrotation()
	     {
		   return driver.findElement(armrotation); 					//Finding element of the Armrotation field and returning it
	     }

	     By connectedWiFi = By.id("ConnectedWiFi");   				//Passing ID to identify "connectedWiFi" field in Installation Checklist form using "By" class
	     /*************************************************************
		 * Creating ConnectedWiFi field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ************************************************************/
	     public WebElement connectedWiFi()
	     {
		   return driver.findElement(connectedWiFi); 				//Finding element of the ConnectedWiFi field and returning it
	     }

	     By accessoriesUnboxed = By.id("Accessoriesunboxed");  		//Passing ID to identify "connectedWiFi" field in Installation Checklist form using "By" class
	     /******************************************************************
		 * Creating Accessoriesunboxed field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *******************************************************************/
	     public WebElement accessoriesUnboxed()
	     {
		   return driver.findElement(accessoriesUnboxed); 			//Finding element of the Accessoriesunboxed field and returning it
	     }

	     By batteriesInserted = By.id("Batteriesinserted");  		//Passing ID to identify "Batteriesinserted" field in Installation Checklist form using "By" class
	     /*****************************************************************
		 * Creating Batteriesinserted field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ******************************************************************/
	     public WebElement batteriesInserted()
	     {
		   return driver.findElement(batteriesInserted); 			//Finding element of the Batteriesinserted field and returning it
	     }

	     By pairedSuccessfully = By.id("Pairedsuccessfully");  		//Passing ID to identify "pairedSuccessfully" field in Installation Checklist form using "By" class
	     /******************************************************************
		 * Creating Pairedsuccessfully field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *******************************************************************/
	     public WebElement pairedSuccessfully()
	     {
		   return driver.findElement(pairedSuccessfully); 			//Finding element of the Pairedsuccessfully field and returning it
	     }

	     By benchAssembly = By.id("Benchassembly");   				//Passing ID for Benchassembly field in checklist
	     /*************************************************************
		 * Creating Benchassembly field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ************************************************************/
	     public WebElement benchAssembly()
	     {
		   return driver.findElement(benchAssembly); 		  		//Finding element of the Benchassembly field and returning it
	     }

	     By capturedSN = By.id("CapturedSN");      					//Passing ID for CapturedSN field in checklist
	     /**********************************************************
		 * Creating CapturedSN field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
	     public WebElement capturedSN()
	     {
		   return driver.findElement(capturedSN); 					//Finding element of the CapturedSN field and returning it
	     }

	     By packagingPlaced = By.id("Packagingplaced");   			//Passing ID Packagingplaced for field in checklist
	     /***************************************************************
		 * Creating Packagingplaced field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 ****************************************************************/
	     public WebElement packagingPlaced()
	     {
		   return driver.findElement(packagingPlaced); 				//Finding element of the Packagingplaced field and returning it
	     }

	     By vacuum= By.id("Vacuum");  								//Passing ID Vacuum for field in checklist
	     /******************************************************
		 * Creating Vacuum field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *****************************************************/
	     public WebElement vacuum()
	     {
		   return driver.findElement(vacuum); 						//Finding element of the Vacuum field and returning it
	     }
	     
	     By file = By.xpath("//input[@id='file']");  								//Passing ID Vacuum for field in checklist
	     /******************************************************
		 * Creating chosse file method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *****************************************************/
	     public WebElement file()
	     {
		   return driver.findElement(file); 						//Finding element of the Vacuum field and returning it
	     }

	     By comments = By.id("Comments");   						//Passing ID Comments for field in checklist
	     /********************************************************
		 * Creating Comments field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
	     public WebElement comments()
	     {
		   return driver.findElement(comments); 						 //Finding element of the Comments field and returning it
	     }

	     By installationPerformedNo = By.id("InstallationPerformed");  //Passing ID InstallationPerformedNo for field in checklist
	     /********************************************************
		 * Creating Comments field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *******************************************************/
	     public WebElement installationPerformedNo()
	     {
		   return driver.findElement(installationPerformedNo); 			//Finding element of the InstallationPerformedNo field and returning it
	     }
	   
	   
	     By installationException = By.id("InstallationException");    //Passing ID InstallationException for field in checklist          
	     /********************************************************
		 * Creating Comments field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
	     public WebElement installationException()
	     {
		   return driver.findElement(installationException); 			//Finding element of the InstallationExceptions field and returning it
	     }
	     
	     By tether = By.id("Tetherinstalled");    //Passing ID InstallationException for field in checklist          
	     /********************************************************
		 * Creating Comments field method to pass LOCATOR OBJECT
		 * @return the WebElement
		 *********************************************************/
	     public WebElement tetherInstalled()
	     {
		   return driver.findElement(tether); 			//Finding element of the InstallationExceptions field and returning it
	     }
	   
}



