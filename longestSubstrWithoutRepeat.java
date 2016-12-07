public class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        if (s.length() <= 1) return s.length();
        
        int[] found = new int[256];
        Arrays.fill(found, -1);
        
        int start = 0, cur = 0;
        int result = 0;
        
        
        while( cur < s.length()) {
            
            if (found[s.charAt(cur)] >= start) {
                result = Math.max(cur - start, result);
                start =  found[s.charAt(cur)] + 1;
            }
            found[s.charAt(cur)] = cur;
            cur++;
        }
         result = Math.max(cur - start, result);

        return result;
    }

      //Hashp working
    //   if (s.length() <= 1) return s.length();
         
    //     HashMap<Character, Integer> locTable = new  HashMap<Character, Integer>();
        
    //     int start = 0, cur=0;
    //     int result = 0;
 
    //     while(cur < s.length()) {
    //         char temp = s.charAt(cur); //bad Char
    //         if (locTable.containsKey(temp) && locTable.get(temp) >= start) {
    //             //dup  //locTable.get(temp) willnull pointer exception
    //             result = Math.max(result, cur - start);
    //             // int oldIndex = ;
    //             // //reset M FOR < start
    //             // for( ; start < oldIndex; start++ ) {
    //             //     if (locTable.containsKey(temp))
    //             //         locTable.remove(temp);
    //             // }
    //              start = locTable.get(temp) + 1;
    //         }
    //         //bad else, cur will not increase for aa  : else { //uni
    //          locTable.put(temp, cur);
    //           cur++;
             
    //     }
    //     result = Math.max(result, cur - start);

    //     return result;
    // }
    //     if (s == null || s.length() == 0) {
    //         return 0;
    //     }
    //     HashMap<char, Integer> M = new  HashMap<char, Integer>();
        
    //     int start = -1, cur=0;
    //     int result = 0;
    //      M.set(s.charAt(cur), cur);

    //     while(cur < s.legnth()) {
    //         int oldIdx = M.get(s.charAt(cur));
    //         if (oldIdx > start) { //dup
    //             M[cur] = true;
    //             //reset M FOR < start
    //             for(int j = start; j < oldIdx; j++ ) {
    //                 M[j] = false;
    //             }
    //             start = oldIdx + 1;
    //         }
    //         else { //uni
    //             result = Math.max(result, cur - start);

    //             M.set(s.charAt(cur), cur);
    //             cur++;
    //         }
    //     }
    //     return result;
    // }
}