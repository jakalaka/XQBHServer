package XQBHServer.Test;

import java.util.Random;

public class synchronizedTest {
    static int a = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 40; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    hey();
                }
            }).start();
        }
    }

    public   synchronized static void hey() {
        a++;
        try {
            Thread.sleep((long) (Math.random()*50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
