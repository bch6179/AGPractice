  easy DP
Word Segmentation
we can also work from backward

state: f[i]表示前i个字符能否被完美切分 function: f[i] = OR{f[j]}, j < i, j+1 ~ i是一个词 典中的单词 intialize: f[0] = true answer: f[s.length()] 注意：切分位置的枚举->单词长度枚举 O(NL), N: 字符串长度, L: 最长的单词的长
  but the optimization
  and another idea of increase by len: j
   maxLength = max([len(w) for w in dict])
 
 for j in range(1, min(i, maxLength) + 1):
                if not f[i - j]:
                    continue
                if s[i - j:i] in dict:
                    f[i] = True
                    break
