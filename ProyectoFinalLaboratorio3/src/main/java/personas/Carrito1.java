package personas;

import prendas.Prenda;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Carrito1 {
    private Cliente cliente;
    private static ArrayList<Prenda> prendas=new ArrayList<>();
    private double monto;


    public Carrito1(Cliente cliente) {
        this.cliente = cliente;
        this.monto = 0;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public ArrayList<Prenda> getPrendas() {
        return prendas;
    }

    public boolean agregarPrenda(Prenda prenda){
        if (prenda.getStock()>=1){
            this.monto+=prenda.getPrecio();
            prenda.setStock(prenda.getStock()-1);
            return this.prendas.add(prenda);
        }
        return false;

    }
    public boolean eliminarPrenda(Prenda prenda){
        if (this.prendas.contains(prenda)){
            this.monto-=prenda.getPrecio();
            prenda.setStock(prenda.getStock()+1);
            return this.prendas.remove(prenda);
        }
        return false;
    }
    public void limpiarCarrito(){
        prendas.clear();
    }



    @Override
    public String toString(){
        return String.format("""
                Carrito:
                Cliente: %s
                Monto: %s
                Prendas:%s\s
                """,this.cliente.getUsuario(),this.monto,this.prendas.stream().map(prenda -> prenda.toString()).collect(Collectors.joining()));
    }


    public void listarCarrito(){
        this.prendas.forEach(prenda -> System.out.println("\n"+prenda));
    }
}
