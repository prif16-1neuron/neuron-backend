package neuron.entities;

public class Extra {

    private  String layer;
    private final int iterations;
    private final double rate;

    public Extra(String layer, int iterations, double rate) {
        this.layer = layer;
        this.iterations = iterations;
        this.rate = rate;
    }

    public String getLayer() {
        return layer;
    }

    public int getIterations() {
        return iterations;
    }

    public double getRate() {
        return rate;
    }
}
