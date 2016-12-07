package notTested;

[LinkedIn] Find K nearest (closest) neighbors from point (comparator/comparable, priority queue )
分类： 面试题目们
2015-03-31 15:37 91人阅读 评论(0) 收藏 举报
/**
This is for finding k nearest neighbor from the original point
using a MAX heap, each time if the dist is less than the MAX we put it into the q.
**/
public Collection<Point> getClosestPoints(Collection<Point> points, int k) {
    PriorityQueue<Point> queue = new PriorityQueue<Point>(k);

    for (Point point : points) {
        if (queue.size() < k) {
            queue.offer(point);
        } else {
            if (queue.peek().compareTo(point) < 0) {
                queue.poll();
                queue.offer(point);
            }
        }
    }

    return queue;
}


class Point implements Comparable<Point> {
    int x, y;
    double dist;

    public Point(int x, int y, Point originPoint) {
        this.x = x;
        this.y = y;
        //Math.hypot() returns sqrt(x^2 + y^2)
        this.dist = Math.hypot(x - originPoint.x, y - originPoint.y);
    }

    //assuming the original point is (0,0)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.dist = Math.hypot(x , y );
    }

    @Override
    public int compareTo(Point that) {
        return Double.valueOf(that.dist).compareTo(dist);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}



//cc
