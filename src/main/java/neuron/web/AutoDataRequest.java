package neuron.web;

import neuron.entities.AutoData;

import java.util.List;

/**
 * Request entity
 */
public class AutoDataRequest {
        public List<AutoData> data;
        public String layer;
        public int iterations;
        public double rate;
        public String xColumns;
}
