package neuron.entities;

/**
 * data format
 */
public class Data {
    private final String w0;
    private final String x1;
    private final String x2;
    private final String w1;
    private final String w2;
    private String t;

    public Data(String w0, String x1, String x2, String w1, String w2) {
        this.x1 = x1;
        this.x2 = x2;
        this.w1 = w1;
        this.w2 = w2;
        this.w0 = w0;
    }

    public String getX1() {
        return x1;
    }

    public String getX2() {
        return x2;
    }

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public String getW0() {
        return w0;
    }

}
