package edu.uga.cs1302.list;

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.Iterator;
import java.io.Serializable;



/**
 * This class provides a simple generic list implmented as a doubly linked list. 
 * It is similar to the LinkedList class included in the java.util package.
 *
 * The elements on the list are ordered, and the first element of the list 
 * is at position 0 and the last element is at position list.size() - 1.
 */
public class SimpleLinkedList<E> 
    implements SimpleList<E>, Iterable<E>, Serializable
{
    private Node<E> first;        // first node of the list
    private Node<E> last;         // last node of the list
    private int     count;	  // number of list elements
    private int     modCount;     // the total number of modifications 
    			          // (add and remove calls)
    
    /**
     * Creates an empty SimpleLinkedList.
     */
    public SimpleLinkedList()
    {
	first = null;
	last = null;
	count = 0;
	modCount = 0;
    }
    
    /**
     * Checks if this SimpleLinkedList is empty.
     * @return true if and only if the list is empty
     */
    public boolean isEmpty()
    {
	return count == 0;
    }
    
    /**
     * Returns the number of elements in this SimpleLinkedList.
     * @return the number of elements in this list
     */
    public int size()
    {
	return count;
    }
    
    /**
     * Adds an element at the end of this list.
     * @param e the element to be added to the end of this list
     * @return true once the method has finished adding the element
     */
    public boolean add( E e )
    {
	Node<E> node = new Node<E>( e, last, null );
	
	if( last == null )  // list is empty
	    first = node;
	else
	    last.next = node;
	last = node;
	count++;
	modCount++;     // increase modification count; element added
	return true;
    }
    
    /**
     * Adds an element at the argumented index.
     * @param index the indices if this list in which the argumented element shall be placed.
     * @param e the element to be added to the argumented index.
     * @return true once the method has finished adding the specified element to the specified index.
     * @throws IndexOutOfBoundsException if the index is {@literal <} 0 or {@literal >} size().
     */
    public boolean add( int index, E e ) throws IndexOutOfBoundsException {
	if (index < 0 || index > size()) // if case to throw the exception
	    {
		throw new IndexOutOfBoundsException(); // throwing the exception
	    }
	else if (index == size()) // if the index is equal to the size of this list
	    {
		add(e); // then we're going to add the element to the end of the list.
	    }
	else if (index == 0) // if the index is equal to 0
	    {
		
		// adding to head of non-empty list                                                                                                                                                   
		Node<E> newNode = new Node<E>(e, null, first);
		
		if (first != null) { // if the first element is not null
		    first.prev = newNode; // then well assign the previous to the specified element 
		}   
		first = newNode; // linking the list
		count++; // increment out number of elements in this list
	    }
	else
	    {	
		// adding somewhere in the middle of this list                                                                                                                                          
		// position before at node before insertion point                                                                                                                                   
		Node<E> previous = first;
		Node<E> temp = null;	// creating a temp list to place hold
		
		for (int i = 0; i < index; i++) // for loop to get to the indices of the list to add
		    {
			temp = previous; //assigning the previous element to the temp variable
			previous = previous.next; // assigning the next element to the previous variable
		    }
		
		// make new node                                                                                                                                                        
		Node<E> newNode = new Node<E>(e, temp , previous);
		
		// link previous.prev and the temp list to new node                                                                                                
		previous.prev = newNode;
		temp.next = newNode;
		
		count++; // increment the number of elements in this list
		
	    }
	return true;
    }
    
    /**
     * Returns the element of the list at the indicated position.
     * @param index the position of the list element to return
     * @return the element at position index
     * @throws IndexOutOfBoundsException if the index is {@literal <} 0 or {@literal >=} size()
     */
    public E get( int index ) throws IndexOutOfBoundsException
    { 
        if (index < 0 || index >= size())
	    {
		throw new IndexOutOfBoundsException();
	    }
        else {
	    validateIndex( index, count-1 ); // must be an index of an existing element
	    Node<E> node = getNodeAt( index );
	    return node.elem;
        }
    }   
    
    /**
     * Removes and returns the element at the argumented index.
     * @param index the position of the list element to remove and return.
     * @return the element that is removed from this list.
     * @throws IndexOutOfBoundsException if the index is {@literal <} 0 or {@literal >=} size().
     */
    public E remove( int index ) throws IndexOutOfBoundsException {	
	
        if (index < 0 || index > size()) // if case to throw the exception
	    {
		throw new IndexOutOfBoundsException(); // throwing the exception
	    }
	else if (count == 1) // if this list only contains one element
	    {
		// removing only element
		E removed = first.elem; //assigning the only element to a variable to be returned
		count = 0; // initializing the number of elements in this list to be zero
		first = null; // assigning the first element to be null
		last = null; // and assigning the last element to be null
		return removed; // returning the element that was removed
	    }
	else if (index == 0) // if the argumented index is 0
	    {
		// removing first element
		E removed = first.elem; // getting the first element of this list and storing it.
		first = first.next; //setting the list to start one element over now
		count--; //decreminting the size of the list since an element was removed
		return removed; // returning the element that was removed
	    }
	else
	    {
		// position previous before the node to remove
		Node<E> previous = first;
		
		for (int i = 1; i < index; i++) // for loop to get the argumented index
		    {
			previous = previous.next; // initializing previous to the next element until the loop is over
		    }
		
		// position after after the node to remove
		Node<E> after = previous.next.next;
		
		// storing the removed element
		E removed = previous.next.elem;
		
		// link previous to after
		previous.next = after;
		
		if (after == null) // if statement to update the tail of this list
		    {
			// updating the tail of this list
			last = previous;
		    }
		
		count--;
		
		return removed;
	    }  	
    }
    
    /**
     * Returns the index of the argumented element in this list./
     * @param e the element in this list to be searched for.
     * @return the index of the element, and -1 if the element is not in this list.
     */
    public int indexOf( E e ) {
	
    	int index = 0; // initializing a variable to hold the index of the variable
        
    	Node<E> current = first; //assigning the list
    	
        while (current != null) { // while the current element is not null
            if (current.elem == e) { // this will capture when the current element equals the argumented element.
                return index; // return the index if found
            }
            index++; //increment the index as the loop goes on
            current = current.next; //go to the next element if an index hasnt been returned
        }
        return -1; //return -1 if the argumented element is not found.
    }
    
    /**
     * Returns an Iterator of the list elements, starting at the beginning of this list.
     * @return the created Iterator
     */
    public Iterator<E> iterator() 
    {
        return new SimpleLinkedListIterator( 0 );
    }
    
    /**
     * Returns a ListIterator of the list elements, starting at the given position in this list.
     * @param index the position of the first element on the list to be returned from the iterator
     * @return the created ListIterator
     * throws IndexOutOfBoundsException if the index is {@literal <} 0 or {@literal >=} the size of the list
     */
    public ListIterator<E> listIterator( int index ) throws IndexOutOfBoundsException
    {
    	if (index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException();
        }
	else {
	validateIndex( index, count ); // must be possible to insert after the last element
        return new SimpleLinkedListIterator( index );
            }
    }

    // The methods and inner classes below are private, and are intended for internal use only.

    // Return the node at a given index.
    // The argument, index, must be verified to be a legal index into this list.
    private Node<E> getNodeAt( int index )
    {
	Node<E> curr = first;
	for( int i = 0; i < index; i++ )
	    curr = curr.next;
	return curr;
    }

    // Verify that a given index is within bounds 0 through end.
    // The second argument, end, should be either count-1, if the given index must
    // be a valid index of an existing element, or count, if an insert is to be at 
    // the end of a list, or an iterator starting at the right end of the list.
    private void validateIndex( int index, int end )
    {
	if( index < 0 || index > end )
	    throw new IndexOutOfBoundsException( "Illegal list index: " + index );
    }

    // This is a private inner class implementing a doubly-linked list node.
    // It makes sense for this class to be private, as it is only useful internally to
    // the SimpleLinnkedList class.
    // Because this class is private, so it is accessible only to the host class SimpleLinkedList,
    // therefore, there is no need to define the variables as private.
    private static class Node<E> {
        E       elem;
        Node<E> next;
        Node<E> prev;

        Node( E elem, Node<E> prev, Node<E> next ) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * This class provides an iterator for the SimpleLinkedList.
     * Some methods have not been implemented intentaionlly;  you 
     * are not expected to implement them.
     */
    private class SimpleLinkedListIterator
	implements ListIterator<E>
    {
	private Node<E> currNode;
	private Node<E> previouslyReturned;
	private int     currPos; // index of the element to be returned next
        private int     expectedModCount; // the count of modifications at the time of this iteractor creation

	// Creates a new iterator starting at position index.
	// javadoc comment needed
	public SimpleLinkedListIterator( int index )
	{
	    validateIndex( index, count ); // verify the staring index;  may be equal to count
	    expectedModCount = modCount;
	    previouslyReturned = null;
	    if( count == 0 )
		currNode = null;
	    else
		currNode = getNodeAt( index );
	    currPos = index;
	}
	    
	// Returns true if this list iterator has more elements when traversing the list forward.
	// javadoc comment needed
	public boolean hasNext() 
	{
	    return currPos < count;
	}

	// Returns true if this list iterator has more elements when traversing the list in the reverse direction.
	// javadoc comment needed
	public boolean hasPrevious()
	{
	    return currPos > 0;
	}

	// Returns the next element on the list.
	// May throw NoSuchElementException if the next element does not exist.
	public E next() 
	{
	    checkForComodification();
	    if( currPos >= count || currNode == null )
		throw new NoSuchElementException();
	    previouslyReturned = currNode;
	    currPos++;
	    currNode = currNode.next;
	    return previouslyReturned.elem;
	}

	// Returns the index of the element that would be returned by a call to next.
	// javadoc comment needed
	public int nextIndex() 
	{
	    return currPos;
	}

	// Returns the previous element in the list.
	// javadoc comment needed
	public E previous() 
	{
	    checkForComodification();
	    if( currPos <= 0 )
		throw new NoSuchElementException();
	    currPos--;
	    if( currNode == null ) {
		currNode = last;
		previouslyReturned = last;
		return previouslyReturned.elem;
	    }
	    else {
		currNode = currNode.prev;
		previouslyReturned = currNode;
		return previouslyReturned.elem;
	    }
	}

	// Returns the index of the element that would be returned by a call to previous.
	// javadoc comment needed
	public int previousIndex() 
	{
	    return currPos - 1;
	}

	// The following are optional operations which are not supported in the 
	// SimpleLinkedList implementation.

	// Adds a new element
	// not implemented here
	public void add(Object e)
	{
	    throw new UnsupportedOperationException( "add called while iterating is not available" );
	}

	// Removes from the list the last element that was returned by next or previous (optional operation).
	// not implemented here
	public void remove() 
	{
	    throw new UnsupportedOperationException( "remove called while iterating is not available" );
	}

	// Replaces the last element returned by next or previous with the specified element (optional operation).
	// not implemented here
	public void set(Object e)
	{
	    throw new UnsupportedOperationException( "set called while iterating is not available" );
	}

	// check if there was a concurrent modification of the list contents.
	// if yes, throw a ConcurrentModificationException exception
	private final void checkForComodification() 
	{
	    if( expectedModCount != SimpleLinkedList.this.modCount )
		throw new ConcurrentModificationException( "list modified while iterator is in progress" );
	}
    }
}
