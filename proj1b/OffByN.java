public class OffByN {

    private int offset;

    public OffByN(int N) {
        this.offset = N;
    }

    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == this.offset || diff == -this.offset;
    }

}
