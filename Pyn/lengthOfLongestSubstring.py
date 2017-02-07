#[Note]
#=====
#can adjust left by + 1 while init with 0
#s[i] in found
#for i in range(len(s))

/*Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) <= 1:
            return len(s)
            
        left = 0
        ans = 0 
        found = {}
        
        for i in range(len(s)):
            if s[i] in found and found[s[i]] >= left:
                left = found[s[i]]+1
            else: ans = max(i-left+1, ans)
            found[s[i]] = i
        
        return ans