package personas;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class EnviadorDeCorreo1 {
    private final String servidor;
    private final String puerto;
    private final String usuario;
    private final String contraseña;
    public EnviadorDeCorreo1(String servidor, String puerto, String usuario, String contraseña) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }


    public void enviarCorreo(String destinatario, String asunto, String texto) {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", servidor);
        propiedades.put("mail.smtp.port", puerto);
        propiedades.put("mail.smtp.ssl.trust", servidor);

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });

        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(texto);

            // Enviar el mensaje
            Transport.send(mensaje);

            System.out.println("Correo enviado exitosamente");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
