/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: Sorted interval list.
     * @return: A new sorted interval list.
     */
    Comparator<Interval> comp = new Comparator<Interval>() {// cannot infer type arguments for Comparator;
        public int compare(Interval i1, Interval i2) {
            if (i1.start < i2.start) { //(i2.start > i1.end)  should be i1 - i2 value
                return -1;
            }
            else if (i1.start > i2.start) {
                return 1;
            }
            else {
                if ( i1.end < i2.end) {
                    return -1;
                }
                else if (i1.end > i2.end) {
                    return 1;
                }
                return 0; // forgot
            }
        }

    };

    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        int size = intervals.size();
        if (size <=1) return intervals;

        Collections.sort(intervals, comp);
        ArrayList<Interval> result = new ArrayList<>();

        Interval prev = intervals.get(0);
        for (int i = 1; i < size; i++) {
            Interval cur = intervals.get(i);
            if (cur.start > prev.end) {
                result.add(prev);
                prev = cur;
            }
            else {
                prev.end = Math.max(prev.end, cur.end);
            }
        }
        result.add(prev);
        return result;
    }

}