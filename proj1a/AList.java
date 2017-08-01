public class AList<Item> {

    private Item[] items;
    private int size;
    /** Creates an empty list. */
    public AList() {
        this.items = (Item[]) new Object[100];
        this.size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        items[size] = x;
        size += 1;
    }

    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        this.items = a;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        if (size == tems.length) {
            this.resize(size * 2);
        }
        return items[size - 1];        
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return item[i];        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return this.size;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item temp = items[size - 1];
        items[size - 1] = null;
        size -= 1;
        return temp;
    }
} 