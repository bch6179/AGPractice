[Note]
=====
  
Good answer:
         Go through the string from beginning to end.  If the first character is a  negative sign, remember this fact.  Keep a running total, which starts at 0.  Each time  you reach a new digit, multiply the total by 10 and add the new digit.  When you  reach the end, return the current total, or, if there was a negative sign, the inverse of  the number.

 [Code]
  =====
       public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {

        if(str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
            
        int sign = 1;
        int index = 0;
    
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        long num = 0;
        for (; index < str.length(); index++) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9')
                break;
            num = num * 10 + (str.charAt(index) - '0');
            if (num > Integer.   ) {
                break;
            }
        }   
        if (num * sign >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (num * sign <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)num * sign;
    }