//Axita Patel

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class PriorityQueue2011 <E extends Comparable<? super E>> extends AbstractQueue <E> {

	private int size;
	private Object prQu[];
	private final int DEFAULT_CAPACITY= 11;
	private int currentCapacity = 11;
	
	
	/**
	 * Default constructor for the priority queue
	 * Create a priority queue with a default capacity of 11
	 */
	PriorityQueue2011() {
		prQu = new Object[DEFAULT_CAPACITY];
		prQu[0] = null;
		this.size = 0;
	}
	
	/**
	 * Swaps the position of the elements specified by the index x and y in the
	 * priority queue
	 * @param x - the index of the element to swap
	 * @param y - the index of the element to swap
	 */
	private void swap(int x, int y) {
		Object temp = prQu[x];
		prQu[x] = prQu[y];
		prQu[y] = temp;	
	}
	
	/**
	 * Restores the heap property by moving up towards the parent
	 * @param c - the index of the element to indicate where to begin
	 * restoring the heap order
	 * uses code from https://algs4.cs.princeton.edu/24pq/
	 */
	@SuppressWarnings("unchecked")
	private void upHeap(int c) {
		int p = c/2;
		while(c > 1) {
		
			if(((E)prQu[p]).compareTo((E) prQu[c]) > 0) {
				swap(c, p);
				 c = p;
				 p = c/2;
			}
			else {
				break;
			}
		} 
	
	}
	
	/**
	 * Restores the heap property by moving down towards the children
	 * @param p - the index of the element to indicate where to begin
	 * restoring the heap order
	 */
	@SuppressWarnings("unchecked")
	private void downHeap(int p) {
		
		int lc;
		int rc;
		int smallest;
		while(p < size) {
			lc = p *2;
			rc = p *2 + 1;
			smallest = p;
			if(lc <= size && ((E) prQu[p]).compareTo(((E) prQu[lc])) > 0) {
				smallest = lc;
			}
			if(rc <= size && ((E)prQu[smallest]).compareTo((E) prQu[rc]) > 0) {
				smallest = rc;
				
			}
			if(smallest != p) {
				swap(p, smallest);
				p = smallest;
			}
			else {
				break;
			}
			
		} 
		
	}
	
	/**
	 * Grows the size of the array storing the priority queue
	 */
	private void grow() {
		//size increased by two folds each time the method is called 
		// add 1 for the first null element at index 0
		int newSize = (this.size * 2) + 1;
		currentCapacity = newSize;
		prQu = Arrays.copyOf(prQu, newSize);
	}
	
	/**
	 * Inserts the given element into the priority queue
	 * @param e - the element to be added 
	 * @return true 
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub	
		return add(e);
	}
	
	/**
	 * Inserts the given element into the priority queue
	 * @param e - the element to be added 
	 * @return true 
	 * @throws NullPointerException if the given element is null
	 */
	public boolean add(E e) {
		if(e == null) {
			throw new NullPointerException("Cannot add null element");
		}
		if(currentCapacity <= this.size + 1) {
			grow();
		}
		
		prQu[this.size + 1] = e;
		upHeap(this.size + 1);
		
		this.size++;
		return true;
	}

	/**
	 * Retrieves and removes the head of the priority queue
	 * @return the removed element which was the head, or null if the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		if(this.size == 0) {
			return null;
		}
		E ans = (E) prQu[1];
		swap(1, size);
		this.size--;
		
		downHeap(1);
		
		return ans;
	}
	
	/**
	 * Retrieves and removes the head of the priority queue
	 * @return the removed element which was the head
	 * @throws NoSuchElementException when the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	public E remove() {
		
		if(this.size == 0) {
			throw new NoSuchElementException("The priority queue is empty");
		}
		E ans = (E) prQu[1];
		swap(1, size);
		this.size--;
		
		downHeap(1);
		
		return ans;
	}

	/**
	 * Retrieves the head of the priority queue (does not remove it)
	 * @return the head of the priority queue, or null if the queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(this.size == 0) {
			return null;
		}
		return (E) prQu[1];
		
	}
	
	/**
	 * Retrieves the head of the priority queue (does not remove it)
	 * @return the head of the priority queue
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	public E element() {
		if(this.size == 0) {
			throw new NoSuchElementException("The priority queue is empty");
		}
		return (E) prQu[1];
	} 

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the number of elements in the priority queue
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	/**
	 * @return the string representation of the objects in the priority queue
	 */
	public String toString() {
		if(this.size == 0) {
			return "[null]";
		}
		
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i = 0; i < this.size; i++) {
			str.append(prQu[i]);
			str.append(", ");
			
		}
		str.append(prQu[this.size]);
		str.append("]");
		return str.toString();
	}
	
	/**
	 * Gets the height of the tree
	 * @param n - the starting index of the tree 
	 * @return the height of the tree from the given index 
	 */
	private int get_height(int n) {
		if (n > size)
			return 0;
		int left = 1 + get_height(n*2);
		int right = 1 + get_height(n*2+1);
		return Math.max(left, right);
	}
	
	
	/**
	 * @return a tree string representation of the objects in the priority queue
	 */
	public String toTree() {
		if(this.size == 0) {
			return "";
		}
		StringBuilder tree = new StringBuilder();
		
		int height = get_height(1);
		
		int totalSpace = (int) Math.pow(2, height)*4 +(int) Math.pow(2, height) -1;
		int initialSpace = totalSpace/2;
		for(int j = 1; j <= size; j *= 2) {
			
			
			tree.append(initialSpace != 0 ? String.format("%"+(initialSpace)+"s","") : "");
			int interSpace = initialSpace*2;
			for (int i = j; i < j*2; i++) {
				if (i <= size)
					tree.append(String.format("%s", prQu[i]) + String.format("%"+(interSpace)+"s","") ) ;
				
			}
			initialSpace /= 2;
			
			tree.append("\n");
		
			}
		
		return tree.toString();
	}
	
	public static void main(String args[]) {
		PriorityQueue2011<Integer> heap= new PriorityQueue2011<Integer>();
		heap.add(2);
		heap.add(4);
		heap.add(9);
		heap.add(6);
		heap.add(7);
		heap.add(11);
		heap.add(11);
		heap.add(8);
		heap.add(1);
		heap.remove();
		heap.remove();
		heap.add(3);
		
		String a = heap.toString();
		System.out.println(a);
	}

}

