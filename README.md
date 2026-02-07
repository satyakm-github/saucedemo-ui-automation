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

```text
saucedemo-ui-automation/
├── pom.xml
├── testng.xml
└── src
    ├── main
    │   └── java
    └── test
        └── java
            └── com
                └── keerthi
                    └── automation
                        └── saucedemo
                            ├── BaseTest.java
                            ├── LoginHelper.java
                            └── SauceDemoSmokeTests.java

```
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

## 🚀 How to Run the Tests

### ▶️ Option 1: Using Eclipse IDE

- Import the project as a Maven Project

- Locate the testng.xml file

- Right-click → Run As → TestNG Suite

### ▶️ Option 2: Using Maven (Command Line)

- mvn test

---

## 📊 Test Reports

- Uses default TestNG HTML reports

- Reports are generated at: test-output/index.html

---

## 🧩 Key Design Highlights

- Clean and structured Maven project layout

- Test lifecycle handled using @BeforeMethod and @AfterMethod

- Reusable helper classes for common flows (e.g., login)

- Tests are independent with no shared state

- Strong assertions instead of console print statements

- Stable locators with explicit waits where required

---

## 🌐 Application Under Test

- URL: https://www.saucedemo.com/

---

## 🔮 Future Enhancements

- Refactor framework to Page Object Model (POM)

- Integrate Extent Reports

- Add screenshot capture on test failure

- CI integration using GitHub Actions

---