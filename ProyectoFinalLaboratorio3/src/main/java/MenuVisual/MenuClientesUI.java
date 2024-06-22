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
    private  Carrito1 carrito1;
    private GestorPrendas gestorPrendas=new GestorPrendas();


    public MenuClientesUI(Cliente cliente_) {
        this.cliente=cliente_;
        this.carrito1=new Carrito1(cliente);

        Buzo buzo=new Buzo(Talle.XS, Color.AZUL, Genero.FEMENINO,234.54,4);
        Remera remera =new Remera(Talle.XS, Color.AZUL, Genero.FEMENINO,234.54,4);
        Pantalon pantalon =new Pantalon(Talle.XS, Color.ROSA, Genero.MASCULINO,234.54,4);
        Media media =new Media(Talle.XL, Color.AZUL, Genero.FEMENINO,234.54,4);

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
                // Cerrar el menú actual
                frame.dispose();

                // Mostrar el menú del carrito
               new MenuCarritoUI(carrito1).mostrar();
            }
        });

        JPanel productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));

        List<Prenda> prendasList= gestorPrendas.listarPrendas();

        for (Prenda prenda : prendasList) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            productPanel.setBorder(BorderFactory.createEtchedBorder());

            JLabel productLabel = new JLabel(prenda.toString());
            productPanel.add(productLabel, BorderLayout.CENTER);

            JButton addToCartButton = new JButton("Agregar al Carrito");
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carrito1.agregarPrenda(prenda);
                    JOptionPane.showMessageDialog(panel, "Producto " + prenda.toString() + " agregado al carrito.");
                }
            });
            productPanel.add(addToCartButton, BorderLayout.EAST);

            productListPanel.add(productPanel);
        }

        panel.add(new JScrollPane(productListPanel), BorderLayout.CENTER);


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