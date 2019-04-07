package neuron.entities;

public class CalculatedData {

   private final String t;

   private String y;

   public CalculatedData(String t,String y){
       this.t = t;
       this.y = y;
   }

    public CalculatedData(String t) {
        this.t = t;
    }

    public String getT() {
        return t;
    }
}
