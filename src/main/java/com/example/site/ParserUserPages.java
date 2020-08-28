package com.example.site;

import me.tongfei.progressbar.ProgressBar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParserUserPages {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public static List<User> users = new ArrayList<User>();
    public static List<User> refreshUsers = new ArrayList<User>();

    public static void filledUsersList(List<String> userLinks) {

        for (String userlink : ProgressBar.wrap(userLinks, "usersData")
        ) {
            getOneProgerInfo(userlink);
            users = refreshUsers; //чтобы не падало при загрузке новых
            // этот механизм переписывает кусками по одному соискателю
        }
    }

    private static void getOneProgerInfo(String userlink) {
        Document userPage = null;
        try {
            userPage = Jsoup.connect("https://sochi.hh.ru"+userlink).get();
        } catch (IOException e) {
            log.info(" {}  " + "обрати внимание на ссылку на страницу пользователя  ", dateFormat.format(new Date()));
            e.printStackTrace();
        }
        refreshUsers.add(new User(
                        userPage.getElementsByAttributeValue("class", "highlighted").text(),
                        userPage.getElementsByAttributeValue("data-qa", "resume-personal-age").text(),
                        userPage.getElementsByAttributeValue("data-qa", "resume-personal-gender").text(),
                        "phone",
                        userPage.getElementsByAttributeValue("data-qa", "resume-block-salary").text(),
                        userPage.getElementsByAttributeValue("data-qa", "resume-block-skills-content").text(),
                        getListNavik(userPage.getElementsByAttributeValue("class", "bloko-tag-list"))
                )
        );


    }

    private static List<String> getListNavik(Elements naviki) {
        List<String> nav= new ArrayList<>();
        naviki.forEach(x->nav.add(x.text()));
        return nav;
    }
}
