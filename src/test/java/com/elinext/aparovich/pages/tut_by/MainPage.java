package com.elinext.aparovich.pages.tut_by;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://www.tut.by";

    @FindBy(id = "search_from_str")
    private WebElement inputText;

    @FindBy(name = "search")
    private WebElement searchButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Home page opened");
    }

    public void findClick(String text) {
        inputText.sendKeys(text);
        searchButton.click();
    }
}
