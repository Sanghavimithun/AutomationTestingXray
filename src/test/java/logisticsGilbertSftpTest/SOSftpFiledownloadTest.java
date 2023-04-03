package logisticsGilbertSftpTest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import resources.BrowserConfigGUI;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SOSftpFiledownloadTest {

	private static Logger log = LogManager.getLogger(BrowserConfigGUI.class.getName()); // Used to print validation
	// messages in log4j2 file 
	
	public void downloadSftpFile() {
		String hostname = "sftp1.tonal-ops.com";
		String username = "sanghavi";
		String password = "Access_Read8564$";
		String copyFrom = "/inbound/";
		String copyTo = "D:\\Logistics automation\\Logistics\\Output CSV files of Logistics";

		JSch jsch = new JSch();
		Session session = null;
		log.info("Trying to connect.....");
		try {
			session = jsch.getSession(username, hostname, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();

			ChannelSftp sftpChannel = (ChannelSftp) channel;

			Vector<LsEntry> vector = (Vector<LsEntry>) sftpChannel.ls(copyFrom);
			ChannelSftp.LsEntry list = vector.get(0);
			String oldestFile = list.getFilename();
			SftpATTRS attrs = list.getAttrs();
			int currentOldestTime = attrs.getMTime();
			String nextName = null;
			LsEntry lsEntry = null;
			int nextTime;
			for (Object sftpFile : vector) {
				lsEntry = (ChannelSftp.LsEntry) sftpFile;
				nextName = lsEntry.getFilename();
				attrs = lsEntry.getAttrs();
				nextTime = attrs.getMTime();
				if (nextTime > currentOldestTime) {
					oldestFile = nextName;
					currentOldestTime = nextTime;

				}
			}

			log.info("File name is ...." + oldestFile);

			//sftpChannel.get(copyFrom + oldestFile, copyTo);

			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}

		log.info("Done !!");
	}

}