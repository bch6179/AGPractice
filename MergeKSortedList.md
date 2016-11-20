Merge k sorted linked lists and return it as one sorted list. [Java]
1) First think about a simple merge idea. Go through k head elements each time, they are the smallest among their list, k pointers and totally nk elements.
time: O(nk^2) space:O(k);
[CODE] 略，自己写一下把，这个简单

2) Use heap. This is a classic question for heap. each time we change a heap value we only use Log(k) time 
time: O(nkLogk) space:O(k)

1) A comparator used can be passed to Collections.sort(coll,comparator) 
2) In java, heap is implemented as PriorityQueue. The constructor
PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
constructor is initialized with capacity and compare rule.
3) Java anonymous class is used to simply create and use this comparator 
4) The node inside list could be null
5) PriorityQueue use poll() method to pop out it’s first element

1、这一题明显是考Heap的，如果对Java PriorityQueue不够了解的，就应该去复习一下。Java7和Java8不太一样，Java8在传入comparator不再需要给定size。
2、代码中写到了Collections.sort()，和在sort中写匿名类。
3、为什么要写dummy？在那些题中需要写dummy而return dummy.next。那些题不需要？
4、为什么用Queue interface，用List－>LinkedList可以吗，是否要复习总结一下list，queue和它们的implementation? 如果对Object Oriented 不熟悉的，是否要再看看继承(Inheritence)和多态(Polymorphism)。那再往深处想一想，Abstract Class 和 Interface 的区别，优缺点呢？

这些 Java 基础就是做题的时候要想，不是很理解就要及时解决。基础不好的同学，刚开始做会比较慢，所以一道题做完过去了大半天，这很正常，等做多了，烂熟于心了，做题就快了。每次做题也都是对知识点的强化。

代码[CODE]部分和代码上面我自己的思考笔记都是写在Evernote上的，每次写完代码要把遇到这个题时候的想法，解题思路，看完别人答案之后自己的理解，基于答案对自己code的改进写进去。