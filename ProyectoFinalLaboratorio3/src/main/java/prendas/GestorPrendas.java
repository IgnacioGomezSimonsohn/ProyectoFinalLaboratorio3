package prendas;

import java.util.HashMap;

public class GestorPrendas {
    private HashMap <String, Prenda> prendas;

    public GestorPrendas() {
        this.prendas = new HashMap<>();
    }

    public void agregarPrenda (Prenda prenda){
        prendas.put(prenda.getId(),prenda);
    }
    public boolean eliminarPrenda (String id){
        if (prendas.remove(id)!=null){
            return true;
        }
        return false;
    }
    public Prenda obtenerPrenda (String id){
        return prendas.get(id);
    }



}