 public boolean isUnique(String str) {
        // write your code here
        if (str == null || str.length() <= 1) return true;

        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                return false;
            }
            else {
                set.add(str.charAt(i));
            }
        }
        return true;
    }

       public boolean isUnique(String str) {
        // write your code here
        if (str == null || str.length() <= 1) return true;
        if (str.length() > 128) return false;

        boolean[] set = new boolean[256];

        for (int i = 0; i < str.length(); i++) {
            int temp = str.charAt(i);
            if (set[temp]) {
                return false;
            }
            else {
                set[temp] = true;
            }
        }
        return true;
    }

        public boolean isUnique(String str) {
        // write your code here
        if (str == null || str.length() <= 1) return true;
        if (str.length() > 20) return false;

        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int val =  str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            else {
                checker |= 1 << val;
            }
        }
        return true;
    }