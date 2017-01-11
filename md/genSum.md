
*        for i, d in reversed(list(enumerate(digits))):

*   curval = (i >= 0 and a[i] == '1') + (j >= 0 and b[j] == '1')

* arrange coin:  should find the real rule by enumerating, instead of changing courses
*int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }
* originalDigits if really hard, specialize find that, or customize for each
*findContentChildren think further really start assigning to greater factor? or lower
* searchInsertbad: if compare only two for start < end, there's a chance to get stuck. so always leave three
  if nums[m] >= target:
                end = m
            else:  
                start = m  # get stuck here if target bigger but only 0 1, start =0 
* lengthOfLastWord, string for the last, doing backward will be the best bet
  if start >0 and s[start-1] == ' ' and s[start] != ' ':
  lastcount,count won't work 
* wordSearch 
  #Mistake forget the visited and not test (["aa"], "aaa"
* hasPathSum don't make 
use start and end for array searching related recursion

when you don't know what to do divid and conquery , specific D&D: for certain i house, dp relation based on the assump is red blue black, get analysis for each, and then combine 
asked what's the property or hidden property can be utilized
can you abstract one level and step back one and relax

reverse = reverse * neg (let neg == -1 instead of True)
if reverse < -(1 << 31) or reverse > (1 << 31) - 1:
            return 0

              if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;


list business to avoid changing content in another branch, considering pop(), or make copy , or just in  argument list+1

* TRIE
trie, also called digital tree and sometimes radix tree or prefix tree (as they can be searched by prefixes), is a kind of search tree—an ordered tree data structure that is used to store a dynamic set or associative array where the keys are usually strings. Unlike a binary search tree, no node in the tree stores the key associated with that node; instead, its position in the tree defines the key with which it is associated. All the descendants of a node have a common prefix of the string associated with that node, and the root is associated with the empty string. Values are not necessarily associated with every node. Rather, values tend only to be associated with leaves, and with some inner nodes that correspond to keys of interest. For the space-op
 a trie has a number of advantages over binary search trees.[6] A trie can also be used to replace a hash table, over which it has the following advantages:

Looking up data in a trie is faster in the worst case, O(m) time (where m is the length of a search string), compared to an imperfect hash table. An imperfect hash table can have key collisions. A key collision is the hash function mapping of different keys to the same position in a hash table. The worst-case lookup speed in an imperfect hash table is O(N) time, but far more typically is O(1), with O(m) time spent evaluating the hash.
There are no collisions of different keys in a trie.
Buckets in a trie, which are analogous to hash table buckets that store key collisions, are necessary only if a single key is associated with more than one value.
There is no need to provide a hash function or to change hash functions as more keys are added to a trie.
A trie can provide an alphabetical ordering of the entries by key.ome tries can require more space than a hash table, as memory may be allocated for each character in the search string, rather than a single chunk of memory for the whole entry, as in most hash tables.
mmon application of a trie is storing a predictive text or autocomplete dictionary, such as found on a mobile telephone. Such applications take advantage of a trie's ability to quickly search for, insert, and delete entri

trie = Trie()
    words = 'hello goodbye help gerald gold tea ted team to too tom stan standard money'
    for word in words.split():
        trie.add(word)
    print "'goodbye' in trie: ", trie.has_word('goodbye')
    print trie.start_with_prefix('g')