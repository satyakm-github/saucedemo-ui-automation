package com.keerthi.automation.saucedemo;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();

	    ChromeOptions options = new ChromeOptions();

	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);

	    options.setExperimentalOption("prefs", prefs);

	   
	    options.addArguments("--disable-features=PasswordManagerOnboarding,PasswordManagerEnableAutosignin");
	    options.addArguments("--disable-notifications");
	    options.addArguments("--disable-popup-blocking");

	    options.addArguments("--incognito");

	    driver = new ChromeDriver(options);
	    driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void tearDown() {
	    if (driver != null) driver.quit();
	}


}
