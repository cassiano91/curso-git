package br.com.unimeduberaba.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

public class Arquivo {

	public static String executarComando(String command) {
		System.out.println("\n\n\n" + command + "\n\n\n");

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				System.err.println(line);
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString().trim();
	}

	public static String blobToBase64(Blob blob) {
		if (blob != null) {
			try {
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				return java.util.Base64.getEncoder().encodeToString(bytes);
			} catch (SQLException ex) {
				Logger.getLogger(Base64.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	public static String byteToBase64(byte[] file) throws SQLException {
		return blobToBase64(new javax.sql.rowset.serial.SerialBlob(file));
	}

	public static String getArquivoResources(String fileName) {
		Arquivo arq = new Arquivo();
		return arq.getFileWithUtilString(fileName);
	}

	private String getFileWithUtilString(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			return IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getArquivoResourcesFile(String fileName) {
		Arquivo arq = new Arquivo();
		return arq.getFileWithUtilFile(fileName);
	}

	private byte[] getFileWithUtilFile(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			return IOUtils.toByteArray(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}