package XQBHServer.Test;

import XQBHServer.Utils.log.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class logback {
    static     public org.slf4j.Logger logger = LoggerFactory.getLogger("XQBHServer");

    public static void main(String[] args) {
        MDC.put("logFileName", "logs/1.log");
        logger.debug("1111");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1; ++i) {
            executorService.execute(new Counter(String.valueOf(i)));
        }
        executorService.shutdown();
//        org.slf4j.Logger syslogger = LoggerFactory.getLogger("XQBHServer");
//        String loggerFile = Logger.getLogPath("temp");
//        MDC.put("logFileName", loggerFile); //获取日志文件
//        for (int i = 0; i < 1000000; i++)
//            Logger.comLog(syslogger, "debug");
    }
}
