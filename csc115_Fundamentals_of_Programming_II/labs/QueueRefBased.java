public class QueueRefBased<T> implements Queue<T> {
    
    private QueueNode<T> front;
	private QueueNode<T> back;
	
	public QueueRefBased(){
        front = null;
        back = null;
    }
    
    public int size() {
        int count = 0;
        // TODO: complete
		QueueNode<T> temp = front;
		while(temp != null){
			count++;
			temp=temp.getNext();
		}
        return count;
    }


    public boolean isEmpty() {
        // TODO: complete
		if(front == null)
			return true;
		return false;
    }


    public void enqueue (T element) {
        // TODO: complete
		QueueNode temp = new QueueNode(element);
		if(front == null){
			front = temp;
			back = temp;
		}else{
		back.setNext(temp);
		back = temp;
		}
    }

    public T dequeue() throws QueueEmptyException {
        // TODO: complete
        if(front == null){
			throw new QueueEmptyException("dequeue");
		}
		T temp = front.getValue();
		
		front = front.getNext();
		
		
		
        return temp;
    }

    public T peek() throws QueueEmptyException {
        // TODO: complete
		if(front == null){
			throw new QueueEmptyException("peek");
		}
        
        return front.getValue();
    }

    public void makeEmpty() {
        // TODO: complete
		front = null;
		back = null;
    }
}

