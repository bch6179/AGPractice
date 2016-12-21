class Solution(object):
    def isSubsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        if t == None:  #Mistake "" :  "", confused s and t , "", None, None, and ""
            return False
        if s == None or s == "": 
            return True
        i, j = 0, 0
        while i < len(t) and j < len(s):
            if s[j] == t[i]:
                j+=1
                if j == len(s):
                    return True
            i+=1
        return False
    
        def isSubsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        i, j = 0, 0
        while i < len(t) and j < len(s):
            if s[j] == t[i]:
                j+=1
                if j == len(s):
                    return True
            i+=1
        return j == len(s)    #No need the two if for len(0) check 


        I think the Map and TreeSet could be simplified by Array and binarySearch. Since we scan T from beginning to the end (index itself is in increasing order), List will be sufficient. Then we can use binarySearch to replace with TreeSet ability which is a little overkill for this problem. Here is my solution.

    // Follow-up: O(N) time for pre-processing, O(Mlog?) for each S.
    // Eg-1. s="abc", t="bahbgdca"
    // idx=[a={1,7}, b={0,3}, c={6}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=3
    //  i=2 ('c'): prev=6 (return true)
    // Eg-2. s="abc", t="bahgdcb"
    // idx=[a={1}, b={0,6}, c={5}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=6
    //  i=2 ('c'): prev=? (return false)
    public boolean isSubsequence(String s, String t) {
        List<Integer>[] idx = new List[256]; // Just for clarity
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null)
                idx[t.charAt(i)] = new ArrayList<>();
            idx[t.charAt(i)].add(i);
        }
        
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i)] == null) return false; // Note: char of S does NOT exist in T causing NPE
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j < 0) j = -j - 1;
            if (j == idx[s.charAt(i)].size()) return false;
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }

    
B
 
  1 out of 1   
Home   OJ   Is Subsequence   Greedy Alg, Dynamic Programming, Binary Search plus Follow Up Analysis 
New users please read the instructions to format your code properly. Discuss is a place to post interview questions or share solutions / ask questions related to OJ problems.
Greedy Alg, Dynamic Programming, Binary Search plus Follow Up Analysis

0
L Louisliu0507 
Reputation:  0
Methods:

Greedy Algorithm
Dynamic Programming
Binary Search
Suppose string s length is m, string t length is n , n>>m

Follow up: If we have k string s, and a super long string t, which method is better:
Answer: Binary Search Way beats greedy and DP

Greedy Alg: k* O(m+n)
Binary Searc < O(n) + k* O(mlogn) --Best
DP: k * O(m*n)

Greedy Algorithm: O(m+n) Time, O(1) Space

Greedy Algorithm Solution:
public class Solution {
    public boolean isSubsequence(String s, String t) {
        int p = 0;
        int q = 0;
        while(p < s.length() && q < t.length())
        {
            if(s.charAt(p) == t.charAt(q))
            {
                p++;
                q++;
            }
            else
            {
                q++;
            }
        }
        return (p == s.length());
    }
}
Dynamic Programming: O(n) space + O(m*n) time

public class Solution {
    public boolean isSubsequence(String s, String t) {
        boolean[] mem1 = new boolean[t.length()];
        boolean[] mem2 = new boolean[t.length()];
        if(s.length() == 0) return true;
        if(t.length() == 0) return false;
        mem1[0] = (s.charAt(0) == t.charAt(0));
        for(int j = 1; j < t.length(); j++)
        {
            mem1[j] = (s.charAt(0) == t.charAt(j))?true:mem1[j-1];
        }

        for(int i = 1 ; i < s.length() ; i++)
        {
            for(int j = 1 ; j < t.length(); j++)
            {
                if(s.charAt(i) == t.charAt(j))
                    mem2[j] = mem2[j-1] || mem1[j-1];
                else
                    mem2[j] = mem2[j-1];
            }
            mem1 = mem2;
            mem2 = new boolean[t.length()];
        }   
        return mem1[t.length()-1];
    }
}
Binary Search: O(n) + O(mlogn) at most (Actually far less than mlogn)

