package com.elinext.aparovich.tests.tut_by;

import com.elinext.aparovich.driver.DriverSingleton;
import com.elinext.aparovich.pages.tut_by.MainPage;
import com.elinext.aparovich.pages.tut_by.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TutBySearchTest {

    private WebDriver driver;
    private String searchText = "automated testing";
    private String searchTextResults = "Minsk Automated Testing Community";
    private MainPage homePage;
    private ResultPage resultPage;

    @BeforeMethod(groups = { "tut_by" }, description = "Init browser")
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @Test(groups = { "tut_by" })
    public void testSearch() {
        homePage = new MainPage(driver);
        homePage.openPage();
        homePage.findClick(searchText);
        resultPage = new ResultPage(driver);
        int countResults = resultPage.getNumberResultsFound();
        System.out.println(countResults + " elements found on this page");
        WebElement element = resultPage.getElementByText(searchTextResults);
        if (element != null) {
            element.click();
            assertNotNull(element);
        } else {
            throw new WebDriverException();
        }
    }

    @AfterMethod(groups = { "tut_by" }, description = "Stop Browser")
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
