#practise, look at explanation at another
from heapq import *

a = [5,3,6, 7,6,5,4,3]
    6:4 6:2 5:0 3:1 
windows = []
res = []
for i in range(k):
    heappush(windows, (-a[i],i))
for i in range(k, len(a)):
     t = windows[0]
     res.append(-t[0])
    
     while t[1] <= i - k:
         heappop(windows)
         t = windows[0]
     heappush(windows, a[i])
res.append(-t[0])

from collections import deque
a = [5,3,6, 7,6,5,4,3]
    6:4 6:2 5:0 3:1 
windows = deque()
res = []
for i in range(k):
    while window and a[window[-1]] <= a[i]:
        window.pop()
    windows.append( i)
    
for i in range(k, n):
    res[i-k] = a[window[0]]
    while window and a[window[-1]] <= a[i]:
        window.pop()
    while windown and window[0] <= i-k:
        window.popleft()
    windows.append( i)
res[n-k] = a[window[0]]




def sliding_window_minimum(k, li):
    '''
    A iterator which takes the size of the window, `k`, and an iterable,
    `li`. Then returns an iterator such that the ith element yielded is equal
    to min(list(li)[max(i - k + 1, 0):i+1]).
    Each yield takes amortized O(1) time, and overall the generator takes O(k)
    space.
    '''

    window = deque()
    for i, x in enumerate(li):
        while window and window[-1][0] >= x:
            window.pop()
        window.append((x, i))
        while window[0][1] <= i - k:
            window.popleft()
        yield window[0][0]























