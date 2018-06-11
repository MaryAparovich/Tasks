package com.elinext.aparovich.pages.delta;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketsSelectionPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private WebDriverWait driverWait = new WebDriverWait(driver, 20);

    public TicketsSelectionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        logger.info("Tickets selection page opened");
    }

    @FindBy(xpath = "//table[@class='flightResultTable']//td[2]/div/a")
    List<WebElement> selectTicketList;

    public void selectTickets()  {
        selectTicketList.get(0).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-overlay")));
        selectTicketList.get(0).click();
    }
}
