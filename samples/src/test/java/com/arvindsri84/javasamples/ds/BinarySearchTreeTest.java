package com.arvindsri84.javasamples.ds;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {

    @Test
    public void testBST() {
        var bst = new BinarySearchTree(10);
        bst.insert(5).insert(15)
                .insert(2).insert(5).insert(13).insert(22)
                .insert(1).insert(14);

        assertEquals("10 5 2 1 5 15 13 14 22", bst.print());
        bst.remove(10);
        assertEquals("13 5 2 1 5 15 14 22", bst.print());
    }

    @Test
    public void testBST2() {

        var bst = new BinarySearchTree(1);
        bst.insert(2).insert(3).insert(4);

        assertEquals("1 2 3 4", bst.print());
        bst.remove(1);
        assertEquals("2 3 4", bst.print());
    }

    @Test
    public void testBSTDepth() {
        var bst = new BinarySearchTree(10);
        bst.insert(5).insert(15)
                .insert(2).insert(5).insert(13).insert(22)
                .insert(1).insert(14).insert(-1);

        assertEquals(4, bst.getDepth());
    }

}
