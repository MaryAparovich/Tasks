package com.elinext.aparovich.pages.delta;

import com.elinext.aparovich.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TripSummaryPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    public TripSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    @Override
    public void openPage() {
        logger.info("Trip summary page opened");
    }

    @FindBy(id = "tripSummarySubmitBtn")
    WebElement tripSummaryContinueBtn;


    public void nextPage() {
        tripSummaryContinueBtn.click();
    }

}
