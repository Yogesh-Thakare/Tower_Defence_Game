package td.game.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is used for the bag in the game
 * @author Team3
 * @param <Item> Items in the bag
 */
public class Box<Item> implements Iterable<Item> 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private int n;               // number of elements in bag
	private Node<Item> first;    // beginning of bag

	/**
	 * @author Team3
	 * This class is a helper linked list class
	 * @param <Item>
	 */
	private static class Node<Item> 
	{
		private Item item;
		private Node<Item> next;
	}
	
	/**
	 * This constructor is used to initializes an empty bag.
	 */
	public Box() 
	{
		first = null;
		n = 0;
	}
	
	/**
	 * This function is used to check if the bag is empty
	 * @return true if this bag is empty; false otherwise
	 */
	public boolean isBagEmpty() 
	{
		return first == null;
	}
	
	/**
	 * Returns the number of items in this bag.
	 * @return the number of items in this bag
	 */
	public int size() 
	{
		return n;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<Item> iterator()  
	{
		return new ListIterator<Item>(first);  
	}
	
	/**
	 * This function adds the item to this bag.
	 * @param item the item to add to this bag
	 */
	public void addItems(Item item) 
	{
		try 
		{
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			n++;
		} 
		catch (Exception e) 
		{
			logger.writer("Box.java", e);
		}
	}
	
	/**
	 * This class returns an iterator that iterates over the items in the bag in arbitrary order.
	 * @return an iterator that iterates over the items in the bag in arbitrary order
	 */
	private class ListIterator<Item> implements Iterator<Item> 
	{
		private Node<Item> current;

		/**
		 * Constructor to set the iterator to first
		 * @param first
		 */
		public ListIterator(Node<Item> first) 
		{
			current = first;
		}
		
		/* (non-Javadoc)
		 * Remove method
		 * @see java.util.Iterator#remove()
		 */
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
		
		/* (non-Javadoc)
		 * This method returns current value
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() 
		{
			return current != null;
		}

		/* (non-Javadoc)
		 * This method is used to return item
		 * @see java.util.Iterator#next()
		 */
		public Item next() 
		{
			try 
			{
				if (!hasNext()) throw new NoSuchElementException();
				Item item = current.item;
				current = current.next; 
				return item;
			} 
			catch (Exception e) 
			{
				return null;
			}
		}
	}
}