package logisticsGilbertSftpTest;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import logisticsPasoSftpTest.PasoSftpDatabaseTest;
import logisticsPasoSftpTest.PasoSftpFileUploadTest;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.SearchResult;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import resources.BrowserConfigGUI;

public class SOShippingConfirmationLogicsTest extends BrowserConfigGUI {

	private JiraClient Jira;
	private String project = "LTEST";
	private String JiraUrl = "";
	String remoteFile = "/Test/Outbound";
	private static Logger log = LogManager.getLogger(BrowserConfigGUI.class.getName()); // Used to print validation
	// messages in log4j2 file

	@DataProvider(name = "data-provider")
	public Object[][] filesList() {

		File folder = new File(System.getProperty("user.dir") + "\\ShippingConfirmationFiles\\Gilbert 2.0");
		File[] listOfFiles = folder.listFiles();
		Object[][] data = new Object[listOfFiles.length][listOfFiles.length];
		int i = 0;
		for (File file : listOfFiles) {
			Object[] aTest = new Object[] { file };
			data[i] = aTest;
			i++;
		}
		// log.info("DATA SIZE" + Arrays.deepToString(data));
		return data;

	}

	@Test(dataProvider = "data-provider", priority = 1)
	public void processFile(File file) throws IOException, JiraException, Exception {

		String fileName = file.getName();
		String localFile = System.getProperty("user.dir") + "\\ShippingConfirmationFiles\\Gilbert 2.0\\" + fileName;
		log.info("localFile" + " " + localFile);

		PasoSftpFileUploadTest sc = new PasoSftpFileUploadTest();
		sc.uploadFile(localFile, remoteFile);

		Thread.sleep(60000);
		String jql;
		jql = "project = 'LTEST' AND text ~ " + fileName + " ORDER BY created DESC";
		searchJiraIssues("https://tonalsys.atlassian.net", "sanghavi.mithun@tonal.com", "ATATT3xFfGF0nshooRbTtHsNkMqPgLZ1ISz5gfhW8SVtfNE7uErAY5pzftbVLyg6xTcgbPBNQUuzrWVMLWRE_Tz7LnBzFiXAhB3tRzFsjhjqoRdcdWmTtYWwTqxQxT7TocyI4MELucA-IqEyTqXrivNu343S6BmapRh-QrH7mvzuL9O7IuyVhgY=EA14EA3D",
				"LTEST", jql);

	}

	public void searchJiraIssues(String JiraUrl, String username, String password, String project, String jql)
			throws JiraException {

		this.JiraUrl = JiraUrl;
		BasicCredentials creds = new BasicCredentials(username, password);

		Jira = new JiraClient(JiraUrl, creds);
		Issue issue = null;

		List<Issue> issueList = new ArrayList<Issue>();

		log.info("JQL" + jql);
		SearchResult searchResult = Jira.searchIssues(jql);

		issue = searchResult.issues.get(0);
		log.info("Jira Issue Id: " + " " + issue.getKey());
		log.info("Summary: " + " " + issue.getSummary());

	}

	@Test(priority = 2)
	public void ods() throws JiraException, InterruptedException {

		// Thread.sleep(20000);
		String jql1 = "project = 'LTEST' AND text ~ 'RYD ODS' ORDER BY created DESC";
		searchJiraIssues("https://tonalsys.atlassian.net", "sanghavi.mithun@tonal.com", "ATATT3xFfGF0nshooRbTtHsNkMqPgLZ1ISz5gfhW8SVtfNE7uErAY5pzftbVLyg6xTcgbPBNQUuzrWVMLWRE_Tz7LnBzFiXAhB3tRzFsjhjqoRdcdWmTtYWwTqxQxT7TocyI4MELucA-IqEyTqXrivNu343S6BmapRh-QrH7mvzuL9O7IuyVhgY=EA14EA3D",
				"LTEST", jql1);

	}

	@Test(priority = 3)
	public void sftp() throws JiraException, InterruptedException {

		// Thread.sleep(10000);
		SOSftpFiledownload sftp = new SOSftpFiledownload();
		sftp.downloadSftpFile();
	}

	@Test(priority = 4)
	public void db() throws JiraException, InterruptedException, SQLException {

		//Thread.sleep(10000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = simpleDateFormat.format(date);
		log.info(currentDate);
		PasoSftpDatabaseTest db = new PasoSftpDatabaseTest();
		db.verifyData(currentDate);
	}

}
