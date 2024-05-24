import personas.Cliente;
import prendas.Prenda;

public class Reserva {
    private Cliente cliente;
    private Prenda prenda;

    public Reserva(Cliente cliente, Prenda prenda) {
        this.cliente = cliente;
        this.prenda = prenda;
    }

    public void avisarCliente(){
        EnviadorDeCorreo enviador = new EnviadorDeCorreo("smtp.gmail.com", "587", "nacho2012gomez@gmail.com", "fxchouxgngubxzvo");
        enviador.enviarCorreo(this.cliente.getEmail(), "Prenda en stock", "Se encuentra en stock: "+ prenda);

    }


}
