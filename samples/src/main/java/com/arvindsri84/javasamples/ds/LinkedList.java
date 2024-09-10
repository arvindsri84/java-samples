package com.arvindsri84.javasamples.ds;

class LinkedList<T> {

    private static class Node<T> {
        private T data;

        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node<T> head;

    private Node<T> current;

    public LinkedList(T data) {
        this.head = new Node<T>(data);
        this.current = head;
    }

    public LinkedList<T> add(T data) {
        var next = new Node<>(data);
        this.current.next = next;
        this.current = next;
        return this;
    }

    public <T> String print() {
        var printHead = this.head;
        var printedList = new StringBuilder("");
        while (printHead != null) {
            if (!printedList.isEmpty()) printedList.append(" -> ");
            printedList.append(printHead.data);
            printHead = printHead.next;
        }
        return printedList.toString();
    }

    public void reverse() {
        var n = this.head.next;
        var c = this.head;
        Node tmp = null;

        //        5 -> 4 -> 15 -> 17 -> null
        // Null<- 5 <- 4 <- 15 <- 17

        while (n != null) {

            if (tmp == null) {
                c.next = tmp;
            }

            tmp = n.next;
            n.next = c;

            c = n;
            n = tmp;
        }

        this.head = c;

    }


}
