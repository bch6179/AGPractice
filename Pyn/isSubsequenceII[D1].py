class Solution(object):
    def isSubsequence(self, s, t):
        charPos = dict()
        for pos, char in enumerate(t):
            charPos.setdefault(char, [])
            charPos.append(pos)
        
        lowerBound = 0
        for char in s:
            if char not in charPos:
                return False
            else:
                firstPos = self.findFirstPos(charPos[char], lowerBound)
                if firstPos == -1:
                    return False
                else:
                    lowerBound = firstPos + 1
        return True
                
                
    def findFirstPos(self, poses, lowerBound):
        low = 0
        high = len(poses) - 1
        while low < high:
            mid = (high - low) / 2 + low
            if poses[mid] >= lowerBound:
                high = mid
            else:
                low = mid + 1
        if poses[low] >= lowerBound:
            return poses[low]
        else:
            return -1
s = Solution() 
s.isSubsequence("abc", "adbce")