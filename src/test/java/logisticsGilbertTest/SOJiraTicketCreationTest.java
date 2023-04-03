/*************************************************************************************************
 *   COPYRIGHT (C) 2022 Tonal Systems, Inc.
 *   The reproduction, transmission or use of this document/file or its
 *   contents is not permitted without  written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *------------------------------------------------------------------------------------------------
 *   Purpose:  JiraTicketCreationTest.java file
 *   Project:  Tonal(Test Automation of Logistics application using jiraclient methods)
 *   Platform: Cloud Service Provider Independent
 *   Compiler: JavaSE-11
 *   IDE:	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *			   Version: 2021-09 (4.21.0)
 *             Build id: 20210910-1417
 *************************************************************************************************/
package logisticsGilbertTest;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraException;
import resources.BrowserConfigGUI;

public class SOJiraTicketCreationTest extends BrowserConfigGUI {
	
	private static Logger log = LogManager.getLogger(BrowserConfigGUI.class.getName()); // Used to print validation
	// messages in log4j2 file

	SODatabaseTest dbTest = new SODatabaseTest();

	@DataProvider(name = "data-provider")
	public Object[][] filesList() {
		File folder = new File(System.getProperty("user.dir") + "\\GilbertFiles\\Gilbert2.0\\gilbert11-7-2022");
		File[] listOfFiles = folder.listFiles();

		Object[][] data = new Object[listOfFiles.length][listOfFiles.length];
		int i = 0;
		for (File file : listOfFiles) {
			Object[] aTest = new Object[] { file };
			data[i] = aTest;
			i++;
		}
		log.info("DATA SIZE" + Arrays.deepToString(data));
		return data;

	}

	@Test(dataProvider = "data-provider")
	public void processFile(File file) throws IOException, JiraException, Exception {
		processOrders(file);
	}

	public void processOrders(File file) throws IOException, JiraException, Exception {

		log.info(file.getName());
		ShippingOrderLogics jiraServiceProvider = new ShippingOrderLogics("https://tonalsys.atlassian.net",
				"sanghavi.mithun@tonal.com", "fzFiLGhER00CyWRlRJekFD15", "LTEST");
		
		String issueDescription = "Automation Testing";
		String orderId = String.valueOf(System.currentTimeMillis());
		// JiraServiceProvider.getIssueLinks("LTEST-1465");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // To print the report
																							// name using time
																							// and
																							// date format
		Date date = new Date(System.currentTimeMillis());
		String currentDate = simpleDateFormat.format(date);
		String issueSummary = "QA:DEBUG RMA TEST SO:GIL" + " " + currentDate;
		String issueATTACHMENT = System.getProperty("user.dir") + "\\GilbertFiles\\Gilbert2.0\\gilbert11-7-2022\\"
				+ file.getName();
		Issue createdIssue = jiraServiceProvider.createJiraIssue("E-Mail Request", issueSummary, issueDescription,
				"Sanghavi Mithun (Contractor)", issueATTACHMENT);

		Thread.sleep(60000);
		// To get the created issue details and download the files from the SFTP server
		jiraDetailsWithFiles(createdIssue.getKey(), jiraServiceProvider);
		// jiraDetailsWithFiles("LTEST-2441", jiraServiceProvider);

		// verify the processed in the database
		//Thread.sleep(3000);
		//dbTest.verifyData(currentDate);

	}
	
	// To get the created issue details and download the files from the SFTP server
	public void jiraDetailsWithFiles(String issueKey, ShippingOrderLogics jiraServiceProvider)
			throws InterruptedException, IOException, JiraException, Exception {

	
		Issue createdIssue = jiraServiceProvider.getJiraIssue(issueKey);
		List<String> attachments = jiraServiceProvider.getAttachments(createdIssue);
		List<String> csvFiles = new ArrayList<String>();
		// get only csv files
		for (String attachment : attachments) {
			if (attachment.endsWith(".CSV"))
				csvFiles.add(attachment);
		}

		jiraServiceProvider.getIssueLinks(createdIssue);
		jiraServiceProvider.getComments(createdIssue);
		jiraServiceProvider.getStatus(createdIssue);

		// search the csv files in the SFTP server
				//Note: As the uploaded csv were accessed by XPO server(every single minute), So couldnot able to download the XPO csv file from SFTP server
				try {
					if (!csvFiles.isEmpty()) {

						for (String csvfile : csvFiles) {
							log.info("SOSftpcsvfilename:" + "\n" + csvfile);
							jiraServiceProvider.downloadFile(csvfile);
						}
					} else {
						log.info("could not find any csv file");
					}
				} catch (Exception e) {
					log.info("couldnot download the CSV file" + e.getMessage());
				}

			}

}
