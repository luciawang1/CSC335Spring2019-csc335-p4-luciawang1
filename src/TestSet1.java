import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * JUnit Test Case for ArraySet
 * 
 * @author Lucia Wang
 *
 */
class TestSet1 {

	/**
	 * Test method for {@link ArraySet#add(Object)},
	 * {@link ArraySet#remove(Object)}, and {@link ArraySet#size()}
	 */
	@Test
	void testArraySetString() {
		ArraySet<String> a = new ArraySet<String>();
		assertTrue(a.add("A"));
		assertFalse(a.add("A"));
		assertTrue(a.add("B"));
		assertEquals(a.size(), 2);
		assertTrue(a.remove("B"));
		assertFalse(a.remove("B"));
		assertEquals(a.size(), 1);
		assertTrue(a.remove("A"));
		assertEquals(a.size(), 0);
		assertTrue(a.add("C"));
		assertTrue(a.add("D"));
		assertTrue(a.add("E"));
		assertTrue(a.add("F"));
		assertTrue(a.add("G"));
		assertTrue(a.add("H"));
		assertTrue(a.add("I"));
		assertTrue(a.add("J"));
		assertEquals(a.size(), 8);
		assertTrue(a.remove("E"));
		assertTrue(a.remove("H"));
		assertEquals(a.size(), 6);
	}

	/**
	 * Test method for {@link ArraySet#iterator() NoSuchElementException}
	 */
	@Test
	public void testNoSuchElementException() {
		ArraySet<String> a = new ArraySet<String>();
		Iterator<String> i = a.iterator();
		assertThrows(NoSuchElementException.class, () -> {
			i.next();
		});
	}

	/**
	 * Test method for {@link ArraySetIterator#remove} after Next() has been called,
	 * and {@link ArraySet#iterator() IllegalStateException}
	 */
	@Test
	public void testIteratorIllegalStateExceptionAndRemoveWithNext() {
		ArraySet<String> a = new ArraySet<String>();
		Iterator<String> i = a.iterator();
		a.add("A");
		assertThrows(IllegalStateException.class, () -> {
			i.remove();
		});
		assertTrue(i.hasNext());
		assertEquals(i.next(), "A");
		i.remove();

		assertEquals(a.size(), 0);
	}

	/**
	 * Test method for {@link ArraySetIterator#hasNext(), next()}
	 */
	@Test
	public void testIteratorHasNextAndNext() {
		ArraySet<String> a = new ArraySet<String>();
		Iterator<String> i = a.iterator();
		assertTrue(a.add("A"));
		assertTrue(a.add("B"));
		assertTrue(a.add("C"));
		assertTrue(a.add("D"));
		assertTrue(a.add("E"));
		assertTrue(a.add("F"));
		assertTrue(a.add("G"));
		assertTrue(a.add("H"));
		assertTrue(a.add("I"));
		assertTrue(a.add("J"));

		String s = "";
		while (i.hasNext()) {
			s += i.next();
		}
		assertEquals(s, "ABCDEFGHIJ");
		assertFalse(i.hasNext());
	}

	/**
	 * test method for {ArraySetIterator#remove()}
	 */
	@Test
	public void testIteratorMoreRemove() {
		ArraySet<String> a = new ArraySet<String>();
		Iterator<String> i = a.iterator();
		assertTrue(a.add("A"));
		assertTrue(a.add("B"));
		assertTrue(a.add("C"));
		assertTrue(a.add("D"));
		assertTrue(a.add("E"));
		assertTrue(a.add("F"));
		assertTrue(a.add("G"));
		assertTrue(a.add("H"));
		assertTrue(a.add("I"));
		assertTrue(a.add("J"));

		assertTrue(i.hasNext());
		assertThrows(IllegalStateException.class, () -> {
			i.remove();
		});
		assertTrue(i.next().equals("A"));
		i.remove();
		assertTrue(i.next().equals("B"));
		i.remove();
		assertTrue(i.next().equals("C"));
		assertTrue(i.next().equals("D"));
		while (i.hasNext()) {
			if (!i.next().equals(null))
				i.remove();
		}
		assertEquals(a.size(), 2);
		assertFalse(i.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			i.next();
		});

		Iterator<String> i2 = a.iterator();
		String s2 = "";
		while (i2.hasNext()) {
			s2 += i2.next();
		}
		assertEquals(s2, "CD");
	}
}
