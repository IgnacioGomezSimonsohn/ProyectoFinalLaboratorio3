package MenuVisual;
import exceptionsPersonalizadas.EmailInvalidoException;
import personas.Cliente;
import personas.GestorPersonas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterForm {

    private GestorPersonas gestorPersonas;
    private JFrame registerFrame;

    public RegisterForm(GestorPersonas gestorPersonas1) {
        this.gestorPersonas=gestorPersonas1;
        createRegisterForm();
    }

    private void createRegisterForm() {
        registerFrame = new JFrame("Registro");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(400, 450);
        registerFrame.setLocationRelativeTo(null);


        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        registerFrame.add(registerPanel);

        JLabel[] labels = {
                new JLabel("Nombre:"), new JLabel("Apellido:"), new JLabel("DNI:"),
                new JLabel("Email:"), new JLabel("Usuario:"), new JLabel("Contraseña:"),
                new JLabel("País:"), new JLabel("Provincia:"), new JLabel("Ciudad:"),
                new JLabel("Dirección:"), new JLabel("Código Postal:")
        };

        JTextField[] textFields = {
                new JTextField(20), // Nombre
                new JTextField(20), // Apellido
                new JTextField(20), // DNI
                new JTextField(20), // Email
                new JTextField(20), // Usuario
                new JPasswordField(20), // Contraseña
                new JTextField(20),  // Pais
                new JTextField(20),  // Provincia
                new JTextField(20), // Ciudad
                new JTextField(20), // Direccion
                new JTextField(20) // Codigo postal
        };

        int yPos = 20;
        for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(10, yPos, 120, 25);
            registerPanel.add(labels[i]);
            textFields[i].setBounds(140, yPos, 200, 25);
            registerPanel.add(textFields[i]);
            yPos += 30;
        }

        JButton submitButton = new JButton("Registrarse");
        submitButton.setBounds(140, yPos, 150, 25);
        registerPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textFields[0].getText();
                String apellido = textFields[1].getText();
                String dni = textFields[2].getText();
                String email;
                try {
                    email = textFields[3].getText();
                    gestorPersonas.validarEmail(email);
                }catch (EmailInvalidoException ex1){
                    JOptionPane.showMessageDialog(registerPanel, ex1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String usuario = textFields[4].getText();
                String contrasena = new String(((JPasswordField) textFields[5]).getPassword());
                String pais = textFields[6].getText();
                String provincia = textFields[7].getText();
                String ciudad = textFields[8].getText();
                String direccion = textFields[9].getText();
                int codigoPostal;
                try {
                    codigoPostal = Integer.parseInt(textFields[10].getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(registerPanel, "Código Postal debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Cliente nuevoCliente = new Cliente(nombre, apellido, dni, email, usuario, contrasena, pais, provincia, ciudad, direccion, codigoPostal);
                    gestorPersonas.agregarPersona(nuevoCliente);
                    gestorPersonas.guardarPersonas(gestorPersonas.listarPersonas(), "personas");
                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(registerFrame, "Error al guardar los datos de personas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(registerPanel, "Registro exitoso!");
                registerFrame.dispose();
            }
        });
        registerFrame.setVisible(true);
    }

    public void mostrar() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                registerFrame.setVisible(true);
            }
        });
    }


}
