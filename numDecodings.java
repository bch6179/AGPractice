public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        int[] memo = new int[n+1];

        // We can explain memo[n] and memo[n-1] using the following cases:
        // Case 1: all zero string. The 1 here won't have any effect. Statement "continue" in the for loop will lead to mem[0] = 0.
        // Case 2: zero is in the last digit i.e. 10. Still two ways of decoding. 1 or 10. memo[n] =1 and memo[n-1] = 1. Adds up to 2.
        // Case 3: zero is not in the last digit i.e. 11. Still two ways of decoding. 1 or 11. 
        // Case 4: i.e. 30 82. These numbers only has 1 way of decoding. This is taken care of by the for loop by moving memo[n-1] with 1 to memo[n-2].
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0; // This is the last character
        
        for (int i = n - 2; i >= 0; i--)
            // Case 1: individual 0 does not work
            // Case 2: 0 preceding any number does not work
            if (s.charAt(i) == '0') continue;

            // memo[i+1]+memo[i+2]
            // The character pair works, so we need to add up the total ways of decoding for the two pairs.
            // memo[i+1]
            // The pair does not work, just need to move the previous total so that memo[0] will eventually have the right count.
            // Notice that we do not need to +1 here because we are counting the number of ways to separate the string.
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
            
        return memo[0];
    }
}public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        if(s.charAt(0) != '0') dp[1] = 1;
        
        for(int i = 2; i < len + 1; i ++){
            if(s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];
            int val = Integer.valueOf(s.substring(i - 2, i));
            if(val >= 10 && val <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[len];
    }    public int numDecodings(String s) {
        if(s==null || s.length()==0)
            return 0;
            
        int i = 0;
        int f2 = 1;
        int f1 = (s.charAt(i) != '0') ? 1 : 0;
        
        int numDec = f1;  // numDecodes till position i
        if(numDec==0) return numDec; // starting with zero, no decode possible
        
        for(i=1; i<s.length(); i++){
            numDec = 0;
            if(s.charAt(i) != '0'){
                numDec = f1;
            }
            
            // check if the two chars i-1 and i form a valid decoding
            int val = Integer.parseInt(s.substring(i-1, i+1));
            if(val >9 && val <= 26){
                numDec += f2;
            }
            if(numDec == 0) return numDec;
            
            f2 = f1;
            f1 = numDec;
        }
        
        return numDec;
    }
 ublic int numDecodings(String s) {
    if(s.length() == 0) return 0;
    int pre = 27, digit, answer = 0, first = 1, second = 1;
    for(int i = s.length()-1; i >= 0; i--){
        digit = s.charAt(i) - '0';
        if(digit == 0) answer = 0;
        else answer = first + (digit*10 + pre < 27 ? second : 0);
        second = first; first = answer; pre = digit;
    }
    return answer;
}
9 months ago reply quote 
0
V vimukthi 
Reputation:  377
Without the array if anyone interested :)

public int numDecodings(String s) {
    if(s.length() == 0) return 0;
    int pre = 27, digit, answer = 0, first = 1, second = 1;
    for(int i = s.length()-1; i >= 0; i--){
        digit = s.charAt(i) - '0';
        if(digit == 0) answer = 0;
        else answer = first + (digit*10 + pre < 27 ? second : 0);
        second = first; first = answer; pre = digit;
    }
    return answer;
}M manky 
Reputation:  159
 public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        
        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
        
        return memo[0];
    }
}

    ○ . Assigning memo[n] = 1; means when the string is empty, there is only one answer. memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0; means when there is only one character in the string, if this character is not 0, there will be an answer, or there will be no answer. Then it starts the dp portion. When we add a letter from the end of the string, if the first two letters <=26, we can get memo[n]=memo[n+1]+memo[n+2]. For example, the String now is "123xxxx" and we know all the result from 2. Because 12<26, we can make this string either"12"+"3xxxx" or 1+23xxxx which is exactly memo[n]=memo[n-1]+memo[n-2]. if the String is"32xxxx" memo[n]=memo[n+1]. if there are 0s in the string, we should skip it and look at the next character because there is no answer when the string begins with 0.
7 months agoreply quote 


build up from right =>

num_ways ("") => 1 (empty string can be represented by empty string) (i.e. num_ways[n] = 1) NOTE: only for build up with a valid string. Empty string on it's own doesn't need to be decoded.
num_ways ("3") => 1 (only one way), i.e. num_ways[n-1] = 1
num_ways ("23") => "23" or "2"-"3",
num_ways ("33") => "3""3"
num_ways ("123") => "12"(num_ways("3")) + "1"("num_ways("23")) (i.e. num_ways[i+2] + num_ways[i+1])
num_ways ("323") => "3"(num_ways("23")) (i.e. num_ways[i+1])

so basically if s[i:i+1] (both included) <= 26, 
num_ways[i+2] + num_ways[i+1]
else:
num_ways[i+1]

case with 0:

num_ways ("103")
num_ways ("3") => 1 (only one way)
num_ways ("03") => 0 (can't decode 0)
num_ways ("003") => "00"(num_ways("3")) + "0"(num_ways("03")) => no way to decode "00" = 0 + 0
num_ways ("103") => "10"(num_ways("3")) + "1"(num_ways("03")) => num_ways[i+2] + num_ways[i+1](= 0 in this case)
num_ways ("1003") => "10"(num_ways("03")) + "1"(num_ways("003")) => same eq = 0(no way to decode "03") + 0(no way to decode 003)
