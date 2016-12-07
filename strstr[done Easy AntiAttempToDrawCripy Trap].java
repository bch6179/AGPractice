[Note]
=====
# Forgot check string array overflow: check index for a while
# "" vs " "
# check length == 0 

class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        if (source == null || target == null) return -1;

        if (target.length()==0) return  0;

      for (int i = 0; i < source.length(); i++) {
          int j = 0; 
           
          while ((i + j) < source.length() && j < target.length() && (source.charAt(i+j) ==  target.charAt(j))) {
              j++;
                 if (j == target.length()) {
              return i;
          }
          }
         
      }
       return -1;
    }
}
