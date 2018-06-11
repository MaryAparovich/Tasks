package com.elinext.aparovich.pages.delta;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://www.delta.com/";

    @FindBy(linkText = "Flight")
    WebElement flightLink;

    @FindBy(xpath = "//span[contains(text(),'Round Trip')]")
    WebElement roundTripButton;

    @FindBy(id = "originCity")
    WebElement fromField;

    @FindBy(id = "destinationCity")
    WebElement toField;

    @FindBy(name = "departureDate")
    WebElement departDateField;

    @FindBy(name = "returnDate")
    WebElement returnDateField;

    @FindBy(xpath = "//span[contains(text(),'Exact Dates')]")
    WebElement exactDatesButton;

    @FindBy(xpath = "//span[contains(text(),'Money')]")
    WebElement priceInMoneyButton;

    @FindBy(id = "findFlightsSubmit")
    WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Home page opened");
    }

    public void findFlights() {
        flightLink.click();
        roundTripButton.click();
        fromField.clear();
        fromField.sendKeys("JFK");
        toField.sendKeys("SVO");
        departDateField.sendKeys("06/15/18");
        returnDateField.sendKeys("06/28/18");
        exactDatesButton.click();
        priceInMoneyButton.click();
        findFlightsButton.submit();
        logger.info("Clicked on find flights");
    }
}

