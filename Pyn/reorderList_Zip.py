   
   The recursive idea have been posted by yucheng.wang. Given a example, 1->2->3->4->5, the solution will reorder node(3), then reorder 2 and 4 to have (2->4->3), then 1 and 5 get have 1->5->2->4->3. Each call of reorderList(ListNode* head, int len) will return the last element after this reorderList() call.

int getLength(ListNode *head){
int len = 0;
while( head != NULL ){
++len; head = head->next;
}
return len;
}

ListNode * reorderList(ListNode *head, int len){
    if(len == 0)
        return NULL;
    if( len == 1 )
        return head;
    if( len == 2 )
        return head->next;
    ListNode * tail = reorderList(head->next, len-2);
    ListNode * tmp = tail->next;
    tail->next = tail->next->next;
    tmp->next = head->next;
    head->next = tmp;
    return tail;
}

void reorderList(ListNode *head) {  //recursive
    ListNode  * tail = NULL;
    tail = reorderList(head, getLength(head));
}

class Solution {
public:
    void reorderList(ListNode* head) {
        if (!head || !head->next) return;
        ListNode *cur = NULL;
        finished = false;
        old_head = head;
        reorderList(head, cur);
    }
private:
    void reorderList(ListNode *head, ListNode *&cur)
    {
        if (!head)
        {
            cur = old_head;
            return;
        }
        reorderList(head->next, cur);
        if (!finished)
        {
            if (cur && (cur == head || cur->next == head))
            {
                finished = true;
                head->next = NULL;
                return;
            }
            head->next = cur->next;
            cur->next = head;
            cur = cur->next->next;                
        }
    }
    
    bool finished;
    ListNode *old_head;
};
    // merge two lists: O(n)
    for (p1 = head, p2 = head2; p1; ) {
        auto t = p1->next;
        p1 = p1->next = p2;
        p2 = t;
    }