package com.elinext.aparovich.tests.gmail;

import com.elinext.aparovich.driver.DriverSingleton;
import com.elinext.aparovich.pages.gmail.LoginPage;
import com.elinext.aparovich.pages.gmail.SearchResultsPage;
import com.elinext.aparovich.pages.gmail.UserPage;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GmailTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private UserPage userPage;
    private SearchResultsPage searchResultsPage;
    private static final String EMAIL = "test.automation45651@gmail.com";
    private static final String PASSWORD = "12345qwertyui";
    private static final String SEARCH_WORD = "test";
    private static final String RECIPIENT_EMAIL = "altprint19@mail.ru";
    private static final String SUBJECT = "Test";
    private static final String TEXT_MESSAGE = "Hello!";
    private static final String EXPECTED_SENT_MESSAGE_TEXT = "Your message has been sent. View message";
    private static final String LOGIN_PAGE_TITLE = "Google Accounts";


    @BeforeMethod(groups = {"gmail"}, description = "Init browser")
    public void setUp() {
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @Test(groups = {"gmail"})
    public void checkSuitableMailFolders() {
        loginPage.openPage();
        loginPage.login(EMAIL, PASSWORD);

        userPage.openPage();
        userPage.clickOnSentMessages();
        assertTrue(userPage.isCorrectPageOfSentMessages(), "Page of sent emails is not correct");

        userPage.clickOnInboxMessages();
        assertTrue(userPage.isCorrectPageOfInboxMessages(), "Page of inbox emails is not correct");

        userPage.clickOnSpamMessages();
        assertTrue(userPage.isCorrectPageOfSpamMessages(), "Page of spam emails is not correct");
    }

    @Test(groups = {"gmail"})
    public void checkMessageSearch() {
        loginPage.openPage();
        loginPage.login(EMAIL, PASSWORD);
        userPage.openPage();
        userPage.findInboxMessagesByQuery(SEARCH_WORD);

        int actualCountEmailsFound = searchResultsPage.getActualCountEmailsFound();
        int expectedCountEmailsFound = searchResultsPage.getExpectedCountEmailsFound();
        assertEquals(actualCountEmailsFound, expectedCountEmailsFound);
    }

    @Test(groups = {"gmail"})
    public void checkSendingLetter() {
        loginPage.openPage();
        loginPage.login(EMAIL, PASSWORD);
        userPage.openPage();
        userPage.composeAndSendLetter(RECIPIENT_EMAIL, SUBJECT, TEXT_MESSAGE);
        String actualSentMessageText = userPage.getSendMessageText().getText();
        assertEquals(actualSentMessageText, EXPECTED_SENT_MESSAGE_TEXT, "Message not sent");
    }

    @Test(groups = {"gmail"})
    public void checkSignOut() {
        loginPage.openPage();
        loginPage.login(EMAIL, PASSWORD);
        userPage.openPage();
        userPage.signOut();
        String actualPageTitle = driver.getTitle();
        assertEquals(actualPageTitle, LOGIN_PAGE_TITLE, "Current page title does not match");
    }

    @AfterMethod(groups = {"gmail"}, description = "Stop Browser")
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
