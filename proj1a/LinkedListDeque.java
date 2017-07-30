public class LinkedListDeque<Item> {

    private class IntNode {
        public IntNode prev;
        public Item item;
        public IntNode next;

        public IntNode (IntNode prev, Item item, IntNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private IntNode sentinel;
    private int size;

    public SLList(Item x) {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(Item x) {
        sentinel = new IntNode(null, 0, null);
        sentinel.next = new IntNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(Item x) {
        IntNode newNode = new IntNode(sentinel, x, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(Item x) {
        IntNode newNode = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;

    }

    public boolean isEmpty() {
        if(sentinel.equals(sentinel.next)) {
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
        IntNode tempNode = sentinel.next;
        while(!sentinel.equals(tempNode)){
            System.out.println(tempNode.item);
            tempNode = tempNode.next;
        }
    }

    public Item removeFirst() {
        if(sentinel.equals(sentinel.next)){
            return null;
        }
        IntNode first = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first.item;
    }

    public Item removeLast() {
        if(sentinel.equals(sentinel.prev)){
            return null;
        }
        IntNode last = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last.item;
    }

    

}