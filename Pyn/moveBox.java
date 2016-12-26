package notTested;//select one as bottom as experiment, try every other box to see if can be above;  if true, then set this new box as second bottom,
//update the max stack and height accordingly
// and
//recursively do the same thing; if it's only be the top one, then create a stack , insert this box as the bottom of the new stack; return the stack

private class Box {
    public int width;
    public int len;
    public int height;

    public Box(int width, int len, int height) {
        this.width = width;
        this.len = len;
        this.height = height;
    }

    public boolean canBeAbove(Box another) {
        return this.width < another.width &&
                    this.height < another.height &&
                    this.len < another.len;
    }

}

public class moveBox {
    public stack main() {
        HashMap<Box, ArrayList<Box>>  stackMap = new HashMap<Box, ArrayList<Box>> ();
        ArrayList<Box> result = createStackCached(boxes, null, stackMap);

    }
    ArrayList<Box>createStack(ArrayList<Box> boxes, Box bottom) {
        ArrayList<Box>  maxStack = null;
        int maxHeight = 0;
        if (boxes == null) {
            return maxStack;
        }

        for(int i = 0; i < boxes.length;  i++) {
            if (boxes[i].canBeAbove(bottom)) {
                    ArrayList<Box> newStack = createStack(boxes, boxes[i]);
                    int newHeight = getLength(newStack);
                    if  (newHeight > maxHeight) {
                        maxStack = newStack;
                        maxHeight = newHeight;
                    }
            }

        }

        if (maxStack == null) {
            maxStack = new ArrayList<Box>();
        }
        //ERROR
        if (bottom != null)  {
            maxStack.add(0, bottom);
        }

        return maxStack
    }


    ArrayList<Box>createStackCached(ArrayList<Box> boxes, Box bottom,HashMap<Box, ArrayList<Box>>  stackMap ) {
        ArrayList<Box>  maxStack = null;

        int maxHeight = 0;

        if (bottom != null && stackMap.containsKey(bottom)) {
            return (ArrayList<Box> ) stackMap.get(bottom).clone();
        }

        for(int i = 0; i < boxes.length;  i++) {
            if (boxes[i].canBeAbove(bottom)) {
                    ArrayList<Box> newStack = createStack(boxes, boxes[i]);
                    int newHeight = getLength(newStack);
                    if  (newHeight > maxHeight) {
                        maxStack = newStack;
                        maxHeight = newHeight;
                    }
            }

        }

        if (maxStack == null) {
            maxStack = new ArrayList<Box>();
        }
        //ERROR
        if (bottom != null)  {
            maxStack.add(0, bottom);
            //stackMap.put(0,maxStack);

            stackMap.put(bottom, maxStack)
        }
        return maxStack
    }


}

//DP
createStackDP() {
  int[] F = new int(boxes.length+1);


  List<stack>  maxStackList;
F[0] = 0;

maxStackList[0];

  for(int i = 0; i < boxes.length; i++) {
    for(int k = i-1; k >= 1; k++) {
        bottom = getBottomOf(maxStackList[k]);
        if (boxes[i].canBeAbove(bottom)) {
            F[i] = F[i-k] + 1;
            maxStack.add(boxes[i]);
            break;  //find
        }
    }

    maxStackList.add(maxStack);

}


}