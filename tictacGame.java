
Design an algorithm to figure out if someone has won in a game of tic-tac-toe.

译文：

设计算法检查某人是否赢得了井字游戏。

解答

假设这个检查某人是否赢得了井字游戏的函数为HasWon，那么我们第一步要向面试官确认， 这个函数是只调用一次，还是要多次频繁调用。如果是多次调用， 我们可以通过预处理来得到一个非常快速的版本。

方法一：如果HasWon函数需要被频繁调用

对于井字游戏，每个格子可以是空，我的棋子和对方的棋子3种可能，总共有39 = 19683 种可能状态。我们可以把每一种状态转换成一个整数， 预处理时把这个状态下是否有人赢得了井字游戏保存起来，每次检索时就只需要O(1)时间。 比如每个格子上的3种状态为0(空)，1(我方棋子)，2(对方棋子)，棋盘从左到右， 从上到下依次记为0到8，那么任何一个状态都可以写成一个3进制的数，再转成10进制即可。 比如，下面的状态：


1 2 2
2 1 1
2 0 1
可以写成:
122211201=1*3^8 + 2*3^7 +...+ 0*3^1 + 1*3^0

如果只需要返回是否有人赢，而不需要知道是我方还是对方。 那可以用一个二进制位来表示是否有人赢。比如上面的状态，1赢， 就可以把那个状态转换成的数对应的位置1。如果需要知道是谁赢， 可以用一个char数组来保存预处理结果。

方法二：如果HasWon函数只被调用一次或很少次

如果HasWon函数只被调用一次或很少次，那我们就没必要去预存结果了， 直接判断一下就好。只为一次调用去做预处理是不值得的。

代码如下，判断n*n的棋盘是否有人赢，即同一棋子连成一线：


#include <iostream>
using namespace std;

enum Check{
    ROW, COLUMN, DIAGONAL, REDIAGONAL
};

int CheckRowColumn(int board[], int n, Check check){
    int type = 0;
    for(int i=0; i<n; ++i){
        bool found = true;
        for(int j=0; j<n; ++j){
            int k = 0;
            if(check == ROW)
                k = i * n + j;
            else
                k = i + j * n;
            if(j == 0){
                type = board[k];
            }
            else if(board[k] != type){
                found = false;
                break; // 有一个不满足，检查下一行
            }
        }
        if(found) return type;
    }
    return 0;
}
int CheckDiagonal(int board[], int n, Check check){
    int type = 0;
    bool found = true;
    // 主对角
    for(int i=0; i<n; ++i){
        int k = 0;
        if(check == DIAGONAL)
            k = i + i * n;
        else
            k = i + (n-1-i) * n;
        if(i == 0){
            type = board[k];
        }
        else if(board[k] != type){
            found = false;
            break;
        }
    }
    if(found) return type;

    return 0;
}
int HasWon(int board[], int n){
    int type = 0;
    type = CheckRowColumn(board, n, ROW);
    if(type != 0) return type;
    type = CheckRowColumn(board, n, COLUMN);
    if(type != 0) return type;
    type = CheckDiagonal(board, n, DIAGONAL);
    if(type != 0) return type;
    type = CheckDiagonal(board, n, REDIAGONAL);
    if(type != 0) return type;

    return 0;
}
int main(){
    int n = 3; // 3*3
    int board[] = {
        2, 2, 1,
        2, 1, 1,
        1, 2, 0,
    };
    int type = HasWon(board, n);
    if(type != 0)
        cout<<type<<" won!"<<endl;
    else
        cout<<"nobody won!"<<endl;
    return 0;
}


#define SIZE 3
#define MSIZE -3

int row[SIZE];
int col[SIZE];

int init () {
int i, j;
for (i=0; i<SIZE; ++i)
row[i] = 0;
for (j=0; j<SIZE; ++j)
row[j] = 0;
}

// 0: no win
// 1: 1st player win
// 2: 2nd player win
int check (int x, int y, int p){ // O(1)
if (p == 1){
row[y]++;
col[x]++;
}
else {
col[x]--;
row[y]--;
}

if (row[y] == SIZE ||
row[y] == MSIZE ||
col[x] == SIZE ||
col[x] == MSIZE)
return p; //

return 0;
}

}}

Great Solution. However diagonals need to be handled as IntwPrep pointed out.


if (p == 1){
row[y]++;
col[x]++;

if (x == y)
   diag ++ // similarly decrement for the other player.

if (y == n - x )
   antidiag ++ // similarly decrement for the other player.

}

// finally check

diag == SIZE or diag ==  MSIZE
or antidiag == SIZE or antidiag == MSIZE
- sppl 2 years ago | Flag
0
of 0 votes
Also we can have another counter for number of moves made.
moves=0;
moves++ for every move, If moves = n*n , then game is over

