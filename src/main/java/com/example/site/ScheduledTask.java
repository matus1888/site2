package com.example.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)//**Interval   5000 - 5 sec 60000 - 1 min 3600000 -hour  86400000 -day
//***
//*   We check the total number of programmers at a given interval, if the total number has changed, we write about it in the log
//***
    public void reportCurrentTime() {
        if (!(ParserHTML.lastTotalProgrammers.equals(ParserHTML.getTotalProgrammers())) ) {
            log.info(" {}  " + "total programmers value=" + ParserHTML.getTotalProgrammers(), dateFormat.format(new Date()));
            ParserHTML.createListUsers();
            ParserUserPages.filledUsersList(ParserHTML.userLinks);
        }
//***        uncomment if you need to make sure the scheduler works
//        else {
//        log.info(" {}  " + "no variability", dateFormat.format(new Date()));
//        }
    }
}