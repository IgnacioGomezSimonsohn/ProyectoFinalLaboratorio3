package personas;
import com.google.gson.annotations.Expose;

import java.util.Objects;
public abstract class Persona {
    @Expose
    private String nombre;
    @Expose
    private String apellido;
    @Expose
    private String dni;
    @Expose
    private final String email;
    @Expose
    private String usuario;
    @Expose
    private String contrasenia;

    public Persona(String nombre, String apellido, String dni, String email, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String DNI) {
        this.dni = DNI;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona personas = (Persona) o;
        return Objects.equals(nombre, personas.nombre) && Objects.equals(apellido, personas.apellido) && Objects.equals(dni, personas.dni) && Objects.equals(email, personas.email) && Objects.equals(usuario, personas.usuario) && Objects.equals(contrasenia, personas.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, dni, email, usuario, contrasenia);
    }

    @Override
    public String toString() {
        return String.format("""
                                
                Nombre: %s
                Apellido:%s
                DNI: %s
                Email: %s
                Usuario: %s
                """, this.nombre, this.apellido, this.dni, this.email, this.usuario);
    }




}