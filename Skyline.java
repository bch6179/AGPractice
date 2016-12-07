[LeetCode#218] The Skyline Problem
Problem:

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).



The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
Analysis:


Key idea: use a priority queue to simulate the current dominant edge.
This problem is very very elegant. It tests many aspects of your coding ability.
1. Covert the problem into a simple form, avoid complex analysis(cases).
2. Indentify right cases handling for each simplified case.
3. Use proper data structure. And take advantage of language's inherent customerized sort method.

Analysis:
The input is in the form of [x, y, height], which represents a building. If we analyze the problem based on building, it could be very complex. Many cases could happen, regarding the possible change in x relation, y relation and height relation. The overlapping of many buildings could make the situation gets much worse.

Ask youself?
For all problems we have encountered so far, if we never really need to tackle the complex rectangle relationship?
Nope!!!We always dissect the problem into the realtionship among edges. It often could lead to a much simplifer problem.

For this problem can we always do this?
Firstly, we dissect a rectangle into two edges : left and right.
The skyline could only happen at following cases:
1. Left edge.
Only when the left edge is the tallest among all left edges in its range. (any building includes the edge)

2. Right edge.
2.1 when the right edge is the last edge of a chain of consecutive buildings.
2.2 when right edge is the second largest edge when the previous building was just over.

The two big problems we are facing now are:
1. How to separate each edge out of buildings? Since the building could overlap with each other.
Step 1: Define a proper data type for each edge. (the type should hold x-coordiante, height and whether it is start or end edge)
class Edge {
    int x;
    int height;
    boolean is_start;

    public Edge(int x, int height, boolean is_start) {
        this.x = x;
        this.height = height;
        this.is_start = is_start;
    }
}

Step 2: Spearate each edge out of each buidilding into a List.
for (int[] building : buildings) {
    Edge left_edge = new Edge(building[0], building[2], true);
    edges.add(left_edge);
    Edge right_edge = new Edge(building[1], building[2], false);
    edges.add(right_edge);
}

Step 3: Define the proper comparator and take advantage of Collections' sort method.
Collections.sort(edges, new Comparator<Edge> () {
    @Override
    public int compare(Edge e1, Edge e2) {
        if (e1.x != e2.x)
            return Integer.compare(e1.x, e2.x);
        if (e1.is_start == true && e2.is_start == true)
            return Integer.compare(e2.height, e1.height); //assert e2.height < e1 ,
         if (e1.is_start == false && e2.is_start == false)
            return Integer.compare(e1.height, e2.height);  //asert e1 < e2 , e1 ahead e2
        return e1.is_start ? -1 : 1;
    }
});
Note: the sorting method is very interesting, since e1 and e2 could be differnt types of edge.
Firstly, we want to guarantee the order follow the nature order have seen on the picture.
if (e1.x != e2.x)
    return Integer.compare(e1.x, e2.x);

Sencodly, the order should help us to tackle certain werid case.
Background: iff e1 and e2 share the same x coordinate
--------------------------------------------------------------------------------------------
Special 1: iff e1 and e2 both are left edges, we place the higher one before the smaller one.
if (e1.is_start == true && e2.is_start == true)
    return Integer.compare(e2.height, e1.height);
--------------------------------------------------------------------------------------------
Special 2: iff e1 and e2 both are right edges, we place the smaller one before the higher one.
if (e1.is_start == false && e2.is_start == false)
    return Integer.compare(e1.height, e2.height);
--------------------------------------------------------------------------------------------
Special 3: iff there are different type of edges.  we place the start edge before the end edge
return e1.is_start ? -1 : 1;
--------------------------------------------------------------------------------------------

