 	
display as entry
Flowing Water
Feb 9 2015
输入是一个 N*N的矩阵，代表地势高度。如果下雨水流只能流去比他矮或者一样高的地势。
矩阵左边和上边是太平洋，右边和下边是大西洋。求出所有的能同时流到两个大洋的点。
For example:
1
2
3
4
5
6
7
8
9
10
Pacific: ~
Atlantic: *

~  ~   ~   ~   ~   ~  ~
~  1   2   2   3  (5) *
~  3   2   3  (4) (4) *
~  2   4  (5)  3   1  *
~ (6) (7)  1   4   5  *
~ (5)  1   1   2   4  *
*  *  *  *  *  *  *
括号里即为结果：
[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

Google近期高频题。
Brutal force的解法很简单，每个点搜索一次，接触到太平洋或者大西洋就记录下来。最后扫一遍输出同时能走到两个ocean的点即可。
复杂度为O(n^2)。
优化的话，可以从两个ocean的点开始，从外往里搜。首先搜所有太平洋的点，记录下能接触到的position。然后同理搜大西洋的点，求出交集即可。
搜索用DFS和BFS都可以，复杂度为O(n^2)。
下面给出5个语言版本的DFS解法。
Judge@fgdsb测试结果（测试数据大小247K

ava 代码:

public class Solution {
    void search(Point pt, HashMap<Point,Boolean> visited, int[][] mat) {
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for(int i = 0; i < 4; ++i) {
            int[] dir = dirs[i];
            Point new_pt = new Point(dir[0] + pt.x, dir[1] + pt.y);
            if( new_pt.x < 0 || new_pt.x >= mat.length || new_pt.y < 0 || new_pt.y >= mat.length ) {
                continue;
            }
            if( mat[new_pt.x][new_pt.y] < mat[pt.x][pt.y] || visited.containsKey(new_pt) ) {
                continue;
            }
            visited.put(new_pt, true);
            search(new_pt, visited, mat);
        }
    }
    
    public List<Point> flowing_water(int[][] mat) {
        int n = mat.length;
        
        HashMap<Point, Boolean> visited_pac = new HashMap<Point, Boolean>();        
        for(int i = 0; i < n; ++i) {
            Point p = new Point(0,i);
            visited_pac.put(p, true);
            search(p, visited_pac, mat);
        }        
        for(int i = 0; i < n; ++i) {
            Point p = new Point(i,0);
            visited_pac.put(p, true);
            search(p, visited_pac, mat);
        }
        
        HashMap<Point, Boolean> visited_alt = new HashMap<Point, Boolean>();        
        for(int i = 0; i < n; ++i) {
            Point p = new Point(n-1,i);
            visited_alt.put(p, true);
            search(p, visited_alt, mat);
        }
        
        for(int i = 0; i < n; ++i) {
            Point p = new Point(i,n-1);
            visited_alt.put(p, true);
            search(p, visited_alt, mat);
        }        
        ArrayList<Point> ret = new ArrayList<Point>();
        for(Point key : visited_alt.keySet()) {
            if(visited_pac.containsKey(key)) {
                ret.add(key);
            }
        }
        return ret;
    }
}

def search(pt, visited, mat):
    dirs = [[0,1], [0,-1], [1,0], [-1,0]]
    for i, dir in enumerate(dirs) :
        new_pt = Point(dir[0] + pt.x, dir[1] + pt.y)
        if new_pt.x < 0 or new_pt.x >= len(mat) or new_pt.y < 0 or new_pt.y >= len(mat): continue
        if mat[new_pt.x][new_pt.y] < mat[pt.x][pt.y] or visited.has_key(new_pt): continue
        visited[new_pt] = True
        search(new_pt, visited, mat)


def flowing_water(mat):
    n = len(mat)
    
    visited_pac = {}
    
    for i in range(0, n):
        p = Point(0, i)
        visited_pac[p] = True
        search(p, visited_pac, mat)
        
    for i in range(0, n):
        p = Point(i, 0)
        visited_pac[p] = True
        search(p, visited_pac, mat)
        
    visited_alt = {}
    
    for i in range(0, n): 	
        p = Point(n - 1, i)
        visited_alt[p] = True
        search(p, visited_alt, mat)
        
    for i in range(0, n):
        p = Point(i, n - 1)
        visited_alt[p] = True
        search(p, visited_alt, mat)
        
    ret = []
    for k, v in visited_alt.iteritems() :
        if visited_pac.has_key(k): ret.append(k)
    ret.sort()
    return ret


//     返回坐标的顺序不重要

// m和n均小于150

// 解题思路：
// BFS（广度优先搜索）

// 将矩形的左边和上边各点加入“太平洋”点集pacific

// 将矩形的右边和下边各点加入“大西洋”点集atlantic

// 分别对pacific与atlantic执行BFS

// 两者的交集即为答案。

Python代码：
class Solution(object):
    def pacificAtlantic(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[List[int]]
        """
        m = len(matrix)
        n = len(matrix[0]) if m else 0
        if m * n == 0: return []
        topEdge = [(0, y) for y in range(n)]
        leftEdge = [(x, 0) for x in range(m)]
        pacific = set(topEdge + leftEdge)
        bottomEdge = [(m - 1, y) for y in range(n)]
        rightEdge = [(x, n - 1) for x in range(m)]
        atlantic = set(bottomEdge + rightEdge)
        def bfs(vset):
            dz = zip((1, 0, -1, 0), (0, 1, 0, -1))
            queue = list(vset)
            while queue:
                hx, hy = queue.pop(0)
                for dx, dy in dz:
                    nx, ny = hx + dx, hy + dy
                    if 0 <= nx < m and 0 <= ny < n:
                        if matrix[nx][ny] >= matrix[hx][hy]:
                            if (nx, ny) not in vset:
                                queue.append((nx, ny))
                                vset.add((nx, ny))
        bfs(pacific)
        bfs(atlantic)
        result = pacific & atlantic
        return map(list, result)