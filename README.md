🧪 SwagLabsDemoWebsiteV2WithUtils

Automated testing framework for the Swag Labs Demo Website (https://www.saucedemo.com/
), built with Java, Selenium WebDriver, and JUnit/TestNG (based on project structure). This version includes utilities to simplify test creation and execution.

🔍 About

SwagLabsDemoWebsiteV2WithUtils is a test automation framework designed to validate core functionalities of the Swag Labs demo web application, such as:

Login & authentication

Product listing and interactions

Cart & checkout flows

This project includes reusable utility classes to help with common tasks like configuration, WebDriver setup, logging, waits, and reporting.

🛍️ Website under test: https://www.saucedemo.com/
 — a demo e-commerce site used for training and automation practice. 
GitHub

🧩 Features

✔ Page Object Model (POM)
✔ Centralized configuration
✔ Reusable utility methods
✔ Multiple test runners (e.g., JUnit/TestNG)
✔ Browser control with Selenium WebDriver
✔ Structured test reports
✔ Sample test cases demonstrating common user flows

📁 Project Structure
.
├── src/
│   ├── main/java/... (page objects, utils)
│   └── test/java/... (test cases)
├── test-runners/              # Test execution configs/scripts
├── test-outputs/              # Reports / logs of past runs
├── .gitignore
├── pom.xml                    # Maven configuration
└── README.md

⚙️ Prerequisites

Before you run the tests, make sure you have:

Java JDK 8+ installed

Maven (for build and dependency management)

Chrome browser + compatible ChromeDriver (or another browser driver)

Git (optional, for cloning)

📦 Installation

Clone the repository:

git clone https://github.com/hossamsaleh113/SwagLabsDemoWebsiteV2WithUtils.git
cd SwagLabsDemoWebsiteV2WithUtils


Install dependencies:

This project uses Maven — download dependencies with:

mvn clean install

▶️ Running Tests
⚡ With Maven

Run all tests:

mvn test


Run specific test suite (e.g., TestNG suite):

mvn test -DsuiteXmlFile=test-runners/suite.xml


If using JUnit, configure your test runner accordingly in the pom.xml.

🧠 Utilities & Helpers

The utils/ package contains commonly used helpers such as:

WebDriver factory

Config reader

Logger utilities

Wait methods

Screenshot capture on failure

These utilities ensure cleaner test code and easier maintenance.

📊 Test Reports

After execution, test reports and logs are generated in:

test-outputs/


This directory contains:

HTML / XML reports

Screenshots of failures

Execution logs
