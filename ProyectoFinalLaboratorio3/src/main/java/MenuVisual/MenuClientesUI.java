package MenuVisual;
import personas.Cliente;
import personas.Carrito1;
import prendas.*;
import prendas.enumsPrendas.Color;
import prendas.enumsPrendas.Genero;
import prendas.enumsPrendas.Talle;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.Map;

public class MenuClientesUI {

    private JFrame frame;
    private Cliente cliente;
    private Carrito1 carrito1;
    private GestorPrendas gestorPrendas = new GestorPrendas();

    public MenuClientesUI(Cliente cliente_) {
        this.cliente = cliente_;
        this.carrito1 = new Carrito1(cliente_);

        // TODO AGREGAR LA FUNCION DE CARGAR DATOS DE PRENDAS ACA

        Buzo buzo = new Buzo(Talle.XS, Color.AZUL, Genero.FEMENINO, 234.54, 4);
        Remera remera = new Remera(Talle.XS, Color.AZUL, Genero.FEMENINO, 234.54, 4);
        Pantalon pantalon = new Pantalon(Talle.XS, Color.ROSA, Genero.MASCULINO, 234.54, 0); // Ejemplo con stock 0
        Media media = new Media(Talle.XL, Color.AZUL, Genero.FEMENINO, 234.54, 4);

        gestorPrendas.agregarPrenda(buzo);
        gestorPrendas.agregarPrenda(remera);
        gestorPrendas.agregarPrenda(pantalon);
        gestorPrendas.agregarPrenda(media);

        frame = new JFrame("Menú Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JPanel rightPanel = new JPanel(new BorderLayout());
        panel.add(rightPanel, BorderLayout.EAST);

        JButton viewCartButton = new JButton("Ver Carrito");
        rightPanel.add(viewCartButton, BorderLayout.NORTH);

        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuCarritoUI(carrito1).mostrar();
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

        List<Prenda> prendasList = gestorPrendas.listarPrendas();

        for (Prenda prenda : prendasList) {
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
                        JOptionPane.showMessageDialog(panel, "Producto " + prenda.toString() + " agregado al carrito.");
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
                            } else {
                                JOptionPane.showMessageDialog(panel, "No se pudo reservar el producto " + prenda.toString() + ".");
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Debe iniciar sesión para reservar el producto.");
                            // Aquí podrías redirigir al cliente a iniciar sesión o registrarse si aún no lo ha hecho
                        }
                    }
                });
            }

            productPanel.add(actionButton, BorderLayout.EAST);

            productListPanel.add(productPanel);
        }

        panel.add(new JScrollPane(productListPanel), BorderLayout.CENTER);

        ActionListener actualizarPrendas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String colorSeleccionado = (String) colorComboBox.getSelectedItem();
                String talleSeleccionado = (String) talleComboBox.getSelectedItem();
                String generoSeleccionado = (String) generoComboBox.getSelectedItem();
                productListPanel.removeAll();
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

                        JButton actionButton;
                        if (prenda.getStock() > 0) {
                            actionButton = new JButton("Agregar al Carrito");
                            actionButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    carrito1.agregarPrenda(prenda);
                                    JOptionPane.showMessageDialog(panel, "Producto " + prenda.toString() + " agregado al carrito.");
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
        };

        colorComboBox.addActionListener(actualizarPrendas);
        talleComboBox.addActionListener(actualizarPrendas);
        generoComboBox.addActionListener(actualizarPrendas);

        JButton guardarYSalirButton = new JButton("Guardar y Salir");
        rightPanel.add(guardarYSalirButton, BorderLayout.SOUTH);
        guardarYSalirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO AGREGAR LA FUNCION DE GUARDAR DATOS DE PERSONAS Y DE PRENDAS ACA
                JOptionPane.showMessageDialog(frame, "Gracias por utilizar la aplicación.");
                // Aquí deberías agregar la lógica para guardar los datos en un archivo
                // por ejemplo, gestorPrendas.guardarDatos() y gestorPersonas.guardarDatos()
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Cliente cliente4=new Cliente("a","a","213","nacho2012gomez@gmail.com","usuario","a","a","a","a","a",1);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuClientesUI(cliente4);
            }
        });
    }
}