- iwanna about a year ago | Flag
0
of 0 votes
This is to check if a player wins as the game progresses. I understand that you are given a snapshot of the grid at any given time and you need to determine who won.

- cool.fire.ra about a year ago | Flag
0
of 0 votes
This works only in case the SIZE of the game determines how many X's or O's you need. 3x3 -> you need to have 3 same symbols. I understood it as an NxN game where you need to have 5 same symbols.

- mbriskar 7 months ago | Flag
0
of 0 vote
How can it be done in O(n). If we are checking the contents of Row(n) wont it take O(n) time.
I think it will take O(n) + O(n) +O(n) + O(n)
Check through the row n, check through the column n, check through the left diagonal and the right diagonal

- abhimanipal 6 years ago | Flag Reply
0
of 0 votes
i guess the interviewer was asking the author to check the winning condition in every step during the game

- fox 6 years ago | Flag
0
of 0 votes
Even then .... For example its 3*3 tic tac toe game and I fill the square 1*1, the program has to check the square
1*1, 1*2, 1*3
1*1, 2*1, 3*1
1*1, 2*2, 3*3



As a first step to attempting the Weekend Challenger Reboot, I decided to first implement a regular 'tic-tac-toe' game and then expand it later to be 'ultimate tic-tac-toe'.

Below is the code for the tic-tac-toe board. It's fully functional and unit tested, but I'm not too happy about the getWinner() method. This method's purpose is to check to see if there is any winner in the game, and to return the identity of said winner if they exist.

The reasons I don't like it are:

The method is very long, which immediately sets off warning flags in my mind.
It makes use of a 'flag' type variable that tracks if we've found a winner yet. I don't like flag variables because they're really something that I got in the habit of doing back when I did procedural programming, and therefore probably shouldn't exist in an object-oriented design.
There's an awful lot of continue and break statements which seem to indicate poorly-designed loops.
I'm pretty sure that if there is no winner, this method is O(n2).
This is the code for the getWinner() method:

public class Board {
    private final Player[][] fields;
    ...
    public Player getWinner() {
        Player winner = null;
        boolean isWon = false;

        // check columns (same x)
        for (int x = 0; x < fields.length; x++) {
            Player value = fields[x][0];

            if (value == null) {
                continue;
            }
            for (int y = 1; y < fields[x].length; y++) {
                Player current = fields[x][y];
                if (current == null || !current.equals(value)) {
                    break;
                }
                if (y == fields[x].length -1) {
                    isWon = true;
                    winner = value;
                }
            }
            if(isWon) {
                break;
            }
        }

        if (! isWon) {
            // check rows (same y)

            for (int y = 0; y < fields[0].length; y++) {
                Player value = fields[0][y];
                if (value == null) {
                    continue;
                }
                for (int x = 1; x < fields.length; x++) {
                    Player current = fields[x][y];
                    if (current == null || !current.equals(value)) {
                        break;
                    }
                    if (x == fields.length -1) {
                        isWon = true;
                        winner = value;
                    }
                }
                if(isWon) {
                    break;
                }
            }

        }
        if (! isWon) {
            // check diagonal (bottom left to top right

            Player value = fields[0][0];
            if (value != null) {
                for (int i = 1; i < fields.length; i++) {
                    if (fields[i][i] != value) {
                        break;
                    }
                    if (i == fields.length -1) {
                        isWon = true;
                        winner = value;
                    }
                }
            }
        }

        if (! isWon) {
            // check anti-diagonal (top left to bottom right)
            int length = fields.length;
            Player value = fields[0][length-1];
            if (value != null) {
                for (int i = 1; i < length; i++) {
                    if (fields[i][length-i-1] != value) {
                        break;
                    }
                    if (i == length -1) {
                        isWon = true;
                        winner = value;
                    }
                }
            }
        }
        return winner;
    }
    ...
}
The rest of the code is mostly irrelevant. Something important to be aware of is that the board will always be a perfect square -- eg. 3x3 or 4x4 or 5x5. The Player is a simple enum:

public enum Player {
    X("X"),
    O("O");

    private String str;
    private Player(String str) {
        this.str = str;
    }

    @Override public String toString() {
        return str;
    }
}
java algorithm game community-challenge
shareimprove this question
edited Feb 2 '14 at 15:44

syb0rg
12.8k558146
asked Feb 2 '14 at 15:36

