package utils;

import java.sql.Time;

public class FormatTime {
    public String formatTimeSQL(Time t) {
        String a = t.toString();
        String b = "";
        int count = 0, k = 0;
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == ':' && count < 1) {
                b = b.concat(a.substring(k, i));
                k = i;
                count++;
            }
            else if(a.charAt(i) == ':' && count == 1) {
                b = b.concat(a.substring(k, i));
                break;
            }
        }

        return b;
    }
}

