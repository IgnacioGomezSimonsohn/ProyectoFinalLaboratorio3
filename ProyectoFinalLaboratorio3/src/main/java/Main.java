import exceptionsPersonalizadas.AdministradorNoEcontrado;
import exceptionsPersonalizadas.EmailOContraseniaIncorrectos;
import personas.Administrador;
import personas.Cliente;
import personas.GestorPersonas;
import prendas.*;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String servidor = "smtp.gmail.com";
//        String puerto = "587";
//        String usuario = "nacho2012gomez@gmail.com";
//        String contrasenia = "fxchouxgngubxzvo";
//        EnviadorDeCorreo enviador = new EnviadorDeCorreo(servidor, puerto, usuario, contrasenia);
//        enviador.enviarCorreo("ivantaulamet@gmail.com", "Salamin", "Sorete con queso");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Cliente cliente=new Cliente("Nombre","Apellido","DNI","nacho2012gomez@gmail.com","Usuario","Contrasenia1","Pais","Provincia","Ciudad","Direccion",7600);
        Administrador administrador=new Administrador("AdminNombre","Apellido","DNI","email","Usuario","Contrasenia");

        Buzo buzo=new Buzo(Talle.XS, Color.AZUL, Genero.FEMENINO,234.54,4);
        Remera remera =new Remera(Talle.XS, Color.AZUL, Genero.FEMENINO,234.54,4);
        Pantalon pantalon =new Pantalon(Talle.XS, Color.ROSA, Genero.MASCULINO,234.54,4);
        Media media =new Media(Talle.XL, Color.AZUL, Genero.FEMENINO,234.54,4);
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
//        factura.enviarFactura();
         Reserva reserva=new Reserva(cliente,buzo);
//         reserva.avisarCliente();
        GestorPrendas gestor=new GestorPrendas();
        gestor.agregarPrenda(buzo);
        gestor.agregarPrenda(remera);
        gestor.agregarPrenda(pantalon);
        gestor.agregarPrenda(media);

        List list= new ArrayList<>();
        list=gestor.filtrarRemeras();
//        System.out.println(list);

//
//        System.out.println(carrito.getMonto());
        GestorPersonas gestorPersonas=new GestorPersonas();
        gestorPersonas.agregarPersona(administrador);
        gestorPersonas.agregarPersona(cliente);
//        System.out.println(administrador.getLegajo());
//        try {
//            System.out.println(gestorPersonas.buscarAdministrador(administrador.getLegajo()));
//        }catch (AdministradorNoEcontrado e){
//            System.out.println(e.getMessage());
//        }
        try {
            System.out.println(gestorPersonas.coincideContrasenia(administrador.getEmail(),cliente.getContrasenia()));
        }catch (EmailOContraseniaIncorrectos e){
            System.out.println(e.getMessage());
        }





    }

}