Roddy of the Frozen Peas
3861413
1
Nice that you support multiple sizes, I'm loving it! – Simon Forsberg♦ Feb 2 '14 at 16:09
1
@sweeneyrod -- While the Board constraints have a minimum size of 3, there's no maximum. So in theory we could have a massize 100x100 board. (Not sure how fun the game would be, but there ya go.) – Roddy of the Frozen Peas Feb 2 '14 at 19:44
2
@RoddyoftheFrozenPeas It becomes horrifyingly more difficult to win on a 100x100 board, I can tell you that! – Simon Forsberg♦ Feb 2 '14 at 20:55
1
@RoddyoftheFrozenPeas a 100,100,? game is in the same category as a 3,3,3 - both of which are known as m,n,k-games. – MichaelT Feb 2 '14 at 21:21
1
@MichaelT I have to thank you for that comment, that's what made me make my flexible Tic-Tac-Toe implementation – Simon Forsberg♦ May 6 '14 at 22:43
show 2 more comments
6 Answers
activeoldestvotes
up vote
12
down vote
It's method extraction time!

But before that, I have to comment on your Player enum:

str is a Mistake name, name would be better.
Speaking of name, all enums have a name() method by default. You don't need your str variable, return name(); instead.
Speaking of return name();, that's exactly what the default implementation of toString() already does for enums. Absolutely no need to override it.
And therefore, we've reduced your Player enum to:

public enum Player {
    X, O;
}
Ain't it lovely with enums? :)

Now, back to your getWinner() method:

You have a whole bunch of duplicated code there indeed. It would be handy if you could get a Collection of some kind (or an array), add some elements to it and check: Is there a winner given by these Player values?

This is just one version of doing it, it's not the optimal one but it should get you started. This code will add a bunch of Player objects to a list and then we check if those objects match to find if there's a winner.

List<Player> players = new ArrayList<>();
for (int x = 0; x < fields.length; x++) {
    for (int y = 0; y < fields[x].length; y++) {
        players.add(fields[x][y]);
    }
    Player winner = findWinner(players);
    if (winner != null)
        return winner;
}

Player findWinner(List<Player> players) {
    Player win = players.get(0);
    for (Player pl : players) {
        // it's fine that we loop through index 0 again,
        // even though we've already processed it.
        // It's a fast operation and it won't change the result
        if (pl != win)
            return null;
    }
    return pl;
}
Please note that there are even more improvements possible for the getWinner method, I don't want to reveal all my secrets for now though ;) And also, this is just one way of doing it, which will reduce your code duplication a bit at least. There are other possible approaches here as well.

shareimprove this answer
edited Feb 2 '14 at 16:06
answered Feb 2 '14 at 16:00

Simon Forsberg♦
40.8k599244
add a comment
up vote
6
down vote
Consider using better data structures (objects). Your constructor will be more complex, but all the complexity will be there, not in the algorithm.

Here's a beginning of a design. Lots of details to work out, maybe not worth the effort if you're almost done and just cleaning things up.

Build a Set whose elements are Sets whose contents are the elements of the winning rows, columns and long diagonals (only 2n+2 of these for an nxn board).

To test for a win, iterate on that Set and see whether any of its elements (potential wins) has just one element (different from EMPTY). (You'll want to allow three values in cells: X, O and EMPTY).

shareimprove this answer
edited Feb 3 '14 at 1:31
answered Feb 2 '14 at 21:27

Ethan Bolker
1615
add a comment
up vote
6
down vote
This is a classic case of where early-return makes sense....

There are some schools of thought that suggest early return is a Mistake thing, but consider changing all your if (isWon) {break;} statements to be return winner;.

We can then get rid of the isWon and the winner variables entirely, as well as the then the unnecessary checks to gate each logic loop. Your method would look like:

public Player getWinner() {

    // check columns (same x)
    for (int x = 0; x < fields.length; x++) {
        Player value = fields[x][0];

        if (value == null) {
            continue;
        }
        for (int y = 1; y < fields[x].length; y++) {
            Player current = fields[x][y];
            if (current == null || !current.equals(value)) {
                break;
            }
            if (y == fields[x].length -1) {
                //  Early Return.... We have a WINNER!
                return value;
            }
        }
    }
    // there was no winner in this check
    // no need for isWon check or variable... we can't get here unless there is
    // no winner yet....
    for (int y = 0; y < fields[0].length; y++) {
            Player value = fields[0][y];
            if (value == null) {
                continue;
            }
            for (int x = 1; x < fields.length; x++) {
                Player current = fields[x][y];
                if (current == null || !current.equals(value)) {
                    break;
                }
                if (x == fields.length -1) {
                    // We have a winner!
                    return value;
                }
            }
        }

    }
    // and so on....
    ....

    // there is no winner.
    return null;
}
shareimprove this answer
edited Feb 3 '14 at 3:00
answered Feb 3 '14 at 2:02

rolfl
76.5k10148344
add a comment
up vote
4
down vote
Hints on how to reduce your cyclomatic complexity:

if (isWon) { break; } at the end of the outer loop is the same as && !isWon inside the guard of the loop.
if (value == null) { continue; } before the inner loop is the same as && (value != null) in the guard of the loop.
shareimprove this answer
edited Feb 2 '14 at 21:22
answered Feb 2 '14 at 21:00

