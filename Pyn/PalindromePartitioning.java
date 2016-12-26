// Palindrome Partitioning II
// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return the minimum cuts needed for a palindrome partitioning of s.

// For example, given s = "aab",
// Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.


// 分析：

// 求出要让一个字符串满足得到的子串集合都为回文字符串的最小切割数

// (跟上一题有一点关系，上一题网上有位牛人用的方法是DP做的，但上一题我们没有用那种方法，但是这题要用DP哈，不然会TLE哦)



// 解题思路：

// 我们可以把这个问题转换成动态规划dp的问题


// 首先我们先定义几个变量，并对这几个量做一定的说明！为了方便理解，下面这些为伪码!!!
// len  = str.length();   // 字符串的长度

// int[] cuts = new int[len + 1]; //cuts数组，cuts[i] 表示 以 i 开头到len结尾的子串 要达到题意需要的最少切割数（这样子最终 cuts[0]就是我们要的结果）【初始化 cuts[i] = len - i, 因为最坏情况以 i 开头到len结尾的子串要切割数就是每个字符都切一次】

// int[][] matrix = new  int[len][len]; //设置出一个邻接矩阵数组，它所表示的意思：如matrix[i][j] = true, 表示 子串 sub(i, j) 是满足回文字符串条件的！

// 那么判断matrix[i][j] 是否满足回文字符串的条件是： 



// matrix[i+1][j-1] == true (表示sub(i+1,j-1)是满足回文字符串) && str[i] == str[j] 

// 或者 

// j - i < 2 && str[i] == str[j] （即如果j - i == 1时，为两个字符相等，如果j - i == 0时，为同一个字符） 



// 这两种情况，我们都将matrix[i][j]设置成true，方便下一次的DP，并且我们可以求出最小的切割次数

// cuts[i] = min{cuts[i], cuts[j+1] + 1};  状态转移方程式

// 这样最后cuts[0] - 1便为 字符串str的最小的切割数！！！！


 
public class test {  
    public int minCut(String s) {  
        int min = 0;  
        int len = s.length();  
        boolean[][] matrix = new boolean[len][len];  
        int cuts[] = new int[len+1];  
          
        if (s == null || s.length() == 0)  
            return min;  
        //初始化cuts里面的值为最坏情况的值  
        for (int i=0; i<len; ++i){  
            cuts[i] = len - i;  
        }  
        //dp过程  
        for (int i=len-1; i>=0; --i){  
            for (int j=i; j<len; ++j){  
                if ((s.charAt(i) == s.charAt(j) && (j-i<2))  
                        || (s.charAt(i) == s.charAt(j) && matrix[i+1][j-1]))  
                {  
                    matrix[i][j] = true;  
                    cuts[i] = getMinValue(cuts[i], cuts[j+1]+1);  
                }  
            }  
        }  
        min = cuts[0];  
        return min-1;  
    }  
      
    public int getMinValue(int a, int b){  
        return a > b ? b : a;  
    }  
      
    public static void main(String[] args) {  
        System.out.println(new test().minCut("ababbbabbaba"));  
    }  
}  

// Palindrome Partitioning  I   DFS

// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return all possible palindrome partitioning of s.

// For example, given s = "aab",
// Return

//   [
//     ["aa","b"],
//     ["a","a","b"]
//   ]

// 分析：

// 题意给我们一个字符串，求出所有这个字符串的子串，并且子串都为回文字符串的情况，输出它的集合结果



// 解题思路：（跟DFS深度遍历有点像!）

// 字符串Str = "aab";

// 分析了题目的数据之后，我们知道它的结果，可能是 a, a, b 或者  aa, b 这样的情况！

// 也就是说，我们需要去考虑 i = 1,  2 ....  n 的情况，比如

// Str = "aaa"

// 结果集


// [[a, a, a], [a, aa], [aa, a], [aaa]]

// 根据这样的情况，我们用类似于DFS的算法

// str1 = str.substr(0, i); 取出前面下标从 0 开始到 i 结束的子串，判断str1是否满足回文字符串的要求，

// 1. 满足：这样子，有可能成为一种分割的情况，所以我们new出一个list集合，把str1放入到list中，然后我们求出str剩余的子串  str2 = str.substr(i); 取出前面下标从 i 开始到整个字符串结尾的子串， 然后将str2 作为新的字符串，同时把list集合也传入到函数中，递归调用。递归的结束条件就是到传入的这个字符串的长度为0（这样就意味着已经到了字符串的末尾了），此时证明这种情况下得到的list集合是满足条件的，我们把这个list集合 加入到 结果集合result中。

// 2. 不满足的话，继续 i++， 直到 i == str.length();

// 3. 全部结束之后，返回 最终的结果集合 result



// AC代码：


// [java] view plaincopyprint?
package copylist;  
  
import java.util.ArrayList;  
  
  
public class Solution {  
    /* 
     * 供外部调用 
     * */  
    public ArrayList<ArrayList<String>> partition(String s) {  
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();  
        ArrayList<String> list = new ArrayList<String>();  
          
        if (s == null || s.length() == 0)  
            return result;  
          
        calResult(result,list,s);  
        return result;  
    }  
    /** 
     * 判断一个字符串是否是回文字符串 
     * i -> str[0] 
     * j -> str[len-1] 
     * i:往后移 
     * j:往前移 
     * @param str 
     * @return  
     */  
    private boolean isPalindrome(String str){  
          
        int i = 0;  
        int j = str.length() - 1;  
        while (i < j){  
            if (str.charAt(i) != str.charAt(j)){  
                return false;  
            }  
            i++;  
            j--;  
        }  
        return true;  
    }  
      
    /** 
     *  
     * @param result : 最终要的结果集 ArrayList<ArrayList<String>> 
     * @param list : 当前已经加入的集合 ArrayList<String> 
     * @param str : 当前要处理的字符串 
     */  
    private void calResult(ArrayList<ArrayList<String>> result  
            , ArrayList<String> list  
            , String str)  
    {  
        //当处理到传入的字符串长度等于0,则这个集合list满足条件，加入到结果集中  
        if (str.length() == 0)  
            result.add(new ArrayList<String>(list));  
        int len = str.length();  
        //递归调用  
        //字符串由前往后，先判断str.substring(0, i)是否是回文字符串  
        //如果是的话，继续调用函数calResult，把str.substring(i)字符串传入做处理  
        for (int i=1; i<=len; ++i){  
            String subStr = str.substring(0, i);  
            if (isPalindrome(subStr)){  
                list.add(subStr);  
                String restSubStr = str.substring(i);  
                calResult(result,list,restSubStr);  
                list.remove(list.size()-1);  
            }  
        }  
    }  
      
    public static void main(String[] args) {  
        System.out.println(new Solution().partition("aabaa"));  
    }  
}  

