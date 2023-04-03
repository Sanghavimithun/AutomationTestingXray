package logisticsPasoTest;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class PasoSftpServerTest {

	private static final String REMOTE_HOST = "sftp1.tonal-ops.com";
	private static final String USERNAME = "sanghavi";
	private static final String PASSWORD = "Access_Read8564$";
	private static final int REMOTE_PORT = 22;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 10000;

	
	public void downloadFile(String remoteFile, String downloadPath) {
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
			// download file from remote server to local
			channelSftp.get(remoteFile, downloadPath);
			channelSftp.exit();

		} catch (JSchException | SftpException e) {
			e.printStackTrace();

		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}
	}

}