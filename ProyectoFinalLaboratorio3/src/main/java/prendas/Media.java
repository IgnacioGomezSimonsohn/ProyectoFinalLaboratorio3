package prendas;

import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

public class Media extends Prenda{

    public Media(Talle talle, Color color, Genero genero, Double precio, int stock) {
        super(talle, color, genero, precio, stock);
        super.setId("M"+super.getId());
    }
    @Override
    public String toString(){
        return """
                
                Medias:
                """ + super.toString();
    }

}
