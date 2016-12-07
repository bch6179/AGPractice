 这道题主要是考虑一下最后是不是空格，方法是倒着找不是空格的字符并计数，如果遇到空格且计数不是0，说明最后一个单词已经被计数了，所以可以返回了。



代码如下：

复制代码
 1     public int lengthOfLastWord(String s) {
 2          if (s == null || s.length() == 0)
 3             return 0;
 4
 5         int len = s.length();
 6         int count = 0;
 7         for (int i = len - 1; i >= 0; i--) {
 8             if (s.charAt(i) != ' ') {
 9                 count++;
10             }
11             if(s.charAt(i)==' '&&count != 0){
12                 return count;
13             }
14         }
15         return count;
16     }
复制代码
 当然这道题也能用投机取巧的方法，用split函数把字符串按照空格分隔好，返回最后那个就行。。。

代码如下：

复制代码
1     public int lengthOfLastWord(String s) {
2         String[] a = s.split(" ");
3         if(a == null || a.length == 0)
4             return 0;
5
6         return a[a.length-1].length();
7     }

    public int lengthOfLastWord_Mybad(String s) {
        // Write your code here
        int end = s.length() - 1;

        int count = 0;
        boolean skip = false;

        for (int i = end; i >= 0; i--) {
            int temp = i;

            if (skip) break;

            while(i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            skip = (temp != i);

            if (i < 0) break;

             count++;

        }
        return count;
    }