1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
To check if a window is valid, we use a map to store (char, count) for chars in t. And use counter for the number of chars of t to be found in s. The key part is m[s[end]]--;. We decrease count for each char in s. If it does not exist in t, the count will be negativ

The code of solving this problem is below. It might be the shortest among all solutions provided in Discuss.

string minWindow(string s, string t) {
        vector<int> map(128,0);
        for(auto c: t) map[c]++;
        int counter=t.size(), begin=0, end=0, d=INT_MAX, head=0;
        while(end<s.size()){
            if(map[s[end++]]-->0) counter--; //in t
            while(counter==0){ //valid
                if(end-begin<d)  d=end-(head=begin);
                if(map[s[begin++]]++==0) counter++;  //make it invalid
            }  
        }
        return d==INT_MAX? "":s.substr(head, d);
    }
Here comes the template.

For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions. A general way is to use a hashmap assisted with two pointers. The template is given below.

int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { /* initialize the hash map here */ }

        while(end<s.size()){

            if(map[s[end++]]-- ?){  /* modify counter here */ }

            while(/* counter condition */){ 
                 
                 /* update d here if finding minimum*/

                //increase begin to make it invalid/valid again
                
                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
            }  

            /* update d here if finding maximum*/
        }
        return d;
  }
One thing needs to be mentioned is that when asked to find maximum substring, we should update maximum after the inner while loop to guarantee that the substring is valid. On the other hand, when asked to find minimum substring, we should update minimum inside the inner while loop.

The code of solving Longest Substring with At Most Two Distinct Characters is below:

int lengthOfLongestSubstringTwoDistinct(string s) {
        vector<int> map(128, 0);
        int counter=0, begin=0, end=0, d=0; 
        while(end<s.size()){
            if(map[s[end++]]++==0) counter++;
            while(counter>2) if(map[s[begin++]]--==1) counter--;
            d=max(d, end-begin);
        }
        return d;
    }
The code of solving Longest Substring Without Repeating Characters is below:

Update 01.04.2016, thanks @weiyi3 for advise.

int lengthOfLongestSubstring(string s) {
        vector<int> map(128,0);
        int counter=0, begin=0, end=0, d=0; 
        while(end<s.size()){
            if(map[s[end++]]++>0) counter++; 
            while(counter>0) if(map[s[begin++]]-->1) counter--;
            d=max(d, end-begin); //while valid, update d
        }
        return d;
    }
    

class Solution(object):
    def minWindow(self, s, t):
        if not t: return ""
        map = [0 for _ in range(128)]
        for i in t: map[ord(i)] += 1
        begin = 0
        end = 0
        minbegin = 0
        minlength = len(s) + 2
        count = len(t)
        while end < len(s):
            if map[ord(s[end])] > 0: count -= 1
            map[ord(s[end])] -= 1
            end += 1
            while count == 0:
                if end - begin < minlength:
                    minbegin = begin
                    minlength = end - begin
                map[ord(s[begin])] += 1
                if map[ord(s[begin])] > 0:
                    count += 1
                begin += 1
        if minlength > len(s): return ""
        return s[minbegin: minbegin + minlength]

class Solution(object):
    def min_window(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        # Struggled with this problem for a long while.
        # Idea: Two pointers: moving end forward to find a valid window,
        #                     moving start forward to find a smaller window
        #                     counter and hash_map to determine if the window is valid or not

        # Count the frequencies for chars in t
        hash_map = dict()
        for c in t:
            if c in hash_map:
                hash_map[c] += 1
            else:
                hash_map[c] = 1

        start, end = 0, 0

        # If the minimal length doesn't change, it means there's no valid window
        min_window_length = len(s) + 1

        # Start point of the minimal window
        min_window_start = 0

        # Works as a counter of how many chars still need to be included in a window
        num_of_chars_to_be_included = len(t)

        while end < len(s):
            # If the current char is desired
            if s[end] in hash_map:
                # Then we decreased the counter, if this char is a "must-have" now, in a sense of critical value
                if hash_map[s[end]] > 0:
                    num_of_chars_to_be_included -= 1
                # And we decrease the hash_map value
                hash_map[s[end]] -= 1

            # If the current window has all the desired chars
            while num_of_chars_to_be_included == 0:
                # See if this window is smaller
                if end - start + 1 < min_window_length:
                    min_window_length = end - start + 1
                    min_window_start = start

                # if s[start] is desired, we need to update the hash_map value and the counter
                if s[start] in hash_map:
                    hash_map[s[start]] += 1
                    # Still, update the counter only if the current char is "critical"
                    if hash_map[s[start]] > 0:
                        num_of_chars_to_be_included += 1

                # Move start forward to find a smaller window
                start += 1

            # Move end forward to find another valid window
            end += 1

        if min_window_length == len(s) + 1:
            return ""
        else:
            return s[min_window_start:min_window_start + min_window_length]