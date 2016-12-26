
public:
        int cnt,upper;
        int totalNQueens(int n) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function
        cnt = 0;
        upper = (1<<n)-1 ;// if n=4 then 1111
        Queen(0,0,0);
        return cnt;

        }
        void Queen(int row,int ld,int rd)
        {
        int all,p;
        if(row!=upper)
        {
        all = upper & (~(row | ld |rd));//if row 0100 ld 0010 rd 0001, then ~(row | ld |rd) is 1000 ,"1" represent empty
        while(all !=0 )
        {
        p = all & (-all);//get the right most "1", -a equals to !a+1//...
        all = all - p;
        Queen(row+p,(ld+p)<<1,(rd+p)>>1);
        }
        }
        else ++cnt;
        }
        };
        second time

class Solution {
//always pay attention to do not change the parameters in current level by forwarding
    public:
    void solveNQueensUtil(int n, int curRow, int upc, int ld, int rd, int& totalCnt)
    {
        if(curRow == n)
        {
            totalCnt++;
            return;
        }

        for(int j = 0; j < n; ++j)
        {
            int curNum = 1<<j;
            if(curNum & (ld | rd | upc)) continue;
            solveNQueensUtil(n, curRow+1, upc|curNum, (ld|curNum)>>1, (rd|curNum)<<1, totalCnt);
        }
    }
    int totalNQueens(int n) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function
        int totalCnt = 0;
        solveNQueensUtil(n, 0, 0, 0, 0, totalCnt);
        return totalCnt;
    }
};