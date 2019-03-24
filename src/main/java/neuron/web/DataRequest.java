package neuron.web;

import neuron.entities.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Request json format class
 */

public class DataRequest {

    @NotNull
    @NotEmpty
    @Valid
    public List<Data> data;

}
