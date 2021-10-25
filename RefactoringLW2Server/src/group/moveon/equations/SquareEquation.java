package group.moveon.equations;

public class SquareEquation {

    private int a;
    private int b;
    private int c;
    private int[] x;

    public SquareEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public void setResult(int[] res) {
        this.x = res;
    }

    public int[] getResult() {
        return x;
    }
}
