package org.example.automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class easymytrip {


	public static ArrayList<flight> getEasymytripflights(String fromcity, String tocity) throws InterruptedException {
		System.out.println("this is easymytrip");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.easemytrip.com/flights.html");
		driver.manage().window().maximize();
		WebElement frombox = driver.findElement(By.id("FromSector_show"));
		frombox.click();
		Thread.sleep(2000);


		WebElement firstsearchbox = driver.findElement(By.id("a_FromSector_show"));
		firstsearchbox.sendKeys(fromcity);
		Thread.sleep(2000);
		WebElement firstsearchresult = driver.findElement(By.xpath("//*[@id=\"fromautoFill\"]/ul/li[1]"));
		firstsearchresult.click();

		Thread.sleep(2000);
		WebElement tobox = driver.findElement(By.id("Editbox13_show"));
		tobox.click();
		Thread.sleep(2000);

		WebElement secondsearchbox = driver.findElement(By.id("a_Editbox13_show"));
		secondsearchbox.sendKeys(tocity);
		Thread.sleep(2000);
		WebElement secondsearchresult = driver.findElement(By.xpath("//*[@id=\"toautoFill\"]/ul/li[1]"));
		secondsearchresult.click();

		Thread.sleep(2000);

		WebElement searchbutton = driver.findElement(By.xpath("//*[@id=\"showOWRT\"]/div/div[7]/button"));
		searchbutton.click();

		Thread.sleep(5000);
		ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.className("main-bo-lis"));
		ArrayList<flight> flights = new ArrayList<>();

		for (WebElement curelement : list) {
			String flightname = curelement.findElement(By.xpath("./div[1]/div[1]/div/div[2]/span[1]")).getText();
			String flightnumber1 = curelement.findElement(By.xpath("./div[1]/div[1]/div/div[2]/span[2]/span[1]")).getText();
			String flightnumber2 = curelement.findElement(By.xpath("./div[1]/div[1]/div/div[2]/span[2]/span[2]")).getText();
			String flightnumber = flightnumber1 + "-" + flightnumber2;
			String starttime = curelement.findElement(By.xpath("./div[1]/div[2]/span[1]")).getText();
			String endtime = curelement.findElement(By.xpath("./div[1]/div[4]/span[1]")).getText();
			String totaljourneytime = curelement.findElement(By.xpath("./div[1]/div[3]/span[1]")).getText();
			String totalstops = curelement.findElement(By.xpath("./div[1]/div[3]/span[2]")).getText();
			String price = "";
			try {
				price = curelement.findElement(By.xpath("./div[1]/div[5]/div[1]/div[2]")).getText();
			} catch (Exception e) {
				price = curelement.findElement(By.xpath("./div[1]/div[5]/div[2]/div[2]")).getText();
			}
			flight f = new flight(flightname, flightnumber, starttime, endtime, totaljourneytime, totalstops, price);
			flights.add(f);
		}
		driver.close();
		return flights;
	}


}
