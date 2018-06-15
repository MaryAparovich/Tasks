package com.elinext.aparovich.pages.tut_by;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultPage extends AbstractPage {
    private WebDriverWait driverWait = new WebDriverWait(driver, 200);
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//ol[@class='b-results-list']/li[h3]")
    private List<WebElement> searchResults;

    public ResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        logger.info("Result page opened");
    }

    public int getNumberResultsFound() {
        return searchResults.size();
    }

    public WebElement getElementByText(String searchTextResults) {
        for (int i = 0; i < searchResults.size(); i++) {
            if (searchResults.get(i).getText().toLowerCase().contains(searchTextResults.toLowerCase())) {
                return searchResults.get(i);
            }
        }
        return null;
    }

    public WebElement getElementTitle(WebElement webElement) {
        return webElement.findElement(By.xpath("h3"));
    }
}
