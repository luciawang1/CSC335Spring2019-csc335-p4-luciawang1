import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class TestSet1 {

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
		assertTrue(a.remove("E"));
		assertTrue(a.remove("H"));

	}

	@Test
	public void testNoSuchElementException() {
		ArraySet<String> a = new ArraySet<String>();
		Iterator<String> i = a.iterator();
		assertThrows(NoSuchElementException.class, () -> { i.next(); });
	}

	@Test
	public void testIteratorIllegalStateExceptionAndRemoveWithNext() {
		ArraySet<String> a = new ArraySet<String>();
		Iterator<String> i = a.iterator();
		a.add("A");
		assertThrows(IllegalStateException.class, () -> { i.remove(); });
		assertTrue(i.hasNext());
		assertEquals(i.next(), "A");
		i.remove();

		assertEquals(a.size(), 0);
	}

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

		while (i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		assertFalse(i.hasNext());
	}

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
		assertTrue(i.next().equals("A"));
		i.remove();
		assertTrue(i.next().equals("B"));
		i.remove();
		while (i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		assertEquals(a.size(), 8);
	}
}
