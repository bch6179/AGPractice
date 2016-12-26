IR {
    10 1 5 1 24/10 = 2 4 xxiiii
    10 5 1
    int[] values = { 1000,500, 100, 10, 5,1};




    while (num > 0) {
        int  k = num / values[i];
        for (int i = 0; i < k; i++) {
                sb.append(symbol[i]);
        }
        num -= values[i] * k;
        i--;
    }

    String symbols[] = {"L", "X"}

Map<Character, Integer> map = new HashMap<>() {
    put('x', 10);
    put('v', 5);
};
  // IVII

RI(String input) {
    char[] in = input.toCharArray();
    for (char x : in) {
        int temp = map.get(x)   ;
        sum +=  temp > prev ? (-2 * temp) : temp;
    }
}