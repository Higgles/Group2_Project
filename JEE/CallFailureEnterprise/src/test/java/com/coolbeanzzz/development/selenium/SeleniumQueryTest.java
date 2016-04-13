package com.coolbeanzzz.development.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumQueryTest extends TestCase {
	
	private WebDriver driver;

	/**
	 * Set up the web driver for selenium tests and login as NetMan
	 *
	 */
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("http://localhost:8080/CallFailureEnterprise");
        
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));

        username.sendKeys("netman");
        password.sendKeys("netman");

        username.submit();
        
        Thread.sleep(1000);
    }

    /**
     * Test query 1 - Display Event IDs and Cause Codes a given IMSI
     * @throws Exception
     */
    public void testQuery1() throws Exception {
    	driver.findElement(By.id("button1")).click();
    	driver.findElement(By.id("availQuery1")).click();
        driver.findElement(By.id("select2-imsiDropdown-container")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("select2-results__option")).click();
        driver.findElement(By.id("query1")).click();
        
        Thread.sleep(3000);
        
        String[] query1headings = {"Date/Time", "Event Id", "Cause Code", "Description"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query1test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query1test.add(element.getText());
        }
        Assert.assertArrayEquals(query1headings, query1test.toArray());
    }
    
    /**
     * Test for query 14 - Display Unique CauseCodes for given IMSI
     * Runs the query and checks the table headings
     * @throws InterruptedException
     */
    public void testQuery14() throws InterruptedException{
        driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery14")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("select2-imsiDropdown-container")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("select2-results__option")).click();
        driver.findElement(By.id("query1")).click();
        
        Thread.sleep(3000);
        
        String[] query14headings = {"Event Id", "Cause Code", "Description", "Number of Occurences"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query14test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query14test.add(element.getText());
        }
        Assert.assertArrayEquals(query14headings, query14test.toArray());
    }
    
    /**
     * Test for query 9 - Display failure count for a given IMSI during a time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery9() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery9")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("datefrom")).clear();
        driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
        
        driver.findElement(By.id("select2-imsiDropdown-container")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-results__option")).click();
        Thread.sleep(500);
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query9headings = {"Count", "Total Duration"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query9test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query9test.add(element.getText());
        }
        Assert.assertArrayEquals(query9headings, query9test.toArray());
    }
    
    /**
     * Test for query 2 - Display all IMSIs with call failures during a time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery2() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
    	driver.findElement(By.id("availQuery2")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.id("datefrom")).clear();
    	driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
    	driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query2headings = {"IMSI", "Market", "Operator", "Total Duration"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query2test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query2test.add(element.getText());
        }
        Assert.assertArrayEquals(query2headings, query2test.toArray());
    }
    
    /**
     * Test for query 3 - Display count of call failures for a given model of phone, during a time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery3() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery3")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("datefrom")).clear();
        driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
        
        driver.findElement(By.id("select2-manufacturerDropdown-container")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-results__option")).click();
        Thread.sleep(500);
        driver.findElement(By.id("select2-modelDropdown-container")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-results__option")).click();
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query3headings = {"Manufacturer", "Model", "Number of Failures", "Total Duration"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query3test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query3test.add(element.getText());
        }
        Assert.assertArrayEquals(query3headings, query3test.toArray());
    }
    
    /**
     * Test for query 16 - Display IMSIs for a given failure Cause Class
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery16() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery16")).click();
        Thread.sleep(1000);
        
        driver.findElement(By.id("select2-failureDropdown-container")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-results__option")).click();
        Thread.sleep(500);
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query16headings = {"IMSIs", "Count"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query16test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query16test.add(element.getText());
        }
        Assert.assertArrayEquals(query16headings, query16test.toArray());
    }
    
    /**
     * Test for query 4 - Display count, for each IMSI, the number of call failures and their total duration during a time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery4() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery4")).click();
        Thread.sleep(1000);
        
        driver.findElement(By.id("datefrom")).clear();
        driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query4headings = {"IMSI", "Number of Failures", "Total Duration"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query4test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query4test.add(element.getText());
        }
        Assert.assertArrayEquals(query4headings, query4test.toArray());
    }
    
    /**
     * Test for query 5 - Display, for a given model of phone, all the unique failure Event Id and Cause Code combinations and the number of occurrences
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery5() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery5")).click();
        Thread.sleep(1000);
        
        driver.findElement(By.id("select2-manufacturerDropdown-container")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-results__option")).click();
        Thread.sleep(500);
        driver.findElement(By.id("select2-modelDropdown-container")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-results__option")).click();
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query5headings = {"Event Id", "Cause Code", "Number of Occurences", "UE Type", "Manufacturer", "Model"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query5test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query5test.add(element.getText());
        }
        Assert.assertArrayEquals(query5headings, query5test.toArray());
    }
    
    /**
     * Test for query 12 - Display Top 10 Market/Operator/Cell ID combinations with call failures during a time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery12() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery12")).click();
        Thread.sleep(1000);
        
        driver.findElement(By.id("datefrom")).clear();
        driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query12headings = {"Market", "Operator", "Cell Id", "Count"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query12test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query12test.add(element.getText());
        }
        Assert.assertArrayEquals(query12headings, query12test.toArray());
    }
    
    /**
     * Test for query 15 - Display top 10 IMSIs with call failures during a time period
     * Runs the query and checks table headings
     * @throws InterruptedException
     */
    public void testQuery15() throws InterruptedException{
    	driver.findElement(By.id("button1")).click();
        driver.findElement(By.id("availQuery15")).click();
        Thread.sleep(1000);
        
        driver.findElement(By.id("datefrom")).clear();
        driver.findElement(By.id("datefrom")).sendKeys("01/04/2010 12:04");
        driver.findElement(By.id("query1")).click();

        Thread.sleep(3000);
        
        String[] query15headings = {"IMSI", "Market", "Operator", "Number of Occurences"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> query15test = new ArrayList<String>();
        for(WebElement element : tableRows){
        	query15test.add(element.getText());
        }
        Assert.assertArrayEquals(query15headings, query15test.toArray());
    }
    
    /**
     * Close the web driver for selenium
     */
    public void tearDown() throws Exception {
        this.driver.quit();
    }
    
}