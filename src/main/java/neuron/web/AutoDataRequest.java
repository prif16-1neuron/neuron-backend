package neuron.web;

import neuron.entities.AutoData;
import neuron.entities.Data;
import neuron.entities.Extra;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AutoDataRequest {
        public List<AutoData> data;
        public String layer;
        public int iterations;
        public double rate;
}
