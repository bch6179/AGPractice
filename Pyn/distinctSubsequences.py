state: f[i][j] first i of S choose first j of T
 • function: f[i][j] = f[i - 1][j] + f[i - 1][j - 1]  // S[i-1] == T[j-1],means current b useful,so remove privious  b to see how many
  •                     = f[i - 1][j]                       //  S[i-1] != T[j-1] remove 
  
   • initialize: f[i][0] = 1, f[0][j] = 0 (j > 0) • answer: f[n][m] (n = sizeof(S), m = sizeof(T))
➨23页


