class Solution(object):
    def arrangeCoins(self, n):
        """
        :type n: int
        :rtype: int
        """   
        #n=0:0 edge
        # 1  sum = 1  1    , 1:1, 2:1,  n/1 >=1, n < 3   k =1
        # 2        3 < n 2    3:2 , 4:2:, 5:2    n /3 > =1 , n < 6 , k=2 
        # 3        6 < n  3   n = 6: 3,  7, 8 , 9:3    n / sum >= 1, n < sum+k+1, k=3
        #          6+4 > n 4
        if n == 0:  #Mistake
            return 0
        k = 1
        sum = 0
        while True:
            sum+= k
            if n >= sum and n < sum+k+1:
                return k
            k+=1
    class Solution(object):
    def arrangeCoins(self, n):
        """
        :type n: int
        :rtype: int
        """   
        #n=0:0 edge
        # 1  sum = 1  1    , 1:1, 2:1,  n/1 >=1, n < 3   k =1
        # 2        3 < n 2    3:2 , 4:2:, 5:2    n /3 > =1 , n < 6 , k=2 
        # 3        6 < n  3   n = 6: 3,  7, 8 , 9:3    n / sum >= 1, n < sum+k+1, k=3
        #          6+4 > n 4
        if n == 0:  #Mistake
            return 0
        k = 1
        sum = 0
        while True:
            sum+= k
            if n >= sum and n < sum+k+1:
                return k
            k+=1
        #Mistake 
        #sum,count = 0,0
        # while True:
        #     sum += k
        #     count += 1
        #     if (sum >= n):
        #         return count
        #     k+=1