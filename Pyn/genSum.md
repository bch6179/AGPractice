* searchInsertbad: if compare only two for start < end, there's a chance to get stuck. so always leave three
  if nums[m] >= target:
                end = m
            else:  
                start = m  # get stuck here if target bigger but only 0 1, start =0 
* lengthOfLastWord, string for the last, doing backward will be the best bet
  if start >0 and s[start-1] == ' ' and s[start] != ' ':
  lastcount/count won't work 
* wordSearch 
  #Mistake forget the visited and not test (["aa"], "aaa"
* hasPathSum don't make 
use start and end for array searching related recursion

when you don't know what to do divid and conquery , specific D&D: for certain i house, dp relation based on the assump is red blue black, get analysis for each, and then combine 
asked what's the property or hidden property can be utilized
can you abstract one level and step back one and relax

reverse = reverse * neg (let neg == -1 instead of True)
if reverse < -(1 << 31) or reverse > (1 << 31) - 1:
            return 0

              if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;


list business to avoid changing content in another branch, considering pop(), or make copy , or just in  argument list+1