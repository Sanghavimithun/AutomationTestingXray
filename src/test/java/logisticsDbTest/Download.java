package logisticsDbTest;

import java.util.Vector;

import org.testng.annotations.Test;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Download {

	private static final String REMOTE_HOST = "sftp1.tonal-ops.com";
	private static final String USERNAME = "sanghavi";
	private static final String PASSWORD = "Access_Read8564$";
	private static final int REMOTE_PORT = 22;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 10000;

	String downloadPath = "D:\\Logistics automation\\Logistics\\Output CSV files of Logistics";

	@Test
	public void downloadFile() {

		Session jschSession = null;
		try {

			JSch jsch = new JSch();
			jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
			jschSession.setConfig("StrictHostKeyChecking", "no");
			// authenticate using password
			jschSession.setPassword(PASSWORD);
			// 10 seconds session timeout
			jschSession.connect(SESSION_TIMEOUT);
			Channel sftp = jschSession.openChannel("sftp");
			// 5 seconds timeout
			sftp.connect(CHANNEL_TIMEOUT);

			ChannelSftp channelSftp = (ChannelSftp) sftp;
			Vector<ChannelSftp.LsEntry> entries = channelSftp.ls("/inbound/");
			for (ChannelSftp.LsEntry en : entries) {
				if (en.getFilename().equals(".") || en.getFilename().equals("..") || en.getAttrs().isDir()) {
					continue;
				}
				String fileName = en.getFilename().replace(" ", "\\ ");
				System.out.println("print" + " " + en.getFilename());
				// channelSftp.get("/inbound/" + fileName, downloadPath);
			}
			channelSftp.exit();

		} catch (JSchException | SftpException e) {
			System.out.println("exception:" + e.getMessage());
			e.printStackTrace();

		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}
	}

}





