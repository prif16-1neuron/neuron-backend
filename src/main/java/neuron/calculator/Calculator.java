package neuron.calculator;

import neuron.entities.AutoData;
import neuron.entities.CalculatedAutoData;
import neuron.entities.CalculatedData;
import neuron.entities.Data;

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

    public List<CalculatedAutoData> autoCalculate(List<AutoData> consumedData){
        List<CalculatedAutoData> calculatedData = new ArrayList<>();
        for(AutoData consumed: consumedData) {
            CalculatedAutoData data = new CalculatedAutoData(String.valueOf(calculateAutoT(consumed)),"0.31");
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

    private double convertOfString(String value) {
        return Double.valueOf(value);
    }

    private int calculateAutoT(AutoData data){
        double w0 = convertOfString(data.getW0());
        double x1 = convertOfString(data.getX1());
        double x2 = convertOfString(data.getX2());
        double w1 = convertOfString(data.getW1());
        double w2 = convertOfString(data.getW2());
        double x3 = convertOfString(data.getX3());
        double w3 = convertOfString(data.getW3());

        return 0;
    }

}
