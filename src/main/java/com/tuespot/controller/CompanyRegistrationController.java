package com.tuespot.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyRegistrationController {

	@GetMapping("/getCompany")
	public void getCompany() {
//
//		try {
		System.setProperty("webdriver.chrome.driver", "C:\\chrome15\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
////			options.addArguments("--user-data-dir=C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data");
////			options.addArguments("--profile-directory=Profile 1");
////			options.addArguments("--disable-extensions");
		options.addArguments("--remote-allow-origins=*");
//			
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.linkedin.com/login");
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));

		username.sendKeys("sagar657r@protonmail.com");
		password.sendKeys("8765sag4321");
//			
		driver.findElement(By.className("btn__primary--large")).click();

		driver.get(
				"https://www.linkedin.com/search/results/companies/?heroEntityKey=urn%3Ali%3Aorganization%3A19120471&keywords=java%20developer&origin=SWITCH_SEARCH_VERTICAL&position=0&searchId=8349752d-e484-4b95-9ddc-f7f8ff0a4404&sid=F3Z");
		driver.manage().window().maximize();

		WebElement findElement = driver.findElement(By.className("reusable-search__entity-result-list"));
		List<WebElement> list = findElement.findElements(By.tagName("li"));
		System.out.println("size of list is:" + list.size());

		for (WebElement li : list) {
			WebElement href = li.findElement(By.tagName("a"));
			ChromeDriver jobDetails = new ChromeDriver(options);
			jobDetails.get(href.getAttribute("href").toString());

			jobDetails.findElement(By.id("ember8"));
			jobDetails.findElement(By.className("ember-view")).click();
		//	jobDetails.findElement(By.xpath("//*[@class=\"ember-view\"]")).click();
			jobDetails.findElement(By.xpath("//*[@id=\"ember8\"]")).click();
			
			
			
 //driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[2]/ul"));

			//jobDetails.close();
			// jobDetails.get
		}

//			 driver.findElement(By.xpath("//div[@class=\"scaffold-layout__inner\"]/ul"));
//			 driver.findElement(By.className("caffold-layout"));

		// } catch (Exception e) {
//
//			e.printStackTrace();
//		}
	}

//	@GetMapping("/getData")
//	public void getData() {
//
//		try {
//			System.setProperty("webdriver.chrome.driver", "C:\\chrome15\\chromedriver.exe");
//
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--remote-allow-origins=*");
//
//			WebDriver driver = new ChromeDriver(options);
//			driver.get(
//					"https://in.indeed.com/jobs?q=java+developer&sc=0kf%3Afcckey%2800bf65393a58dc64%29%3B&vjk=9b7d03c581c43865");
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}

	@GetMapping("/any")
	public void any() {
//asdf!@#jkl
		System.setProperty("webdriver.chrome.driver", "C:\\chrome_driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");

		ChromeDriver driver = new ChromeDriver(options);

//		driver.get("https://accounts.google.com/");
		driver.get("http://facebook.com");
//		new ChromeDriver(options);
		// URL launch
//		driver.get(

//		options.addArguments("user-data-dir=C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data");
//		options.setExperimentalOption("debuggerAddress", "localhost:9222");
//		ChromeDriver driver = new ChromeDriver(options);
//		driver.get("https://accounts.google.com/");
	}
}
