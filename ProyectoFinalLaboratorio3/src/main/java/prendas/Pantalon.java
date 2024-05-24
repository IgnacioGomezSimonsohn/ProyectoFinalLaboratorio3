package prendas;

import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

public class Pantalon extends Prenda{

    public Pantalon(Talle talle, Color color, Genero genero, Double precio, int stock) {
        super(talle, color, genero, precio, stock);
        super.setId("P"+super.getId());
    }
    @Override
    public String toString(){
        return """
                
                Pantalon:
                """ + super.toString();
    }
}
