/**
 * @author Coolbeanzzz
 */
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

public class ErroneousRecordsTest extends TestCase{
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

        username.sendKeys("admin");
        password.sendKeys("admin");

        username.submit();
        
        Thread.sleep(1000);
    }
    
    public void testErroneousRecords() throws Exception {
    	driver.findElement(By.id("erroneous_button")).click();
        
        Thread.sleep(3000);
        
        String[] erroneousheadings = {"dateTime","EventId", "FailureClass", "UEType", "Market", "Operator", "CellId", "Duration", "CauseCode", "NeVersion", "IMSI", "HIER3_ID", "HIER32_ID", "HIER321_ID"};
        List<WebElement> tableRows = driver.findElements(By.tagName("th"));
        
        ArrayList<String> resultHeadings = new ArrayList<String>();
        for(WebElement element : tableRows){
        	resultHeadings.add(element.getText());
        }
        Assert.assertArrayEquals(erroneousheadings, resultHeadings.toArray());
    }
    
    /**
     * Close the web driver for selenium
     */
    public void tearDown() throws Exception {
        this.driver.quit();
    }
}
