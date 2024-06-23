package personas;

import java.time.LocalDate;

public class Factura1 {
    private Cliente cliente;
    private Carrito1 carrito;
    private LocalDate fecha;


    public Factura1(Cliente cliente, Carrito1 carrito) {
        this.cliente = cliente;
        this.carrito = carrito;
        this.fecha=LocalDate.now();
    }

    @Override
    public String toString(){
        return  String.format("""
                Factura de Compra
                Fecha: %s
                %s
                """,this.fecha,this.carrito,this.carrito.getMonto());
    }

    public void enviarFactura(){
        EnviadorDeCorreo1 enviador = new EnviadorDeCorreo1("smtp.gmail.com", "587", "nacho2012gomez@gmail.com", "fxchouxgngubxzvo");
        enviador.enviarCorreo(this.cliente.getEmail(), "Factura de compra", toString());

    }
}
