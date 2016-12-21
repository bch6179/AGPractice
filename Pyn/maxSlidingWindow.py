A long array A[] is given to you.There is a sliding window of size w which is moving from the very left of the array to the very right.You can only see the w numbers in the window. Each time the sliding window moves rightwards by one position. Following is an example:
The array is [1 3 -1 -3 5 3 6 7], and w is 3.


Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Input: A long array A[], and a window width w
Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
Requirement: Find a good optimal way to get B[i]

The obvious brute force solution with run time complexity of O(nw) is definitely not efficient enough.Every time the window is moved, you have to search for a total of w elements in the window.

A heap data structure quickly comes to mind. We could boost the run time to approximately O(n lg w) (Note that I said approximately because the size of the heap changes constantly and averages about w). Insert operation takes O(lg w) time, where w is the size of the heap.However, getting the maximum value is cheap, it merely takes constant time as the maximum value is always kept in the root(head) of the heap.

As the window slides to the right, some elements in the heap might not be valid anymore(range is outside of the current window). How should you remove them? You would need to be somewhat careful here.Since you only remove elements that are out of the window’s range, you would need to keep track of the elements’ indices too.

Note that as n grows larger, the term lg w is pretty insignificant compared to n, and thus the overall complexity approximates to O(n). (Edit: In fact, the correct run time complexity should be O(n log n). If A is sorted, then the inner while loop will never run.This is due to the next element (which is larger) being pushed to the queue’s top as the new maximum. (Thanks to my readers anonymous and faircoin who pointed out this.)


typedef pair<int, int> Pair;
void maxSlidingWindow(int A [], int n, int w, int B []) {
    priority_queue<Pair> Q;
    for (int i = 0; i < w; i++)
        Q.push(Pair(A[i], i));
    for (int i = w; i < n; i++)
    {
        Pair p = Q.top();
        B[i - w] = p.first;
        while (p.second <= i - w)
        {
            Q.pop();
            p = Q.top();
        }
        Q.push(Pair(A[i], i));
    }
    B[n - w] = Q.top().first;
}

You might be wondering: Is there a better way of doing this without using a heap? How about using a double-ended queue? (A linked list should be fine too)

The double-ended queue is the perfect data structure for this problem.It supports insertion/deletion from the front and back. The trick is to find a way such that the largest element in the window would always appear in the front of the queue.How would you maintain this requirement as you push and pop elements in and out of the queue?

Besides, you might notice that there are some redundant elements in the queue that we shouldn’t even consider about. For example, if the current queue has the elements: [10 5 3], and a new element in the window has the element 11. Now, we could have emptied the queue without considering elements 10, 5, and 3, and insert only element 11 into the queue.

A natural way most people would think is to try to maintain the queue size the same as the window’s size.Try to break away from this thought and try to think outside of the box. Removing redundant elements and storing only elements that need to be considered in the queue is the key to achieve the efficient O(n) solution below.



void maxSlidingWindow(int A[], int n, int w, int B[])
{
    deque<int> Q;
    for (int i = 0; i < w; i++)
    {
        while (!Q.empty() && A[i] >= A[Q.back()])
            Q.pop_back();
        Q.push_back(i);
    }
    for (int i = w; i < n; i++)
    {
        B[i - w] = A[Q.front()];
        while (!Q.empty() && A[i] >= A[Q.back()])
            Q.pop_back();
        while (!Q.empty() && Q.front() <= i - w)
            Q.pop_front();
        Q.push_back(i);
    }
    B[n - w] = A[Q.front()];
}
 
 
The above algorithm could be proven to have run time complexity of O(n). This is because each element in the list is being inserted and then removed at most once.Therefore, the total number of insert + delete operations is 2n.
