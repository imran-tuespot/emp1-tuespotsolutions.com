package com.tuespot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.tuespot.model.JobModel;

@RestController
public class Controller {

	@GetMapping("/test")
	public void test() {
		try {
			JobModel jobModel = new JobModel();
			// setting the driver executable
			System.setProperty("webdriver.chrome.driver", "C:\\chrome15\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			// Initiating your chromedriver
			ChromeDriver driver = new ChromeDriver(options);

			driver.get("https://in.linkedin.com/jobs/engineer-jobs?position=1&pageNum=0");

			// maximize window
			driver.manage().window().maximize();
			
			
			
			
//			scaffold-layout__main
//			G0mKjo+4TsGIfTrM7n9row==
			
			

			// To Delay execution for 10 sec. as to view the maximize browser
			WebElement UiLelement = driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[2]/ul"));

			int count = 0;
			int scroll = 0;
			while (scroll < 500) {
				Thread.sleep(100);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,100)", "");
				scroll++;
			}

			List<WebElement> list = UiLelement.findElements(By.tagName("li"));
			System.out.println("size is " + list.size());

			for (WebElement i : list) {
				WebElement ahref = i.findElement(By.tagName("a"));
				count = count + 1;
				// title
				// System.out.println(ahref.getText());
				System.out.println("=========================" + count + ahref.getText() + "========================");
				// link
				// setting title to model
				jobModel.setTitle(ahref.getText());

				System.out.println(ahref.getAttribute("href"));

				ChromeDriver jobDetails = new ChromeDriver(options);
//			WebDriver jobDetails = new ChromeDriver();

				// display one by one jobs in minimize windows
				jobDetails.get(ahref.getAttribute("href").toString());

//				try {
//					jobDetails.findElement(By.xpath(
//							"//*[@id=\"main-content\"]/section[1]/div/div/section[1]/div/div/section/button[1]"));
//
//				} catch (NoSuchElementException n) {
//
//				}

				// job details

				String description = jobDetails.findElement(By.className("show-more-less-html__markup"))
						.getAttribute("innerHTML");
				// setting job details
				jobModel.setDescription(description);
				jobModel.setType("true");

				System.out.println("description :-" + description);

				if (description.contains("Skill:-")) {
					String[] split = description.split("Skill:-");
					if (split.length >= 50 || split.length >= 51) {
						String substring = split[1].substring(0, 50);
						jobModel.setSkills(substring);
					} else {
						String substring = split[1];
						jobModel.setSkills(substring);
					}
				} else if (description.contains("Skills:-")) {
					String[] split = description.split("Skills:-");
					if (split.length >= 50 || split.length >= 51) {
						String substring = split[1].substring(0, 50);
						jobModel.setSkills(substring);
					} else {
						String substring = split[1];
						jobModel.setSkills(substring);
					}
				} else if (description.contains("skills :-")) {
					String[] split = description.split("skills :-");
					if (split.length >= 50 || split.length >= 51) {
						String substring = split[1].substring(0, 50);
						jobModel.setSkills(substring);
					} else {
						String substring = split[1];
						jobModel.setSkills(substring);
					}
				} else if (description.contains("Skills")) {
					String[] split = description.split("Skills");
					if (split.length >= 50 || split.length >= 51) {
						String substring = split[1].substring(0, 50);
						jobModel.setSkills(substring);
					} else {
						String substring = split[1];
						jobModel.setSkills(substring);
					}
				} else {
					jobModel.setSkills("-");
				}

				// get company logo
				System.out.println(i.findElement(By.tagName("img")).getAttribute("src"));

				// company name]
				String companyName = "";
				try {
					System.out.println(i.findElement(By.className("base-search-card__subtitle")).getText());
					companyName = i.findElement(By.className("base-search-card__subtitle")).getText();
				} catch (Exception e) {

				}
				// job locations
				try {
					System.out.println(i.findElement(By.className("topcard__flavor-row")).getText());
				} catch (Exception e) {

				}
				// setting locations to model
				try {
					System.out.println("location (Full Address) :- "
							+ jobDetails.findElement(By.className("topcard__flavor-row")).getText());

					System.out.println("location (Full Address) :- "
							+ i.findElement(By.className("job-search-card__location")).getText());

					jobModel.setLocation(i.findElement(By.className("job-search-card__location")).getText());
					// job salary

				} catch (Exception e) {

				}
				try {
					System.out.println(i.findElement(By.className("job-search-card__salary-info")).getText());
				} catch (Exception e) {

				}

				// find experience
				try {
					System.out.println("exp -:"
							+ jobDetails.findElement(By.className("description__job-criteria-subheader")).getText());
					String experience = jobDetails.findElement(By.className("description__job-criteria-subheader"))
							.getText();

					System.out.println("exp level-:"
							+ jobDetails.findElement(By.className("description__job-criteria-text")).getText());
					String expLevel = jobDetails.findElement(By.className("description__job-criteria-text")).getText();
					// setting experience to model
					jobModel.setExperience(expLevel);
				} catch (Exception e) {

				}

				// find department
				try {

					System.out.println("department:-"
							+ jobDetails.findElement(By.className("description__job-criteria-text")).getText());

					String department = jobDetails
							.findElement(By.xpath("//ul[@class='description__job-criteria-list']//li[last()]//span"))
							.getText();
					System.out.println("department:- " + department);

					// setting department to model
					jobModel.setDepartment(department);
				} catch (Exception e) {

				}

				// find JobType
				try {

					System.out.println("department:-"
							+ jobDetails.findElement(By.className("description__job-criteria-text")).getText());

					String jobType = jobDetails
							.findElement(By.xpath("//ul[@class='description__job-criteria-list']//li[2]//span"))
							.getText();
					System.out.println("jobType:- " + jobType);

					// setting JobType to model
					jobModel.setJobType(jobType);
				} catch (Exception e) {

				}

				jobDetails.quit();

				try {
					System.out.println(i.findElement(By.className("job-search-card__listdate")).getText());
				} catch (Exception e) {

				}
				// job posted date
				try {
					System.out
							.println(i.findElement(By.className("job-search-card__listdate")).getAttribute("datetime"));
				} catch (Exception e)

				{

				}
				jobModel.setCompanyId(8L);

				JobPostServices applications = new JobPostServices();
				applications.postJob(jobModel, companyName);

				System.out.println("----------------" + jobModel);
				Thread.sleep(100);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,100)", "");

			}

			// driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}