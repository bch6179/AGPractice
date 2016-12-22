class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {integer[]}
    def maxSlidingWindow(self, nums, k):
        if nums == []:
            return []
        ans,tmp = [], []
        for i in range(0, k):
            while tmp != [] and nums[i] > nums[tmp[-1]]:
                tmp.pop()
            tmp.append(i)
        for i in range(k, len(nums)):
            ans.append(nums[tmp[0]])
            while tmp != [] and nums[i] > nums[tmp[-1]]:
                tmp.pop()
            tmp.append(i)
            while tmp != [] and tmp[0] <= i-k:
                tmp.pop(0)
        ans.append(nums[tmp[0]])
        return ans

         The dequeue maintain the elements in the current window and possible maximum value.

import collections
class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {integer[]}
    def maxSlidingWindow(self, nums, k):
        n = len(nums)
        dq = collections.deque()
        result = [0 for x in range(n-k+1)]
        for i in range(k):
            while dq and nums[i]>nums[dq[-1]]:
                dq.pop()
            dq.append(i)
    
        for i in range(n-k):
            result[i] = nums[dq[0]] #The max value if at the front
            while dq and dq[0]<=i: #Pop out the elements that are not in window
                dq.popleft()
            while dq and nums[dq[-1]]<nums[i+k]:
                dq.pop()
            dq.append(i+k)
        result[-1] = nums[dq[0]] #Last iteration
        return result

    e scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]

public int[] maxSlidingWindow(int[] a, int k) {		
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}