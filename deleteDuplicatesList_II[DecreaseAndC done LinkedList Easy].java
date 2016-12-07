[Note]
=====
  #Property: connect next of head to next not duplicated
  #*Conflicts*: after first try, see the problem of conflicts: while skip one a time, but requires to skip both 
  #Instead of keeping skip and skip, use some zhangfa, decrease and conquer:
    add flag of duplicates , then check it to finish the next skip
  

[Code]
  =====
  public ListNode deleteDuplicates_II(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int val = head.next.val;
                while (head.next.next != null && head.next.next.value != val) {
                  duplicate = true;
                }
                      
                if (head.next.next == null) head.next = null;
            }
            else if (duplicates) {
               head.next = head.next.next;
               duplicate = false;
            }
            } else {
                head = head.next;
            }
        }

        return dummy.next;
最开始还是需要建立一个fakehead，让fakehead的next指向head。然后，使用我刚才说过的3个指针方法来初始化3个指针，如下： 

  ListNode ptr0 = fakehead; //prev
  ListNode ptr1 = fakehead.next; //current
  ListNode ptr2 = fakehead.next.next; //post

同时还需要引入一个布尔型的判断flag，来帮助判断当前是否遇到有重复，这个flag能帮助识别是否需要删除重复。

 当没有遇到重复值（flag为false）时，3个指针同时往后移动：

 ptr0 = ptr1;

 ptr1 = ptr2;

 ptr2 = ptr2.next; 

当遇到重复值时，设置flag为true，并让ptr2一直往后找找到第一个与ptr1值不等的位置时停止，这时，ptr1指向的node的值是一个重复值，需要删除，所以这时就需要让ptr0的next连上当前的ptr2，这样就把所有重复值略过了。然后，让ptr1和ptr2往后挪动继续查找。

这里还需要注意的是，当ptr2一直往后找的过程中，是有可能ptr2==null（这种情况就是list的最后几个元素是重复的，例如1->2->3->3->null)，这时ptr1指向的值肯定是需要被删除的，所以要特殊处理，令ptr0的next等于null，把重复值删掉。其他情况说明最后几个元素不重复，不需要处理结尾，遍历就够了。

代码如下：

复制代码
 1      public ListNode deleteDuplicates(ListNode head) {
 2         if(head == null || head.next == null)
 3             return head;
 4         
 5         ListNode fakehead = new ListNode(0);
 6         fakehead.next = head;
 7         
 8         ListNode ptr0 = fakehead;
 9         ListNode ptr1 = fakehead.next;
10         ListNode ptr2 = fakehead.next.next;
11         
12         boolean flag = false;
13         while(ptr2!=null){
14             if(ptr1.val == ptr2.val){
15                 flag = true;
16                 ptr2 = ptr2.next;
17                 if(ptr2 == null)
18                     ptr0.next = null;
19             }else{
20                 if(flag){
21                     ptr0.next = ptr2;
22                     flag = false;
23                 }else{
24                     ptr0 = ptr1;
25                 }
26                 ptr1 = ptr2;
27                 ptr2 = ptr2.next;
28             }
29         }
30         return fakehead.next;
31     }