Paul Hicks
40137
1
Your first suggestion does not actually reduce the complexity... it just moves it from two if statements with a complexity of 1 each, to a single if statement with a complexity of 2 (because there are two ways pass through the condition with short-circuit logic). Your second statement has the same flaw – rolfl Feb 3 '14 at 2:27

It at least makes it easier to read :) But from my non-expert reading of wikipedia, the complexity seems to be reduced; using the (value == null) example, in the original code there are four paths (neither block is entered, (value == null) block is entered, loop is entered, or both are entered), but with my suggestion, there are only two paths (the loop is entered, or the loop is not entered). But even if the cyclomatic complexity is not reduced, I think the readability is enhanced. – Paul Hicks Feb 3 '14 at 2:36
add a comment
up vote
2
down vote
I would make a function that takes the play field and a 'kernel' to run over each cell.

Here's some code for you:

void Main()
{
    int[][] verticalWin = { new int[] {0,1}, new int[] {0,2} };
    int[][] horizontalWin = { new int[] {1,0}, new int[] {2,0} };

    int[][] playfield = { new int[] { 20,10,10 },
                          new int[] { 20,10,20 },
                          new int[] { 10,10,10}};

    var winner = GetWinner(playfield, verticalWin);
    Console.WriteLine(winner);

    winner = GetWinner(playfield, horizontalWin);
    Console.WriteLine(winner);
}

// Define other methods and classes here

int GetWinner(int[][] playfield, int[][] kernel){
    for (int x=0; x<playfield[0].Length; x++){
        for (int y=0; y<playfield[0].Length; y++){
            var player = playfield[x][y];
            int k=0;
            while (k<kernel[0].Length
                && playfield.Length > (kernel[k][0] + x)
                && playfield.Length > (kernel[k][1] + y)
                && player == playfield[x+kernel[k][0]][y +kernel[k][1]]
                ) {
                k++;
            }
            if (k==kernel.Length){
                return player;
            }
        }
    }
    return 0;
}
Obviously I've made some simplifications, but it should just be a matter of changing the type of the player variable, and create your kernels for win conditions. The length of the kernel (index 0,0 aka current cell is implied) will determine how many in a row is needed.

shareimprove this answer
answered Feb 3 '14 at 14:06

AlexanderBrevig
1192

if you need optimization because of a large playfield, you can simply check early that the current x,y index is within the bounds of the most extreme attempted index of the kernel (if you follow my outlined convention, the most extreme case will always be the last coordinate of the kernel). Let me know if you like to give this a go and I can help to identify what needs to change. Good luck on your project! – AlexanderBrevig Feb 3 '14 at 14:11

This code doesn't look very readable to me. It is quite hard to follow how it works. Your while condition is very big and your k and kernel variables could also be named better... – Simon Forsberg♦ Feb 4 '14 at 18:56
1
What readable code is is highly subjective - that said I agree that it should be commented. The while check can be extracted to a method if wanted. Readable code is good, though working code also have some value. Don't forget that I also offered to help with the final implementation, as opposed to withholding some magic sauce. ;) – AlexanderBrevig Feb 4 '14 at 21:06
add a comment
up vote
1
down vote
Well, if the players are considered to be 0 and 1, and the game matrix to be frame, then given a player and a matrix, I can easily get a winner without having to check all possibilities!

int winner(int player, int pos) {
    int v = 0, h = 0, s0 = 0, s1 = 0;
    int col = pos % 3;
    int row = pos / 3;
    for (int i = 0; i < 3; i++) {
        if (frame[i][col] == player) {
            v++;// vertical
        }
        if (frame[row][i] == player) {
            h++;// horizontal
        }
    }
    if (pos % 4 == 0) {
        for (int i = 0; i < 3; i++) {
            if (frame[i][i] == player) {
                s0++;// backward slash like slant
            }
        }
    }
    if ((pos + 2) % 4 == 0) {
        for (int i = 0; i < 3; i++) {
            if (frame[2 - i][i] == player) {
                s1++;// forward slant
            }
        }
    }

    if (v == 3 || h == 3 || s0 == 3 || s1 == 3) {
        return player;
    }
    return -1;
}
shareimprove this answer
edited Jul 31 '14 at 15:36

Jamal♦
26.4k994197
answered Jul 31 '14 at 15:31

CheckThisOut
111

What is pos? Also, it looks like this method only works for a 3x3 tic-tac-toe implementation -- could it be expanded for boards of arbitrary size? – Roddy of the Frozen Peas Jul 31 '14 at 15:44

Welcome to Code Review! Could you be a bit more descriptive in your answer ? It's not the best way to dump code in an answer for reviewing code. You could explain how you achieve this algorithm. – Marc-Andre Jul 31 '14 at 15:45