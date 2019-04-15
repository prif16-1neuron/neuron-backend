package neuron.entities;

public class CalculatedAutoData {

    private final String t;

    private final String y;

    private final String w0;

    private final String w1;

    private final String w2;

    private final String w3;

    public CalculatedAutoData(String w0, String w1, String w2, String w3, String y, String t) {
        this.t = t;
        this.y = y;
        this.w0 = w0;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
    }

    public String getT() {
        return t;
    }

    public String getY() {
        return y;
    }

    public String getW0() {
        return w0;
    }

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public String getW3() {
        return w3;
    }
}
