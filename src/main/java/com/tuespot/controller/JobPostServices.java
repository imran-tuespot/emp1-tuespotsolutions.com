package com.tuespot.controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tuespot.model.FilterWithValue;
import com.tuespot.model.Job;
import com.tuespot.model.JobModel;
import com.tuespot.trustmanager.TrustAnyTrustManager;

@RestController
public class JobPostServices {

	public void postJob(JobModel data, String companyName) {

		try {

			String urls = "https://appts.in/jobportal/job/post";

			Job job = new Job();
			job.setId(data.getId());

			String loc = data.getLocation();
			System.out.println("loc" + loc);
			Long cityId = null;
			Long workModeId = 2L;
			if (loc != null) {
				loc = loc.substring(0, loc.indexOf(","));
				System.out.println("location -=:" + loc);
			}

			System.out.println("loc substring:-" + loc);
			if (loc.equalsIgnoreCase("Mohali")) {
				cityId = 3L;
			} else if (loc.equalsIgnoreCase("Chandigarh")) {
				cityId = 4L;
			} else if (loc.equalsIgnoreCase("Delhi")) {
				cityId = 5L;
			} else if (loc.equalsIgnoreCase("Mumbai")) {
				cityId = 6L;
			} else if (loc.equalsIgnoreCase("Pune")) {
				cityId = 7L;
			} else if (loc.equalsIgnoreCase("Chennai")) {
				cityId = 8L;
			} else if (loc.equalsIgnoreCase("Bengaluru")) {
				cityId = 9L;
			} else if (loc.equalsIgnoreCase("kochi")) {
				cityId = 10L;
			} else if (loc.equalsIgnoreCase("Ahmedabad")) {
				cityId = 11L;
			} else if (loc.equalsIgnoreCase("Hyderabad")) {
				cityId = 12L;
			} else if (loc.equalsIgnoreCase("Gurugram")) {
				cityId = 13L;
			} else if (loc.equalsIgnoreCase("Gurgaon")) {
				cityId = 14L;
			} else if (loc.equalsIgnoreCase("Surat")) {
				cityId = 15L;
			} else if (loc.equalsIgnoreCase("Bengaluru North")) {
				cityId = 16L;
			} else if (loc.equalsIgnoreCase("Jaipur")) {
				cityId = 17L;
			} else if (loc.equalsIgnoreCase("Noida")) {
				cityId = 18L;
			} else {

			}

			// add the city
			// Pune, Maharashtra, India string break by comman ,
			// Pune =========== enum pune(1), delhi(2)
			// set the enum id
			// job.setCityId(0);
			//

			job.setCompanyId(data.getCompanyId());
			job.setDepartment(data.getDepartment());
			job.setDescription(data.getDescription());
			job.setExperience(data.getExperience());
			job.setId(data.getId());
			job.setJobType(data.getJobType());
			// add the work locations
			job.setLocation(companyName + " " + data.getLocation());
			job.setSkills(data.getSkills());
			job.setStatus(true);
			job.setTitle(data.getTitle());
			job.setType(data.getType());
			job.setWorkModeId(1L);
			List<FilterWithValue> filter = new ArrayList<FilterWithValue>();
			FilterWithValue location = new FilterWithValue();
			location.setFilterId(1L);
			location.setFilterValueId(cityId);
			filter.add(location);
			FilterWithValue workMode = new FilterWithValue();
			workMode.setFilterId(2L);
			workMode.setFilterValueId(workModeId);
			filter.add(workMode);
			job.setFilterWithValues(filter);
			// jobList.add(job);

			if (cityId != null) {
				Gson gson = new Gson();
				// System.out.println("line no 59 " + job.toString());
				String json = new Gson().toJson(job);
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
							"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE2ODY4MTAxMzYsImV4cCI6MTY4Njg5NjUzNn0.qbMiVfQjCXiI2y7KQAlDP-xh0AE6Xnyulbqrq-vq-9C64Cvb6M0Lp8rYWNu--w4bWCO-NRZgTMEYjAsU52-r9A");
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
				}

			} else {
				System.out.println("city not exist in application !!");
			}
		} catch (Exception e) {
			System.out.println("Not Inserted !!!");
			e.printStackTrace();
		}
		// return jsonObject;

	}

	@GetMapping("/get")
	public void post() {

		String string = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). Skills:- Java, Spring, Hibernate (Java), RESTful APIs and MySQL  ";

		if (string.contains("Skills")) {

			String[] split = string.split("Skills:- ");
			// System.out.println(split.length);
			// System.out.println("qq"+split[0]);
			System.out.println(split[1]);
			String string2 = split[1];
			String substring = string2.substring(0, 50);

			System.out.println("substring" + substring);
			int length = split[1].length();

		}

		System.out.println();

//		try {
//
//			String urls = "https://appts.in/jobportal/job/post";
//
//			String json = "hellllllo";
//			System.out.println("json = " + json);
//
//			if (urls.contains(" "))
//				urls = urls.replace(" ", "%20");
//
//			URL console = new URL(urls);
//			if ("https".equals(console.getProtocol())) {
//				SSLContext sc = SSLContext.getInstance("SSL");
//				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
//				HttpURLConnection conn = (HttpURLConnection) console.openConnection();
//				SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//				// conn.setSSLSocketFactory(sslsocketfactory);
//				// conn.setConnectTimeout(5000);
//				conn.setRequestProperty("Authorization",
//						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE2ODY1NTYyMTAsImV4cCI6MTY4NjY0MjYxMH0.iePv3V2l3mZKlFYnl1re1V2c2RcUK6uC8sZkCEm57zkmUcB0yIQxdjxQ2EWbYmWzTN3uy31tegNeEsgnfy-ZyQ");
//				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//
//				conn.setRequestProperty("User-Agent",
//						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
//				conn.setDoOutput(true);
//				conn.setDoInput(true);
//				conn.setRequestMethod("POST");
//
//				OutputStream os = conn.getOutputStream();
//				os.write(json.getBytes("UTF-8"));
//				os.close();
//
//				// read the response
//
//				InputStream in = new BufferedInputStream(conn.getInputStream());
//				String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
//				// JSONObject jsonObject = new JSONObject(result);
//
//				in.close();
//				conn.disconnect();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// return jsonObject;

	}

}
