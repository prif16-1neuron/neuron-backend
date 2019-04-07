package neuron.web;

import neuron.entities.AutoData;
import neuron.entities.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AutoDataRequest {

        @NotNull
        @NotEmpty
        @Valid
        public List<AutoData> data;

}
