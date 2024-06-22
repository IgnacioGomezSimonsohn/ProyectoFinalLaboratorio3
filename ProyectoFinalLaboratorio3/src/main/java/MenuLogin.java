import personas.Cliente;
import personas.GestorPersonas;

import java.util.Scanner;

public class MenuLogin {

    public MenuLogin() {
        this.scanner = new Scanner(System.in);
    }

    private Scanner scanner;
    GestorPersonas gestorPersonas = new GestorPersonas();

    public void menuInicio() {

        boolean salir = false;
        while (!salir) {
            System.out.println("Bienvenido! Seleccione una opción:");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarNuevoCliente();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void registrarNuevoCliente(){
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();


        System.out.println("Ingrese su apellido:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese su DNI:");
        String dni = scanner.nextLine();

        System.out.println("Ingrese su email:");
        String email = scanner.nextLine();

        if (!gestorPersonas.validarEmail(email)) {
            System.out.println("El email ingresado no es válido. Debe contener un '@' y un dominio válido (por ejemplo, '.com').");
            return;
        }

        if (gestorPersonas.emailExiste(email)) {
            System.out.println("El email ya está registrado. Intente con otro email.");
            return;
        }

        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String contrasenia = scanner.nextLine();

        System.out.println("Ingrese el pais en el que se encuentra:");
        String pais = scanner.nextLine();

        System.out.println("Ingrese su provincia:");
        String provincia = scanner.nextLine();

        System.out.println("Ingrese su ciudad:");
        String ciudad = scanner.nextLine();

        System.out.println("Ingrese su direccion:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese su codigo postal :");
        int codigoPostal = this.scanner.nextInt();

        Cliente cliente = new Cliente(nombre, apellido, dni, email, usuario, contrasenia, pais,provincia, ciudad, direccion, codigoPostal);
        gestorPersonas.agregarPersona(cliente);
        System.out.println("Cliente registrado exitosamente!");
    }

    public void iniciarSesion() {
        System.out.println("Ingrese su email:");
        String email = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String contrasenia = scanner.nextLine();

        if (gestorPersonas.iniciarSesionVerificar(email, contrasenia)) {
            System.out.println("Login exitoso!");
        } else {
            System.out.println("Email o contraseña incorrectos.");
        }
    }


}