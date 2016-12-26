[Note]
=====
  Integer is initialized as the index, updated to -1 when the word is added to map to make sure that no duplicate situation happens Or add the original str to the map value, and updated to null

[Problem]
=======
Given an array of strings, return all groups of strings that are anagrams.

[Code]
=======
 ```java
  
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 *
 * Note: All inputs will be in lower-case.
 *
 * Tags: Hashtable, String
 */
class Anagrams {
    public static void main(String[] args) {
        String[] strs = { "dog", "dot", "cog", "log", "god", "tod"};
        List<String> res = anagrams(strs);
        System.out.println(res.toString());
    }
    public List<String> anagrams(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();
        HashMap<String, ArrayList<String>> group = new HashMap<String, ArrayList<String>>();
        if (strs.length == 0) return res;
        for (int i = 0; i < strs.length; ++i) {
            char[] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            String s = String.valueOf(tmp);
            if(group.containsKey(s))
                (group.get(s)).add(strs[i]);
            else {
                ArrayList<String> t = new ArrayList<String>();
                t.add(strs[i]);
                group.put(s,t);
            }
        }
        Iterator<Entry<String, ArrayList<String>>> iter = group.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            ArrayList<String> val = (ArrayList<String>) entry.getValue();
            if (val.size() > 1) res.addAll(val);
        }
        return res;
    }


    /**
     * Use map<String, Integer>
     * Integer is initialized as the index, updated to -1 when the word is
     * added to map to make sure that no duplicate situation happens
     */
    public static List<String> anagrams2(String[] strs) {
        List<String> res = new ArrayList<String>();
        if (strs == null || strs.length == 0) return res;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < strs.length; i++) { // traverse the array
            /*generate key*/
            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String key = new String(word);
            if (map.containsKey(key)) {
                res.add(strs[i]); // add this string
                if (map.get(key) >= 0) { // key string not added
                    res.add(strs[map.get(key)]);
                    map.put(key, -1); // mark already added as -1
                }
            } else map.put(key, i); // first put sorted string and index
        }
        return res;
    }
}
```