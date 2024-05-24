package prendas;

import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

public class Remera extends Prenda{

    public Remera(Talle talle, Color color, Genero genero, Double precio, int stock) {
        super(talle, color, genero, precio, stock);
        super.setId("R"+super.getId());
    }
    @Override
    public String toString(){
        return """
                
                Remera:
                """ + super.toString();
    }
}
