package MenuVisual;
import personas.Carrito1;
import personas.Cliente;
import personas.Factura1;
import prendas.Prenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuCarritoUI {

    private JFrame frame;
    private Carrito1 carrito;

    public MenuCarritoUI(Carrito1 carrito1) {

        this.carrito=carrito1;
        frame = new JFrame("Menú Carrito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        mostrarProductosEnCarrito(panel);

        frame.setVisible(true);
    }

    private void mostrarProductosEnCarrito(JPanel panel) {
        panel.removeAll();

        JPanel productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));

        List<Prenda> prendasList = carrito.getPrendas();

        for (Prenda prenda : prendasList) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            productPanel.setBorder(BorderFactory.createEtchedBorder());

            JLabel productLabel = new JLabel(prenda.toString());
            productPanel.add(productLabel, BorderLayout.CENTER);

            JButton removeFromCartButton = new JButton("Eliminar");
            removeFromCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carrito.eliminarPrenda(prenda);
                    mostrarProductosEnCarrito(panel);
                    JOptionPane.showMessageDialog(panel, "Eliminar producto " + prenda.toString() + " del carrito.");
                    actualizarMontoLabel(panel);
                }
            });
            productPanel.add(removeFromCartButton, BorderLayout.EAST);

            productListPanel.add(productPanel);
        }

        panel.add(new JScrollPane(productListPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton seguirComprandoButton = new JButton("Seguir Comprando");
        seguirComprandoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                new MenuClientesUI(carrito.getCliente()).mostrar();
            }
        });
        buttonPanel.add(seguirComprandoButton);

        JButton finalizarButton = new JButton("Finalizar");
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Factura1 factura1 = new Factura1(carrito.getCliente(), carrito);
                factura1.enviarFactura();
                JOptionPane.showMessageDialog(panel, "Compra finalizada. Gracias por su compra!");
                carrito.limpiarCarrito();
                mostrarProductosEnCarrito(panel);
                actualizarMontoLabel(panel);
            }
        });
        buttonPanel.add(finalizarButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        JLabel montoLabel = new JLabel("Monto total del carrito: $" + carrito.getMonto());
        panel.add(montoLabel, BorderLayout.NORTH);

        panel.revalidate();
        panel.repaint();
    }

    private void actualizarMontoLabel(JPanel panel) {
        JLabel montoLabel = new JLabel("Monto total del carrito: $" + carrito.getMonto());
        panel.add(montoLabel, BorderLayout.NORTH);
        panel.revalidate();
        panel.repaint();
    }

    public void mostrar() {
        frame.setVisible(true);
    }


}
