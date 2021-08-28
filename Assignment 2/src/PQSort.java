//Axita Patel

public class PQSort {
	
	/**
	 * Sorts the elements in the array in non-decreasing order using the java PriorityQueue 
	 * @param a- an array of elements
	 * 
	 */
	public static <E extends Comparable <? super E>> void heapSort(E[] a) {
		PriorityQueue2011<E> prQu = new PriorityQueue2011<E>();
		
		for(E e: a) {
			prQu.add(e);
		}
		
		for(int i = 0; i < a.length; i++) {
			a[i] = prQu.remove();
		}
		
	}
	
	/**
	 * Sorts the elements in the array in non-decreasing order using the PriorityQueue2011 implementation
	 * @param a- an array of elements
	 * 
	 */
	public static <E extends Comparable <? super E>> void heapSort2011(E[] a) {
		PriorityQueue2011<E> prQu = new PriorityQueue2011<E>();
		
		for(E e: a) {
			prQu.add(e);
		}
		
		for(int i = 0; i < a.length; i++) {
			a[i] = prQu.remove();
		}

			
	}
	
	

}
