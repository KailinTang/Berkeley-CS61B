public class LinkedListDeque{

    private class IntNode {
        public IntNode prev;
        public int item;
        public IntNode next;

        public IntNode (IntNode prev, int item, IntNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private IntNode sentinel;
    private int size;

    public SLList(int x) {
        sentinel = new IntNode(null, 0, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(int x) {
        sentinel = new IntNode(null, 0, null);
        sentinel.next = new IntNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(int x) {
        IntNode newNode = new IntNode(sentinel, x, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(int x) {
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

}