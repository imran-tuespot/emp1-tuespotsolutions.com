package com.tuespot.controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.google.gson.Gson;
import com.tuespot.model.Candidate;
import com.tuespot.trustmanager.TrustAnyTrustManager;

public class CandidatePostServices {

	public void postCandidate(Candidate candidateModel) {

		try {

			String urls = "https://appts.in/jobportal/job/post";

			String json = new Gson().toJson(candidateModel);
			System.out.println("json = " + json);

			URL console = new URL(urls);
			if ("https".equals(console.getProtocol())) {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
				HttpURLConnection conn = (HttpURLConnection) console.openConnection();
				SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
				// conn.setSSLSocketFactory(sslsocketfactory);
				// conn.setConnectTimeout(5000);
				conn.setRequestProperty("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE2ODc1ODI0MjgsImV4cCI6MTY4NzY2ODgyOH0.38J782lIkf_W6JB-eqXUWkeLj9jK_Z0aR7J-AAL_g8h59XeAMGwMsEvhY_ssgIZNK1hrAzwaLH8UxvKZVBIqAA");
				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setRequestMethod("POST");

				OutputStream os = conn.getOutputStream();
				os.write(json.getBytes("UTF-8"));
				os.close();

				// read the response
				InputStream in = new BufferedInputStream(conn.getInputStream());
				String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
				// JSONObject jsonObject = new JSONObject(result);

				in.close();
				conn.disconnect();

				System.out.println("Inserted SuccessFully !!!");
			} else {
				System.out.println("city not exist in application !!");
			}
		} catch (Exception e) {
			System.out.println("Not Inserted !!!");
			e.printStackTrace();
		}

	}
}