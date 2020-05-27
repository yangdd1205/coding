package thinking.in.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} &#x793a;&#x4f8b;
 */
public class MessageFormatDemo {

    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        String pattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(pattern);


        String result = messageFormat.format(new Object[]{planet, new Date(), event});


        System.out.println(result);

        // 重置 MessageFormatPattern
        String messagePattern = "This is a text :{0}，{1}，{2}";
        messageFormat.applyPattern(messagePattern);
        result = messageFormat.format(new Object[]{"hello,world", "666"});
        System.out.println(result);


        // 重置 Locale
        messageFormat.setLocale(Locale.ENGLISH);
        messagePattern = pattern;
        messageFormat.applyPattern(messagePattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);


        // 重置 Format
        // 根据参数索引来设置，Pattern
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }

}
