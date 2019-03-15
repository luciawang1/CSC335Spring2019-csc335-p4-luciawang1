import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Set implemented with an array and an iterator object
 * 
 * @author Lucia Wang
 *
 * @param <E>
 */

public class ArraySet<E> extends AbstractSet<E> {

	private int size; // number of elements in the set
	public Object[] set; // array to store the set

	/**
	 * Constructor: constructs an ArraySet object and initializes size and set
	 */
	public ArraySet() {
		size = 0;
		set = new Object[1];
	}

	/**
	 * ArraySetIterator is a private inner class that implements Iterator
	 */
	class ArraySetIterator<T> implements Iterator<T> {

		private boolean removable = false; // removable is only true if next has been called right before remove is
											// called
		int pos = 0; // current position we are iterating at

		/**
		 * hasNext(): Determines if iterator has a next element
		 * 
		 * Does this by determining whether the current position is less than the size
		 * of the data, if it is, then there are more items to iterate over
		 * 
		 * @return true if there is a next element, false otherwise
		 */
		@Override
		public boolean hasNext() {
			removable = false;
			if (pos < size)
				return true;
			return false;
		}

		/**
		 * next(): Returns the next element
		 * 
		 * Does this by incrementing the position and returning the item that is the
		 * next element
		 * 
		 * @return the next element, or NoSuchElementException if there is no next
		 *         element
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			pos += 1;
			removable = true;
			return (T) set[pos - 1];
		}

		/**
		 * remove(): Removes the element at the current position of iterator
		 * 
		 * Since this method cannot be called unless next() is called, the position
		 * index has to be decremented bc next increments it, so then we can remove the
		 * element by shifting all the other elements in the set
		 */
		@Override
		public void remove() {
			if (!removable)
				throw new IllegalStateException();
			pos -= 1;
			for (int i = pos; i < size - 1; i++) {
				set[i] = set[i + 1];
			}
			size -= 1;
			removable = false;
		}
	}

	/**
	 * iterator(): Creates an iterator object
	 * 
	 * Specifically, creates an ArraySetIterator object, which implements Iterator
	 * 
	 * @return Iterator<E> object that walks over the set
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArraySetIterator<E>();
	}

	/**
	 * size(): returns size of ArraySet
	 * 
	 * returns the global variable size, which gets updated every time elements are
	 * added or removed
	 * 
	 * @return size (int)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * add(): adds an element
	 * 
	 * Adds the element E to the set if it does not already exist in the ArraySet
	 * 
	 * @return true if if the element was successfully added to the set, false if it
	 *         was already in the set
	 */
	@Override
	public boolean add(E element) {
		for (int i = 0; i < size; i++) {
			if (set[i].equals(element))
				return false;
		}
		if (set.length == size) {
			doubleSize();
		}
		set[size] = element;
		size++;
		return true;
	}

	/**
	 * doubleSize(): doubles the length of the array
	 * 
	 * When we are adding elements and we need more space in the array, this method
	 * is called to double the length of the array
	 */
	private void doubleSize() {
		Object[] newSet = new Object[set.length * 2];
		for (int i = 0; i < set.length; i++) {
			newSet[i] = set[i];
		}
		set = newSet;
	}

	/**
	 * remove(): Removes the Object elem from the array
	 * 
	 * If the element is in the set, this method removes it from the set, if it is
	 * not, nothing happens
	 * 
	 * @return true if the Object elem has been successfully removed from the array,
	 *         false if it wasn't in the array to begin with
	 */
	@Override
	public boolean remove(Object elem) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (elem.equals(set[i])) {
				index = i;
			}
		}
		if (index == -1)
			return false;
		else {
			for (int i = index; i < size - 1; i++) {
				set[i] = set[i + 1];
			}
			size--;
			return true;
		}
	}
}
