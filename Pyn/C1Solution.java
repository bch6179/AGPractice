class C1Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        
        if (source == null ||  target == null  ) // "" "", rtunr 0 instead of -1; source.length() == 0
            return -1;
 
         
        for(int i = 0; i < source.length() - target.length() + 1; i++) {
            int j = 0;
            
            for(; j < target.length(); j++) {
                if (target.charAt(j) != source.charAt(i+j)) {
                    break;
                }
            }
            
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }
}