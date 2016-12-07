int hashValue()	{
        int hash = 31*my_var_;
        for(int i= 0; i<my_str_.length();i++) {
        hash = my_str_[i]+hash*31;
        }
        return hash;
        }


        复制代码
        In data structure Hash, hash function is used to convert a string(or any other type) into an integer smaller than hash size and bigger or equal to zero. The objective of designing a hash function is to "hash" the key as unreasonable as possible. A good hash function can avoid collision as less as possible. A widely used hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:

        hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33 + ascii(d)) % HASH_SIZE

        = (97* 33^3 + 98 * 33^2 + 99 * 33 +100) % HASH_SIZE

        = 3595978 % HASH_SIZE

        here HASH_SIZE is the capacity of the hash table (you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

        Given a string as a key and the size of hash table, return the hash value of this key.f



        Example
        For key="abcd" and size=100, return 78

        Clarification
        For this problem, you are not necessary to design your own hash algorithm or consider any collision issue, you just need to implement the algorithm as described.
        复制代码
        对于overflow要特别小心，即便是intermediate result，也要小心overflow

        跟Fast Power那道题类似，modular multiplication addition的证明在此

        (a + b) % p = (a % p + b % p) % p （1）
        (a - b) % p = (a % p - b % p) % p （2）
        (a * b) % p = (a % p * b % p) % p （3）
        a ^ b % p = ((a % p)^b) % p （4）
        复制代码
        1 class Solution {
    2     /**
     3      * @param key: A String you should hash
     4      * @param HASH_SIZE: An integer
     5      * @return an integer
     6      */
            7     public int hashCode(char[] key,int HASH_SIZE) {
        8         if (key.length == 0) return 0;
        9         int result = 0;
        10         int base = 1;
        11         for (int i=key.length-1; i>=0; i--) {
            12             int num = (int)(key[i] - '\0');
            13             result += modMultiply(num, base, HASH_SIZE);
            14             result = result % HASH_SIZE;
            15             base = modMultiply(base, 33, HASH_SIZE);
            16         }
        17         return result % HASH_SIZE;
        18     }
    19
            20     public int modMultiply(int a, int b, int c) {
        21         long temp = (long)a * b;
        22         return (int)(temp % c);
        23     }
    24 };
复制代码
        15行如果如果每次都 base *= 33，即使把base 定义为long型，String一长也会溢出的; 我又试了用Math.pow(33, i), 一样overflow。说明强行算33的power还是不行的，是会溢出的。还是要用Modular Multiplication

        (A * B) mod C, 需要注意的是，A、B、C都定义为int，A*B要小心overflow, 所以用long来存这个乘积，然后mod C之后再把结果存成int型

        第21行，如果是就是错的：a * b已经overflow了

        1 public int modMultiply(int a, int b, int c) {
        2          long temp = a * b;
        3          return (int)(temp % c);
        4      }
        5 };
        处理方法可以是：long temp = (long) a * b;  或者a和b都定义为long型然后long temp = a * b



        方法二：不用long type, 网上别人的做法，没看懂

        复制代码
        1     public int hashCode(char[] key,int HASH_SIZE) {
        2         int result = 0;
        3         for (int i = 0; i < key.length; i++) {
        4             result = helper(result, 33, HASH_SIZE);
        5             result += key[i];
        6             result %= HASH_SIZE;
        7         }
        8         return result;
        9     }
        10
        11     int helper(int num, int base, int mod) {
        12         int result = 0;
        13         int temp = num - mod;
        14         for (int i = 0; i < base; i++) {
        15             if (result + temp > 0) {
        16                 result += temp;
        17             } else {
        18                 result += num;
        19             }
        20         }
        21         return result;