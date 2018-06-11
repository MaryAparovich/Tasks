package cnn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParserHtml {
    public static void main(String[] args)  {
        ParserHtml parserCnn = new ParserHtml();
        try {
            parserCnn.parse();
        } catch (IOException e) {
            System.out.println("ParserHtml threw IOException");
        }
    }

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
