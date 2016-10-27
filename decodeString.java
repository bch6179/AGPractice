LEETCODE 394. DECODE STRING
http://massivealgorithms.blogspot.com/2016/09/leetcode-394-decode-string.html
https://all4win78.wordpress.com/2016/09/07/leetcode-394-decode-string/
LC address: Decode String

Given an encoded string, return it’s decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note thatk is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won’t be input like 3a or2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
Analysis:

这道题考察两点，首先是看到这种括号配对的题目，首先要想到使用stack来帮助我们进行数据暂存和读取。我这里用了两个stack，一个用来放字符串，一个用来放重复的次数。每次读取到'[‘的时候，把当前的字符串push进入stack，同时把在'[‘之前的重复的次数也push进入另一个stack；每次读取到’]’的时候，pop得到stack顶层的字符串和重复次数， 把当前的字符串append若干次到pop出的字符串后面，append的次数由重复次数决定。

考察的另一点是实现方面的细节，对于java来说，String这个class在进行append等操作的时候并不是在原来String上操作，而是新建一个String，进行操作，再替换原来的String。所以每次append其实都是new String()，是很不高效的。一般稍有经验的话，会选择用StringBuilder这个class来代替，StringBuilder在进行append等操作的时候就是在原数据基础上修改，在这个情况下优于String。

当然，还需要注意一点就是，重复次数可以是多位数，可能存在”12[a]”这样的，所以处理数字的时候需要注意考虑这个case。

Solution:
 
public class Solution {
    public String decodeString(String s) {
        if (s == null) {
            return null;
        }
         
        Stack<StringBuilder> sbStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int repeat = 0;
         
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                sbStack.push(sb);
                intStack.push(repeat);
                sb = new StringBuilder();
                repeat = 0;
            } else if (c == ']') {
                StringBuilder temp = sb;
                sb = sbStack.pop();
                repeat = intStack.pop();
                while (repeat > 0) {
                    sb.append(temp);
                    repeat -= 1;
                }
            } else if (c >= '0' && c <= '9') {
                repeat *= 10;
                repeat += c - '0';
            } else {
                sb.append(c);
            }
        }
         
        return sb.toString();
    }
}
Solution code can al


X.DFS
http://blog.csdn.net/qq508618087/article/details/52439114
X.DFS
思路: 一个DFS的题目, 给定的字符串可能会有嵌套很多层, 在每一层我们只要在碰到正常的字符就保存到当前层的结果中, 如果碰到数字就另外保存起来作为倍数, 碰到'[' 就进入下层递归, 碰到']' 就将当前层结果返回, 这样在返回给上层之后就可以用倍数加入到上层结果字符串中. 最终当所有层都完成之后就可以得到结果. 在不同层次的递归中, 我们可以维护一个共同的位置索引, 这样在下层递归完成之后上层可以知道已经运算到哪里了.
    string DFS(string s, int &k)  
    {  
        string ans;  
        int cnt = 0;  
        while(k < s.size())  
        {  
            if(isdigit(s[k])) cnt = cnt*10 + (s[k++]-'0');  
            else if(s[k]=='[')  
            {  
                string tem = DFS(s, ++k);  
                for(int i = 0; i < cnt; i++) ans += tem;  
                cnt = 0;  
            }  
            else if(s[k]==']')  
            {  
                k++;  
                return ans;  
            }  
            else ans += s[k++];  
        }  
        return ans;  
    }  
      
    string decodeString(string s) {  
        int k = 0;  
        return DFS(s, k);  
    }  
X. Iterative


recursive

}
http://www.cnblogs.com/dongling/p/5843795.html
    public String decodeString(String s) {
        if(s==null||s.length()==0)
            return s;
        char ch;
        int index=0;
        int repeat=0;
        StringBuilder head=new StringBuilder(""),body=new StringBuilder("");
        while(index<s.length()&&!Character.isDigit(ch=s.charAt(index))){
            head.append(ch);
            index++;
        }
        if(index<s.length()){
            //indicating that next character is Digit
            while(index<s.length()&&Character.isDigit(ch=s.charAt(index))){
                repeat=repeat*10+ch-'0';
                index++;
            }//got the leading num
            //now index is pointing to '[';
            
            //next, to get the index of ']';
            int rightBracket=index+1;
            int leftBracketNum=1;
            while(leftBracketNum>0){
                ch=s.charAt(rightBracket);
                if(ch==']'){
                    leftBracketNum--;
                }
                else if(ch=='['){
                    leftBracketNum++;
                }
                else{
                    
                }
                rightBracket++;
            }
            rightBracket--;//now rightBracket is pointing to the right position of the ']';
            String res1=decodeString(s.substring(index+1,rightBracket));
            String tail=decodeString(s.substring(rightBracket+1));
            
            for(int i=1;i<=repeat;i++){
                body.append(res1);
            }
            body.append(tail);
        }
        
        return head.toString()+body.toString();
    }
