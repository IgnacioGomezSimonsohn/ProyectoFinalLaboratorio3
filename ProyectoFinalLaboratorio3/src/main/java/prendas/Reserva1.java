package prendas;
import personas.Cliente;
import personas.EnviadorDeCorreo1;
import prendas.Prenda;

public class Reserva1 {
    private Cliente cliente;
    private Prenda prenda;

    public Reserva1(Cliente cliente, Prenda prenda) {
        this.cliente = cliente;
        this.prenda = prenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public void avisarCliente(){
        EnviadorDeCorreo1 enviador = new EnviadorDeCorreo1("smtp.gmail.com", "587", "nacho2012gomez@gmail.com", "fxchouxgngubxzvo");
        enviador.enviarCorreo(this.cliente.getEmail(), "Prenda en stock", "Se encuentra en stock: "+ prenda);

    }


}
