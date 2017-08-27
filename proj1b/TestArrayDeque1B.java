import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void addRemoveTest() {
        StudentArrayDeque<Integer> stuArrDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrDequeSol = new ArrayDequeSolution<>();
        OperationSequence operSeq = new OperationSequence();
        for(int i = 0; i < 1000; i++) {
            double randNum = StdRandom.uniform();
            if (randNum < 0.25) {
                operSeq.addOperation(new DequeOperation("addFirst", i));
                stuArrDeque.addFirst(i);
                arrDequeSol.addFirst(i);
            } else if (randNum < 0.5) {
                operSeq.addOperation(new DequeOperation("addLast", i));
                stuArrDeque.addLast(i);
                arrDequeSol.addLast(i);
            } else if (randNum < 0.75) {
                if (stuArrDeque.isEmpty() || arrDequeSol.isEmpty()) {
                    continue;
                }
                operSeq.addOperation(new DequeOperation("removeFirst"));
                Integer actual = stuArrDeque.removeFirst();
                Integer expected = arrDequeSol.removeFirst();
                assertEquals(operSeq.toString(), actual, expected);
            } else {
                if (stuArrDeque.isEmpty() || arrDequeSol.isEmpty()) {
                    continue;
                }
                operSeq.addOperation(new DequeOperation("removeLast"));
                Integer actual = stuArrDeque.removeLast();
                Integer expected = arrDequeSol.removeLast();
                assertEquals(operSeq.toString(), actual, expected);
            }
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", TestArrayDeque1B.class);
    }
}
