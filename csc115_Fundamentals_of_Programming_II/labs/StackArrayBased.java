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
        return top +1;
    }

    public boolean isEmpty() {
		if(top == -1)
			return true;
		return false;
    }

    public void push(char val) {
		if(top+1 >= data.length){
			char[] newdata = new char[data.length*2];
			for(int i =0; i<data.length; i++){
				newdata[i] = data[i];
			}
			data = newdata;
		}
		top++;
		data[top] = val;
    }


    public char pop() throws StackEmptyException {
		if(top == -1){
			throw new StackEmptyException("pop method");
		}
		char temp = data[top];
		data[top] = 0;
		top--;
		
        return temp;
    }


    public char peek() throws StackEmptyException {
		if(top == -1){
		throw new StackEmptyException("peek method");
		}
		
        return data[top];
    }


    public void makeEmpty() {
		for(int i =0; i<= top; i++){
			data[i] = 0;
		}
		top = -1;
		
    }

    public String toString() {
        String result = "{";
		for(int i =0; i<this.size(); i++){
			result += data[i];
			if(data[i+1] != 0){
				result += ", ";
			}
		}

        result += "}";
        return result;
    }
}

