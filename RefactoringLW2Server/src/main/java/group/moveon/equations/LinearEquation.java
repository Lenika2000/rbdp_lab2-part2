package group.moveon.equations;

public class LinearEquation {

    private int k;
    private int b;
    private int x;

    public LinearEquation(int k, int b) {
        this.k = k;
        this.b = b;
    }

    public void setResult(int res) {
        this.x = res;
    }

    public int getB() {
        return b;
    }

    public int getK() {
        return k;
    }

    public int getResult() {
        return x;
    }
}
