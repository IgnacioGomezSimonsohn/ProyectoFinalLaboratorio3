package prendas;

import GestionArchivos.Impresora;
import exceptionsPersonalizadas.PrendaNoEncontradaException;
import personas.Cliente;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GestorPrendas {
    private HashMap <String, Prenda> prendas;
    private ArrayList<Reserva1> reservas;
    private Impresora<Prenda> impresora = new Impresora<>();


    public GestorPrendas() {
        this.prendas = new HashMap<>();
        this.reservas=new ArrayList<>();
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

    public List<Prenda> listarPrendas(){
        ArrayList<Prenda> list=new ArrayList<>();
        for (Map.Entry<String,Prenda> entry: prendas.entrySet()){
            list.add(entry.getValue());
        }
        return  list;
    }
    public boolean reservarPrenda (Prenda prenda, Cliente cliente){
        Reserva1 reserva = new Reserva1(cliente,prenda);
        if (this.reservas.add(reserva)){
            return true;
        }else return false;
    }
    public void enviarMailReservar(){
        for (Reserva1 reserva1 : this.reservas){
            if (reserva1.getPrenda().getStock()>0){
                reserva1.avisarCliente();
                this.reservas.remove(reserva1);
            }
        }
    }
    public List<Prenda> cargarPrendas(String filename) throws IOException {
        return impresora.cargar(filename, new TypeToken<List<Prenda>>() {});
    }
    public void guardarPrendas(List<Prenda> prendas, String filename) throws IOException {
        impresora.guardar(prendas, filename);
    }







}