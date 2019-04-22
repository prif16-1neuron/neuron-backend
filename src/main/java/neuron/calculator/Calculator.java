package neuron.calculator;

import neuron.entities.AutoData;
import neuron.entities.CalculatedAutoData;
import neuron.entities.CalculatedData;
import neuron.entities.Data;
import neuron.entities.Extra;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Main class, made for calculations
 * @method calculate
 * @method autoCalculateList
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
    } /*!< Calculate list class */



    public List<CalculatedAutoData> autoCalculate(List<AutoData> consumedData, Extra extra){
        List<CalculatedAutoData> calculatedData = new ArrayList<>();
        for(AutoData consumed: consumedData) {
            CalculatedAutoData data = calculateAutoY(consumed, extra);
//            int score = Integer.valueOf(data.getY());
//            if(score>1){
//                score = 1;
//            }
//            data.setY(String.valueOf(score));
            calculatedData.add(data);
        }
        for (CalculatedAutoData calculatedDatum : calculatedData) {
            calculatedDatum.setW0(String.valueOf(w[1]));
            calculatedDatum.setW1(String.valueOf(w[2]));
            calculatedDatum.setW2(String.valueOf(w[3]));
            calculatedDatum.setW3(String.valueOf(w[0]));
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

    /**
     * function for different function endings
     * @param x1
     * @param x2
     * @param x3
     * @param type
     * @return
     */

    private double calculateAutoTSum(double x1, double x2, double x3, String type){
        double sum = x1 * w[0] + x2 * w[1] + x3 * w[2] + w[3];

        switch (type) {
            case "Linear":
                return getTheta(sum);
            case "Sigmoid":
                return 1.0 / (1.0 + Math.exp(-sum));
            case "Tangent":
                return Math.tanh(sum);
        }
        return 0;
    }


    /*!
    linear neuron activation
     */
    private double getLinear(double w0, double sum) {
        if (sum >= w0) {
            return 1;
        }
        return 0;
    }

    private double getTheta(double sum) {
        if (sum >= w[3]) {
            return 1;
        }
        return 0;
    }

    private double convertOfString(String value) {
        return Double.valueOf(value);
    }

    /**
     * function for auto calculations
     * @param data
     * @param extra
     * @return
     */

    CalculatedAutoData calculateAutoY(AutoData data, Extra extra){
        double output;
        double error;
        double localError;
        double rate = extra.getRate();
        double[] outputs = {data.getX1(), data.getX2(), data.getX3()};
        ArrayList<Double> y = new ArrayList<>();
        w[0] = data.getW1();
        w[1] = data.getW2();
        w[2] = data.getW3();
        w[3] = data.getW0();

        int it = 0;

        do {
            it++;
            error = data.getY();
            for(int i = 0; i < outputs.length; i++) {
                output = calculateAutoTSum(data.getX1(), data.getX2(), data.getX3(), extra.getLayer());
                localError = outputs[i] - output;
                w[0]+= rate * localError * data.getX1();
                w[1]+= rate * localError * data.getX2();
                w[2]+= rate * localError * data.getX3();
                w[3]+= rate * localError;

                error += (localError * localError);
            }
            y.add(Math.sqrt(error / outputs.length));
        } while (error != 0 && it <= extra.getIterations());

        int len = y.size()-1;
        double yRes  = y.get(len);
        int res = (int)Math.round(yRes);
        if(res> 1.0 || yRes > 1){
            yRes = 1.0d;
            res = 1;
        }
        return new CalculatedAutoData(
                  String.valueOf(res),
                  String.valueOf(yRes));
    }
}
