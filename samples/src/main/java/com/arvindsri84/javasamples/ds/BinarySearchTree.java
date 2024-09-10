package com.arvindsri84.javasamples.ds;

import java.util.Comparator;
import java.util.HashMap;

public class BinarySearchTree {

    public int value;
    public BinarySearchTree left;
    public BinarySearchTree right;

    public BinarySearchTree(int value) {
        this.value = value;
    }

    public BinarySearchTree insert(int value) {
        // Write your code here.
        // Do not edit the return statement of this method.
        var current = this;
        while (true) {
            if (current.value > value) {
                if (current.left == null) {
                    current.left = new BinarySearchTree(value);
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new BinarySearchTree(value);
                    break;
                }
                current = current.right;
            }
        }

        return this;
    }

    public boolean contains(int value) {
        var current = this;
        while (current != null) {
            if (current.value > value) {
                current = current.left;
            } else if (current.value < value) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public void remove(int value) {
        remove(value, this, null);
    }

    private BinarySearchTree remove(int value, BinarySearchTree current, BinarySearchTree prev) {

        while (current != null) {
            if (current.value > value) {
                prev = current;
                current = current.left;
            } else if (current.value < value) {
                prev = current;
                current = current.right;
            } else {
                // value has been found and we need to delete it
                if (current.left != null && current.right != null) {
                    // find min node to the righ of the tree, remove it and get the value
                    var minNode = minNode(current.right);
                    remove(minNode.value, current.right, current);
                    current.value = minNode.value;
                } else if (current.left != null && current.right == null) {
                    if (prev == null) {
                        current.value = current.left.value;
                        current.right = current.left.right;
                        current.left = current.left.left;
                    } else {
                        if (prev.left == current) {
                            prev.left = current.left;
                        } else {
                            prev.right = current.left;
                        }
                    }
                } else if (current.left == null && current.right != null) {
                    if (prev == null) {
                        current.value = current.right.value;
                        current.left = current.right.left;
                        current.right = current.right.right;
                    } else {
                        if (prev.left == current) {
                            prev.left = current.right;
                        } else {
                            prev.right = current.right;
                        }
                    }
                } else {
                    if (prev == null) return null;
                    if (prev.left == current) {
                        prev.left = null;
                    } else {
                        prev.right = null;
                    }
                }
                break;
            }
        }
        return this;
    }


    private BinarySearchTree minNode(BinarySearchTree current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    public String print() {
        var buffer = new StringBuffer();
        print(this, buffer);
        return buffer.toString().trim();
    }

    private void print(BinarySearchTree current, StringBuffer buffer) {
        if (current == null) {
            return;
        }
        buffer.append(current.value).append(" ");
        print(current.left, buffer);
        print(current.right, buffer);
    }

    static class NodeMeta {
        BinarySearchTree node;
        int level;
        boolean leaf;

        @Override
        public String toString() {
            return "NodeMeta{" +
                    "node=" + node.value +
                    ", level=" + level +
                    ", leaf=" + leaf +
                    '}';
        }
    }

    public int getDepth() {
        var depthMap = new HashMap<Integer, NodeMeta>();
        traverseForDepth(this, null, depthMap);
        System.out.println(depthMap);
        var maxDepth =
                depthMap.values().stream().filter(x -> x.leaf).max(Comparator.comparingInt(x -> x.level));
        return maxDepth.orElse(new NodeMeta()).level;
    }

    private void traverseForDepth(BinarySearchTree current, BinarySearchTree prev, HashMap<Integer, NodeMeta> depthMap) {
        if (current == null) {
            return;
        }
        var meta = new NodeMeta();
        meta.node = current;
        if (prev != null) {
            meta.level = depthMap.get(prev.value).level + 1;
        }
        meta.leaf = current.right == null && current.left == null;
        depthMap.put(current.value, meta);

        traverseForDepth(current.left, current, depthMap);
        traverseForDepth(current.right, current, depthMap);
    }

}
