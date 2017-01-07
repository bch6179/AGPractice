class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if (len(prices) <= 1): return 0
        
        minprice , maxprofit = prices[0], 0
        for price in prices[1:]:
            minprice = min(price, minprice)
            maxprofit = max(maxprofit, price - minprice)
        return maxprofit
    def maxProfitRange(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if (len(prices) <= 1): return 0
        a=b=s = 0

        minprice , maxprofit = prices[0], 0
        for i in range(1, len(prices)):
            price = prices[i]
            if price < minprice:
                minprice = price
                s = i
            if maxprofit <  price - minprice:
                a = s
                b = i
                maxprofit = price -minprice
            
        return a, b
s = Solution() 
print s.maxProfitRange([7, 1, 5, 3, 6, 4])