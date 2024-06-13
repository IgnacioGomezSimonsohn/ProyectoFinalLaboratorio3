package exceptionsPersonalizadas;

public class EmailOContraseniaIncorrectos extends Exception{
    public EmailOContraseniaIncorrectos() {
        super("Email o contrasenia incorrecto");
    }
}
