package neuron.entities;

public class CalculatedAutoData {

    private final String t;

    private  String y;

    private String w0;

    private String w1;

    private String w2;

    private String w3;

    public CalculatedAutoData(String w0, String w1, String w2, String w3, String y, String t) {
        this.t = t;
        this.y = y;
        this.w0 = w0;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
    }

    public void setY(String y) {
        this.y = y;
    }

    public CalculatedAutoData(String t, String y) {
        this.t = t;
        this.y
                = y;
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

    public void setW0(String w0) {
        this.w0 = w0;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }
}
