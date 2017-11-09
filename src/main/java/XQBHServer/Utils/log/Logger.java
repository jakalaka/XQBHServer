package XQBHServer.Utils.log;

import XQBHServer.ServerTran.TranObj;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.*;

public class Logger {
    private static final String LOG_FOLDER_NAME = "Log";

    private static final String LOG_FILE_SUFFIX = ".log";

    private static final int miss = 128;
    static int XH = 0;

    private static final MyLogHander myLogHander = new MyLogHander();
    public static FileHandler fileHandler = null;
    public static String path = "";
    private static ConsoleHandler consoleHandler;

    static {

        consoleHandler = new ConsoleHandler();


    }

    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");

    public static void log(TranObj tranObj, String LogLV, String Msg) {

        StringBuilder builder = new StringBuilder();
        builder.append(df.format(new Date())).append("-");
        builder.append("[").append(Thread.currentThread().getStackTrace()[2].getClassName()).append(".");
        builder.append(Thread.currentThread().getStackTrace()[2].getMethodName()).append(":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]-");
        builder.append("[").append(LogLV).append("]-");
        builder.append(Msg);
        builder.append("\n");


        if ("SYS".equals(tranObj.flLogLV) && "LOG_SYS".equals(LogLV)) {
            tranObj.filePrinter.append(builder);
        } else if ("ERR".equals(tranObj.flLogLV) && ("LOG_SYS".equals(LogLV) || "LOG_ERR".equals(LogLV))) {
            tranObj.filePrinter.append(builder);
        } else if ("IO".equals(tranObj.flLogLV) && ("LOG_SYS".equals(LogLV) || "LOG_ERR".equals(LogLV) || "LOG_IO".equals(LogLV))) {
            tranObj.filePrinter.append(builder);
        } else if ("DEBUG".equals(tranObj.flLogLV) && ("LOG_SYS".equals(LogLV) || "LOG_ERR".equals(LogLV) || "LOG_IO".equals(LogLV) || "LOG_DEBUG".equals(LogLV))) {
            tranObj.filePrinter.append(builder);
        }
        if ("LOG_SYS".equals(LogLV)||"LOG_ERR".equals(LogLV))
            sysLog(Msg,Thread.currentThread().getStackTrace()[2].getClassName(),Thread.currentThread().getStackTrace()[2].getMethodName(),Thread.currentThread().getStackTrace()[2].getLineNumber());



    }

    public static void writte(TranObj tranObj) {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        StringBuffer logFilePath = new StringBuffer();
        logFilePath.append(LOG_FOLDER_NAME);
        logFilePath.append(File.separatorChar);
        logFilePath.append(year);
        logFilePath.append(File.separatorChar);
        logFilePath.append(month);
        logFilePath.append(File.separatorChar);
        logFilePath.append(day);
        logFilePath.append(File.separatorChar);
        logFilePath.append(tranObj.getHead("HTJYM_"));

        File dir = new File(logFilePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuffer tmplogFilePath = new StringBuffer();
        int XH = 1;
        while (true) {
            tmplogFilePath.delete(0, tmplogFilePath.length());
            tmplogFilePath.append(logFilePath.toString());
            tmplogFilePath.append(File.separatorChar);
            tmplogFilePath.append(tranObj.getHead("HTJYM_"));
            tmplogFilePath.append("_");
            tmplogFilePath.append(XH);
            tmplogFilePath.append(LOG_FILE_SUFFIX);

            File file = new File(tmplogFilePath.toString());
            if (file.exists() && file.isFile()) {
                if (10240000 < file.length() + tranObj.filePrinter.toString().length() + miss) {
                    XH++;
                } else
                    break;
            } else {
                break;
            }
        }

        try {
            FileWriter fw=new FileWriter(tmplogFilePath.toString(),true);
            fw.write(tranObj.filePrinter.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }


    public static void sysLog(String Msg) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(df.format(new Date())).append("-");
        stringBuilder.append("[").append(Thread.currentThread().getStackTrace()[2].getClassName()).append(".");
        stringBuilder.append(Thread.currentThread().getStackTrace()[2].getMethodName()).append(":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]-");
        stringBuilder.append(Msg);
        stringBuilder.append("\n");
        try {
            FileWriter fw=new FileWriter(getLogPath(stringBuilder.toString()),true);
            fw.write(stringBuilder.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder.toString());

    }
    public static void sysLog(String Msg,String ClassName,String MethodName,int LineNumber) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(df.format(new Date())).append("-");
        stringBuilder.append("[").append(ClassName).append(".");
        stringBuilder.append(MethodName).append(":" + LineNumber+ "]-");
        stringBuilder.append(Msg);
        stringBuilder.append("\n");
        try {
            FileWriter fw=new FileWriter(getLogPath(stringBuilder.toString()),true);
            fw.write(stringBuilder.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getLogPath(String Msg) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        StringBuffer logFilePath = new StringBuffer();
        logFilePath.append(LOG_FOLDER_NAME);
        logFilePath.append(File.separatorChar);
        logFilePath.append(year);
        logFilePath.append(File.separatorChar);
        logFilePath.append(month);
        logFilePath.append(File.separatorChar);
        logFilePath.append(day);

        File dir = new File(logFilePath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuffer tmplogFilePath = new StringBuffer();

        while (true) {
            tmplogFilePath.delete(0, tmplogFilePath.length());
            tmplogFilePath.append(logFilePath.toString());
            tmplogFilePath.append(File.separatorChar);
            tmplogFilePath.append("SYSRun");
            tmplogFilePath.append("_");
            tmplogFilePath.append(XH);
            tmplogFilePath.append(LOG_FILE_SUFFIX);

            File file = new File(tmplogFilePath.toString());
            if (file.exists() && file.isFile()) {
                if (10240000 < file.length() + Msg.length() + miss) {
                    XH++;

                } else
                    break;
            } else {
                break;
            }
        }
        logFilePath.append(File.separatorChar);
        logFilePath.append("SYSRun");
        logFilePath.append("_");
        logFilePath.append(XH);
        logFilePath.append(LOG_FILE_SUFFIX);
        return logFilePath.toString();
    }

}
