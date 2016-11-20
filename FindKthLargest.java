k largest(or smallest) elements in an array | added Min Heap method
Question: Write an efficient program for printing k largest elements in an array. Elements in array can be in any order.

For example, if given array is [1, 23, 12, 9, 30, 2, 50] and you are asked for the largest 3 elements i.e., k = 3 then your program should print 50, 30 and 23.


Method 1 (Use Bubble k times)
Thanks to Shailendra for suggesting this approach.
1) Modify Bubble Sort to run the outer loop at most k times.
2) Print the last k elements of the array obtained in step 1.

Time Complexity: O(nk)

Like Bubble sort, other sorting algorithms like Selection Sort can also be modified to get the k largest elements.

Method 2 (Use temporary array)
K largest elements from arr[0..n-1]

1) Store the first k elements in a temporary array temp[0..k-1].
2) Find the smallest element in temp[], let the smallest element be min.
3) For each element x in arr[k] to arr[n-1]
If x is greater than the min then remove min from temp[] and insert x.
4) Print final k elements of temp[]

Time Complexity: O((n-k)*k). If we want the output sorted then O((n-k)*k + klogk)

Thanks to nesamani1822 for suggesting this method.

Method 3(Use Sorting)
1) Sort the elements in descending order in O(nLogn)
2) Print the first k numbers of the sorted array O(k).

Time complexity: O(nlogn)

Method 4 (Use Max Heap)
1) Build a Max Heap tree in O(n)
2) Use Extract Max k times to get k maximum elements from the Max Heap O(klogn)

Time complexity: O(n + klogn)

Method 5(Use Oder Statistics)
1) Use order statistic algorithm to find the kth largest element. Please see the topic selection in worst-case linear time O(n)
2) Use QuickSort Partition algorithm to partition around the kth largest number O(n).
3) Sort the k-1 elements (elements greater than the kth largest element) O(kLogk). This step is needed only if sorted output is required.

Time complexity: O(n) if we don’t need the sorted output, otherwise O(n+kLogk)

Thanks to Shilpi for suggesting the first two approaches.

Method 6 (Use Min Heap)
This method is mainly an optimization of method 1. Instead of using temp[] array, use Min Heap.

Thanks to geek4u for suggesting this method.

1) Build a Min Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)

2) For each element, after the kth element (arr[k] to arr[n-1]), compare it with root of MH.
……a) If the element is greater than the root then make it root and call heapify for MH
……b) Else ignore it.
// The step 2 is O((n-k)*logk)

3) Finally, MH has k largest elements and root of the MH is the kth largest element.

Time Complexity: O(k + (n-k)Logk) without sorted output. If sorted output is needed then O(k + (n-k)Logk + kLogk)

All of the above methods can also be used to find the kth largest (or smallest) element.



Please write comments if you find any of the above explanations/algorithms incorrect, or find better ways to solve the same problem.

References:


OLUTION 1:

使用改进的Quicksort partition，可以达到O(n)的时间复杂度，并且不需要额外空间。

请参考： http://en.wikipedia.org/wiki/Quickselect#Time_complexity



 

时间复杂度：如果我们是这样的数组：1，2，3，4，5，然后又每一次取左边的当pivot，就会达到最坏的时间复杂度。也就是O(N2)

我们也有一些解决方法：

以下来自维基，我们可以取3个数进行平均。

http://stackoverflow.com/questions/7559608/median-of-three-values-strategy

The easiest solution is to choose a random pivot, which yields almost certain linear time. Deterministically, one can use median-of-3 pivot strategy (as in the quicksort), which yields linear performance on partially sorted data, as is common in the real world. However, contrived sequences can still cause worst-case complexity; David Musser describes a "median-of-3 killer" sequence that allows an attack against that strategy, which was one motivation for his introselect algorithm.


复制代码
 1 package Algorithms.algorithm.NChapter.findKthNumber;
 2 
 3 import java.util.ArrayList;
 4 
 5 // The link: 
 6 
 7 // http://www.lintcode.com/en/problem/kth-largest-element/
 8 
 9 class KthLargestElement_lintCode {
10     //param k : description of k
11     //param numbers : array of numbers
12     //return: description of return
13     public int kthLargestElement(int k, ArrayList<Integer> numbers) {
14         // write your code here
15         if (k < 1 || numbers == null) {
16             return 0;
17         }
18         
19         return getKth(numbers.size() - k + 1, numbers, 0, numbers.size() - 1);
20     }
21     
22     public int getKth(int k, ArrayList<Integer> numbers, int start, int end) {
23         // Choose the last one as the pivot
24         int pivot = numbers.get(end);
25         
26         int left = start;
27         int right = end;
28         
29         while (true) {
30             while (numbers.get(left) < pivot && left < right) {
31                 left++;    
32             }
33             
34             while (numbers.get(right) >= pivot && right > left) {
35                 right--;
36             }
37             
38             if (left == right) {
39                 break;
40             }
41             
42             swap(numbers, left, right);
43         }
44         
45         // left: the first one which is bigger than pivot.
46         swap(numbers, left, end);
47         
48         if (k == left + 1) {
49             return pivot;
50         // Try to find the element from the left side.
51         } else if (k < left + 1) {
52             return getKth(k, numbers, start, left - 1);
53         } else {
54         // Try to find the element from the right side.            
55             return getKth(k, numbers, left + 1, end);
56         }
57     }
58     
59     /*
60         Swap the two nodes.
61     *
62     public void swap(ArrayList<Integer> numbers, int n1, int n2) {
63         int tmp = numbers.get(n1);
64         numbers.set(n1, numbers.get(n2));
65         numbers.set(n2, tmp);
66     }
67 };
复制代码
 

SOLUTION 2:

以下这些链接有详细的讨论，就不再一一叙述了。目前来讲Solution 1应该是最优的啦。

http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/

http://www.quora.com/What-is-the-most-efficient-algorithm-to-find-the-kth-smallest-element-in-an-array-having-n-elements