  public boolean isSubsequence(String s, String t) {
        if (t == null) return false;
        if (s == null) return true;
        
        int i = 0, j = 0;
        
        while(i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        
        if (i == s.length()) return true;
        else return false;
    }



    II

     public boolean isSubsequence(String s, String t) {
        int MAX_LEN = 500000;
        //int[][] map = new int[26][MAX_LEN];
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
    //    ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>(26);
        //Init to -1;
        
        
        for (int i = 0, int k = 0; i < t.length(); i++) {
           char c = t.charAt(i);
           if (!map.containsKey(c))  {
               map.put(c, new ArrayList<>());  
           }
           map.getKey(c).add(i);
        }
        
        for (int i = 0, int k = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) return false;
            else {
                ArrayList<> temp = map.getValue(c);
                if (temp.size() <= i) return false;
                k = getNextHigher(temp, i);
                if (k ==  -1 || k >= t.length()) return false;
             
            }
        }

                int MAX_LEN = 500000;
        //int[][] map = new int[26][MAX_LEN];
        
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
    //    ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>(26);
        //Init to -1;
        
        
        for (int i = 0, int k = 0; i < t.length(); i++) {
           char c = t.charAt(i);
           if (!map.containsKey(c))  {
               map.put(c, new ArrayList<>());  
           }
           map.getKey(c).add(i);
        }
        int i = 0;
        for (int k = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) return false;
            else {
                ArrayList<> temp = map.getValue(c);
                if (temp.size() <= i) return false;
                k = getNextHigher(temp, i);
                if (k ==  -1 || k >= t.length()) return false;
             
            }
        }
        if (i == s.length()) return true;
        else return false;
    }
    
    int getNextHigher(ArrayList<> temp, int k) {
        int start = 0; 
        int end = temp.size() - 1;
        while( start + 1 < end) {
            
            if (k >= temp.get(m)) {
                start = m;
            }
            else end = m;
        }
        if (temp.get(end) >= k) return end;
        else return start;
        
    }
       class MyClass {
        int index;
        ArrayList<Integer> pos;
        MyClass() {
            index = 0;
            pos = new ArrayList<Integer>();
        }
        void setIndex(int i) {index = i;}
        void add(int) i) {pos.add(i);}
        void size() {}
    }
    public boolean isSubsequence(String s, String t) {
        int MAX_LEN = 500000;
        //int[][] map = new int[26][MAX_LEN];
        
        HashMap<Character, MyClass> map = new HashMap<>();
    //    ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>(26);
        //Init to -1;
        
        
        for (int i = 0, int k = 0; i < t.length(); i++) {
           char c = t.charAt(i);
           if (!map.containsKey(c))  {
               map.put(c, new MyClass());  
           }
           map.getKey(c).add(i);
        }
        
        int i = 0;
        
        for (int k = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) return false;
            else {
                MyClass temp = map.getValue(c);
                if (temp.size() <= i) return false;
                k = temp.index;
                if (k ==  -1 || k >= t.length()) return false;
                getNextHigher(temp.pos, k, i);
                temp.setIndex(k++);
            }
        }
        if (i == s.length()) return true;
        else return false;
    }
    
    int getNextHigher(ArrayList<> temp, int start, int k) {