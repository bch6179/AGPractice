leetcode[161] One Edit Distance

判断两个字符串的编辑距离是不是1.

关于编辑距离可以参见之前有一题leetcode[72] Edit Distance

思路：

如果字符串相差2个以及以上长度，那么肯定不止1，直接false

如果字符串长度相等，那么判断对应位置不同的字符数是不是1即可。

如果字符串长度相差1，那么肯定是要在长的那个串删掉一个，所以两个字符串一起加加，一旦遇到一个不同，
那么剩下的子串就要是一样，否则就是不止一个不同，false。

复制代码
    bool isOneDifL(string s, string t)
    {
        for (int i = 0; i < s.size(); i++)
        {
            if (s[i] == t[i])
                continue;
            return s.substr(i) == t.substr(i+1);
        }
        return true; // 说明前面的都相等只有最后一个不匹配
    }
    bool isSameLen(string s, string t)
    {
        int cnt = 0;
        for (int i = 0; i < s.size(); i++)
        {
            if (s[i] != t[i])
                cnt++;
        }
        return cnt == 1;
    }
    bool isOneEditDistance(string s, string t)
    {
        if (abs(s.size() - t.size()) > 1) return false;
        if (s.size() == t.size()) return isSameLen(s, t);
        else if (s.size() < t.size()) return isOneDifL(s, t);
        else return isOneDifL(t, s);
    }
复制代码
还可以参见一个函数搞定的，巧妙的合在一起

复制代码
    bool isOneEditDistance2(string s, string t){
        if (s.length() > t.length()) {
            swap(s,t);
        }
        if (t.length() - s.length() > 1) {
            return false;
        }
        bool have = false;
        for (int i = 0, j = 0; i < s.length(); ++i,++j){
            if (s[i] != t[j]) {
                if (have) {
                    return false;
                }
                have = true;
                if (s.length() < t.length()) {
                    --i;
                }
            }
        }
        return (have) || (s.length() < t.length());
    }
复制代码


分类: LeetCode
好文要顶 关注我 收藏该文
higerzhang
关注 - 12
粉丝 - 14
+加关注
0 0
(请您对文章做出评价)
« 上一篇：leetcode[159] Longest Substring with At Most Two Distinct Characters
» 下一篇：leetcode[163] Missing Ranges
posted on 2014-12-26 00:01 higerzhang 阅读(1018) 评论(1) 编辑 收藏

评论:
#1楼 2015-10-10 03:13 | xiaocq203
public boolean isOneEditDistance(String s, String t) {
String longStr = s.length() > t.length() ? s : t;
String shortStr = longStr == s ? t : s;

if (longStr.length() - shortStr.length() > 1) {
return false;
}

for (int i = 0; i < shortStr.length(); i++) {
if (longStr.charAt(i) != shortStr.charAt(i)) {
if (longStr.length() != shortStr.length()) {
return longStr.substring(i + 1).equals(shortStr.substring(i));
} else {
return longStr.substring(i + 1).equals(shortStr.substring(i + 1));
}
}
}

return !s.equals(t);
}
