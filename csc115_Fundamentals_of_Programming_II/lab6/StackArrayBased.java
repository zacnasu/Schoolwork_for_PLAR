public class StackArrayBased implements Stack {
    private static final int INIT_SZ = 4;
    private char[] data;
    private int top;
    // notice there is no count

    public StackArrayBased() {
        data = new char[INIT_SZ];
        top = -1;
    }

    public int size() {
        return top+1;
    }

    public boolean isEmpty() {
        if(data[0]==0){
            return true;
        }
        return false;
    }

    public void push(char val)  {
        if(top == data.length-1){
            char[] temp = new char[data.length*2];
            for(int i = 0; i<data.length ; i++){
                temp[i] = data[i];
            }
            data = temp;
        }
        data[++top] = val;
    }


    public char pop() throws StackEmptyException {
        if(top == -1)
            throw new StackEmptyException("ya fucked up");

        char temp = data[top];
        data[top--] = 0;
        return temp;
    }


    public char peek() throws StackEmptyException {
        if(top == -1)
            throw new StackEmptyException("ya fucked up");

        return data[top];
    }


    public void makeEmpty() {
        data = new char[INIT_SZ];
        top = -1;
    }

    public String toString() {
        String result = "{";
        for(int i = 0; i < top; i++){
            result += data[i] + ", ";
        }
        result+= data[top];
        result += "}";
        return result;
    }
}

