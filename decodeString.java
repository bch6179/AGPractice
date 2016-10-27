public class Solution {
    public String decodeString(String str) {
        if (str.length <=1) rturn str;
        return helper(0, str.length()-1, str);
    }
    
    String herper(int s, int e, int str) {
        int i= s;
        int count = 0;
        int s1 = 0, e1 = 0;
         while(i <= e) {
            if (str.charAt(i) == '[') {
                if (count == 0) {
                    s1 = i+1;
    
                    sb.add(str.subString(s, s1-1));
                }
                count++;
                
            }
            else if (str.charAt(i) == ']') {
                count--;
                if (count == 0) {
                    e1 = i;
                  
                    String t = helper(s1,e1, str);
                    for (int k = 0; k < str.charAt(s1-1);k++) {
                        sb.add(t);
                    }
                }
            }
            i++;
        
         }
         if (e1 < e) sb.add(str.substring(e1+1,e+1);
         return sb.toString();
    }
}