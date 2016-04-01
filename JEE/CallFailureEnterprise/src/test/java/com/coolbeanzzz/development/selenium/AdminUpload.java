package com.coolbeanzzz.development.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AdminUpload {
	/**
	 * Set up selenium to login as admin, click the browse button to allow user to select
	 * a file and then uploads after a wait time
	 * @param args
	 * @throws InterruptedException
	 */
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        
        driver.get("http://localhost:8080/CallFailureEnterprise");
        
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        
        username.sendKeys("admin");
        password.sendKeys("admin");
        
        username.submit();
        
        driver.findElement(By.name("uploadFile")).click();
        
        Thread.sleep(10000);
        
        driver.findElement(By.id("upload-button")).submit();
    }
}