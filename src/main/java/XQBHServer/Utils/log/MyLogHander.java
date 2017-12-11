package XQBHServer.Utils.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class MyLogHander extends Formatter {
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    public String format(LogRecord record) {

        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis()))).append("-");
        builder.append("[").append(record.getParameters()[0]).append(".");
        builder.append(record.getParameters()[1]).append(":"+record.getParameters()[2]+"]-");
        builder.append("-");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }
}