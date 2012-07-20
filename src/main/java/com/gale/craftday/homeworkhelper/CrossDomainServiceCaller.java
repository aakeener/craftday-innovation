package com.gale.craftday.homeworkhelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class CrossDomainServiceCaller {

	private static final int INITIAL_BYTE_ARRAY_SIZE = 4096;
	
	private static final Logger LOGGER = Logger
			.getLogger(CrossDomainServiceCaller.class);


	public String retrieveContent(String formattedURL, int timeout, String charset)
			throws IOException {
		String text = null;
		HttpURLConnection conn = null;
		
		URL url = new URL(formattedURL);
		
		try {
			conn = establishConnection(url, timeout);
			text = retrieveContentFromConnection(conn, charset);
		} finally {
			closeConnection(conn);
		}
		return text;
	}

	private HttpURLConnection establishConnection(URL originalUrl, int timeout)
			throws IOException {
		HttpURLConnection conn;
		conn = (HttpURLConnection) originalUrl.openConnection();
		conn.setConnectTimeout(timeout);
		conn.setInstanceFollowRedirects(true);
		return conn;
	}

	protected String retrieveContentFromConnection(HttpURLConnection conn,
			String charset) throws IOException {
		String text = null;
		if (isAcceptableHTTPResponse(conn)) {
			InputStream inputStream = conn.getInputStream();
			text = readInputStreamFromServerResponse(inputStream, charset)
					.toString();
		}
		return text;
	}

	private StringBuffer readInputStreamFromServerResponse(
			InputStream fromServer, String charset) throws IOException {
		StringBuffer response = new StringBuffer();
		byte[] buffer = new byte[INITIAL_BYTE_ARRAY_SIZE];
		int bytesRead;
		try {
			while ((bytesRead = fromServer.read(buffer)) != -1) {
				response.append(bytesToString(buffer, bytesRead, charset));
			}
		} finally {
			fromServer.close();
		}
		return response;
	}

	private String bytesToString(byte[] buffer, int bytesRead, String charset) {
		if (charset != null) {
			try {
				return new String(buffer, 0, bytesRead, charset);
			} catch (UnsupportedEncodingException e) {
				LOGGER
						.warn(
								"Unable to convert charset set to String; using default charset instead",
								e);
			}
		}

		return new String(buffer, 0, bytesRead);
	}

	private void closeConnection(HttpURLConnection connection) {
		if (connection != null) {
			connection.disconnect();
		}
	}

	protected boolean isAcceptableHTTPResponse(HttpURLConnection conn) {
		boolean acceptableResponseCode = false;
		try {
			acceptableResponseCode = HttpURLConnection.HTTP_NOT_FOUND != conn
					.getResponseCode();
		} catch (IOException e) {
			LOGGER
					.error("Unable to determine whether or not a valid response was received due to exception. url = "
							+ conn.getURL().toString()
							+ " Error = "
							+ e.getMessage());
		}
		return acceptableResponseCode;
	}

}
