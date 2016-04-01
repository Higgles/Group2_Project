package com.coolbeanzzz.development.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SupEngQueries extends TestCase {
	
	private WebDriver driver;
	
	/**
	 * Set up the web driver for selenium tests and login as the
	 * Support Engineer
	 */
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "11");
        capabilities.setCapability("platform", Platform.WINDOWS);
        capabilities.setCapability("name", "Testing Selenium 2");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        driver.get("http://localhost:8080/CallFailureEnterprise");
        
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        
        username.sendKeys("supeng");
        password.sendKeys("supeng");
        
        username.submit();
        
        Thread.sleep(5000);
    }
    
    /**
     * Test for query 1 - Display Event ID and CauseCodes for given IMSI
     * Runs the query and checks the table headings
     * @throws InterruptedException
     */
    public void testQuery1() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery1")).click();
        driver.findElement(By.id("phead")).click();
        driver.findElement(By.id("query1")).click();
        
        String[] query1headings = {"Date/Time", "Event Id", "Cause Code", "Description"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        int count = 0;
        for(WebElement e : tableRows){
        	assert(e.getText().equals(query1headings[count]));
        	count++;
        }
    }
    
    /**
     * Test for query 14 - Display Unique CauseCodes for given IMSI
     * Runs the query and checks the table headings
     * @throws InterruptedException
     */
    public void testQuery14() throws InterruptedException{
    	Thread.sleep(2000);
        driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery14")).click();
        driver.findElement(By.id("phead")).click();
        driver.findElement(By.id("query1")).click();
        
        Thread.sleep(5000);
        
        String[] query14headings = {"Event Id", "Cause Code", "Description", "Number of Occurences"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        int count = 0;
        for(WebElement e : tableRows){
        	assert(e.getText().equals(query14headings[count]));
        	count++;
        }
    }
    
    /**
     * Test for query 2 - Display all IMSIs with call failures during a period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery2() throws InterruptedException{
    	Thread.sleep(2000);
    	driver.findElement(By.id("button1")).click();
    	driver.findElement(By.id("availQuery2")).click();
    	driver.findElement(By.id("phead")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.id("datefrom")).clear();
    	driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
    	driver.findElement(By.id("query1")).click();

        Thread.sleep(5000);
        
        String[] query2headings = {"IMSI", "Market", "Operator", "Total Duration"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        int count = 0;
        for(WebElement e : tableRows){
        	assert(e.getText().equals(query2headings[count]));
        	count++;
        }
    }
    
    /**
     * Test for query 3 - Display count of call failures for a given model of phone, during time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery3() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery3")).click();
        driver.findElement(By.id("phead")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("datefrom")).clear();
        driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
        
//        driver.findElement(By.id("")).sendKeys();
//      driver.findElement(By.id("")).sendKeys();
        driver.findElement(By.id("query1")).click();

        Thread.sleep(5000);
        
        String[] query3headings = {"Manufacturer", "Model", "Number of Failures", "Total Failures"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        int count = 0;
        for(WebElement e : tableRows){
        	assert(e.getText().equals(query3headings[count]));
        	count++;
        }
    }
    
    public void tearDown() throws Exception {
        this.driver.quit();
    }
    
}