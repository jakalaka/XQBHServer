package XQBHServer.Utils.WxpayHelper;

import java.util.Random;

public class RandomString {
    public static String generate(int length){
        Random random=new Random();
        String string = "";
        for (int i=0;i<10;i++)
            string+=random.nextInt(10);
        return string;
    }
}
