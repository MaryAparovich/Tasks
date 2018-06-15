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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private WebDriverWait driverWait = new WebDriverWait(driver, 200);

    @FindBy(xpath = "//div[not(contains(@style, 'display: none'))]/div/div/div/div[not(contains(@style, 'display: none'))]/div/div/div/span/div/span[@class='Dj']/span[1]/span[2]")
    WebElement countEmailsLabel;

    @FindBy(xpath = "//div[@class='ae4 UI']//table//tbody/tr")
    List<WebElement> emailsOnFirstPage;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        logger.info("Search results page opened");
    }

    public int getActualCountEmailsFound() {
        List<List<String>> listResult = new ArrayList<>();
        for (int i = 0; i < emailsOnFirstPage.size(); i++) {
            if (emailsOnFirstPage.get(i).isDisplayed()) {
                List<String> wordList = Arrays.asList(emailsOnFirstPage.get(i).getText().split(" "));
                listResult.add(wordList);
            }
        }
        return  listResult.size();
    }

    public int getExpectedCountEmailsFound() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        int result = Integer.valueOf(countEmailsLabel.getText());
        return result;
    }
}
