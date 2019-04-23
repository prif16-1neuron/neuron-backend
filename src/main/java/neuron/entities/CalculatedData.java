package neuron.entities;

public class CalculatedData {

    private  String t;

    private double y;

    public CalculatedData(String t) {
        this.t = t;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
