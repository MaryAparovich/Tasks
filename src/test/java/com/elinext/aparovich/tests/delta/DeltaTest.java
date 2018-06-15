package com.elinext.aparovich.tests.delta;

import com.elinext.aparovich.pages.delta.*;
import com.elinext.aparovich.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeltaTest {
    private WebDriver driver;
    private HomePage homePage;
    private TicketsSelectionPage ticketsSelectionPage;
    private TripSummaryPage tripSummaryPage;
    private PassengerInfoPage passengerInfoPage;
    private ReviewPurchaseInfoPage reviewPurchaseInfoPage;
    private static final String NAME = "Maria";
    private static final String LASTNAME = "Aparovich";
    private static final String GENDER = "F";
    private static final String MONTH = "May";
    private static final String DAY = "12";
    private static final String YEAR = "1993";
    private static final String PHONENUMBER = "12457296301";
    private static final String EMAIL = "mary@gmail.com";

    @BeforeMethod(groups = { "delta" }, description = "Init browser")
    public void setUp() {
        driver = DriverSingleton.getDriver();
        homePage = new HomePage(driver);
        ticketsSelectionPage = new TicketsSelectionPage(driver);
        tripSummaryPage = new TripSummaryPage(driver);
        passengerInfoPage = new PassengerInfoPage(driver);
        reviewPurchaseInfoPage = new ReviewPurchaseInfoPage(driver);
    }

    @Test (groups = { "delta" })
    public void checkFlightBooking() {
        homePage.openPage();
        homePage.findFlights();

        ticketsSelectionPage.openPage();
        ticketsSelectionPage.selectTickets();

        tripSummaryPage.openPage();
        tripSummaryPage.nextPage();

        passengerInfoPage.openPage();
        passengerInfoPage.fillPassengerInformation(NAME, LASTNAME, GENDER, MONTH, DAY, YEAR, PHONENUMBER, EMAIL);
        passengerInfoPage.nextPage();

        reviewPurchaseInfoPage.openPage();
        assertTrue(reviewPurchaseInfoPage.isCompletePurchaseEnabled());
    }

    @AfterMethod(groups = { "delta" }, description = "Stop Browser")
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
