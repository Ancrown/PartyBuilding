package zhuri.com.partybuilding.util;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述:
 */

public class StringUtil {

    public static String[] getLetter() {
        return new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    }

    public static String getLetter(String index) {
        String x = "";
        String[] index1 = index.split(",");
        for (int i = 0; i < index1.length; i++) {
            int j = Integer.parseInt(index1[i]);
            x = x + getLetter()[j] + ((i == index1.length - 1) ? "" : " ");
        }
        return x;
    }

    public static String getLetter(int index) {
        return new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}[index];
    }


}
