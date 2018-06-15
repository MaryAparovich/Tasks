package com.elinext.aparovich.pages.gmail;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UserPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private WebDriverWait driverWait = new WebDriverWait(driver, 200);

    @FindBy(css = "a[href*='https://mail.google.com/mail/u/0/#sent'")
    private WebElement sentLink;

    @FindBy(css = "a[href*='https://mail.google.com/mail/u/0/#inbox'")
    private WebElement inboxLink;

    @FindBy(xpath = "//div[@role='navigation']//span[@role='button']")
    private WebElement moreButton;

    @FindBy(css = "a[href*='https://mail.google.com/mail/u/0/#spam'")
    private WebElement spamLink;

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(xpath = "//div[@role='search']//div[@role='button']")
    private WebElement advancedSearchButton;

    @FindBy(xpath = "//span/div[@role='listbox'][1]")
    WebElement selectOptionsButton;

    @FindBy(xpath = "//span/div[@role='listbox'][2]/div[2]")
    WebElement selectInboxListbox;

    @FindBy(xpath = "//div[@role='search']//button")
    WebElement searchButton;

    @FindBy(xpath = "//div[@role='button'][@gh='cm']")
    WebElement composeButton;

    @FindBy(name = "to")
    WebElement recipientField;

    @FindBy(name = "subjectbox")
    WebElement subjectField;

    @FindBy(xpath = "//div[@role='dialog']//div[@role='textbox']")
    WebElement messageBody;

    @FindBy(xpath = "//div[@role='dialog']//table[@role='group']//div[@role='button'][1]")
    WebElement sendButton;

    @FindBy(xpath = "//a[@role='button']/span")
    WebElement userLink;

    @FindBy(xpath = "//div[contains(text(),'Your message has been sent.')]")
    WebElement sendMessageText;

    @FindBy(xpath = "//a[contains(@href, 'https://accounts.google.com/Logout')]")
    WebElement signOutLink;

    public UserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        logger.info("User page opened");
    }

    public WebElement getSendMessageText() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        return sendMessageText;
    }

    public void clickOnSentMessages() {
        sentLink.click();
        logger.info("Clicked on the sent messages");
    }

    public Boolean isCorrectPageOfSentMessages() {
        return driverWait.until(ExpectedConditions.titleContains(sentLink.getText()));
    }

    public void clickOnInboxMessages() {
        inboxLink.click();
        logger.info("Clicked on the inbox messages");
    }

    public Boolean isCorrectPageOfInboxMessages() {
        return driverWait.until(ExpectedConditions.titleContains(inboxLink.getText()));
    }

    public void clickOnSpamMessages() {
        moreButton.click();
        spamLink.click();
        logger.info("Clicked on the spam messages");
    }

    public Boolean isCorrectPageOfSpamMessages() {
        return driverWait.until(ExpectedConditions.titleContains(spamLink.getText()));
    }

    public void findInboxMessagesByQuery(String searchWord) {
        inboxLink.click();
        searchField.sendKeys(searchWord);
        advancedSearchButton.click();
        logger.info("Showed search options");
        selectOptionsButton.click();
        selectInboxListbox.click();
        searchButton.click();
    }

    public void composeAndSendLetter(String recipientEmail, String subject, String textMessage) {
        composeButton.click();
        driverWait.until(ExpectedConditions.visibilityOf(recipientField));
        recipientField.sendKeys(recipientEmail);
        subjectField.sendKeys(subject);
        messageBody.sendKeys(textMessage);
        sendButton.click();
        logger.info("Message sent");
    }

    public void signOut(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        userLink.click();
        signOutLink.click();
    }
}