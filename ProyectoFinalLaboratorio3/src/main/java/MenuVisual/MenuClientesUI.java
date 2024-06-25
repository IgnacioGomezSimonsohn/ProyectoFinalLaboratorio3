package MenuVisual;
import personas.Cliente;
import personas.Carrito1;
import prendas.*;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.Map;

public class MenuClientesUI {

    private JFrame frame;
    JPanel panel = new JPanel();
    private Cliente cliente;
    private Carrito1 carrito1;
    private JPanel productListPanel;
    private JComboBox<String> colorComboBox;
    private JComboBox<String> talleComboBox;
    private JComboBox<String> generoComboBox;
    private GestorPrendas gestorPrendas = new GestorPrendas(); // Suponiendo que tenes una clase GestorPrendas

    public MenuClientesUI(Cliente cliente_) {
        this.cliente = cliente_;
        this.carrito1 = new Carrito1(cliente_);


        try {
            List<Prenda> listaPrendas = gestorPrendas.cargarPrendas("prendas");
            for (Prenda prenda : listaPrendas) {
                gestorPrendas.agregarPrenda(prenda);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las prendas: " + e.getMessage());
        }

        frame = new JFrame("Menú Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        panel.add(rightPanel, BorderLayout.EAST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;

        gbc.gridy = 0;
        JButton viewCartButton = new JButton("Ver Carrito");
        rightPanel.add(viewCartButton, gbc);

        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuCarritoUI(carrito1).mostrar();
            }
        });

        gbc.gridy = 1;
        JButton editProfileButton = new JButton("Editar Perfil");
        rightPanel.add(editProfileButton, gbc);

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField usuarioField = new JTextField(cliente.getUsuario());
                JTextField paisField = new JTextField(cliente.getPais());
                JTextField provinciaField = new JTextField(cliente.getProvincia());
                JTextField ciudadField = new JTextField(cliente.getCiudad());
                JTextField codigoPostalField = new JTextField(String.valueOf(cliente.getCodigoPostal()));

                JPanel editProfilePanel = new JPanel(new GridLayout(5, 2));
                editProfilePanel.add(new JLabel("Usuario:"));
                editProfilePanel.add(usuarioField);
                editProfilePanel.add(new JLabel("País:"));
                editProfilePanel.add(paisField);
                editProfilePanel.add(new JLabel("Provincia:"));
                editProfilePanel.add(provinciaField);
                editProfilePanel.add(new JLabel("Ciudad:"));
                editProfilePanel.add(ciudadField);
                editProfilePanel.add(new JLabel("Código Postal:"));
                editProfilePanel.add(codigoPostalField);

