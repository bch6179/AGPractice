package notTested;

import java.util.*;


class countAndSay {

    public static void main() {
        String result = countAndSay(2);
        System.out.println(result);

     }
 public String countAndSayBad(int n) {
  // Write your code here
            String result = "";
            String prev = "1";
            while(n > 0) {
                int count = 1;
                StringBuilder newOne = new StringBuilder();

                for (int i = 0; i < prev.length(); i++) {
                    while(i+1 < prev.length() && prev.charAt(i) == prev.charAt(i+1)) {
                        count++;
                        i++;
                    }

                    newOne.append(String.valueOf(count)+String.valueOf(prev.charAt(i)));
                    prev =  newOne.toString();
                }
                n--;
            }

            return prev;

 }
     // public String countAndSay2(int n) {
     //        // Write your code here
     //        StringBuilder result = "";
     //        String prev = "1";

     //        while(n > 0) {
     //            int count = 1;
     //            for (int i = 0,count = 1; i < prev.length() - 1; i++) {
     //                if (prev.charAt(i) == prev.charAt(i+1))
     //                    count++;
     //                else {

     //                    result.append(count);
     //                    result.append(prev.charAt())

     //                }
     //            }

     //            result.append(count);
     //            result.append(prev.charAt())
     //            n--;
     //        }

     //        return result.toString();
     //    }
}