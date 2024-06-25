package MenuVisual;

import exceptionsPersonalizadas.EmailOContraseniaIncorrectos;
import personas.Administrador;
import personas.Cliente;
import personas.GestorPersonas;
import personas.Persona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class LogIn {
    public static GestorPersonas gestorPersonas = new GestorPersonas();
    public static void main(String[] args){

        try {
            List<Persona> listaPersonas = gestorPersonas.cargarPersonas("personas");
            for (Persona persona : listaPersonas){
                gestorPersonas.agregarPersona(persona);

            }

            if (gestorPersonas.listarAdministradores().isEmpty()) {
                Administrador admin = new Administrador("admin", "admin", "45400287", "admin123@gmail.com", "admin", "123");
                gestorPersonas.agregarPersona(admin);
                gestorPersonas.guardarPersonas(gestorPersonas.listarPersonas(), "personas");
            }



        }catch (IOException ex){
            System.err.println("Error al cargar las personas: " + ex.getMessage());
        }
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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Persona persona= gestorPersonas.coincideContrasenia(userText.getText(),passwordText.getText());
                    if (persona instanceof Cliente){
                        abrirMenuClientes(persona);
                    }else if (persona instanceof Administrador){
                        abrirMenuAdministradroes();
                    }
                } catch (EmailOContraseniaIncorrectos ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                }



            }
        });

        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(170, 80, 150, 25);
        panel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(gestorPersonas);
                registerForm.mostrar();

            }
        });
    }

    private static void abrirMenuClientes(Persona cliente) {
        MenuClientesUI menuClientes = new MenuClientesUI((Cliente) cliente);
        menuClientes.mostrar();
    }
    private static void abrirMenuAdministradroes() {
        MenuAdministradores menuAdministradores = new MenuAdministradores();
        menuAdministradores.mostrar();
    }
}
