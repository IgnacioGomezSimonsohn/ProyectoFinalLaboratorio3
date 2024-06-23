package prendas;

import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

public class Buzo extends Prenda{


    public Buzo(Talle talle, Color color, Genero genero, Double precio, int stock) {
        super(talle, color, genero, precio, stock);
        super.setId("B"+super.getId());
    }
    @Override
    public String toString(){
        return """
                Buzo:
                """ + super.toString();
    }


}
