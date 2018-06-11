package com.elinext.aparovich.tests.cnn;

import cnn.ParserHtml;
import org.jsoup.nodes.Document;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import java.io.IOException;

public class CnnTest {

    @Test(groups = {"cnn"})
    public void parseCnn() throws IOException {
        ParserHtml parserHtml = new ParserHtml();
        Document document = parserHtml.parse();
        assertNotNull(document);
    }
}
