package exceptionsPersonalizadas;

public class AdministradorNoEcontrado extends Exception{
    public AdministradorNoEcontrado(String legajo) {
        super("No se encontro ningun administrador con legajo: "+legajo);
    }
}
