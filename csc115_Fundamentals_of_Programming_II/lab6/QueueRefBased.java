public class QueueRefBased implements Queue {
    
    private QueueNode front;
    private QueueNode back;
    
    public QueueRefBased() {
        front = null;
        back = null;
    }
    
    public int size() {
        int count = 0;
        // TODO: complete
        return count;
    }


    public boolean isEmpty() {
        // TODO: complete
        return true;
    }


    public void enqueue (int element) {
        // TODO: complete
    }

    public int dequeue() throws QueueEmptyException {
        // TODO: complete
        
        return 0;
    }

    public int peek() throws QueueEmptyException {
        // TODO: complete
        
        return 0;
    }

    public void makeEmpty() {
        // TODO: complete
    }
}

