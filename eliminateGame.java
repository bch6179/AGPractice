	Elimination GameDescribe:There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.
We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
Find the last number that remains starting with a list of length n.
Example:

diff = 1;
left = true;
while(n >= 2) {
  
  if (left || n % 2 == 1) 
              head = head + diff;
  n = n/2;
  left = !left;
  diff *= 2;
}
return head;


Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6
Output:
6

Solution:解题思路是每次遍历删除元素时，更新并用head记录数组第一个元素。每次遍历之后，数组相邻元素间隔step都会变成2倍，当数组个数为1时，head就是最后剩下的元素。
那什么时候更新head呢？

	1. 当我们从左边开始遍历删除元素时

	2. 当我们从右边开始遍历元素，并且剩下的数组元素个数为奇数时


例如：
2,4,6,8,10
从10开始移动，会删除10,6,2。数组的第一个元素head会被删除，因此需要更新head
2,4,6,8,10,12
从12从右至左遍历，我们会删除12,8,4，head此时依然是2
Code:


public int lastRemaining(int n) {
boolean left = true;
int remain = n;
int step = 1;
int head = 1;
while(remain>1){
if(left||remain%2==1){
head +=step;
}
remain /=2;
left =!left;
step *=2;
}
return head;
}
