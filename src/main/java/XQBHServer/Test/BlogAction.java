package XQBHServer.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BlogAction {
    //定义一个全局的记录器，通过LoggerFactory获取

    /**
     * @param args
     */


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserFileAppender.map.put(Thread.currentThread().getName(), "logs/" + Thread.currentThread().getName() + ".txt");
                    Logger logger = LoggerFactory.getLogger("XQBHServer");

                    System.out.println("1111"+UserFileAppender.map.get(Thread.currentThread().getName()));
                    for (int i = 0; i < 10; i++)
                        logger.info("yes");

                }
            }).start();

        }

        long endTime = System.currentTimeMillis();
        System.out.println("spand " + (endTime - startTime) + "ms");

    }

}