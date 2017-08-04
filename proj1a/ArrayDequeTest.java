
public class ArrayDequeTest {

    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }


    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkArrayLength(int expected, int actual) {
        if (expected != actual) {
            System.out.println("array length is: " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }


    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> arrayDeque = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, arrayDeque.isEmpty());

        arrayDeque.addFirst("front");

        passed = checkSize(1, arrayDeque.size()) && passed;
        passed = checkEmpty(false, arrayDeque.isEmpty()) && passed;

        arrayDeque.addLast("middle");
        passed = checkSize(2, arrayDeque.size()) && passed;

        arrayDeque.addLast("back");
        passed = checkSize(3, arrayDeque.size()) && passed;

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();

        printTestStatus(passed);

    }

    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> arrayDeque = new LinkedListDeque<Integer>();
        boolean passed = checkEmpty(true, arrayDeque.isEmpty());

        arrayDeque.addFirst(10);
        passed = checkEmpty(false, arrayDeque.isEmpty()) && passed;

        arrayDeque.removeFirst();
        passed = checkEmpty(true, arrayDeque.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void getTest() {
        System.out.println("Running get test.");
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        arrayDeque.addFirst(1);
        boolean passed = arrayDeque.get(0) == 1;
        arrayDeque.addLast(2);
        passed = (arrayDeque.get(1) == 2) && passed;
        arrayDeque.addFirst(3);
        passed = (arrayDeque.get(0) == 3) && passed;
        printTestStatus(passed);
    }

    public static void resizeTest() {
        System.out.println("Running resize test.");

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        boolean passed = checkEmpty(true, arrayDeque.isEmpty());
        passed = checkArrayLength(8, arrayDeque.getArrayLength()) && passed;

        for (int i = 0; i < 4; i++) {
            arrayDeque.addLast(i);
        }
        for (int i = 0; i < 6; i++) {
            arrayDeque.addFirst(i);
        }

        passed = checkSize(10, arrayDeque.size()) && passed;
        passed = checkArrayLength(16, arrayDeque.getArrayLength()) && passed;

        for (int i = 0; i < 12; i++) {
            arrayDeque.addFirst(i);
        }
        for (int i = 0; i < 18; i++) {
            arrayDeque.addLast(i);
        }

        passed = checkSize(40, arrayDeque.size()) && passed;
        passed = checkArrayLength(64, arrayDeque.getArrayLength()) && passed;

        for (int i = 0; i < 15; i++) {
            arrayDeque.removeLast();
        }
        for (int i = 0; i < 15; i++) {
            arrayDeque.removeFirst();
        }

        passed = checkSize(10, arrayDeque.size()) && passed;
        passed = checkArrayLength(32, arrayDeque.getArrayLength()) && passed;

        for (int i = 0; i < 150; i++) {
            arrayDeque.addLast(i);
        }
        for (int i = 0; i < 150; i++) {
            arrayDeque.addFirst(i);
        }

        passed = checkSize(310, arrayDeque.size()) && passed;
        passed = checkArrayLength(512, arrayDeque.getArrayLength()) && passed;

        for (int i = 0; i < 279; i++) {
            arrayDeque.removeLast();
        }

        passed = checkSize(31, arrayDeque.size()) && passed;
        passed = checkArrayLength(64, arrayDeque.getArrayLength()) && passed;

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        getTest();
        resizeTest();
    }

}
