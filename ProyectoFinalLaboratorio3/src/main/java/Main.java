import personas.Administrador;
import personas.Cliente;
import prendas.Buzo;
import prendas.Media;
import prendas.Pantalon;
import prendas.Remera;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

public class Main {
    public static void main(String[] args) {
//        String servidor = "smtp.gmail.com";
//        String puerto = "587";
//        String usuario = "nacho2012gomez@gmail.com";
//        String contrasenia = "fxchouxgngubxzvo";
//        EnviadorDeCorreo enviador = new EnviadorDeCorreo(servidor, puerto, usuario, contrasenia);
//        enviador.enviarCorreo("ivantaulamet@gmail.com", "Salamin", "Sorete con queso");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Cliente cliente=new Cliente("Nombre","Apellido","DNI","nacho2012gomez@gmail.com","Usuario","Contrasenia","Pais","Provincia","Ciudad","Direccion",7600);
        Administrador administrador=new Administrador("Nombre","Apellido","DNI","email","Usuario","Contrasenia");

        Buzo buzo=new Buzo(Talle.XS, Color.GRIS, Genero.FEMENINO,234.54,4);
        Remera remera =new Remera(Talle.XS, Color.GRIS, Genero.FEMENINO,234.54,4);
        Pantalon pantalon =new Pantalon(Talle.XS, Color.GRIS, Genero.FEMENINO,234.54,4);
        Media media =new Media(Talle.XS, Color.GRIS, Genero.FEMENINO,234.54,4);
        Carrito carrito= new Carrito(cliente);

        carrito.agregarPrenda(buzo);
        carrito.agregarPrenda(remera);
        carrito.agregarPrenda(pantalon);
        carrito.agregarPrenda(media);

//        System.out.println("Carrito lleno");
//        carrito.listarCarrito();
//        System.out.println("Carrito sin buzo");
//        carrito.eliminarPrenda(buzo);
//        carrito.listarCarrito();
//        System.out.println(carrito.toString());
//
         Factura factura=new Factura(cliente,carrito);
//        System.out.println(factura);
//         factura.enviarFactura();
         Reserva reserva=new Reserva(cliente,buzo);
//         reserva.avisarCliente();
//
//        System.out.println(carrito.getMonto());

    }

}
