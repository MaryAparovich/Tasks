package com.elinext.aparovich.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    private static final String DRIVER = "webdriver.gecko.driver";
    private static final String DRIVER_EXE_PATH = "D:\\JAVA\\IDEA\\geckodriver.exe";

    public DriverSingleton() {

    }

    public static WebDriver getDriver() {
        if (null == driver) {
            System.setProperty(DRIVER, DRIVER_EXE_PATH);
            driver = new FirefoxDriver();
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            logger.info("Browser started");
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
