
public class ArrayDeque<Item> {

    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final static int RESIZE_FACTOR = 2;
    private final static double USAGE_FACTOR = 0.25;
    private final static int INITIAL_SIZE = 8;

    public ArrayDeque() {
        this.items = (Item[]) new Object[INITIAL_SIZE];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    public void addFirst(Item x) {
        if(this.isFull()) {
            this.resizeLarge(size * RESIZE_FACTOR);
        }
        if(nextFirst > 0) {
            items[nextFirst] = x;
            this.nextFirst -= 1;
            this.size += 1;
        }
        else if(nextFirst == 0) {
            items[nextFirst] = x;
            this.nextFirst = this.items.length - 1;
            this.size += 1;
        }
    }

    public void addLast(Item x) {
        if(this.isFull()) {
            this.resizeLarge(size * RESIZE_FACTOR);
        }
        if(nextLast < items.length-1) {
            items[nextLast] = x;
            this.nextLast += 1;
            this.size += 1;
        }
        else if(nextLast == items.length-1) {
            items[nextLast] = x;
            this.nextLast = 0;
            this.size += 1;
        }
    }

    public int size() {
        return this.size;
    }

    private void resizeLarge(int capacity) {
            Item[] tempItem = (Item[]) new Object[capacity];
            if(this.nextFirst == this.size-1) {
                this.nextFirst = 0;
            }
            else {
                this.nextFirst += 1;
            }
            int frontLength = this.size - this.nextFirst;
            System.arraycopy(items, nextFirst, tempItem, 1, frontLength);
            int backLength = this.nextLast;
            System.arraycopy(items, 0, tempItem, frontLength+1, backLength);
            this.items = tempItem;
            this.nextFirst = 0;
            this.nextLast = this.size + 1;
    }


    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFull() {
        if(size == items.length) {
            return true;
        }
        else {
            return false;
        }
    }

    public void printDeque() {

    }

    public Item removeFirst() {
        if (this.nextFirst == this.size-1) {
            Item tempItem = items[0];
            this.items[0] = null;
            this.nextFirst = 0;
            this.size -= 1;
            return tempItem;
        }
        else if(!this.isEmpty()) {
            Item tempItem = items[this.nextFirst + 1];
            this.items[this.nextFirst + 1] = null;
            this.nextFirst += 1;
            this.size -= 1;
            return tempItem;
        }
        else {
            return null;
        }
    }

    public Item removeLast() {
        if(this.nextLast == 0) {
            Item tempItem = items[this.size-1];
            this.items[this.size-1] = null;
            this.nextLast = this.size-1;
            if(this.isResizeSmallRequired(this.calculateUsageRate())) {
                this.resizeSmall();
            }
            this.size -= 1;
            return tempItem;
        }
        else if(!this.isEmpty()) {
            Item tempItem = items[this.nextLast - 1];
            this.items[this.nextLast -1] = null;
            this.nextLast -= 1;
            if(this.isResizeSmallRequired(this.calculateUsageRate())) {
                this.resizeSmall();
            }
            this.size -= 1;
            return tempItem;
        }
        else{
            return null;
        }
    }

    private double calculateUsageRate() {
        return ((double) this.size()) / this.items.length;
    }

    private boolean isResizeSmallRequired(double usageRate) {
        if(this.items.length == INITIAL_SIZE) {
            return false;
        }
        else if(usageRate < USAGE_FACTOR) {
            return true;
        }
        else{
            return false;
        }
    }

    private void resizeSmall() {
            Item[] tempItem = (Item[]) new Object[this.items.length/this.RESIZE_FACTOR];
            if(this.nextFirst < this.nextLast) {
                System.arraycopy(this.items, this.nextFirst+1, tempItem, 1, this.size-1);
                this.items = tempItem;
                this.nextFirst = 0;
                this.nextLast = this.size;
            }
            else {
                System.arraycopy(this.items, this.nextFirst+1, tempItem, 1, this.items.length-this.nextFirst-1);
                System.arraycopy(this.items, 0, tempItem, this.items.length-this.nextFirst, nextLast);
                this.items = tempItem;
                this.nextFirst = 0;
                this.nextLast = this.size;
            }
    }


    public Item get(int index) {
        return null;
    }

    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.size();
        arrayDeque.addFirst("1");
        arrayDeque.addFirst("2");
        arrayDeque.addFirst("3");
        arrayDeque.addFirst("4");
        arrayDeque.addFirst("5");
        arrayDeque.addFirst("6");
        arrayDeque.addFirst("7");
        arrayDeque.addFirst("8");
        arrayDeque.addFirst("9");
        arrayDeque.addFirst("10");
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
    }

}

/*
        arrayDeque.addFirst("1");
        arrayDeque.addFirst("2");
        arrayDeque.addFirst("3");
        arrayDeque.addFirst("4");
        arrayDeque.addFirst("5");
        arrayDeque.addFirst("6");
        arrayDeque.addFirst("7");
        arrayDeque.addFirst("8");
        arrayDeque.addFirst("9");
        arrayDeque.addFirst("10");
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
        arrayDeque.removeLast();
 */


