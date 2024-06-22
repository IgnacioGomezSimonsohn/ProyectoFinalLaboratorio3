package personas;

import exceptionsPersonalizadas.AdministradorNoEcontrado;
import exceptionsPersonalizadas.EmailOContraseniaIncorrectos;
import jdk.jshell.spi.SPIResolutionException;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPersonas {

    private HashSet<Persona> personas;

    public GestorPersonas() {
        this.personas= new HashSet<>();
    }

    public void agregarPersona(Persona persona){
        this.personas.add(persona);
    }

    public void eliminarAdministrador(Administrador administrador){
        this.personas.remove(administrador);
    }
    public Administrador buscarAdministrador(String legajo) throws AdministradorNoEcontrado{
        Administrador buscado= (Administrador) this.personas.stream().filter(persona -> persona instanceof Administrador && ((Administrador) persona).getLegajo().equals(legajo)).findFirst().orElse(null);
        if (buscado!=null){
            return buscado;
        }
        throw new AdministradorNoEcontrado(legajo);
    }
    public Persona coincideContrasenia(String email, String contrasenia) throws EmailOContraseniaIncorrectos {

       Persona persona1 = this.personas.stream()
               .filter(persona -> persona.getContrasenia().equals(contrasenia) && persona.getEmail().equals(email))
               .findFirst()
               .orElse(null);
           if (persona1!= null){
               return persona1;
           }else throw new EmailOContraseniaIncorrectos();
    }

    public List listarAdministradores(){
        return this.personas.stream().filter(persona -> persona instanceof  Administrador).collect(Collectors.toList());
    }


    public boolean iniciarSesionVerificar(String email, String contrasenia) {
        for (Persona persona : personas) {
            if (persona.getEmail().equals(email) && persona.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }
     public boolean emailExiste (String email) {
            for (Persona persona : personas) {
                if (persona.getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
    }

    public boolean validarEmail(String email) {
        // Verificar que el email contenga un '@' y termine con '.com'
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);

    }

    public void cargarDatos() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 6) {
                    String tipo = datos[0];
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String dni = datos[3];
                    String email = datos[4];
                    String usuario = datos[5];
                    String contrasenia = datos[6];

                    if (tipo.equals("Cliente") && datos.length == 12) {
                        String pais = datos[7];
                        String provincia = datos[8];
                        String ciudad = datos[9];
                        String direccion = datos[10];
                        int codigoPostal = Integer.parseInt(datos[11]);
                        Persona persona = new Cliente(nombre, apellido, dni, email, usuario, contrasenia, pais, provincia, ciudad, direccion, codigoPostal);
                        personas.add(persona);
                    } else if (tipo.equals("Administrador") && datos.length == 8) {
                        String legajo = datos[7];
                        Persona persona = new Administrador(nombre, apellido, dni, email, usuario, contrasenia);
                        personas.add(persona);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Persona persona : personas) {
                bw.write(persona.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }}
