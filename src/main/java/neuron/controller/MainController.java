package neuron.controller;

import neuron.calculator.Calculator;
import neuron.entities.AutoData;
import neuron.entities.CalculatedAutoData;
import neuron.entities.CalculatedData;
import neuron.entities.Data;
import neuron.entities.Extra;
import neuron.web.AutoDataRequest;
import neuron.web.DataRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1")
public class MainController {

    /**
     *
     * @param body, json containing data
     * @return calculated result
     */


    @PostMapping(path = "/calculate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Data> getCalculations(@RequestBody DataRequest body) {
       List<Data> data = body.data.stream()
               .map(d -> new Data(d.getW0(), d.getX1(), d.getX2(), d.getW1(), d.getW2()))
               .collect(Collectors.toList());
       List<CalculatedData> response = new Calculator().calculate(data);
       return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(path = "/calculate/auto", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoData> getAutoCalculations(@RequestBody AutoDataRequest body){
        List<AutoData> data = body.data.stream()
                .map(d -> new AutoData(d.getW0(), d.getX1(), d.getX2(), d.getW1(), d.getW2(), d.getW3(), d.getX3()))
                .collect(Collectors.toList());
        Extra extra = new Extra(body.layer, body.iterations, body.rate);
        List<CalculatedAutoData> response = new Calculator().autoCalculate(data, extra);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}

