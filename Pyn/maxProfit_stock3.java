class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */

     int getOneTimeProfit(int[] prices, int s, int e) {
         if (s >= e) return 0;
          int min = prices[s];
         int max = 0;
        for (int i = s+1; i <= e; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i]-min);
        }
        return max;

     }
    public int maxProfit3(int[] prices) {
        // write your code here

       if (prices == null || prices.length <= 1) return 0;
       int max = getOneTimeProfit(prices, 0, prices.length-1);

        for (int i = 1; i < prices.length; i++) {
            int first =     getOneTimeProfit(prices, 0, i);
            int second =    getOneTimeProfit(prices, i, prices.length-1);

            max = Math.max(max, (first + second));
        }
        return max;
    }

        public int maxProfit2(int[] prices) {
        // write your code here
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            sum += Math.max(prices[i] - prices[i-1], 0);
        }
        return sum;
    }
};

/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most
 * <strong>two</strong> transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 *
 * Tags: Array, DP
 */
class BestTimeStock3 {
    public static void main(String[] args) {
        BestTimeStock3 b = new BestTimeStock3();
        int[] prices = { 6, 1, 3, 2, 4, 7, 6, 10, 15 };
        System.out.println(b.maxProfit(prices));
    }

    /**
     * Goes forward to build single transaction max profit
     * Then goes backward to build max since day i profit
     * Find the max of the sum of these two
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length < 2) return maxProfit;
        int len = prices.length;
        int[] maxBy = new int[len];
        int[] maxSince = new int[len];
        int valley = prices[0];
        int peak = prices[len - 1];

        for (int i = 1; i < len; i++) {
            valley = Math.min(valley, prices[i]);
            maxBy[i] = Math.max(maxBy[i - 1], prices[i] - valley);
        }
        /*update maxProfit while build maxSince*/
        for (int i = len - 2; i >= 0; i--) {
            peak = Math.max(peak, prices[i]);
            maxSince[i] = Math.max(maxSince[i + 1], peak - prices[i]);
            maxProfit = Math.max(maxProfit, maxBy[i] + maxSince[i]); // find i such that maxBy[i]+maxSince[i+1] is the max two-transaction profit, no overlap
        }
        return maxProfit;
    }

     public static int[] stockMarket(int[] arr) {
        int buy = 0;    // index of when to buy
        int sell = 0;   // index of when to sell
        int lowest = 0; // index of the lowest stock price

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] - arr[lowest] > arr[sell] - arr[buy]) {
                buy = lowest;
                sell = i;
            }

            if(arr[i] < arr[lowest])
                lowest = i;
        }

        return new int[] {buy, sell};
    }
}
}
