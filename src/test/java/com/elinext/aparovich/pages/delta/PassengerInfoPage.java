package com.elinext.aparovich.pages.delta;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassengerInfoPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    public PassengerInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        logger.info("Passenger info page opened");
    }

    @FindBy(id = "firstName0")
    WebElement firstNameField;

    @FindBy(id = "lastName0")
    WebElement lastNameField;

    @FindBy(id = "gender0-button")
    WebElement gender;

    @FindBy(xpath = "//ul[@id='gender0-menu']")
    WebElement genderSelect;

    @FindBy(id = "month0-button")
    WebElement month;

    @FindBy(xpath = "//ul[@id='month0-menu']")
    WebElement monthSelect;

    @FindBy(id = "day0-button")
    WebElement day;

    @FindBy(xpath = "//ul[@id='day0-menu']")
    WebElement daySelect;

    @FindBy(id = "year0-button")
    WebElement year;

    @FindBy(xpath = "//ul[@id='year0-menu']")
    WebElement yearSelect;

    @FindBy(id = "emgcFirstName_0")
    WebElement emgcFirstName;

    @FindBy(id = "emgcLastName_0")
    WebElement emgcLastName;

    @FindBy(id = "emgcPhoneNumber_0")
    WebElement emgcPhoneNumber;

    @FindBy(id = "telephoneNumber0")
    WebElement telephoneNumber;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "reEmail")
    WebElement reEmail;

    @FindBy(id = "paxReviewPurchaseBtn")
    WebElement continueButton;

    public void fillPassengerInformation(String name, String lastName, String gender, String month, String day, String year, String phoneNumber, String email) {
        fillMainInformation(name, lastName, gender);
        fillDayOfBirth(month, day, year);
        fillEmergencyContactInformation(name, lastName, phoneNumber);
        fillContactInformation(phoneNumber, email);
    }

    public void fillDayOfBirth(String month, String day, String year) {
        this.month.click();
        monthSelect.sendKeys(month);
        monthSelect.click();

        this.day.click();
        daySelect.sendKeys(day);
        daySelect.click();

        this.year.click();
        yearSelect.sendKeys(year);
        yearSelect.click();
    }

    public void fillMainInformation(String name, String lastName, String gender) {

        firstNameField.sendKeys(name);
        lastNameField.sendKeys(lastName);
        this.gender.click();
        genderSelect.sendKeys(gender);
        genderSelect.click();
    }


    public void fillEmergencyContactInformation(String name, String lastName, String phoneNumber) {
        emgcFirstName.sendKeys(name);
        emgcLastName.sendKeys(lastName);
        emgcPhoneNumber.sendKeys(phoneNumber);
    }

    public void fillContactInformation(String phoneNumber, String email) {
        telephoneNumber.sendKeys(phoneNumber);
        this.email.sendKeys(email);
        reEmail.sendKeys(email);
    }

    public void nextPage() {
        continueButton.click();
    }
}



