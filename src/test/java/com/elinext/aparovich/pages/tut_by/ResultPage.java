package com.elinext.aparovich.pages.tut_by;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ResultPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='search-i']/ol/li")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='search-i']//a[2]")
    private List<WebElement> resultsByLink;

    public ResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        logger.info("Result page opened");
    }

    public int getNumberResultsFound() {
        return resultsByLink.size();
    }

    public WebElement getElementByText(String searchTextResults) {
        for (int i = 0; i < searchResults.size(); i++) {
            if (searchResults.get(i).getText().toLowerCase().contains(searchTextResults.toLowerCase())) {
                return searchResults.get(i).findElement(By.xpath("ul/li/a"));
            }
        }
        return null;
    }
}
