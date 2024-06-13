package exceptionsPersonalizadas;

public class PrendaNoEncontradaException extends Exception{
    public PrendaNoEncontradaException(String id) {
        super("No se encontro ninguna prenda con id: "+id);
    }
}
