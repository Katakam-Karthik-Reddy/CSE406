package org.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class ixigo {


    public static ArrayList<flight> getIxigoflghts(String fromcity, String tocity) throws InterruptedException {

        System.out.println("working ixigo");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ixigo.com/?utm_source=Google_Search&utm_medium=paid_search_google&utm_campaign=Ixigo_Brand&utm_source=google&utm_medium=paid_search_google&utm_campaign=ixigo_brand&gclid=EAIaIQobChMI06-x8IfW_QIVZoJLBR3ngQlYEAAYASAAEgK74fD_BwE");

        try {
            WebElement dialogbox = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[1]/div/div[2]"));
            dialogbox.click();
        }
        catch(Exception e) {
            System.out.println("dialog box not found");
        }


        WebElement frombox = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[1]/div/div[1]/input"));
        frombox.sendKeys(fromcity);
        Thread.sleep(3000);
        WebElement firstresult = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[1]/div/div[3]/div/div[1]"));
        firstresult.click();

        try{
            WebElement some = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[1]/div/div[3]/div/div[1]"));
            some.click();
        }
        catch(Exception e){
            System.out.println(e);
        }
        WebElement secondbox = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[3]/div/div[1]/input"));
        secondbox.sendKeys(tocity);
        Thread.sleep(3000);
        WebElement secondresult = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[3]/div/div[3]/div/div[1]"));
        secondresult.click();
        WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[6]/button"));
        searchbox.click();
        Thread.sleep(10000);


        ArrayList<WebElement> pages = (ArrayList<WebElement>) driver.findElements(By.className("page-num"));
        //System.out.println(pages);
        ArrayList<flight> flights = new ArrayList<>();
        for(WebElement curpage:pages) {

            try {
                WebElement page = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[4]/div[1]/div[3]/div/span[7]"));
                page.click();
            } catch (Exception e) {
                System.out.println("next page not found");
            }

            WebElement main = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[4]/div[1]/div[2]"));
            ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.className("c-flight-listing-row-v2"));
            for (WebElement curelement : list) {
                String flightname = curelement.findElement(By.xpath("./div/div[1]/div[1]/div[2]/div/a/div")).getText();
                String flightnumber = curelement.findElement(By.xpath("./div/div[1]/div[1]/div[2]/div/div")).getText();
                String starttime = curelement.findElement(By.xpath("./div/div[1]/div[2]/div[1]/div[2]")).getText();
                String endtime = curelement.findElement(By.xpath("./div/div[1]/div[2]/div[3]/div[2]")).getText();
                String totaljourneytime = curelement.findElement(By.xpath("./div/div[1]/div[2]/div[2]/div/div[2]")).getText();
                String totalstops = curelement.findElement(By.xpath("./div/div[1]/div[2]/div[2]/div/div[6]")).getText();
                String price = curelement.findElement(By.xpath("./div/div[2]/div/div/div/div[1]/div/span[2]")).getText();
                flight f = new flight(flightname, flightnumber, starttime, endtime, totaljourneytime, totalstops, price);
                flights.add(f);
            }

        }
        driver.close();
        return flights;
    }
}
