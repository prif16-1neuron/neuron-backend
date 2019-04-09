package neuron.entities;

public class AutoData {
    private  double w0;
    private  double x1;
    private  double x2;
    private  double w1;
    private  double w2;
    private  double w3;
    private  double x3;

    public AutoData(double w0, double x1, double x2, double w1, double w2, double w3, double x3) {
        this.w0 = w0;
        this.x1 = x1;
        this.x2 = x2;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.x3 = x3;
    }

    public double getW0() {
        return w0;
    }

    public void setW0(double w0) {
        this.w0 = w0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getW1() {
        return w1;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public double getW2() {
        return w2;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }

    public double getW3() {
        return w3;
    }

    public void setW3(double w3) {
        this.w3 = w3;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

}
