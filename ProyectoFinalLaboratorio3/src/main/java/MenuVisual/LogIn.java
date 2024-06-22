package MenuVisual;

import exceptionsPersonalizadas.EmailOContraseniaIncorrectos;
import personas.Administrador;
import personas.Cliente;
import personas.GestorPersonas;
import personas.Persona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LogIn {
    public static GestorPersonas gestorPersonas = new GestorPersonas();
    public static void main(String[] args){
        Cliente cliente=new Cliente("Nombre","Apellido","DNI","nacho2012gomez@gmail.com","Usuario","con","Pais","Provincia","Ciudad","Direccion",7600);
        gestorPersonas.agregarPersona(cliente);

        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);


        placeComponents(panel);


        frame.setVisible(true);


    }
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(10, 80, 150, 25);
        panel.add(loginButton);

        // Añadir acción al botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Persona persona= gestorPersonas.coincideContrasenia(userText.getText(),passwordText.getText());
                    if (persona instanceof Cliente){
                        abrirMenuClientes(persona);
                    }else if (persona instanceof Administrador){
                        //abrirMenuAdministradroes();
                    }
                } catch (EmailOContraseniaIncorrectos ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                }

                // Aquí va la lógica para el inicio de sesión
                // Simplemente abrir el menú de clientes al presionar este botón
                // Al buscarr la persona por mail y contrasenia, tiene que devolverla
                // y con un if si es instancia de cliente que se ejecute abrir menu clientes
                // y si es instacia de admin que se abra el menu de admins

            }
        });

        // Crear botón de registrarse
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(170, 80, 150, 25);
        panel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.mostrar();

            }
        });
    }

    // Método para abrir el menú de clientes
    private static void abrirMenuClientes(Persona cliente) {
        MenuClientesUI menuClientes = new MenuClientesUI((Cliente) cliente);
        menuClientes.mostrar();
    }
}
