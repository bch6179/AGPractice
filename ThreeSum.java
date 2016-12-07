import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a
 * + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 *
 * Note:
 * Elements in a triplet (a,b,c) must be in <strong>non-descending</strong>
 * order.
 * (ie, a ≤ b ≤ c)
 * The solution set must not contain <strong>duplicate</strong> triplets.
 *
 * For example, given array S = {-1 0 1 2 -1 -4},
 *
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 *
 * Tags: Array, Two Pointers
 */
class ThreeSum {
    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        int[] s = { -1, 0, 1, 2, -1, -4 };
        t.printResult(t.threeSum(s));
    }

    /**
     * Two Pointers.
     * Sort given array first.
     * Traverse the array with 1 pointer
     * Use 2 more pointers from both start(i + 1) and end to find target
     */
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) continue; // skip duplicate
            if (num[i] > 0) break; // stop at positive integers

            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (j > i + 1 && num[j] == num[j - 1]) { // skip duplicate
                    j++;
                    continue;
                }
                if (num[i] + num[j] > 0) break;// already bigger than 0

                if (num[i] + num[j] + num[k] < 0) j++;
                else if (num[i] + num[j] + num[k] > 0) k--;
                else { // num[i] + num[j] + num[k] == 0
                    List<Integer> triplets = new ArrayList<Integer>();
                    triplets.add(num[i]);
                    triplets.add(num[j]);
                    triplets.add(num[k]);
                    res.add(triplets);
                    j++; // move j ahead
                    k--;
                }
            }
        }

        return res;
    }

    private void printResult(List<List<Integer>> result) {
        for (List<Integer> l : result) {
            System.out.print("{");
            for (Integer i : l) {
                System.out.print(" " + i);
            }
            System.out.println(" }");
        }
    }
}

public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
     public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        final int length = num.length;
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    HashMap<Integer, int[]> hashMap = new HashMap<Integer, int[]>();

    // if length is less than 3, return empty result set
    if (length < 3) return result;

    Arrays.sort(num);

    for (int i = 0; i < length - 2; i++) {
        if (num[i] > 0) break;
        hashMap.clear();

        if (i == 0 || num[i] > num[i - 1]) {
            for (int j = i + 1; j < length; j++) {
                if (hashMap.containsKey(num[j])) { // found target
                    ArrayList<Integer> elem = new ArrayList<Integer>(3);

                    elem.add(hashMap.get(num[j])[0]);
                    elem.add(hashMap.get(num[j])[1]);
                    elem.add(num[j]);

                    result.add(elem);

                    // remove duplicated elements
                    while (j < (length - 1) && num[j] == num[j + 1]) j++;
                } else {
                    int[] temp = new int[2];
                    temp[0] = num[i];
                    temp[1] = num[j];
                    hashMap.put(0 - (num[i] + num[j]), temp);
                }
            }
        }
    }
    return result;


 }
    public ArrayList<ArrayList<Integer>> threeSum1(int[] numbers) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        //-1 0 1
        //[[-1,0,1]]
        // [1, 0, -1]
        //[1,0,-1,-1,-1,-1,0,1,1,1]
        //instead of sorting inside, sort ahead
        //avoid duplicate
        //just use full ArrayList<Integer> four 2D array
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            int target = -numbers[i];
            if (target < 0) break;
            map.clear();

            if (i !=0 && numbers[i] == numbers[i-1]) continue;
            for (int j = i+1; j < numbers.length; j++) {
                int temp = target - numbers[j];
                 if (map.containsKey(temp) ) { //&& map.get(temp) != j && map.get(temp) != i
                 // 0 0 0
                    ArrayList<Integer> list = new ArrayList<>(3);
                    list.add(numbers[i]);
                    list.add(temp);
                    list.add(numbers[j]);
                    result.add(list);
                    while(j < numbers.length -1 && numbers[j] == numbers[j+1]) j++;
                }
                else {
                    map.put(numbers[j], j); // map
                }
            }
        }
        return result;
    }
}
public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
          public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);
        int N = num.length;
        for (int i = 0; i < N-2 && num[i] <= 0; ++i)
        {
            if (i > 0 && num[i] == num[i-1])
                continue; // avoid duplicates
            int twosum = 0 - num[i];
            int l = i + 1, r = N - 1;
            while (l < r)
            {
                int sum = num[l] + num[r];
                if (sum < twosum) ++l;
                else if (sum > twosum) --r;
                else {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(num[i]); tmp.add(num[l]); tmp.add(num[r]);
                    res.add(tmp);
                    ++l; --r;
                    while (l < r && num[l] == num[l-1]) ++l;  // avoid duplicates
                    while (l < r && num[r] == num[r+1]) --r;  // avoid duplicates
                }
            }
        }
        return res;
     }
     public ArrayList<ArrayList<Integer>> threeSum2(int[] num) {
        final int length = num.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        // if length is less than 3, return empty result set
        if (length < 3) return result;

        Arrays.sort(num);

        for (int i = 0; i < length - 2; i++) {
            if (num[i] > 0) break;
            int target = -num[i];
            int start = i + 1;
            int end = length - 1;
            if (i > 0 && num[i] ==num[i-1] ) continue;
            while (start < end) {
                   if (end > start + 1 && num[start] == num[start - 1]) { // skip duplicate
                    start++;
                    continue;
                }
                if (num[start] + num[end] == target) {
                    ArrayList<Integer> elem = new ArrayList<Integer>(3);

                    elem.add(num[i]);
                    elem.add(num[start]);
                    elem.add(num[end]);

                    result.add(elem);
                    start++;
                    end--;
                    while (start < end && num[start+1] == num[start]) start++;
                     while (start  <  end && num[end-1] == num[end]) end--;//not while (start+1 <= end

                }
                else if (num[start] + num[end] < target) {

                    start++;
                }
                else {
                    end--;
                }
            }
        }
        return result;

     }

}