import personas.Cliente;

import java.time.LocalDate;
import java.util.Date;
public class Factura {
    private Cliente cliente;
    private Carrito carrito;
    private LocalDate fecha;


    public Factura(Cliente cliente, Carrito carrito) {
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
                """,this.fecha,this.carrito);
    }

    public void enviarFactura(){
        EnviadorDeCorreo enviador = new EnviadorDeCorreo("smtp.gmail.com", "587", "nacho2012gomez@gmail.com", "fxchouxgngubxzvo");
        enviador.enviarCorreo(this.cliente.getEmail(), "Factura de compra", toString());

    }
}
