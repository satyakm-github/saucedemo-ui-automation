# SauceDemo UI Automation Framework

A Selenium-based UI automation framework built using **Java, TestNG, and Maven** to validate critical end-to-end user flows on the SauceDemo e-commerce application.

This project focuses on **realistic test scenarios**, **clean assertions**, and **independent, reliable test execution**, following industry best practices.

---

## 🛠 Tech Stack

- **Language**: Java (JDK 21)
- **Automation Tool**: Selenium WebDriver
- **Test Framework**: TestNG
- **Build Tool**: Maven
- **Browser**: Google Chrome
- **Driver Management**: WebDriverManager
- **IDE**: Eclipse

---

## 📂 Project Structure

saucedemo-ui-automation
├── pom.xml
├── testng.xml
└── src
├── test
│ └── java
│ └── com/keerthi/automation/saucedemo
│ ├── BaseTest.java
│ ├── LoginHelper.java
│ └── SauceDemoSmokeTests.java
└── main
└── java


---

## ✅ Automated Test Scenarios

### Authentication
- Valid login
- Invalid login error validation

### Product & Cart
- Products page loads successfully
- Add selected products to cart
- Cart badge count validation
- Remove items and verify empty cart

### Checkout
- Checkout information validation (missing postal code)
- Error message verification

### Sorting & Filters
- Sort products by **Price (Low → High)**
- Validate price list is sorted in ascending order

### Session
- Logout from application menu

---

##  How to Run the Tests

### Option 1: Using Eclipse
1. Import the project as a **Maven project**
2. Right-click `testng.xml`
3. Select **Run As → TestNG Suite**

### Option 2: Using Maven (CLI)
```bash
mvn test

##  Test Reports

- Uses default TestNG HTML reports

- Reports generated under: test-output/index.html

##  Key Design Highlights

- Clean Maven project structure

- TestNG lifecycle management using @BeforeMethod / @AfterMethod

- Helper classes for reusable flows (login)

- Independent tests (no shared state)

- Strong assertions instead of console prints

- Stable locators and explicit waits where required

##  Application Under Test

- URL: https://www.saucedemo.com/

##  Future Enhancements

- Page Object Model (POM) refactor

- Extent Reports integration

- Screenshot capture on failure

- CI integration using GitHub Actions