                int result = JOptionPane.showConfirmDialog(frame, editProfilePanel, "Editar Perfil", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String usuario = usuarioField.getText();
                    String pais = paisField.getText();
                    String provincia = provinciaField.getText();
                    String ciudad = ciudadField.getText();
                    String codigoPostalStr = codigoPostalField.getText();

                    if (!usuario.isEmpty() && !pais.isEmpty() && !provincia.isEmpty() && !ciudad.isEmpty() && !codigoPostalStr.isEmpty()) {
                        if (pais.matches("[a-zA-Z]+") && provincia.matches("[a-zA-Z]+") && ciudad.matches("[a-zA-Z]+") && codigoPostalStr.matches("\\d+")) {
                            int codigoPostal = Integer.parseInt(codigoPostalStr);
                            cliente.setUsuario(usuario);
                            cliente.setPais(pais);
                            cliente.setProvincia(provincia);
                            cliente.setCiudad(ciudad);
                            cliente.setCodigoPostal(codigoPostal);

                            JOptionPane.showMessageDialog(panel, "Perfil actualizado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(panel, "País, provincia y ciudad deben contener solo letras, y código postal solo números.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios.");
                    }
                }
            }
        });

        colorComboBox = new JComboBox<>();
        colorComboBox.addItem("Todos los colores");
        for (Color color : Color.values()) {
            colorComboBox.addItem(color.name());
        }

        talleComboBox = new JComboBox<>();
        talleComboBox.addItem("Todos los talles");
        for (Talle talle : Talle.values()) {
            talleComboBox.addItem(talle.name());
        }

        generoComboBox = new JComboBox<>();
        generoComboBox.addItem("Todos los géneros");
        for (Genero genero : Genero.values()) {
            generoComboBox.addItem(genero.name());
        }

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Color:"));
        filterPanel.add(colorComboBox);
        filterPanel.add(new JLabel("Talle:"));
        filterPanel.add(talleComboBox);
        filterPanel.add(new JLabel("Género:"));
        filterPanel.add(generoComboBox);

        panel.add(filterPanel, BorderLayout.NORTH);

        productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
        actualizarProductos();

        panel.add(new JScrollPane(productListPanel), BorderLayout.CENTER);

        ActionListener actualizarPrendas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProductos();
            }
        };

        colorComboBox.addActionListener(actualizarPrendas);
        talleComboBox.addActionListener(actualizarPrendas);
        generoComboBox.addActionListener(actualizarPrendas);

        gbc.gridy = 2;
        JButton guardarYSalirButton = new JButton("Guardar y Salir");
        rightPanel.add(guardarYSalirButton, gbc);
        guardarYSalirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gestorPrendas.guardarPrendas(gestorPrendas.listarPrendas(), "prendas");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al guardar los datos de prendas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(frame, "Gracias por utilizar la aplicación.");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void actualizarProductos() {
        String colorSeleccionado = (String) colorComboBox.getSelectedItem();
        String talleSeleccionado = (String) talleComboBox.getSelectedItem();
        String generoSeleccionado = (String) generoComboBox.getSelectedItem();

        productListPanel.removeAll();

        for (Prenda prenda : gestorPrendas.listarPrendas()) {
            boolean coincideColor = colorSeleccionado.equals("Todos los colores") || prenda.getColor().name().equals(colorSeleccionado);
            boolean coincideTalle = talleSeleccionado.equals("Todos los talles") || prenda.getTalle().name().equals(talleSeleccionado);
            boolean coincideGenero = generoSeleccionado.equals("Todos los géneros") || prenda.getGenero().name().equals(generoSeleccionado);

            if (coincideColor && coincideTalle && coincideGenero) {
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BorderLayout());
                productPanel.setBorder(BorderFactory.createEtchedBorder());

                JLabel productLabel = new JLabel(prenda.toString());
                productPanel.add(productLabel, BorderLayout.CENTER);

                JButton actionButton;
                if (prenda.getStock() > 0) {
                    actionButton = new JButton("Agregar al Carrito");
                    actionButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            carrito1.agregarPrenda(prenda);
                            prenda.restarStock();
                            JOptionPane.showMessageDialog(panel, "Producto " + prenda.toString() + " agregado al carrito.");
                            actualizarProductos();
                        }
                    });
                } else {
                    actionButton = new JButton("Reservar");
                    actionButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (cliente != null) {
                                boolean reservado = gestorPrendas.reservarPrenda(prenda, cliente);
                                if (reservado) {
                                    JOptionPane.showMessageDialog(panel, "Producto " + prenda.toString() + " reservado para usted.");
                                    actualizarProductos();
                                } else {
                                    JOptionPane.showMessageDialog(panel, "No se pudo reservar el producto " + prenda.toString() + ".");
                                }
                            } else {
                                JOptionPane.showMessageDialog(panel, "Debe iniciar sesión para reservar el producto.");
                            }
                        }
                    });
                }

                productPanel.add(actionButton, BorderLayout.EAST);
                productListPanel.add(productPanel);
            }
        }

        productListPanel.revalidate();
        productListPanel.repaint();
    }


    public void mostrar() {
        frame.setVisible(true);
    }
}