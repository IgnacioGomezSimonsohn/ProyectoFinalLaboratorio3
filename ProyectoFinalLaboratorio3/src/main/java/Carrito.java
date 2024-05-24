import personas.Cliente;
import prendas.Prenda;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Carrito {
    private Cliente cliente;
    private ArrayList<Prenda> prendas;
    private double monto;


    public Carrito(Cliente cliente) {
        this.cliente = cliente;
        this.monto = 0;
        this.prendas=new ArrayList<>();
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
