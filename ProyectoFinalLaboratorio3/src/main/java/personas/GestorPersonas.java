package personas;

import GestionArchivos.Impresora;
import exceptionsPersonalizadas.AdministradorNoEcontrado;
import exceptionsPersonalizadas.EmailInvalidoException;
import exceptionsPersonalizadas.EmailOContraseniaIncorrectos;
import jdk.jshell.spi.SPIResolutionException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import prendas.Prenda;

public class GestorPersonas {

    private HashSet<Persona> personas;
    private Impresora<Persona> impresora = new Impresora<>();

    public GestorPersonas() {
        this.personas=new HashSet<>();
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

    public List<Persona> listarAdministradores(){
        List<Persona> administradores=new ArrayList<>();
        for (Persona persona : this.personas){
            if (persona instanceof Administrador){
                administradores.add(persona);
            }
        }
        return administradores;
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

    public boolean validarEmail(String email)throws EmailInvalidoException {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        if (email.matches(emailRegex)== true){
            return true;
        }else throw new EmailInvalidoException();

    }


    public List<Persona> cargarPersonas(String filename) throws IOException {
        try {
            List<Persona> listaPersonas = impresora.cargar(filename, new TypeToken<List<Persona>>() {});
            // Limpia personas y agrega las nuevas.
            this.personas.clear();
            this.personas.addAll(listaPersonas);
            return listaPersonas;
        } catch (IOException ex) {
            throw new IOException("Error al cargar las personas desde el archivo " + filename, ex);
        }
    }
    public void guardarPersonas(List<Persona> personas, String filename) throws IOException {
        impresora.guardar(personas, filename);
    }

//    public void cargarDatos() {
//        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.json"))) {
//            Type setType = new TypeToken<Set<Persona>>() {}.getType();
//            personas = gson.fromJson(br, setType);
//            if (personas == null) {
//                personas = new HashSet<>();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Archivo no encontrado, creando nuevo archivo...");
//            personas = new HashSet<>();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void guardarDatos() {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.json"))) {
//            gson.toJson(personas, bw);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}

