package com.example.site;

import me.tongfei.progressbar.ProgressBar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserHTML {

   // private static String urlToJsoup = "https://sochi.hh.ru/resumes/programmist";
    private static String urlToJsoup = "https://sochi.hh.ru/resumes/muzykant";

    public static Integer lastTotalProgrammers = 0;
    public static List pagesUrl = new ArrayList();// список ссылок на страницы по 20 чел
    public static List<String> userLinks = new ArrayList<>();// список ссылок на каждого человека

    //возвращает общее количество программистов
    public static Integer getTotalProgrammers() {
        Document DOMSite = null;
        {
            try {
                DOMSite = Jsoup.connect(urlToJsoup).get();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Please pay attention to the site address");
            }
        }
        String stringTotalProgrammers = DOMSite.getElementsByAttributeValue("data-qa", "resumes-total-found").text().split(" ")[1];
        lastTotalProgrammers = Integer.valueOf(stringTotalProgrammers);
        return Integer.valueOf(stringTotalProgrammers);
    }

    //    метод создает ссылки на страницы по 20 чел на каждой (pagesUrl)
    private static void fillInPagesUrl() {
        for (int i = 0; i < lastTotalProgrammers/20 + 1; i++) {
            String thisUrl = urlToJsoup + "?page=" + i;
            pagesUrl.add(thisUrl);
        }
    }

    // обработка одной страницы
    private static void getUsersLinks20(String urlToJsoup) {

        Document DOMSite = null;
        {
            try {
                DOMSite = Jsoup.connect(urlToJsoup).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DOMSite.getElementsByAttributeValue("class", "resume-search-item__name")
                .forEach(x -> {
                    userLinks.add(x.attr("href"));
                });

    }

    // набить лист ссылками на каждого -
    private static void getUsersLinks(List<String> pagesUrl) {
        for (String page : ProgressBar.wrap(pagesUrl, "Лист ссылок на прогеров")
        ) {
            getUsersLinks20(page);
        }

    }

    //составить полный список
    public static void createListUsers() {
        fillInPagesUrl();
        getUsersLinks(pagesUrl);
    }

}
