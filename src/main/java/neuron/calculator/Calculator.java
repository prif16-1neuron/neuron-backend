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
            CalculatedAutoData data = new CalculatedAutoData(String.valueOf(calculateAutoT(consumed,extra)),"0.31");
            calculatedData.add(data);
        }
        return calculatedData;
    }

    /**
     *
     * @param data, data for calculations
     * @return calculated T value
     */
    private int calculateT(Data data) {
        double w0 = convertOfString(data.getW0());
        double x1 = convertOfString(data.getX1());
        double x2 = convertOfString(data.getX2());
        double w1 = convertOfString(data.getW1());
        double w2 = convertOfString(data.getW2());

        double sum = x1 * w1 + x2 * w2;

        if(sum >= w0) {
            return 1;
        }
        return 0;
    }

    private int calculateAutoT(AutoData data){
        double w0 = data.getW0();
        double x1 = data.getX1();
        double x2 = data.getX2();
        double w1 = data.getW1();
        double w2 = data.getW2();
        double x3 = data.getX3();
        double w3 = data.getW3();

        double sum = x1 * w1 + x2 * w2 + x3 * w3;

        if(sum >= w0) {
            return 1;
        }
        return 0;
    }

    private double convertOfString(String value) {
        return Double.valueOf(value);
    }

    private ArrayList<Double> calculateAutoT(AutoData data, Extra extra){
        int output;
        double error = 0;
        double localError = 0;
        double rate = extra.getRate();
        int[] outputs = {1,1,1,1,0,0,0,0};
        ArrayList<Double> t = new ArrayList<>();

        double a;
        int it = 0; //do array of data different and use it for setting w cause it is a string now and cannot be overused

        do {
            it++;
            error = 0;
            for(int i = 0; i < 8; i++){
                output = calculateAutoT(data);
                localError = outputs[i] - output;
                data.setW0(calcW(data.getW0(), rate, localError, data.getX1()));
                data.setW1(calcW(data.getW0(), rate, localError, data.getX1()));
                data.setW2(calcW(data.getW0(), rate, localError, data.getX1()));
                data.setW3(calcW(data.getW0(), rate, localError, data.getX1()));

                error += error * error;
            }
            t.add(Math.sqrt(error / 8));
        } while (error != 0 && it <= extra.getIterations());


        return t;
    }

    private double calcW(double value, double rate, double localError, double x){
        return value * rate * localError * x;

    }

}
