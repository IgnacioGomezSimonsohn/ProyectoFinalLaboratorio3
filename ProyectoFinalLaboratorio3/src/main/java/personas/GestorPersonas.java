package personas;

import exceptionsPersonalizadas.AdministradorNoEcontrado;
import exceptionsPersonalizadas.EmailOContraseniaIncorrectos;
import jdk.jshell.spi.SPIResolutionException;

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





}
