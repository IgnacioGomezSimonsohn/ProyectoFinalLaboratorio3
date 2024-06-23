package MenuVisual;
import exceptionsPersonalizadas.AdministradorNoEcontrado;
import exceptionsPersonalizadas.EmailInvalidoException;
import personas.Administrador;
import personas.GestorPersonas;
import prendas.*;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuAdministradores {
    private JFrame frame;
    private GestorPrendas gestorPrendas = new GestorPrendas();
    private GestorPersonas gestorAdministradores = new GestorPersonas();

    public MenuAdministradores() {
        // TODO AGREGAR LA FUNCION DE CARGAR DATOS DE PERSONAS Y DE PRENDAS ACA
        Buzo buzo = new Buzo(Talle.XS, Color.AZUL, Genero.FEMENINO, 234.54, 4);
        Remera remera = new Remera(Talle.XS, Color.AZUL, Genero.FEMENINO, 234.54, 4);
        Pantalon pantalon = new Pantalon(Talle.XS, Color.ROJO, Genero.MASCULINO, 234.54, 4);
        Media media = new Media(Talle.XL, Color.AZUL, Genero.FEMENINO, 234.54, 4);
        gestorPrendas.agregarPrenda(buzo);
        gestorPrendas.agregarPrenda(remera);
        gestorPrendas.agregarPrenda(pantalon);
        gestorPrendas.agregarPrenda(media);

        frame = new JFrame("Menú Administradores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JPanel rightPanel = new JPanel(new BorderLayout());
        panel.add(rightPanel, BorderLayout.EAST);

        JButton addAdminButton = new JButton("Agregar Administrador");
        rightPanel.add(addAdminButton, BorderLayout.NORTH);

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
                        Administrador administrador = new Administrador(nombre, apellido, dni, email, usuario, contrasenia);
                        gestorAdministradores.agregarPersona(administrador);
                        JOptionPane.showMessageDialog(panel, "Administrador " + nombre + " agregado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios.");
                    }
                }
            }
        });

        JButton deleteAdminButton = new JButton("Eliminar Administrador");
        rightPanel.add(deleteAdminButton, BorderLayout.SOUTH);

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

        JButton listAdminButton = new JButton("Listar Administradores");
        rightPanel.add(listAdminButton, BorderLayout.CENTER);

        listAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Administrador> adminList = gestorAdministradores.listarAdministradores();
                StringBuilder adminInfo = new StringBuilder();
                for (Administrador admin : adminList) {
                    adminInfo.append(admin.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, adminInfo.toString(), "Lista de Administradores", JOptionPane.INFORMATION_MESSAGE);
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
                                                // Actualizar directamente los valores en la prenda
                                                prenda.setPrecio(nuevoPrecio);
                                                prenda.setStock(nuevoStock);
                                                // Actualizar la etiqueta de producto en el UI
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

        JButton guardarYSalirButton = new JButton("Guardar y Salir");
        rightPanel.add(guardarYSalirButton, BorderLayout.SOUTH);
        guardarYSalirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO AGREGAR LA FUNCION DE GUARDAR DATOS DE PERSONAS Y DE PRENDAS ACA
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuAdministradores().mostrar();
            }
        });
    }
}


