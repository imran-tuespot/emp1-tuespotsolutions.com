package com.tuespot.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewJobController {

	@GetMapping("/fetch")
	public void fetchJobs() {

		// giving the path of web drive of chrome
		System.setProperty("webdriver.chrome.driver", "C:\\chrome15\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);

		// url which will open by seleiumn
		driver.get("https://in.linkedin.com/jobs/java-developer-jobs?position=1&pageNum=0");
		driver.manage().window().maximize();

		String text = driver.findElement(By.className("lazy-loaded")).getAttribute("innerHTML");
		System.out.println(text);

		String text2 = driver.findElement(By.xpath("//ul[@class='filters__list']//li[5]")).getText();
		System.out.println(text2);

		WebElement UiLelement = driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[2]/ul"));
		List<WebElement> list = UiLelement.findElements(By.tagName("li"));
		for (WebElement i : list) {
			WebElement ahref = i.findElement(By.tagName("a"));
			
			String card__subtitle = i.findElement(By.className("base-search-card__subtitle")).getText();
			System.out.println(card__subtitle);

			ChromeDriver jobDetails = new ChromeDriver(options);
			jobDetails.get(ahref.getAttribute("href"));
			
			jobDetails
					.findElement(By
							.xpath("//*[@id=\"main-content\"]/section[1]/div/div/section[1]/div/div/section"));
		}
	}

}
