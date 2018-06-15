package com.elinext.aparovich.cnn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParserHtml {

    public  Document parse() throws IOException {
        Document document = Jsoup.connect("https://cnn.com").get();

        String htmlText = document.body().toString();
        String[] arr = htmlText.split(",");

        int countWords = 0;
        for (String element : arr) {
            if (element.contains("Trump") && (!element.contains("description"))) {
                countWords++;
                System.out.println(element);
            }
        }
        System.out.println(countWords + " words found");
        return document;
    }
}
