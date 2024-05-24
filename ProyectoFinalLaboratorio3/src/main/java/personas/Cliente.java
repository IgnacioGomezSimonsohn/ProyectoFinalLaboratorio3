package personas;
import java.util.Objects;
public class Cliente extends Persona{
    private String pais;
    private String provincia;
    private String ciudad;
    private String direccion;
    private int codigoPostal;

    public Cliente(String nombre, String apellido, String dni, String email, String usuario, String contrasenia, String pais, String provincia, String ciudad, String direccion, int codigoPostal) {
        super(nombre, apellido, dni, email, usuario, contrasenia);
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return codigoPostal == cliente.codigoPostal && Objects.equals(pais, cliente.pais) && Objects.equals(provincia, cliente.provincia) && Objects.equals(ciudad, cliente.ciudad) && Objects.equals(direccion, cliente.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pais, provincia, ciudad, direccion, codigoPostal);
    }

    @Override
    public String toString(){
        return super.toString() + String.format(
                """
                Pais: %s
                Provincia: %s
                Ciudad: %s
                Direccion: %s
                Codigo Postal: %d
                -----
                
                """,this.pais,this.provincia,this.ciudad,this.direccion,this.codigoPostal);
    }




}