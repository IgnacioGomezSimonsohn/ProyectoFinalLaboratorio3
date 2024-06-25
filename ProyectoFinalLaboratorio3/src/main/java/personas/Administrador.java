package personas;

import com.google.gson.annotations.Expose;
import personas.Persona;

import java.util.Objects;
import java.util.SplittableRandom;

public class Administrador extends Persona {
    @Expose
    private final String legajo;

    public Administrador(String nombre, String apellido, String dni, String email, String usuario, String contrasenia) {
        super(nombre, apellido, dni, email, usuario, contrasenia);
        this.legajo=generarLegajo();
    }


    private String generarLegajo (){
        String numeros= super.getDNI().substring(0,3);
        String letrasNombre=super.getNombre().substring(0,3).toLowerCase();
        String letrasApellido=super.getApellido().substring(0,3).toLowerCase();
        return numeros+ letrasNombre+ letrasApellido;
    }

    public String getLegajo() {
        return legajo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(legajo, that.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), legajo);
    }

    @Override
    public String toString(){
        return super.toString()+ String.format(
                """
                Legajo: %s
                -----
                
                """,this.legajo);
    }

    public boolean isAdministrador() {
        return true;
    }
}