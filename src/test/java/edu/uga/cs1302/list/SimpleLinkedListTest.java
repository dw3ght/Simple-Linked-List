package edu.uga.cs1302.list;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class SimpleLinkedListTest {
    
    SimpleLinkedList<String> list = new SimpleLinkedList<String>();
	
    @Before
    public void setUp() throws Exception {
	list.add("0");
	list.add("2");
	list.add("4");
	list.add("6");
    }
    
    //Creating Test Cases
    
    /**
     * Testing the add(int index , E e) method at the
     * beginning of this list.
     * Also testing that the size increases when an element
     * is added to this list.
     */
    @Test
    public void testAddAtZero() {
	list.add(0, "-2");
	assertEquals(list.get(0) , "-2");
	assertEquals(list.size() , 5);
    }
    
    /**
     * Testing the add(int index , E e) method at the
     * middle of this list.
     * Also testing that the size increases when an element
     * is added to this list.
     */
    @Test
    public void testAddInMiddle() { 
	list.add(2, "3");
	assertEquals(list.get(2) , "3");
	assertEquals(list.size() , 5);
    }
    
    /**
     * Testing the add(int index , E e) method at the
     * end of this list.
     * Also testing that the size increases when an element
     * is added to this list.
     */
    @Test
    public void testAddAtEnd() {
	list.add(list.size(), "8");
	assertEquals(list.get(list.size()-1) , "8");
	assertEquals(list.size() , 5);
    }
    
    /**
     * Testing the add(int index , E e) method with
     * a null element.
     * Also testing that the size increases when an element
     * is added to this list.
     */
    @Test
    public void testAddNull() {
	list.add(0, null);
	assertEquals(list.get(0) , null);
	assertEquals(list.size() , 5);
    }
    
    /**
     * Testing the add(int index , E e) method with
     * an illegal index below zero.
     * An exception is expected.
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testIllegalAdd() {
	list.add(-1000 , "-1000");
	assertEquals(list.get(-1000) , "-1000");
    }
    
    /**
     * Testing the add(int index , E e) method with
     * an illegal index above the size.
     * An exception is expected.
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testIllegalAdd2() {
	list.add(1000 , "1000");
	assertEquals(list.get(1000) , "1000");
    }
	
    /**
     * Testing the remove(int index) method at the 
     * beginning of this list.
     */
    @Test
    public void testRemoveAtZero() {
	list.remove(0);
	assertEquals(list.get(0) , "2");
    }
    
    /**
     * Testing the remove(int index) method at the 
     * middle of this list.
     */
    @Test
    public void testRemoveAtMiddle() {
	list.remove(2);
	assertEquals(list.get(2) , "6");
    }
    
    /**
     * Testing the remove(int index) method at the 
     * end of this list.
     */
    @Test
    public void testRemoveAtEnd() {
	list.remove(list.size() - 1);
	assertEquals(list.get(list.size() - 1) , "4");
    }
    
    /**
     * Testing the remove(int index) method with a 
     * null element in this list.
     */
    @Test
    public void testRemoveNull() {
	list.add(0 , null);
	list.remove(0);
	assertEquals(list.get(0) , "0");
    }
    
    /**
     * Testing the remove(int index) with an 
     * illegal index below zero this list.
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testIllegalRemove() {
	list.remove(-1000);
	assertEquals(list.get(-1000) , "-1000");
    }
    
    /**
     * Testing the remove(int index) with an 
     * illegal index above the size of this list.
     */
    @Test( expected = IndexOutOfBoundsException.class )
    public void testIllegalRemove2() {
	list.remove(1000);
	assertEquals(list.get(1000) , "1000");
    }
    
    /**
     * Testing the indexOf(E e) at the 
     * beginning of this list.
     */
    @Test
    public void testIndexOfAt0() {
	assertEquals(list.indexOf("0") , 0);
    }
    
    /**
     * Testing the indexOf(E e) at the 
     * middle of this list.
     */
    @Test
    public void testIndexOfAtMiddle() {
	assertEquals(list.indexOf("4") , 2);
    }
    
    /**
     * Testing the indexOf(E e) at the 
     * end of this list.
     */
    @Test
    public void testIndexOfAtEnd() {
	assertEquals(list.indexOf("6") , 3);
    }
    
    /**
     * Testing the indexOf(E e) with a  
     * null element in this list.
     */
    @Test
    public void testIndexOfNull() {
	list.add(2 , null);
	assertEquals(list.indexOf(null) , 2);
    }
    
    /**
     * Testing the indexOf(E e) with an  
     * element not in this list.
     */
    @Test
    public void testIndexOfNotFound() {
	assertEquals(list.indexOf("") , -1);
    }
    
    
    
}
