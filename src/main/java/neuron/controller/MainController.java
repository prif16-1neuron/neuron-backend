package neuron.controller;

import neuron.calculator.Calculator;
import neuron.entities.CalculatedData;
import neuron.entities.Data;
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

    // TODO: 19.3.24 delete this later
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/calculate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Data> getCalculations(@RequestBody DataRequest body) {
       List<Data> data = body.data.stream()
               .map(d -> new Data(d.getX1(), d.getX2(), d.getW1(), d.getW2()))
               .collect(Collectors.toList());
       List<CalculatedData> response = new Calculator().calculate(data);
       return new ResponseEntity(response, HttpStatus.OK);
    }

}

