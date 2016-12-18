class DoubleLinkedList:
    def __init__(self, key = 0, val =0):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
        
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.map = dict()
        self.head = DoubleLinkedList()
        self.tail = DoubleLinkedList()
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key):
        """
        :rtype: int
        """
        if key in self.map:
            node = self.map[key]
            self._remove(node)
            self._add(node)
            return node.val
        return -1

    def set(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: nothing
        """
        if key in self.map:
            node = self.map[key]
            node.val = value
            self._remove(node)
            self._add(node)
        else:
            if self.capacity <= len(self.map): 
                n = self.head.next
                self._remove(n)
                del self.map[n.key]
                #self.map.pop("key", None)
            new = DoubleLinkedList(key,value)
            self._add(new)
            self.map[key] = new

        
    def _remove(self, node):
        prev = node.prev
        next = node.next
        prev.next = next
        next.prev = node #Mistake
    def _add(self, node):
        prev = self.tail.prev
        prev.next = node
        node.prev = prev
        node.next = self.tail
        self.tail.prev = node #Mistake
import collections
class LRUCache2(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.dict = collections.OrderedDict() 
         

    def get(self, key):
        """
        :rtype: int
        """
        if not key in self.dict:
            return -1

        val = self.dict[key]
        self.dict.pop(key)
        self.dict[key] = val
        return val    

    def set(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: nothing
        """
        #if  self.capacity <= len(self.dict) and not self.dict.pop(key, None)
         #    self.dict.popitem(last=False, None) 
        if key in self.dict:
           self.dict.pop(key)
        elif self.capacity <= len(self.dict):
           self.dict.popitem(last=False)
        self.dict[key] = value

s = LRUCache2(2) 
s.set(1,1)
s.set(2,2)
s.get(1)
s.set(3,3)
print s.get(1)
