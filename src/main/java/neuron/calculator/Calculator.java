package neuron.calculator;

import neuron.entities.AutoData;
import neuron.entities.CalculatedAutoData;
import neuron.entities.CalculatedData;
import neuron.entities.Data;
import neuron.entities.Extra;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculation class
 */

public class Calculator {
    /**
     *
     * @param consumedData, List of data
     * @return calculated data
     */
    public List<CalculatedData> calculate(List<Data> consumedData) {
        List<CalculatedData> calculatedData = new ArrayList<>();
        for (Data consumed : consumedData) {
            CalculatedData data = new CalculatedData(String.valueOf(calculateT(consumed)));
            calculatedData.add(data);
        }
        return calculatedData;
    }

    public List<CalculatedAutoData> autoCalculate(List<AutoData> consumedData, Extra extra){
        List<CalculatedAutoData> calculatedData = new ArrayList<>();
        for(AutoData consumed: consumedData) {
            double autoY = calculateAutoY(consumed,extra);
            CalculatedAutoData data = new CalculatedAutoData(String.valueOf(autoY),String.valueOf((int)Math.round(autoY)));
            calculatedData.add(data);
        }
        return calculatedData;
    }

    /**
     *
     * @param data, data for calculations
     * @return calculated T value
     */
    private double calculateT(Data data) {
        double w0 = convertOfString(data.getW0());
        double x1 = convertOfString(data.getX1());
        double x2 = convertOfString(data.getX2());
        double w1 = convertOfString(data.getW1());
        double w2 = convertOfString(data.getW2());

        double sum = x1 * w1 + x2 * w2;

        return getLinear(w0, sum);
    }

    private double calculateAutoTSum(double[] w, double w0, double x1, double x2, double x3, String type){
        double sum = x1 * w[0] + x2 * w[1] + x3 * w[2] + w[3];

        switch (type) {
            case "Linear":
                return getLinear(w0, sum);
            case "Sigmoid":
                return 1 / (1 + Math.pow(Math.E, -sum));
            case "Tangent":
                return Math.tanh(sum);
        }
        return 0;
    }

    private double getLinear(double w0, double sum) {
        if (sum >= w0) {
            return 1;
        }
        return 0;
    }

    private double convertOfString(String value) {
        return Double.valueOf(value);
    }

    private Double calculateAutoY(AutoData data, Extra extra){
        double output;
        double error;
        double localError;
        double rate = extra.getRate();
        double[] outputs = {data.getX1(), data.getX2(), data.getX3()};
        ArrayList<Double> y = new ArrayList<>();
        double[] w = new double[4];
        w[0] = data.getW0();
        w[1] = data.getW1();
        w[2] = data.getW2();
        w[3] = data.getW3();

        int it = 0;

        do {
            it++;
            error = 0;
            for(int i = 0; i < outputs.length; i++) {
                output = calculateAutoTSum(w, data.getW0(), data.getX1(), data.getX2(), data.getX3(), extra.getLayer());
                localError = outputs[i] - output;
                w[0]+= rate * localError * data.getX1();
                w[1]+= rate * localError * data.getX2();
                w[2]+= rate * localError * data.getX3();
                w[3]+= rate * localError;

                error += (localError * localError);
            }
            y.add(Math.sqrt(error / 8));
        } while (error != 0 && it <= extra.getIterations());

        int len = y.size()-1;
        return y.get(len);
    }
}
