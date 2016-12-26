public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
         ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
         if (num == null || num.length == 0) {
             return rst; 
         }

         ArrayList<Integer> list = new ArrayList<Integer>();
         helper(rst, list, num);
         return rst;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, int[] num){
        if(list.size() == num.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = 0; i<num.length; i++){
            if(list.contains(num[i])){
                continue;
            }
            list.add(num[i]);
            helper(rst, list, num);
            list.remove(list.size() - 1);
        }
        
    }
}

	

是经典的递归模板。需要处理的情况是：我们先把Num排序，然后只能连续地选，这样就可以避免生成重复的solution.
例子：1 2 3 4 4 4 5 6 7 8
444这个的选法只能:4, 44, 444连续这三种选法

我们用一个visit的数组来记录哪些是选过的。


复制代码
 1 public class Solution {
 2     public List<List<Integer>> permuteUnique(int[] num) {
 3         List<List<Integer>> ret = new ArrayList<List<Integer>>();
 4         if (num == null || num.length == 0) {
 5             return ret;
 6         }
 7         
 8         // For deal with the duplicate solution, we should sort it.
 9         Arrays.sort(num);
10         boolean[] visit = new boolean[num.length];
11         
12         dfs(num, new ArrayList<Integer>(), ret, visit);
13         
14         return ret;
15     }
16     
17     public void dfs(int[] num, ArrayList<Integer> path, List<List<Integer>> ret, boolean[] visit) {
18         int len = num.length;
19         if (path.size() == len) {
20             ret.add(new ArrayList<Integer>(path));
21             return;
22         }
23         
24         for (int i = 0; i < len; i++) {
25             // 只能连续地选，这样就可以避免生成重复的solution.
26             // 例子：1 2 3 4 4 4 5 6 7 8
27             // 444这个的选法只能:4, 44, 444连续这三种选法
28             if (visit[i] || (i != 0 && visit[i - 1] && num[i] == num[i - 1])) {
29                 continue;
30             }
31             
32             // 递归以及回溯
33             visit[i] = true;
34             path.add(num[i]);
35             dfs(num, path, ret, visit);
36             path.remove(path.size() - 1);
37             visit[i] = false;
38         }
39     }

package com.epi;

import java.util.*;
//https://www.evernote.com/shard/s279/nl/38212251/284bbd9a-8098-4589-b0cb-907658d8a8d9/ 
public class PermutationsAlternative {
  // @include
  public static List<List<Integer>> permutations(List<Integer> A) {
    List<List<Integer>> result = new ArrayList<>();
    permutationsHelper(0, A, result);
    return result;
  }

  private static void permutationsHelper(int i, List<Integer> A,
                                         List<List<Integer>> result) {
    if (i == A.size() - 1) {
      result.add(new ArrayList<>(A));
      return;
    }

    for (int j = i; j < A.size(); ++j) {
      Collections.swap(A, i, j);
      permutationsHelper(i + 1, A, result);
      Collections.swap(A, i, j);
    }
  }
  // @exclude

  private static void smallTest() {
    List<Integer> A = new ArrayList<Integer>() {
      {
        add(0);
        add(1);
        add(2);
      }
    };
    List<List<Integer>> result = permutations(A);
    assert (result.size() == 6);
    List<List<Integer>> goldenResult = Arrays.asList(Arrays.asList(0, 1, 2),
        Arrays.asList(0, 2, 1), Arrays.asList(1, 0, 2), Arrays.asList(1, 2, 0),
        Arrays.asList(2, 1, 0), Arrays.asList(2, 0, 1));
    assert (result.equals(goldenResult));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
    }
    List<Integer> A = new ArrayList<>(n);
    int val = 0;
    for (int i = 0; i < n; i++) {
      A.add(val++);
    }
    List<List<Integer>> result = permutations(A);
    System.out.println("n = " + n);
    for (List<Integer> vec : result) {
      System.out.println(vec);
    }
  }
}
