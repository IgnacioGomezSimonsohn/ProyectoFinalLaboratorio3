package prendas;

import exceptionsPersonalizadas.PrendaNoEncontradaException;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorPrendas {
    private HashMap <String, Prenda> prendas;

    public GestorPrendas() {
        this.prendas = new HashMap<>();
    }

    public void agregarPrenda (Prenda prenda){
        prendas.put(prenda.getId(),prenda);
    }

    public boolean eliminarPrenda (String id) throws PrendaNoEncontradaException{
        if (prendas.remove(id)!=null){
            return true;
        }
        throw new PrendaNoEncontradaException(id);
    }
    public Prenda obtenerPrenda (String id) throws PrendaNoEncontradaException {
        if (prendas.get(id)!= null){
            return prendas.get(id);
        }
        throw new  PrendaNoEncontradaException(id);
    }

    public List listarPrendas(){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            list.add(entry);
        }
        return  list;
    }
    /// Filtro Prendas
    public List filtrarPrendas(Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getTalle().equals(talle)){
                list.add(entry);
            }
        }
        return  list;
    }
    public List filtrarPrendas(Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getColor().equals(color)){
                list.add(entry);
            }
        }
        return  list;
    }
    public List filtrarPrendas(Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getGenero().equals(genero)){
                list.add(entry);
            }
        }
        return  list;
    }
    public List filtrarPrendas(Genero genero, Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getGenero().equals(genero) && entry.getValue().getColor().equals(color)){
                list.add(entry);
            }
        }
        return  list;
    }
    public List filtrarPrendas(Genero genero,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getGenero().equals(genero) && entry.getValue().getTalle().equals(talle)){
                list.add(entry);
            }
        }
        return  list;
    }
    public List filtrarPrendas(Color color,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle)){
                list.add(entry);
            }
        }
        return  list;
    }
    public List filtrarPrendas(Color color, Talle talle, Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle) && entry.getValue().getGenero().equals(genero)){
                list.add(entry);
            }
        }
        return list;
    }
    /////////////////////////////////////////////////////////////////

    /// Filtrar remeras

    public List filtrarRemeras(){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                list.add(entry);
            }
        }
        return  list;
    }

    public List filtrarRemeras(Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarRemeras(Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarRemeras(Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarRemeras(Genero genero, Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarRemeras(Genero genero,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarRemeras(Color color,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }

        }
        return  list;
    }
    public List filtrarRemeras(Color color, Talle talle, Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Remera){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle) && entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////
    // Filtrar Pantalones

    public List filtrarPantalones(){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                list.add(entry);
            }
        }
        return  list;
    }

    public List filtrarPantalones(Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarPantalones(Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarPantalones(Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarPantalones(Genero genero, Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarPantalones(Genero genero,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarPantalones(Color color,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }

        }
        return  list;
    }
    public List filtrarPantalones(Color color, Talle talle, Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Pantalon){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle) && entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return list;
    }

    ///////////////////////////////////////////////////////////
    // Filtrar Buzos

    public List filtrarBuzos(){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                list.add(entry);
            }
        }
        return  list;
    }

    public List filtrarBuzos(Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarBuzos(Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarBuzos(Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarBuzos(Genero genero, Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarBuzos(Genero genero,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarBuzos(Color color,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }

        }
        return  list;
    }
    public List filtrarBuzos(Color color, Talle talle, Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Buzo){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle) && entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return list;
    }

    ///////////////////////////////////////

    //Filtrar Medias
    public List filtrarMedias(){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                list.add(entry);
            }
        }
        return  list;
    }

    public List filtrarMedias(Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarMedias(Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarMedias(Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarMedias(Genero genero, Color color){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getColor().equals(color)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarMedias(Genero genero,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getGenero().equals(genero) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }
        }
        return  list;
    }
    public List filtrarMedias(Color color,Talle talle){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle)){
                    list.add(entry);
                }
            }

        }
        return  list;
    }
    public List filtrarMedias(Color color, Talle talle, Genero genero){
        ArrayList list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            if (entry.getValue() instanceof Media){
                if(entry.getValue().getColor().equals(color) && entry.getValue().getTalle().equals(talle) && entry.getValue().getGenero().equals(genero)){
                    list.add(entry);
                }
            }
        }
        return list;
    }




}