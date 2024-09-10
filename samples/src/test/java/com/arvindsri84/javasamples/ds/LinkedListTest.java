package com.arvindsri84.javasamples.ds;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkedListTest {

    @Test
    public void testPrint() {
        var linkedList = new LinkedList<Integer>(5).add(4).add(15).add(17);
        assertEquals("5 -> 4 -> 15 -> 17", linkedList.print());
        assertEquals("5 -> 4 -> 15 -> 17", linkedList.print());
        assertEquals("ABC", new LinkedList<String>("ABC").print());
    }

    @Test
    public void reverseList() {
        var linkedList = new LinkedList<Integer>(5).add(4).add(15).add(17);
        assertEquals("5 -> 4 -> 15 -> 17", linkedList.print());
        linkedList.reverse();
        assertEquals("17 -> 15 -> 4 -> 5", linkedList.print());
    }


}
