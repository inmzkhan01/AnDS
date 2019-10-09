package misc;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/reorder-data-in-log-files/solution/

public class Reorder_Data_in_Log_Files {

    static String[] reOrder(String[] logs) {



        /*int n = logs.length;

        int d = n - 1;
        int l = n - 2;

        while(d>=0) {
            String log = logs[d];
            char ch = log.substring(log.indexOf(" ")+1).charAt(0);
            boolean isDigit = (ch >=48 && ch<=57);
            if(!isDigit) {
                break;
            }
            d--;
        }

        l = d - 1;

        while (l>=0 && d>=0) {

            while(d>=0) {
                String log = logs[d];
                char ch = log.substring(log.indexOf(" ")+1).charAt(0);
                boolean isDigit = (ch >=48 && ch<=57);
                if(!isDigit) {
                    break;
                }
                d--;
            }

            while(l>=0) {
                String digitLog = logs[l];
                char ch = digitLog.substring(digitLog.indexOf(" ")+1).charAt(0);
                boolean isDigit = (ch >=48 && ch<=57);
                if(isDigit) {
                    logs[l] = logs[d];
                    logs[d] = digitLog;
                    d--;
                    break;
                }
                l--;
            }
        }

        Arrays.sort(logs, 0, d+1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(o1.indexOf(" ")+1).compareTo(o2.substring(o2.indexOf(" ")+1));
            }
        });*/





        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });

        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art zero can","dig2 3 6","let2 own kit dig","let3 art zero an"};
        System.out.println(Arrays.toString(reOrder(logs)));
    }


}
