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

    double[] w = new double[4];

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
        w[0] = consumedData.get(0).getW0();
        w[1] = consumedData.get(1).getW0();;
        w[2] = consumedData.get(2).getW0();;
        w[3] = consumedData.get(3).getW0();;
        for(AutoData consumed: consumedData) {
            CalculatedAutoData data = calculateAutoY(consumed, extra);
            calculatedData.add(data);
        }
        for (CalculatedAutoData calculatedDatum : calculatedData) {
            calculatedDatum.setW0(String.valueOf(w[0]));
            calculatedDatum.setW1(String.valueOf(w[1]));
            calculatedDatum.setW2(String.valueOf(w[2]));
            calculatedDatum.setW3(String.valueOf(w[3]));
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

    CalculatedAutoData calculateAutoY(AutoData data, Extra extra){
        double output;
        double error;
        double localError;
        double rate = extra.getRate();
        double[] outputs = {data.getX1(), data.getX2(), data.getX3()};
        ArrayList<Double> y = new ArrayList<>();

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
        double yRes  = y.get(len);
        return new CalculatedAutoData(
                  String.valueOf(yRes)
                , String.valueOf((int)Math.round(yRes)));
    }
}