Afterward, we would combine our main algorithm to analyze the sorting method we have defined for special 1 and special 2. (It's very important in algorithm)


2. How could we model the range of a building, and the dominant height among buildings at each coordinate?
Tricky: We use a max heap for this purpose.
Once a start edge appears, we add it into the priority queue. If it is height larger than other start edges in the priority queue,
it means this edge becomes the dominant edge (until it is left edge appears)
--------------------------------------------------------------------------------------------
if (edge.is_start) {
    if (max.isEmpty() || edge.height > max.peek()) {
        int[] skyline = {edge.x, edge.height};
        skylines.add(skyline);
    }
    max.offer(edge.height);
}
--------------------------------------------------------------------------------------------
Thus the peek element of the PriorityQueue always represents the the dominant edge that is valid at present!


When is the dominant edge over? (The building is over)
Once we detect a right edge, and it must have a paired left edge in the priority queue.
Once we encounter the right edge, it menas the building is over, we should remove it's height(left edge) from the priority queue.
--------------------------------------------------------------------------------------------
if (!dge.is_start) {
    max.remove(edge.height);
    if (max.isEmpty() || edge.height > max.peek()) {
        int[] skyline = new int[2];
        skyline[0] = edge.x;
        skyline[1] = (max.isEmpty() ? 0 : max.peek());
        skylines.add(skyline);
    }
}
Note : it works for all building, no the current dominant building.

The idea is so great!!! Right!!!
Now, let us back to special case in sorting edge.
--------------------------------------------------------------------------------------------
Special 1: iff e1 and e2 both are left edges, we place the higher one before the smaller one.
if (e1.is_start == true && e2.is_start == true)
    return Integer.compare(e2.height, e1.height);

Reason:
We know iff both e1 and e2 are start edge, and share same x-coordinate, we should only record the higher one. Thus we could the higher one infront of the lower one, to avoid lower one to be recorded.

--------------------------------------------------------------------------------------------
Special 2: iff e1 and e2 both are right edges, we place the smaller one before the higher one.
if (e1.is_start == false && e2.is_start == false)
    return Integer.compare(e1.height, e2.height);

Reason:
We know only the higher one should be consider as the turning point, thus we arrange the higher one at last, so as to count the higher one only.

Cause my poor code ability, I have made follwing mistakes in the implementation.
mistake 1: Use Integer.comareTo as Integer.compare.
return Integer.compareTo(e1.height, e2.height);

mistake 2: use method "Collections.reverse()" to generate inverse comparator.
PriorityQueue<Integer> max = new PriorityQueue<Integer> (10, Collections.reverse());

Solution:


public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null)
            throw new IllegalArgumentException("buildings is null");
        List<int[]> skylines = new ArrayList<int[]> ();
        if (buildings.length == 0)
            return skylines;
        List<Edge> edges = new ArrayList<Edge> ();
        for (int[] building : buildings) {
            Edge left_edge = new Edge(building[0], building[2], true);
            edges.add(left_edge);
            Edge right_edge = new Edge(building[1], building[2], false);
            edges.add(right_edge);
        }
        Collections.sort(edges, new Comparator<Edge> () {
            @Override
            public int compare(Edge e1, Edge e2) {
                if (e1.x != e2.x)
                    return Integer.compare(e1.x, e2.x);
                if (e1.is_start == true && e2.is_start == true)
                    return Integer.compare(e2.height, e1.height);
                if (e1.is_start == false && e2.is_start == false)
                    return Integer.compare(e1.height, e2.height);
                return e1.is_start ? -1 : 1;
            }
        });
        PriorityQueue<Integer> max = new PriorityQueue<Integer> (10, Collections.reverseOrder());
        for (Edge edge : edges) {
            if (edge.is_start) {
                if (max.isEmpty() || edge.height > max.peek()) {
                    int[] skyline = {edge.x, edge.height};
                    skylines.add(skyline);
                }
                max.offer(edge.height);
            } else{
                max.remove(edge.height);
                if (max.isEmpty() || edge.height > max.peek()) {
                    int[] skyline = new int[2];
                    skyline[0] = edge.x;
                    skyline[1] = (max.isEmpty() ? 0 : max.peek());
                    skylines.add(skyline);
                }
            }
        }
        return skylines;
    }
}


class Edge {
    int x;
    int height;
    boolean is_start;

    public Edge(int x, int height, boolean is_start) {
        this.x = x;
        this.height = height;
        this.is_start = is_start;
    }
}
