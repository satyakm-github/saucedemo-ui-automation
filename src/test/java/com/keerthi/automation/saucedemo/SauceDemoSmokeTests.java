package com.keerthi.automation.saucedemo;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoSmokeTests extends BaseTest {

	String options = "";
	String userName = "standard_user";
	String password = "secret_sauce";
	String expectedPageTitle = "Swag Labs";
	String invalidPassword = "Keerthi";
	String expectedInvalidLoginErrMsg = "Epic sadface: Username and password do not match any user in this service";
	String expectedContainerName = "Products";
	int count = 0;
	String dropdownValue = "Price (low to high)";
	String expectedCheckOutpageTitle = "Checkout: Your Information";
	String expectedCheckOutErrMsg = "Error: Postal Code is required";
	String postalCode = "IG13NZ";
	String expectedOverviewTitle = "Checkout: Overview";
	String expectedOrderSuccessMsg = "Thank you for your order!";

	private LoginHelper lh;

	@BeforeMethod
	public void initHelper() {
		lh = new LoginHelper(driver); 
	}

	@Test
	public void validLogin() {

		lh.login(userName, password);
		Assert.assertEquals(driver.getTitle(), expectedPageTitle);
	}

	@Test
	public void logOut() {

		lh.login(userName, password);
		driver.findElement(By.id("react-burger-menu-btn")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
	}

	@Test
	public void invalidLogin() {

		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(invalidPassword);
		driver.findElement(By.name("login-button")).click();
		String actualInvalidLoginErrMsg = driver.findElement(By.xpath(
				"//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]"))
				.getText();
		Assert.assertEquals(actualInvalidLoginErrMsg, expectedInvalidLoginErrMsg);
	}

	@Test
	public void validateProductsPage() {

		lh.login(userName, password);
		Assert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed());
		String actualContainerName = driver.findElement(By.xpath("//span[@class='title']")).getText();
		Assert.assertEquals(actualContainerName, expectedContainerName);
	}

	@Test
	public void addItemsAndVerifyBadgeAndCart() {

		lh.login(userName, password);
		List<WebElement> products = driver.findElements(By.className("inventory_item"));
		for (int i = 0; i <products.size(); i++) {
			
			WebElement card = products.get(i);

			String productName = card.findElement(By.className("inventory_item_name")).getText();
			
			if(productName.contains("Sauce")) {
				
				WebElement addProductToCart = card.findElement(By.cssSelector("button.btn_inventory"));
				addProductToCart.click();
				count ++;
			}			
		}	
		lh.validateBadge(count);
		lh.validateCart(count);
		
	}
	
	@Test
	public void removeItemsAndVerifyBadgeAndCart() {
		
		lh.login(userName, password);
		List<WebElement> products = driver.findElements(By.className("inventory_item"));
		
		for (int i = 0; i <products.size(); i++) {
			
			WebElement card = products.get(i);
			String priceBar = card.findElement(By.xpath("//div[@class='pricebar']")).getText();
			
			if(priceBar.contains("Remove")) {
				
				WebElement removeProductFromCart = card.findElement(By.xpath("//button[contains(text(),'Remove')]"));
				removeProductFromCart.click();
				count ++;
			}			
		}
		lh.validateBadge(count);
		lh.validateCart(count);
		
	}
	
	@Test
	public void validateFilters() {
		
		lh.login(userName, password);
		WebElement dropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		
		Select selectValue = new Select(dropdown);	
		selectValue.selectByVisibleText(dropdownValue);
		
		WebElement reIdentifyDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select fetchValue = new Select(reIdentifyDropdown);
		
		String selectedValue = fetchValue.getFirstSelectedOption().getText();
		Assert.assertEquals(dropdownValue, selectedValue);
		
		List<WebElement> productsPrice = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		
		double previousPrice = Double.parseDouble(productsPrice.get(0).getText().replace("$", ""));
		for(int i =0; i<productsPrice.size(); i++) {
			
			WebElement price = productsPrice.get(i);
			String updatedPrice = price.getText().replace("$", "");
			double currentPrice = Double.parseDouble(updatedPrice);
			
			if(currentPrice< previousPrice) {
				 Assert.fail("Prices are not sorted in ascending order");
			}else {
				previousPrice = currentPrice;
			}		
		}	
	}
	
	
	@Test
	public void checkOutZipFieldValidation() {
		
		lh.login(userName, password);        
		lh.addAllItemsToCart();
		
		driver.findElement(By.id("shopping_cart_container")).click();
		
		driver.findElement(By.id("checkout")).click();
		String actualPageTitleText = driver.findElement(By.xpath("//span[@class='title']")).getText();
		Assert.assertEquals(actualPageTitleText, expectedCheckOutpageTitle);
		
		driver.findElement(By.id("first-name")).sendKeys(userName);
		driver.findElement(By.id("last-name")).sendKeys(userName);
		driver.findElement(By.id("continue")).click();
		
		String actualCheckOutErrMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertEquals(actualCheckOutErrMsg,expectedCheckOutErrMsg);
		
	}
	
	@Test
	public void checkOutSuccessfully() {
		
		lh.login(userName, password);
		lh.addAllItemsToCart();
		lh.checkOut(userName,postalCode);
		
		String actualOverviewTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
		Assert.assertEquals(actualOverviewTitle, expectedOverviewTitle);
		
		driver.findElement(By.id("finish")).click();
		String actualOrderSuccessMsg = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
		Assert.assertEquals(actualOrderSuccessMsg, expectedOrderSuccessMsg);	
		
	}
}
