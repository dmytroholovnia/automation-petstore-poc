Here’s a polished and more structured version of your README:

---

# Automation Petstore POC

This project is a sample automation framework for API testing using **Java**, **Maven**, **TestNG**, and **RestAssured**.
It demonstrates API validation practices and generates reports using **Allure**.

📎 **API Reference:** [https://petstore.swagger.io/](https://petstore.swagger.io/)
📝 Logging is triggered when tests fail.
📊 Test execution results are available in an Allure report.

---

## ✅ Prerequisites

Ensure the following tools are installed on your machine:

* **JDK 17**
* **Maven**
* **Allure Report** — installation guide: [https://allurereport.org/docs/install/](https://allurereport.org/docs/install/)

---

## 🚀 Running Tests

### 1️⃣ Run automated tests

```bash
mvn clean test
```

### 2️⃣ Generate and view Allure report

```bash
allure serve target/allure-results
```

---

Feel free to modify or extend this framework to suit your testing needs!
