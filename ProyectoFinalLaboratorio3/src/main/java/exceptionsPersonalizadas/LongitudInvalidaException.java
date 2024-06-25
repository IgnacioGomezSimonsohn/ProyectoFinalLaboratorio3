package exceptionsPersonalizadas;

public class LongitudInvalidaException extends Exception{
    public LongitudInvalidaException() {
        super("EL [nombre / apellido / DNI] debe contener 3 caracteres o mas");
    }
}
