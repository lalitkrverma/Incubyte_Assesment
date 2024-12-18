Selenium Automation Project

This project is a Selenium-based automation framework designed to perform web application testing.
Prerequisites

Before running the project, ensure you have the following installed on your system:

    Java 8 or later
    Maven
    WebDriver for the browser of your choice (e.g., ChromeDriver, GeckoDriver)
    An IDE like IntelliJ IDEA, Eclipse, or VS Code

Project Setup

    Clone the repository:

git clone <repository_url>

Navigate to the project directory:

cd <project_directory>

Install dependencies using Maven:

    mvn install

    Ensure your browser driver (e.g., chromedriver for Chrome) is set up correctly and available in your system’s PATH.

Running Tests
Via Maven

To run all the tests using Maven, use the following command:

mvn test

Running Specific Test Class

If you want to run a specific test class, use:

mvn -Dtest=<TestClassName> test

Running via IDE

You can also run the tests directly from your IDE by right-clicking on the test class and choosing the option to run it.
Test Reports

After the tests have completed, the results will be stored in the target/surefire-reports directory. You can view the test reports in the console or through your IDE.
Project Structure

    src/main/java: Contains the main source code of the project.
    src/test/java: Contains the test classes and automation scripts.
    src/test/resources: Contains test data, configuration files, and driver binaries (if any).
