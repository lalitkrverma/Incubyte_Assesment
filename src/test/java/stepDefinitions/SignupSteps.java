package stepDefinitions;

import io.cucumber.java.en.*;

import java.util.Random;

import pages.LoginPage;
import pages.MyAccountPage;
import utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.CreateAccountPage;

public class SignupSteps {
    private WebDriver driver;
    private HomePage homePage;
    private CreateAccountPage createAccountPage;
    private MyAccountPage myAccountPage;
    private LoginPage loginPage;
    private String newEmail;
    private final String newPwd = "Test@1234";

    @Given("the user accesses the Magento homepage")
    public void user_accesses_homepage() {
        BaseTest.setUp();
        driver = BaseTest.driver;
        driver.get("https://magento.softwaretestingboard.com/");
        homePage = new HomePage(driver);
    }

    @When("the user proceeds to the account registration page")
    public void user_proceeds_to_signup_page() {
        homePage.clickCreateAccount();
        createAccountPage = new CreateAccountPage(driver);
    }

    @When("the user provides valid registration information")
    public void user_provides_valid_details() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        newEmail = "testuser" + randomNumber + "@example.com";
        createAccountPage.enterEmail(newEmail);
        createAccountPage.enterPassword(newPwd);
        createAccountPage.enterConfirmPassword(newPwd);
    }

    @And("submits the form using the {string} button")
    public void submits_create_account_form(String buttonName) {
        createAccountPage.clickCreateAccount();
    }

    @Then("the user should successfully complete the registration process")
    public void user_successfully_registers() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("customer/account");
    }

    @Then("a welcome message should be displayed")
    public void welcome_message_is_displayed() {
        String welcomeMessage = driver.findElement(By.cssSelector("div.messages")).getText();
        assert welcomeMessage.contains("Thank you for registering");
    }

    @When("the user logs out of their account")
    public void user_logs_out_account() {
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.signOut();
    }

    @When("logs back in using the newly created credentials")
    public void user_logs_in_new_account() {
        driver.findElement(By.linkText("Sign In")).click();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(newEmail);
        loginPage.enterPassword(newPwd);
        loginPage.clickLoginButton();
    }

    @Then("the user's account dashboard should be visible")
    public void account_dashboard_is_visible() {
        assert loginPage.isAccountDashboardVisible();
        BaseTest.tearDown();
    }

    @When("the user enters an email address that is already registered")
    public void user_enters_registered_email() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        createAccountPage.enterEmail("existinguser1@example.com");
        createAccountPage.enterPassword("Test@1234");
        createAccountPage.enterConfirmPassword("Test@1234");
    }

    @Then("an error message should indicate that the email is already in use")
    public void error_message_for_registered_email() {
        String errorMessage = driver.findElement(By.cssSelector("div.messages")).getText();
        assert errorMessage.contains("There is already an account with this email address.");
    }

    @When("the user provides passwords that do not match")
    public void user_provides_mismatched_passwords() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        createAccountPage.enterEmail(uniqueEmail);
        createAccountPage.enterPassword("Test@1234");
        createAccountPage.enterConfirmPassword("Mismatch@1234"); // Intentionally mismatched
    }

    @Then("an error message should highlight the password mismatch")
    public void error_message_for_password_mismatch() {
        String errorMessage = driver.findElement(By.id("password-confirmation-error")).getText();
        assert errorMessage.contains("Please enter the same value again.");
    }

    @When("the user enters an invalid email format")
    public void user_enters_invalid_email_format() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        createAccountPage.enterEmail("invalidemail.com");
        createAccountPage.enterPassword(newPwd);
        createAccountPage.enterConfirmPassword(newPwd);
    }

    @Then("an error message should indicate that the email format is invalid")
    public void user_sees_error_message_for_invalid_email() {
        String errorMessage = driver.findElement(By.id("email_address-error")).getText();
        assert errorMessage.contains("Please enter a valid email address");
    }
}
