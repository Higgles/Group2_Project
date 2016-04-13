package com.coolbeanzzz.development.selenium;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest extends TestCase {
	
	private WebDriver driver;

	/**
	 * Set up the web driver for selenium tests and login as Sys Admin
	 *
	 */
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Test the page is loaded for login
     * @throws Exception
     */
    public void testPage() throws Exception {
        this.driver.get("http://localhost:8080/CallFailureEnterprise");
        assertEquals("Coolbeanzzz", this.driver.getTitle());
    }
    
    /**
     * Test the login for admin user and that they have SysAd role
     * @throws Exception
     */
    public void testLogin() throws Exception {
        this.driver.get("http://localhost:8080/CallFailureEnterprise");
        
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));

        username.sendKeys("admin");
        password.sendKeys("admin");

        username.submit();
        
        driver.get("http://localhost:8080/CallFailureEnterprise/rest/users/currentUser");
        
        String user = driver.getPageSource();
	    
	    assert(user.contains("System Administrator"));
    }
    
    /**
     * Close the web driver for selenium
     */
    public void tearDown() throws Exception {
        this.driver.quit();
    }
    
}