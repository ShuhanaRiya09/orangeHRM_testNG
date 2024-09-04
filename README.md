# OrangeHRM Employee Management Automation using TestNG

## Project Overview
This project automates the employee management process on the OrangeHRM demo site. The tasks include logging in as an admin, creating a new employee, verifying employee details, and updating personal information. The automation is implemented using TestNG, Selenium WebDriver, and Java.

## Prerequisites
- **Java 11** or above
- **Gradle** for dependency management
- **Selenium** for automation tool
- **TestNG** for automation framework (using POM)
- **IDE** like IntelliJ IDEA or Eclipse
- **Git** for version control

### How to Run This Project

1. **Clone the Project:**
   ```bash
   git clone https://github.com/your-github-username/orangehrm-employee-management-automation.git
   cd orangehrm-employee-management-automation
2. **Open in IntelliJ IDEA:**
   
3. **Build and Test:**   
   ```bash
   gradle clean test

4. **Generate Allure Report:**
   ```bash
   allure generate allure-results --clean -o allure-report

5. **View Allure Report:**
   ```bash
   allure serve allure-results

## Test Scenarios:
1. Login as Admin
   -  Authenticates as an admin and stores the token for further API requests.
   
2. Create Users:
   - Creates two new customers and one agent.
   - Go to the PIM menu and create a new employee.
   - Save the employee's firstname, lastname, employeeId, username, and password into a JSONArray file.
   - Generate a strong, random password that meets the following criteria:Combination of upper and lower case characters
   - Includes symbols and numbers
3. Verify Employee Creation
   - Go to the dashboard and search by employee ID to check if the employee is found.
4. Directory Search:
   - Go to the Directory menu and search by employee name.
   - Verify if the employee is found.
   - Logout the session.
5.Login with New Employee:
   - Login using the newly created employee credentials.
   - Assert that the full name is displayed beside the profile icon.
6. Update Employee Information:
   - Go to "My Info".
   - Scroll down and select "Gender" and "Blood Type" as "O+".
   - Save the changes.
   - Logout the user.

   
## Automation Demonsration:

## Allure Report showcasing:

