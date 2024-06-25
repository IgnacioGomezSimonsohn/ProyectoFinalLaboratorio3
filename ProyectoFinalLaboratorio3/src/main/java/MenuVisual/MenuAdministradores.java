package MenuVisual;
import com.google.gson.reflect.TypeToken;
import exceptionsPersonalizadas.AdministradorNoEcontrado;
import exceptionsPersonalizadas.EmailInvalidoException;
import exceptionsPersonalizadas.PrendaNoEncontradaException;
import personas.Administrador;
import personas.GestorPersonas;
import personas.Persona;
import prendas.*;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MenuAdministradores{
    private JFrame frame;
    private GestorPrendas gestorPrendas = new GestorPrendas();
    private GestorPersonas gestorAdministradores = new GestorPersonas();

    public MenuAdministradores() {

        try {
            List<Persona> listaPersonas = gestorAdministradores.cargarPersonas("personas");
            for (Persona persona : listaPersonas){
                gestorAdministradores.agregarPersona(persona);
            }
        }catch (IOException ex){
            System.err.println("Error al cargar las personas: " + ex.getMessage());
        }


        try {
            List<Prenda> listaPrendas = gestorPrendas.cargarPrendas("prendas");
            for (Prenda prenda : listaPrendas) {
                gestorPrendas.agregarPrenda(prenda);

            }
        } catch (IOException e) {
            System.err.println("Error al cargar las prendas: " + e.getMessage());
        }

        frame = new JFrame("Menú Administradores");
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
        JButton addAdminButton = new JButton("Agregar Administrador");
        rightPanel.add(addAdminButton, gbc);

        gbc.gridy = 1;
        JButton deleteAdminButton = new JButton("Eliminar Administrador");
        rightPanel.add(deleteAdminButton, gbc);

        gbc.gridy = 2;
        JButton listAdminButton = new JButton("Listar Administradores");
        rightPanel.add(listAdminButton, gbc);

        gbc.gridy = 3;
        JButton addProductButton = new JButton("Agregar Prenda");
        rightPanel.add(addProductButton, gbc);

        gbc.gridy = 4;
        JButton deleteProductButton = new JButton("Eliminar Prenda");
        rightPanel.add(deleteProductButton, gbc);

        gbc.gridy = 5;
        JButton guardarYSalirButton = new JButton("Guardar y Salir");
        rightPanel.add(guardarYSalirButton, gbc);

        addAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nombreField = new JTextField();
                JTextField apellidoField = new JTextField();
                JTextField dniField = new JTextField();
                JTextField emailField = new JTextField();
                JTextField usuarioField = new JTextField();
                JPasswordField contraseniaField = new JPasswordField();

                JPanel addAdminPanel = new JPanel(new GridLayout(6, 2));
                addAdminPanel.add(new JLabel("Nombre:"));
                addAdminPanel.add(nombreField);
                addAdminPanel.add(new JLabel("Apellido:"));
                addAdminPanel.add(apellidoField);
                addAdminPanel.add(new JLabel("DNI:"));
                addAdminPanel.add(dniField);
                addAdminPanel.add(new JLabel("Email:"));
                addAdminPanel.add(emailField);
                addAdminPanel.add(new JLabel("Usuario:"));
                addAdminPanel.add(usuarioField);
                addAdminPanel.add(new JLabel("Contraseña:"));
                addAdminPanel.add(contraseniaField);

                int result = JOptionPane.showConfirmDialog(frame, addAdminPanel, "Agregar Nuevo Administrador", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String nombre = nombreField.getText();
                    String apellido = apellidoField.getText();
                    String dni = dniField.getText();
                    String email = emailField.getText();
                    try {
                        gestorAdministradores.validarEmail(email);
                    } catch (EmailInvalidoException ex1) {
                        JOptionPane.showMessageDialog(frame, ex1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String usuario = usuarioField.getText();
                    String contrasenia = new String(contraseniaField.getPassword());

                    if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !email.isEmpty() && !usuario.isEmpty() && !contrasenia.isEmpty()) {
                        if (nombre.matches("[a-zA-Z]+") && apellido.matches("[a-zA-Z]+") && dni.matches("\\d+")) {
                            Administrador administrador = new Administrador(nombre, apellido, dni, email, usuario, contrasenia);
                            gestorAdministradores.agregarPersona(administrador);
                            JOptionPane.showMessageDialog(panel, "Administrador " + nombre + " agregado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(panel, "Nombre y Apellido deben contener solo letras. DNI debe contener solo números.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios.");
                    }
                }
            }
        });

        deleteAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String legajo = JOptionPane.showInputDialog(frame, "Ingrese el legajo del administrador a eliminar:");
                if (legajo != null && !legajo.isEmpty()) {
                    Administrador admin = null;
                    try {
                        admin = gestorAdministradores.buscarAdministrador(legajo);
                    } catch (AdministradorNoEcontrado ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage());
                    }
                    if (admin != null) {
                        int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar al administrador " + admin.getNombre() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            gestorAdministradores.eliminarAdministrador(admin);
                            JOptionPane.showMessageDialog(panel, "Administrador eliminado con éxito.");
                        }
                    }
                }
            }
        });

        listAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Persona> adminList = gestorAdministradores.listarAdministradores();
                StringBuilder adminInfo = new StringBuilder();

                for (Persona admin : adminList) {
                    adminInfo.append(admin.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, adminInfo.toString(), "Lista de Administradores", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new GridLayout(1, 4));

                JButton remeraButton = new JButton("Remera");
                JButton buzoButton = new JButton("Buzo");
                JButton pantalonButton = new JButton("Pantalón");
                JButton mediasButton = new JButton("Medias");

                buttonPanel.add(remeraButton);
                buttonPanel.add(buzoButton);
                buttonPanel.add(pantalonButton);
                buttonPanel.add(mediasButton);

                JFrame addFrame = new JFrame("Seleccionar Prenda");
                addFrame.setSize(400, 100);
                addFrame.add(buttonPanel);
                addFrame.setLocationRelativeTo(frame);
                addFrame.setVisible(true);

                ActionListener agregarPrendaListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addFrame.dispose();
                        String prendaTipo = ((JButton) e.getSource()).getText();

                        JComboBox<Talle> talleComboBox = new JComboBox<>(Talle.values());
                        JComboBox<Color> colorComboBox = new JComboBox<>(Color.values());
                        JComboBox<Genero> generoComboBox = new JComboBox<>(Genero.values());
                        JTextField precioField = new JTextField();
                        JTextField stockField = new JTextField();

                        JPanel addProductPanel = new JPanel(new GridLayout(6, 2));
                        addProductPanel.add(new JLabel("Talle:"));
                        addProductPanel.add(talleComboBox);
                        addProductPanel.add(new JLabel("Color:"));
                        addProductPanel.add(colorComboBox);
                        addProductPanel.add(new JLabel("Género:"));
                        addProductPanel.add(generoComboBox);
                        addProductPanel.add(new JLabel("Precio:"));
                        addProductPanel.add(precioField);
                        addProductPanel.add(new JLabel("Stock:"));
                        addProductPanel.add(stockField);

                        int result = JOptionPane.showConfirmDialog(frame, addProductPanel, "Agregar " + prendaTipo, JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                Talle talle = (Talle) talleComboBox.getSelectedItem();
                                Color color = (Color) colorComboBox.getSelectedItem();
                                Genero genero = (Genero) generoComboBox.getSelectedItem();
                                double precio = Double.parseDouble(precioField.getText());
                                int stock = Integer.parseInt(stockField.getText());

                                Prenda prenda = null;
                                switch (prendaTipo) {
                                    case "Remera":
                                        prenda = new Remera(talle, color, genero, precio, stock);
                                        break;
                                    case "Buzo":
                                        prenda = new Buzo(talle, color, genero, precio, stock);
                                        break;
                                    case "Pantalón":
                                        prenda = new Pantalon(talle, color, genero, precio, stock);
                                        break;
                                    case "Medias":
                                        prenda = new Media(talle, color, genero, precio, stock);
                                        break;
                                }

                                if (prenda != null) {
                                    gestorPrendas.agregarPrenda(prenda);
                                    JOptionPane.showMessageDialog(panel, prendaTipo + " agregado con éxito.");
                                }

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(panel, "Datos inválidos. Por favor, ingrese números válidos.");
                            }
                        }
                    }
                };

                remeraButton.addActionListener(agregarPrendaListener);
                buzoButton.addActionListener(agregarPrendaListener);
                pantalonButton.addActionListener(agregarPrendaListener);
                mediasButton.addActionListener(agregarPrendaListener);
            }
        });


        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prendaId = JOptionPane.showInputDialog(frame, "Ingrese el ID de la prenda a eliminar:");
                if (prendaId != null && !prendaId.isEmpty()) {
                    try {
                        gestorPrendas.eliminarPrenda(prendaId);
                    } catch (PrendaNoEncontradaException ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(panel, "Prenda con ID " + prendaId + " eliminada con éxito.");
                }
            }
        });

        JComboBox<String> colorComboBox = new JComboBox<>();
        colorComboBox.addItem("Todos los colores");
        for (Color color : Color.values()) {
            colorComboBox.addItem(color.name());
        }

        JComboBox<String> talleComboBox = new JComboBox<>();
        talleComboBox.addItem("Todos los talles");
        for (Talle talle : Talle.values()) {
            talleComboBox.addItem(talle.name());
        }

        JComboBox<String> generoComboBox = new JComboBox<>();
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

        JPanel productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(productListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        ActionListener actualizarPrendas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String colorSeleccionado = (String) colorComboBox.getSelectedItem();
                String talleSeleccionado = (String) talleComboBox.getSelectedItem();
                String generoSeleccionado = (String) generoComboBox.getSelectedItem();
                productListPanel.removeAll();
                List<Prenda> prendasList = gestorPrendas.listarPrendas();
                for (Prenda prenda : prendasList) {
                    boolean coincideColor = colorSeleccionado.equals("Todos los colores") || prenda.getColor().name().equals(colorSeleccionado);
                    boolean coincideTalle = talleSeleccionado.equals("Todos los talles") || prenda.getTalle().name().equals(talleSeleccionado);
                    boolean coincideGenero = generoSeleccionado.equals("Todos los géneros") || prenda.getGenero().name().equals(generoSeleccionado);
                    if (coincideColor && coincideTalle && coincideGenero) {
                        JPanel productPanel = new JPanel();
                        productPanel.setLayout(new BorderLayout());
                        productPanel.setBorder(BorderFactory.createEtchedBorder());

                        JLabel productLabel = new JLabel(prenda.toString());
                        productPanel.add(productLabel, BorderLayout.CENTER);

                        JButton updateProductButton = new JButton("Actualizar Prenda");
                        updateProductButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JTextField precioField = new JTextField();
                                JTextField stockField = new JTextField();

                                JPanel updatePanel = new JPanel(new GridLayout(2, 2));
                                updatePanel.add(new JLabel("Nuevo Precio:"));
                                updatePanel.add(precioField);
                                updatePanel.add(new JLabel("Nuevo Stock:"));
                                updatePanel.add(stockField);

                                int result = JOptionPane.showConfirmDialog(frame, updatePanel, "Actualizar Prenda", JOptionPane.OK_CANCEL_OPTION);
                                if (result == JOptionPane.OK_OPTION) {
                                    String nuevoPrecioStr = precioField.getText();
                                    String nuevoStockStr = stockField.getText();
                                    if (nuevoPrecioStr != null && !nuevoPrecioStr.isEmpty() && nuevoStockStr != null && !nuevoStockStr.isEmpty()) {
                                        try {
                                            double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                                            int nuevoStock = Integer.parseInt(nuevoStockStr);
                                            if (nuevoPrecio > 0 && nuevoStock > 0) {

                                                prenda.setPrecio(nuevoPrecio);
                                                prenda.setStock(nuevoStock);

                                                productLabel.setText(prenda.toString());
                                                gestorPrendas.enviarMailReservar();
                                                JOptionPane.showMessageDialog(panel, "Producto " + prenda.toString() + " actualizado con éxito.");
                                            } else {
                                                JOptionPane.showMessageDialog(panel, "El precio y el stock deben ser mayores a 0.");
                                            }
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(panel, "Datos inválidos. Por favor, ingrese números válidos.");
                                        }
                                    }
                                }
                            }
                        });
                        productPanel.add(updateProductButton, BorderLayout.EAST);

                        productListPanel.add(productPanel);
                    }
                }
                productListPanel.revalidate();
                productListPanel.repaint();
            }
        };

        colorComboBox.addActionListener(actualizarPrendas);
        talleComboBox.addActionListener(actualizarPrendas);
        generoComboBox.addActionListener(actualizarPrendas);

        actualizarPrendas.actionPerformed(null);

        guardarYSalirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    gestorPrendas.guardarPrendas(gestorPrendas.listarPrendas(),"prendas");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al guardar los datos de prendas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    gestorAdministradores.guardarPersonas(gestorAdministradores.listarAdministradores(), "personas");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al guardar los datos de personas.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                JOptionPane.showMessageDialog(frame, "Gracias por utilizar la aplicación.");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    public void mostrar() {
        frame.setVisible(true);
    }


// TODO> DEPUES BORRAR ESTA FUNCION DE ABAJO
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MenuAdministradores().mostrar();
//            }
//      });
    }