Tips: Please refer java collection.binarysearch function doc to understand the return value meaning if you can't understand "insertion point"
public class Solution {
    public boolean isSubsequence(String s, String t) {
        HashMap<Character, List<Integer>> hash = new HashMap<>();
        for(int i = 0 ; i < t.length(); i++)
        {
            if(!hash.containsKey(t.charAt(i)))
            {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                hash.put(t.charAt(i),list);
            }
            else
            {
                hash.get(t.charAt(i)).add(i);
            }
        }
        
        int preIndex = 0;
        for(int i = 0 ; i < s.length() ; i++)
        {
            if(hash.containsKey(s.charAt(i)))
            {
                int nextIndex = Collections.binarySearch(hash.get(s.charAt(i)), preIndex);
                if(nextIndex < 0)
                {
                    int insertion_point = (-1)* (nextIndex+1);
                    if(insertion_point == hash.get(s.charAt(i)).size())
                        return false;
                    nextIndex = insertion_point;
                }
                preIndex = hash.get(s.charAt(i)).get(nextIndex)+1;
            }
            else
                return false;
        }
        return true;
    }
}

about a month ago reply quote 
1
POSTS 202
VIEWS Reply Back To Leetcode    Mark unread   Not Watching   Sort by 
The first solution is to use two pointers, which is easy to understand and posted by others:

    def isSubsequence(self, s, t):
        i, j = 0, 0
        while i < len(s) and j < len(t):
            if s[i] == t[j]: i += 1
            j += 1
        return True if i == len(s) else False
The running time is 356 ms with complexity of O(m+n), in which m is length of s and n is length of t.

For the follow-up question, however, this solution is not efficient as we need to compare s with t every time. For example, if we have k=1B, len(s) = 100, len(t)=500,000, the total complexity will be O(10^9*(100+500,000)). So the tricky is we don't want to scan t every time as it costs too much.

Here is solution 2, check comments for a brief explanation. The idea is to scan t once and save the index (as a sorted list) of each letter.

The running time is 525 ms. Although this one-time run cost of solution 2 is bigger than solution 1, if we have many s, we can save a lot of time by avoid comparing s with t every time.

    def isSubsequence(self, s, t):
        # create a list to save the index of each letter in t
        listt = [[] for _ in range(26)]
        
        for i in range(len(t)):
            listt[ord(t[i])- 97].append(i)
        # create a list to find the index of each letter of s in t    
        lists = [0 for _ in range(len(s))]
        
        if not s: return True
        if not listt[ord(s[0])-97]: return False # if first letter of s is not in t
        lists[0] = listt[ord(s[0])-97].pop(0) # min. value for first letter
                
        for i in range(1,len(s)):
            if not listt[ord(s[i])-97]: return False # if the letter is not in t
            index, value = self.helper(listt[ord(s[i])-97],lists[i-1])
            if index == -1: return False
            lists[i],listt[ord(s[i])-97]= value, listt[ord(s[i])-97][index+1:]
                    
        return lists == sorted(lists)
      # a helper function to find the index   
    def helper(self, nums, value):
        if value > nums[-1]: return (-1,-1)
        else:
            temp, i = nums[0], 0
            while value > temp and i < len(nums):
                i+=1
                temp = nums[i]
        return (i,temp)

     Trade space for time and with binary search

0
A asv325 
Reputation:  5
For follow-up. Trade space for time. Store char positions for t.
s = aabac,
t = aaabaac.
{a: [0,1,2,4,5],
b: [3],
c: [6]}
Initialize lowerBound = 0. Iterate over s, binary search the first position of the current char satisfying position >= lowerBound, update lowerBound with the found position + 1.
Of course, you may use bisect instead of implementing findFirstPos https://docs.python.org/2/library/bisect.html
Please let me know if there is any mistake. Thanks.

