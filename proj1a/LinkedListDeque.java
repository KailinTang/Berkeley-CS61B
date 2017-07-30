public class LinkedListDeque<Item> {

    private class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node (Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        size = 0;
    }

    public LinkedListDeque(Item x) {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = new Node(this.sentinel, x, this.sentinel);
        this.sentinel.prev = this.sentinel.next;
        size = 1;
    }

    public void addFirst(Item x) {
        Node newNode = new Node(this.sentinel, x, this.sentinel.next);
        this.sentinel.next.prev = newNode;
        this.sentinel.next = newNode;
        size += 1;
    }

    public void addLast(Item x) {
        Node newNode = new Node(this.sentinel.prev, x, this.sentinel);
        this.sentinel.prev.next = newNode;
        this.sentinel.prev = newNode;
        size += 1;

    }

    public boolean isEmpty() {
        if(this.size == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        Node tempNode = this.sentinel.next;
        while(!this.sentinel.equals(tempNode)){
            System.out.println(tempNode.item);
            tempNode = tempNode.next;
        }
    }

    public Item removeFirst() {
        if(this.isEmpty()){
            return null;
        }
        Node first = this.sentinel.next;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        size -= 1;
        return first.item;
    }

    public Item removeLast() {
        if(this.isEmpty()){
            return null;
        }
        Node last = this.sentinel.prev;
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
        size -= 1;
        return last.item;
    }

    public Item get(int index) {
        if(index >= this.size) {
            return null;
        }
        Node tempNode = this.sentinel;
        for(int i = 0; i <= index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.item;
    }

    public Item getRecursive(int index) {
        if(index >= this.size) {
            return null;
        }
        return getRecursiveHelper(index, this.sentinel.next);
    }

    public Item getRecursiveHelper(int index, Node x) {
        if(index == 0) {
            return x.item;
        }
        return getRecursiveHelper(index - 1, x.next);
    }
}