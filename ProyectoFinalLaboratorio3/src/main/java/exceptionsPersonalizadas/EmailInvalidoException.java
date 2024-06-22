package exceptionsPersonalizadas;

public class EmailInvalidoException extends Exception{
    public EmailInvalidoException() {
        super("El email ingresado no es válido. Debe contener un '@' y un dominio válido (por ejemplo, '.com'");
    }
}
