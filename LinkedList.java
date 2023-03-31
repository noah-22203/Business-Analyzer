public class LinkedList<T> implements List<T> {
    private class Node {
        T data;
        Node next;

        public Node(T item){
            data = item;
            next = null;
        }
    }
    Node head;
    int size;

    @Override
    public T get(int pos) {
        Node curr = head;
        for (int i = 0; i < pos; i++)
            curr = curr.next;
        return curr.data;
    }

    @Override
    public boolean add(T item) {
        if (head == null) {
            head = new Node(item);
            ++size;
            return true;
        }
        Node node = new Node(item);
        node.next = head;
        head = node;
        ++size;
        return true;
    }

    @Override
    public void add(int pos, T item) {
        if (pos == 0) {
            Node node = new Node(item);
            node.next = head;
            head = node;
            ++size;
        } else {
            Node prev = head;
            for (int i = 0; i < pos - 1;
                 i++)
                prev = prev.next;
            Node node = new Node(item);
            node.next = prev.next;
            prev.next = node;
            ++size;
        }
    }

    @Override
    public T remove(int pos) {
        if (pos == 0) {
            Node node = head;
            head = head.next;
            --size;
            return node.data;
        } else {
            Node prev = head;
            for (int i=0; i < pos-1; i++)
                prev = prev.next;
            Node node = prev.next;
            prev.next = node.next;
            --size;
            return node.data;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            Node node = head;
            System.out.println(node.data);
            node = node.next;
        }
    }

    @Override
    public Iterator<T> Iterator() {
        return new ListIterator<>();
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node node = head;

        public boolean hasNext() {
            return node.next != null;
        }

        public T next() { // Return data and advance
            Node prev = node;
            node = node.next;
            return (T) prev.data;
        }
    } // end list iterator
}
