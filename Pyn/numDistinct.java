public int numDistinct(String S, String T) {  
  // Start typing your Java solution below  
  // DO NOT write main() function  
  if (S.length() == 0) {  
    return T.length() == 0 ? 1 : 0;  
  }  
  if (T.length() == 0) {  
    return 1;  
  }  
  int cnt = 0;  
  for (int i = 0; i < S.length(); i++) {  
    if (S.charAt(i) == T.charAt(0)) {  
      cnt += numDistinct(S.substring(i + 1), T.substring(1));  
    }  
  }  
  return cnt;  
}  


 copy

 
public class Solution {  
    public int numDistinct(String S, String T) {  
        int[][] cnt = new int[T.length()+1][S.length()+1];  
        for(int j=0; j<S.length(); j++) {  
            cnt[0][j] = 1;  
        }  
        for(int i=1; i<T.length(); i++) {  
            cnt[i][0] = 0;  
        }  
          
        for(int i=1; i<=T.length(); i++) {  
            for(int j=1; j<=S.length(); j++) {  
                if(T.charAt(i-1) != S.charAt(j-1)) {  
                    cnt[i][j] = cnt[i][j-1];    // The old way to match  
                } else {    // Match, it allows us to have a new way to match  
                    cnt[i][j] = cnt[i][j-1] + cnt[i-1][j-1];    // old way + new way  
                }  
            }  
        }  
          
        return cnt[T.length()][S.length()];  
    }  
}  