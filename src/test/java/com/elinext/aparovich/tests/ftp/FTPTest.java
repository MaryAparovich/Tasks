package com.elinext.aparovich.tests.ftp;

import com.elinext.aparovich.ftp.FtpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FTPTest {

    private static final String HOST = "ftp.byfly.by";
    private static final int PORT = 21;
    private static final String USERNAME = "anonymous";
    private static final String PASSWORD = "anonymous";
    private static final String TEST_DIR_NAME = "new";
    private final Logger logger = LogManager.getLogger();
    private FtpClient ftpClient;

    @BeforeMethod(groups = {"ftp"})
    public void setUp() throws Exception {
        ftpClient = new FtpClient(HOST, PORT);
        ftpClient.connect(USERNAME, PASSWORD);
    }

    @Test(groups = {"ftp"})
    public void testFtpServerCommands() throws Exception {
        String[] content = ftpClient.getDirectoryContent();
        logger.info("Current directory: ");
        for (String contentItem : content) {
            logger.info(contentItem);
        }
        for (String contentItem : content) {
            boolean isMoved = ftpClient.moveToDirectory(contentItem);
            if (isMoved) {
                logger.info("Moved to the directory " + "'" + contentItem + "'");
                boolean isMovedUp = ftpClient.moveUpDirectory();
                if (isMovedUp) {
                    logger.info("Exited from current directory");
                }
            }
        }

        if (!ftpClient.isDirectoryExists(TEST_DIR_NAME)) {
            boolean isDirectoryMade = ftpClient.makeDirectory(TEST_DIR_NAME);
            logger.info("Created new directory '" + TEST_DIR_NAME + "'");
        } else {
            logger.info("Directory '" + TEST_DIR_NAME + "'could not create!");
        }
    }


    @AfterMethod(groups = {"ftp"})
    public void tearDown() throws Exception {
        ftpClient.disconnect();
    }
}
