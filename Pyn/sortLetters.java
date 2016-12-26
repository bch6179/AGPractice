public class Solution {
 2     /**
 3      *@param chars: The letter array you should sort by Case
 4      *@return: void
 5      */
 6     public void sortLetters(char[] chars) {
 7         //write your code here
 8         if (chars==null || chars.length==0) return;
 9         int l=0, r=chars.length-1;
10         while (true) {
11             while (l<r && isLower(chars, l)) {
12                 l++;
13             }
14             while (l<r && isUpper(chars, r)) {
15                 r--;
16             }
17             if (l == r) break;
18             swap(chars, l, r);
19         }
20     }
21
22     public boolean isLower(char[] chars, int index) {
23         if (chars[index]>='a' && chars[index]<='z') return true;
24         else return false;
25     }
26
27     public boolean isUpper(char[] chars, int index) {
28         if (chars[index]>='A' && chars[index]<='Z') return true;
29         else return false;
30     }
31
32     public void swap(char[] chars, int l, int r) {
33         char temp = chars[l];
34         chars[l] = chars[r];
35         chars[r] = temp;
36     }
37 }
复制代码
    public void sortLetters(char[] chars) {
        //write your code here

        int p = 0;
        int q = chars.length-1;
        int i = 0;
        while ( p < q) {
            if ( chars[p] >= 'a' && chars[p] <= 'z' ) {
                p++;
             }
            else {
                 char temp = chars[q];
                chars[q] = chars[p];
                chars[p] = temp;
                 q--;
            }
        }
        }
         public void sortLetters(char[] chars) {
        //write your code here

        int p = 0;
        int q = chars.length-1;
        int i = 0;
        while ( p < q) {
            while (p < q && chars[p] >= 'a' && chars[p] <= 'z' ) {
                p++;
             }
           while (p < q && chars[q] >= 'A' && chars[q] <= 'Z' ) {
                q--;
             }
             if (p < q) {
                 char temp = chars[q];
                chars[q] = chars[p];
                chars[p] = temp;
                 q--;
                 p++;
            }
        }
    }
    }