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
    private WebDriverWait driverwait = new WebDriverWait(driver, 200);

    @FindBy(id = ":hw")
    private WebElement sentLink;

    @FindBy(id = ":hs")
    private WebElement inboxLink;

    @FindBy(id = ":hq")
    private WebElement moreButton;

    @FindBy(id = ":hz")
    private WebElement spamLink;

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(id = "gbqfab")
    private WebElement advancedSearchButton;

    @FindBy(className = "aQc")
    WebElement selectOptionsButton;

    @FindBy(xpath = "//span[@class='aQc']/div[2]/div[2]")
    WebElement selectInboxListbox;

    @FindBy(xpath = "//div[@class='ZC']")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(text(),'COMPOSE')]")
    WebElement composeButton;

    @FindBy(name = "to")
    WebElement recipientField;

    @FindBy(name = "subjectbox")
    WebElement subjectField;

    @FindBy(xpath = "//td[@class='Ap']/div[2]/div")
    WebElement messageBody;

    @FindBy(xpath = "//div[contains(text(),'Send')]")
    WebElement sendButton;

    @FindBy(xpath = "//a[@class='gb_b gb_ib gb_R']")
    WebElement userLink;

    @FindBy(xpath = "//a[contains(text(),'Sign out')]")
    WebElement signOutLink;

    @FindBy(xpath = "//span[contains(text(),'Message sent.')]")
    WebElement sendMessageText;

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
        driverwait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        return sendMessageText;
    }

    public void clickOnSentMessages() {
        sentLink.click();
        logger.info("Clicked on the sent messages");
    }

    public Boolean isCorrectPageOfSentMessages() {
        return driverwait.until(ExpectedConditions.titleContains(sentLink.getText()));
    }

    public void clickOnInboxMessages() {
        inboxLink.click();
        logger.info("Clicked on the inbox messages");
    }

    public Boolean isCorrectPageOfInboxMessages() {
        return driverwait.until(ExpectedConditions.titleContains(inboxLink.getText()));
    }

    public void clickOnSpamMessages() {
        moreButton.click();
        spamLink.click();
        logger.info("Clicked on the spam messages");
    }

    public Boolean isCorrectPageOfSpamMessages() {
        return driverwait.until(ExpectedConditions.titleContains(spamLink.getText()));
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
        driverwait.until(ExpectedConditions.visibilityOf(recipientField));
        recipientField.sendKeys(recipientEmail);
        subjectField.sendKeys(subject);
        messageBody.sendKeys(textMessage);
        sendButton.click();
        logger.info("Message sent");
    }

    public void signOut(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverwait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        userLink.click();
        signOutLink.click();
    }
}