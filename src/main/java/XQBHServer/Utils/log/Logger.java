package XQBHServer.Utils.log;

import XQBHServer.Server.Com;
import XQBHServer.ServerTran.TranObj;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.*;

public class Logger {
    private static final String LOG_FOLDER_NAME = "Log";

    private static final String LOG_FILE_SUFFIX = ".log";

    private static final int miss = 128;


    private static final MyLogHander myLogHander = new MyLogHander();
    public static FileHandler fileHandler = null;
    public static String path = "";
    private static ConsoleHandler consoleHandler;

    public static org.slf4j.Logger logger = LoggerFactory.getLogger("XQBHServer");


    static {
        consoleHandler = new ConsoleHandler();
    }

    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public static void log(TranObj tranObj, String LogLV, String Msg) {

        if (("LOG_ERR".equals(LogLV))) {
            logger.error("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_SYS".equals(LogLV)) && (!"ERR".equals(tranObj.tranLogLV))) {
            logger.warn("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_IO".equals(LogLV)) && (!"ERR".equals(tranObj.tranLogLV) && !"SYS".equals(tranObj.tranLogLV))) {
            logger.info("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_DEBUG".equals(LogLV)) && (!"ERR".equals(tranObj.tranLogLV) && !"SYS".equals(tranObj.tranLogLV) && !"IO".equals(tranObj.tranLogLV))) {
            logger.debug("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        }


        if ("LOG_ERR".equals(LogLV)) {
            String logFile = MDC.get("logFileName");
            String errlogFile = Logger.getLogPath("ERROR");
            MDC.put("logFileName", errlogFile);
//            comLog(logger, Msg, Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            logger.error("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
            MDC.put("logFileName", logFile);
            Com.logFile.remove(errlogFile);

        }

    }

    public static void logException(TranObj tranObj, String LogLV, Exception exception) {

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw, true));
        String Msg = sw.toString();


        if (("LOG_ERR".equals(LogLV))) {
            logger.error("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_SYS".equals(LogLV)) && (!"ERR".equals(tranObj.tranLogLV))) {
            logger.warn("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_IO".equals(LogLV)) && (!"ERR".equals(tranObj.tranLogLV) && !"SYS".equals(tranObj.tranLogLV))) {
            logger.info("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_DEBUG".equals(LogLV)) && (!"ERR".equals(tranObj.tranLogLV) && !"SYS".equals(tranObj.tranLogLV) && !"IO".equals(tranObj.tranLogLV))) {
            logger.debug("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        }


        if ("LOG_ERR".equals(LogLV)) {
            String logFile = MDC.get("logFileName");
            String errlogFile = Logger.getLogPath("ERROR");
            MDC.put("logFileName", errlogFile);
//            comLog(logger, Msg, Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            logger.error(Msg);
            MDC.put("logFileName", logFile);
            Com.logFile.remove(errlogFile);

        }


    }

    public static void comLogException(String LogLV, Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw, true));
        String Msg = sw.toString();


        if (("LOG_ERR".equals(LogLV))) {
            logger.error("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_SYS".equals(LogLV))) {
            logger.warn("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_IO".equals(LogLV))) {
            logger.info("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_DEBUG".equals(LogLV))) {
            logger.debug("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        }
        if ("LOG_ERR".equals(LogLV)) {
            String logFile = MDC.get("logFileName");
            String errlogFile = Logger.getLogPath("ERROR");
            MDC.put("logFileName", errlogFile);
            logger.error(Msg);
            MDC.put("logFileName", logFile);
            Com.logFile.remove(errlogFile);
        }

    }


    /**
     * @param Msg 用于简单的SYSLOG打日志
     */
    public static void comLog(String LogLV, String Msg) {
        if (("LOG_ERR".equals(LogLV))) {
            logger.error("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_SYS".equals(LogLV))) {
            logger.warn("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_IO".equals(LogLV))) {
            logger.info("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        } else if (("LOG_DEBUG".equals(LogLV))) {
            logger.debug("{}.{}:{}", new Object[]{Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getLineNumber(), Msg});
        }

        if ("LOG_ERR".equals(LogLV)) {
            String logFile = MDC.get("logFileName");
            String errlogFile = Logger.getLogPath("ERROR");
            MDC.put("logFileName", errlogFile);
            logger.error(Msg);
            MDC.put("logFileName", logFile);
            Com.logFile.remove(errlogFile);
        }
    }


//    public static void comLog(org.slf4j.Logger timerlogger, String Msg, String ClassName, String MethodName, int LineNumber) {
//        timerlogger.info("{}.{}:{}", new Object[]{MethodName, LineNumber, Msg});
//    }

    public synchronized static String getLogPath(String LogName) {
        int XH = 0;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        StringBuffer logFilePath = new StringBuffer();
        logFilePath.append(LOG_FOLDER_NAME);
        logFilePath.append(File.separatorChar);
        logFilePath.append(year);
        logFilePath.append(File.separatorChar);
        logFilePath.append(String.format("%02d", month));
        logFilePath.append(File.separatorChar);
        logFilePath.append(String.format("%02d", day));

        File dir = new File(logFilePath.toString());
        if (!dir.exists()) {
            if (!dir.mkdirs())
                System.out.println("make dir " + dir.getAbsolutePath() + " fail");
        }


        StringBuffer tmplogFilePath = new StringBuffer();


        while (true) {
            tmplogFilePath.delete(0, tmplogFilePath.length());
            tmplogFilePath.append(logFilePath.toString());
            tmplogFilePath.append(File.separatorChar);
            tmplogFilePath.append(LogName);
            tmplogFilePath.append("_");
            tmplogFilePath.append(XH);
            tmplogFilePath.append(LOG_FILE_SUFFIX);
            File file = new File(tmplogFilePath.toString());

            if ((10240000 < file.length() + miss) || (Com.logFile.get(tmplogFilePath.toString()) != null)) {
                XH++;
            } else {
                break;
            }

        }
        Com.logFile.put(tmplogFilePath.toString(), Thread.currentThread().getName());

        logFilePath.append(File.separatorChar);
        logFilePath.append(LogName);

        logFilePath.append("_");
        logFilePath.append(XH);

        logFilePath.append(LOG_FILE_SUFFIX);
        return logFilePath.toString();
    }


}



