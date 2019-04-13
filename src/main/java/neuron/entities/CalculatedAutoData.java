package neuron.entities;

public class CalculatedAutoData {

        private final String t;

        private final String y;

        public CalculatedAutoData(String y,String t){
            this.t = t;
            this.y = y;
        }

        public String getT() {
            return t;
        }

        public String getY() { return y; }

}
