 public String addBinary(String a, String b) {
        // Write your code here
        int  i = a.length() - 1;
        int j = b.length() - 1;
        if (i > j) return addBinary(b,a);
        int carry = 0;
      //  StringBuilder sb = new StringBuilder();
        String rst = "";
        while(i >= 0) {
          int sum =  (int) (a.charAt(i) - '0') + (int) (b.charAt(j) - '0') + carry;
           //sb.append(String.valueOf(sum % 2));
           rst = String.valueOf(sum % 2) + rst;
           carry = sum / 2;
           i--;
           j--;
        }


          while (j >= 0) {
             int sum = (int) (b.charAt(j) - '0') + carry;
            //sb.append(String.valueOf(sum % 2));
              rst = String.valueOf(sum % 2) + rst;

             carry = sum / 2;
             j--;
              //mistake forgot this
          }
        if (carry == 1) rst = "1" + rst;
            //sb.append(String.valueOf(carry));

    //   sb.reverse();
    //     return sb.toString();
        return rst;
     }