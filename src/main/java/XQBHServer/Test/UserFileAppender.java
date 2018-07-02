package XQBHServer.Test;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class UserFileAppender extends RollingFileAppender<ILoggingEvent> {

    private String currentlyActiveFile;
    public static Map<String, String> map = new HashMap<String, String>();
    boolean flg=false;


    public UserFileAppender(){
        System.out.println(UserFileAppender.map.get(Thread.currentThread().getName()));
        setFile("Log/"+UserFileAppender.map.get(Thread.currentThread().getName()));
    }

    @Override
    protected void subAppend(ILoggingEvent event) {
//
        if (currentlyActiveFile == null) {
            currentlyActiveFile = getFile();
        }
        System.out.println(currentlyActiveFile);
//
//        setFile(currentlyActiveFile.replace("{fileName}", map.get(Thread.currentThread().getId())));
//        start();
        super.subAppend(event);
    }
}