package com.coolbeanzzz.development.selenium;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumUsersTest extends TestCase {
	
	private WebDriver driver;

	/**
	 * Set up the web driver for selenium tests and login as admin
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
    
    /**
     * Test adding a user to the system from addUser.jsp
     * A new user is added to the system. The edit user page is loaded and the
     * username dropdown is checked for the new username
     * @throws Exception
     */
    public void testAddUser() throws Exception {
    	driver.get("http://localhost:8080/CallFailureEnterprise/admin/addUser.jsp");
        Thread.sleep(500);
        
        driver.findElement(By.id("uName")).sendKeys("selenium");
        driver.findElement(By.id("pWord")).sendKeys("selenium");
        driver.findElement(By.id("pWordConf")).sendKeys("selenium");
        driver.findElement(By.name("userType")).click();
        
        driver.findElement(By.id("addUser")).click();
        driver.switchTo().alert().accept();
        
        driver.findElement(By.id("editUserToggle")).click();
        driver.findElement(By.id("select2-uNameDropdown-container")).click();
        Thread.sleep(500);

        driver.findElement(By.id("select2-uNameDropdown-container")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("select2-selection__arrow")).click();
        Thread.sleep(500);
        driver.findElement(By.className("select2-search__field")).sendKeys("selenium");
        Thread.sleep(500);
        driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(500);
        String title = driver.findElement(By.id("select2-uNameDropdown-container")).getAttribute("title");

        driver.findElement(By.id("removeUser")).click();
        
        assertEquals("selenium", title);
    }
    
    /**
     * Close the web driver for selenium
     */
    public void tearDown() throws Exception {
        this.driver.quit();
    }
    
}
