Feature: Signup Process Validation

  Scenario: Successful account creation and login verification
    Given the user accesses the Magento homepage
    When the user proceeds to the account registration page
    And the user provides valid registration information
    And submits the form using the "Create an Account" button
    Then the user should successfully complete the registration process
    And a welcome message should be displayed
    When the user logs out of their account
    And logs back in using the newly created credentials
    Then the user's account dashboard should be visible

  Scenario: Attempt to register with an already registered email address
    Given the user accesses the Magento homepage
    When the user proceeds to the account registration page
    And the user enters an email address that is already registered
    And submits the form using the "Create an Account" button
    Then an error message should indicate that the email is already in use

  Scenario: Attempt to register with mismatched passwords
    Given the user accesses the Magento homepage
    When the user proceeds to the account registration page
    And the user provides passwords that do not match
    And submits the form using the "Create an Account" button
    Then an error message should highlight the password mismatch

  Scenario: Attempt to register with an invalid email format
    Given the user accesses the Magento homepage
    When the user proceeds to the account registration page
    And the user enters an invalid email format
    And submits the form using the "Create an Account" button
    Then an error message should indicate that the email format is invalid
