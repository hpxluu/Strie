import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedLst<T> implements Iterable<T> {
	
	private class Node<T> {
		T value;
		Node<T> next;	
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	private Node<T> head;  
	private Node<T> tail;  
	private int numNodes;
		

	public LinkedLst(){ 
		head = tail = null; 
		numNodes = 0;
	}
		
	public int size(){
		return numNodes; 
	}

	public void addLast(T value){
		if (value != null) {
			Node<T> n = new Node<>(value);
			if (numNodes==0) {
				head = tail = n; numNodes++;
			}
			else {
				tail.next = n; tail = n; numNodes++;
			}
		}
		else {
			throw new IllegalArgumentException("Cannot add null value!");
		}
	}
	
	public T removeFirst(){
		
		T returnvalue = null;

		if (numNodes != 0) { 
			returnvalue = head.value;
			head = head.next; numNodes--; 
		}
		return returnvalue;
	}
	
	public boolean remove(T value){
		
		boolean isRemoved = false; Node<T> n = head;
		
		while(n!=null) {
			
			if(n.value.equals(value)) {
				if (n == head) {
					removeFirst(); isRemoved = true; break;
				}
				
				if (n.next == tail) {
					tail = n; isRemoved = true; numNodes--; break;
				}
				
				else {
					n.next = n.next.next; isRemoved = true; numNodes--; break;
				}
			}
			n = n.next;
		}		
		return isRemoved; 
	}
	
	public T get(T value){
		
		T returnValue = null; Node<T> n = head; 
		
		while(n!=null) {
			if (n.value.equals(value)) {
				returnValue = n.value; break;
			}
			n = n.next;
		}
		return returnValue;
	}
	
	public Iterator<T> iterator(){
		return new Iterator<>(){

			private Node<T> current = head;

			public boolean hasNext(){			
				return (current!=null);
			}
			
			public T next(){
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				T toReturn = current.value;
				current = current.next;
				return toReturn;
			}
		};
	}
	
	@Override
	public String toString(){
		// list all values from head to tail
		StringBuilder s = new StringBuilder("[");
		Node<T> current = head;
		String prefix="";
		while (current!=null){
			s.append(prefix);
			s.append(current.value);
			prefix=",";
			current = current.next;
		}
		s.append("]");
		return s.toString();
	}
}
