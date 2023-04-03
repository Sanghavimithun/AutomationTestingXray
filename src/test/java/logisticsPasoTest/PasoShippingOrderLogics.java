package logisticsPasoTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.rcarz.jiraclient.Attachment;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Comment;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.IssueLink;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import resources.BrowserConfigGUI;

public class PasoShippingOrderLogics extends BrowserConfigGUI {

	private JiraClient Jira;
	private String project;
	private String JiraUrl;
	private static Logger log = LogManager.getLogger(BrowserConfigGUI.class.getName()); // Used to print validation
																						// messages in log4j2 file
	// LogManager.getLogger - API Function
	// BrowserConfig.class.getName() - class name(get entire path of that particular
	// class

	public PasoShippingOrderLogics(String JiraUrl, String username, String password, String project) {

		this.JiraUrl = JiraUrl;
		// create basic authentication object
		BasicCredentials creds = new BasicCredentials(username, password);
		// initialize the Jira client with the url and the credentials
		Jira = new JiraClient(JiraUrl, creds);
		this.project = project;
	}

	public Issue createJiraIssue(String issueType, String summary, String description, String reporterName,
			String attachment) {
		Issue newIssue = null;
		try {

			// Create issue if not exists
			FluentCreate fleuntCreate = Jira.createIssue(project, issueType);
			fleuntCreate.field(Field.SUMMARY, summary);
			fleuntCreate.field(Field.DESCRIPTION, description);
			// fleuntCreate.field(Field.ASSIGNEE, Assignee);
			newIssue = fleuntCreate.execute();
			newIssue.addAttachment(new File(attachment));
			log.info("********************************************");
			log.info("Paso New issue created in Jira with ID: " + newIssue);
			log.info("Paso New issue URL is :" + JiraUrl + "/browse/" + newIssue);
			log.info("Paso issuetype: " + newIssue.getIssueType());
			log.info("Paso Status:" + newIssue.getStatus());
			log.info("*******************************************");

		} catch (JiraException e) {
			e.printStackTrace();
		}
		return newIssue;
	}

	public Issue getJiraIssue(String issueId) throws JiraException {
		Issue newIssue = Jira.getIssue(issueId);

		return newIssue;
	}

	public List<String> getAttachments(Issue newIssue) throws JiraException, InterruptedException, IOException {
		List<String> fileNames = new ArrayList<String>();
		String filename;
		Issue issue = Jira.getIssue(newIssue.getKey());
		log.info("Paso Attachments issuedetails:" + issue.toString());
		List<Attachment> attachments = issue.getAttachments();
		for (Attachment attach : attachments) {
			filename = attach.getFileName();
			log.info("Paso Attachments Filename :" + filename);
			fileNames.add(filename);
		}
		return fileNames;
	}

	public void getStatus(Issue newIssue) throws JiraException, InterruptedException {
		Issue issue = Jira.getIssue(newIssue.getKey());
		log.info("Paso issueid:" + issue.toString());
		log.info("Paso Status:" + issue.getStatus());
	}

	public Issue getComments(Issue newIssue) throws Exception {

		Issue issue = Jira.getIssue(newIssue.getKey());
		// System.out.println("issuedetails:" + issue.toString());
		try {
			List<Comment> comments = issue.getComments();
			// if (issue.getStatus()==Investigate)
			for (Comment statement : comments) {
				log.info("Paso Comments is" + "" + statement.getBody());
			}
		} catch (Exception e) {
			throw new JiraException("Error " + e.getMessage());
		}
		return issue;
	}

	public Issue getIssueLinks(Issue newIssue) throws Exception {
		Issue outwardIssue;
		Issue issue = Jira.getIssue(newIssue.getKey());
		log.info("Xpo issuedetails:" + issue.toString());
		try {
			List<IssueLink> issueLinks = issue.getIssueLinks();
			if (issueLinks.isEmpty())
				// System.out.println("Comments" +comments);
				log.info("Paso Issue links are empty");
			for (IssueLink issueLink : issueLinks) {
				outwardIssue = issueLink.getOutwardIssue(); // Outward -- from source to destination
				log.info(outwardIssue.getKey());
				log.info(outwardIssue.getSummary());
			}
		} catch (Exception e) {
			throw new JiraException("Error " + e.getMessage());
		}
		return issue;
	}

	public void downloadFile(String filename) {

		String downloadPath = "D:\\Logistics automation\\Logistics\\Output CSV files of Logistics";
		PasoSftpServerTest sftp = new PasoSftpServerTest();
		sftp.downloadFile("/Test/Inbound/" + filename, downloadPath);

	}
}
