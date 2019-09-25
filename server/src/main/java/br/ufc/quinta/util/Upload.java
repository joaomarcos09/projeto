package br.ufc.quinta.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import br.ufc.quinta.model.User;

public class Upload {

	public static void uploadFileServer(User user) {
		try {
			FTPClient ftp = new FTPClient();
			ftp.connect("files.000webhost.com");
			ftp.login("andersonalmada", "almada91");			
			ftp.setFileType(FTP.BINARY_FILE_TYPE);			
			ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			//ftp.storeFile("/public_html/uploads/"+user);
			ftp.logout();
		} catch (Exception e) {
		}
	}

	public static void uploadFile(InputStream uploadedInputStream, int id) {
		try {
			InputStream inputStream = Upload.class.getClassLoader().getResourceAsStream("uploads.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			String folder = prop.getProperty("folder");
			String filePath = folder + id;
			saveFile(uploadedInputStream, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
