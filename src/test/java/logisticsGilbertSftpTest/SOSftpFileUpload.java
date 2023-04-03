package logisticsGilbertSftpTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import resources.BrowserConfigGUI;

public class SOSftpFileUpload {

	private static final String REMOTE_HOST = "sftp1.tonal-ops.com";
	private static final String USERNAME = "sanghavi";
	private static final String PASSWORD = "Access_Read8564$";
	private static final int REMOTE_PORT = 22;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 10000;
	private static Logger log = LogManager.getLogger(BrowserConfigGUI.class.getName()); // Used to print validation
	// messages in log4j2 file 
	
	public void uploadFile(String localFile, String remoteFile) {
		
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

			// transfer file from local to remote server
			channelSftp.put(localFile, remoteFile);

			channelSftp.exit();

		} catch (JSchException | SftpException e) {

			e.printStackTrace();

		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}

		log.info("Done");

	}

}
