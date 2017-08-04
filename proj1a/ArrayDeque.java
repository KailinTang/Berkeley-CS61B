
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
        this.nextFirst = 0; //initial front/back pointer position
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

    public int getArrayLength() {
        return this.items.length;
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
        Item[] tempItems = this.arrayRestore();
        for(Item item:tempItems) {
            System.out.print(item + " ");
        }
        System.out.print("\n");
    }

    public Item removeFirst() {
        if (this.nextFirst == this.size-1) {
            Item tempItem = items[0];
            this.items[0] = null;
            this.nextFirst = 0;
            this.size -= 1;
            if(this.isResizeSmallRequired(this.calculateUsageRate())) {
                this.resizeSmall();
            }
            return tempItem;
        }
        else if(!this.isEmpty()) {
            Item tempItem = items[this.nextFirst + 1];
            this.items[this.nextFirst + 1] = null;
            this.nextFirst += 1;
            this.size -= 1;
            if(this.isResizeSmallRequired(this.calculateUsageRate())) {
                this.resizeSmall();
            }
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
            this.size -= 1;
            if(this.isResizeSmallRequired(this.calculateUsageRate())) {
                this.resizeSmall();
            }
            return tempItem;
        }
        else if(!this.isEmpty()) {
            Item tempItem = items[this.nextLast - 1];
            this.items[this.nextLast -1] = null;
            this.nextLast -= 1;
            this.size -= 1;
            if(this.isResizeSmallRequired(this.calculateUsageRate())) {
                this.resizeSmall();
            }
            return tempItem;
        }
        else{
            return null;
        }
    }

    public Item get(int index) {
        return this.arrayRestore()[index];
    }

    private void resizeLarge(int capacity) {
        Item[] tempItems = (Item[]) new Object[capacity];
        if(this.nextFirst == this.size-1) {
            this.nextFirst = 0;
        }
        else {
            this.nextFirst += 1;
        }
        int frontLength = this.size - this.nextFirst;
        System.arraycopy(items, nextFirst, tempItems, 1, frontLength);
        int backLength = this.nextLast;
        System.arraycopy(items, 0, tempItems, frontLength+1, backLength);
        this.items = tempItems;
        this.nextFirst = 0;
        this.nextLast = this.size + 1;
    }

    private void resizeSmall() {
        Item[] tempItems = (Item[]) new Object[this.items.length/this.RESIZE_FACTOR];
        if(this.nextFirst < this.nextLast) {
            System.arraycopy(this.items, this.nextFirst+1, tempItems, 1, this.size);
            this.items = tempItems;
            this.nextFirst = 0;
            this.nextLast = this.size + 1;
        }
        else {
            System.arraycopy(this.items, this.nextFirst+1, tempItems, 1, this.items.length-this.nextFirst-1);
            System.arraycopy(this.items, 0, tempItems, this.items.length-this.nextFirst, nextLast);
            this.items = tempItems;
            this.nextFirst = 0;
            this.nextLast = this.size + 1;
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

    private Item[] arrayRestore() {
        Item[] tempItems = (Item[]) new Object[this.items.length];
        if(this.nextFirst < this.nextLast) {
            System.arraycopy(this.items, this.nextFirst+1, tempItems, 0, this.size());
        }
        else {
            System.arraycopy(this.items, this.nextFirst+1, tempItems, 0, this.items.length-this.nextFirst-1);
            System.arraycopy(this.items, 0, tempItems, this.items.length-this.nextFirst-1, this.nextLast);
        }
        return tempItems;
    }
}


