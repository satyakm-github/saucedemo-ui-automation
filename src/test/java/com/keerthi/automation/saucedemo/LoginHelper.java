package com.keerthi.automation.saucedemo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginHelper extends BaseTest{
	
	private WebDriver driver;

    public LoginHelper(WebDriver driver) {
        this.driver = driver;
    }
	
	public void login(String userName, String password) {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
    }
	
	public void validateBadge(int count) {
			
		String cartBadgeText = driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).getText();
		
		if(cartBadgeText.isBlank()) {
			
		}else {
		
			int cartBadgeCount = Integer.parseInt(cartBadgeText);
		    Assert.assertEquals(count, cartBadgeCount);
		}
		
	}
	
	public void validateCart(int count) {
		
        driver.findElement(By.id("shopping_cart_container")).click();		
		List<WebElement> cartPageItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
		
		if(cartPageItems.isEmpty()) {	
			
		}else {
			int actualItemCount = cartPageItems.size();
		    Assert.assertEquals(count, actualItemCount);
		}
	}
	
	public void addAllItemsToCart() {
		
		List<WebElement> products = driver.findElements(By.className("inventory_item"));
		for (int i = 0; i <products.size(); i++) {
			
			WebElement card = products.get(i);
			WebElement addProductToCart = card.findElement(By.cssSelector("button.btn_inventory"));
			addProductToCart.click();
	
		}

	}
	
	public void checkOut(String userName, String postalCode) {
		
        driver.findElement(By.id("shopping_cart_container")).click();
		
		driver.findElement(By.id("checkout")).click();
		
		driver.findElement(By.id("first-name")).sendKeys(userName);
		driver.findElement(By.id("last-name")).sendKeys(userName);
		driver.findElement(By.id("postal-code")).sendKeys(postalCode);
		driver.findElement(By.id("continue")).click();
	}
}
