package notTested;

public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        // write your code here
        boolean negFlag = false;
        int num = 0;


        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);

            if (!Character.isDigit(x)) {
                if ((x == '-') || (x == '+')) {
                    if (x == '-') {
                        negFlag = true;
                    }
                    continue;
                }
                else break;
            }

            if (num > Integer.MAX_VALUE / 10 ||
                num == Integer.MAX_VALUE / 10 &&
                num > Integer.MAX_VALUE % 10) {
                return negFlag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            num = num * 10 + (x - '0');
        }

        return negFlag ? -num : num;

    }
}

public static final int MAX_DIGITS = 10;
String IntegerToString(int num) {

    char[] temp = new char(MAX_DIGITS + 1);
    int i = 0;
    boolean sign = false;
    if (num < 0)  {
        sign = true;
        num = -num;
    }
    do{
        temp[i++] = (char) (num % 10 + '0');
        num /= 10;
    }while(num = 0);

    StringBuilder b = new StringBuilder();

    if (sign) b.append('-');

    while(i > 0) {
        b.append(temp[--i]);
    }

    return b.toString();
}