package view;

import controller.SpeedFastController;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaSpeedFast extends JFrame {

    private final SpeedFastController controller;

    // PEDIDOS

    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JComboBox<String> cmbEstado;

    private JTable tablaPedidos;
    private DefaultTableModel modeloTablaPedidos;

    // REPARTIDORES

    private JTextField txtNombreRepartidor;


    public VentanaSpeedFast() {

        controller = new SpeedFastController();

        setTitle("SpeedFast");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        iniciarComponentes();
        cargarPedidos();
    }


    private void iniciarComponentes() {

        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(
                new BoxLayout(panelFormulario, BoxLayout.Y_AXIS)
        );

        panelFormulario.add(crearPanelPedidos());
        panelFormulario.add(crearPanelRepartidores());

        add(panelFormulario, BorderLayout.NORTH);

        crearTablaPedidos();
    }


    // PEDIDOS

    private JPanel crearPanelPedidos() {

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createTitledBorder("Registrar pedido")
        );

        panel.add(new JLabel("Dirección:"));

        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        panel.add(new JLabel("Tipo:"));

        cmbTipo = new JComboBox<>(new String[]{
                "COMIDA",
                "ENCOMIENDA",
                "EXPRESS"
        });

        panel.add(cmbTipo);

        panel.add(new JLabel("Estado:"));

        cmbEstado = new JComboBox<>(new String[]{
                "PENDIENTE",
                "EN_REPARTO",
                "ENTREGADO"
        });

        panel.add(cmbEstado);

        JButton btnRegistrarPedido =
                new JButton("Registrar pedido");

        JPanel panelBoton = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        panelBoton.add(btnRegistrarPedido);

        panel.add(new JLabel(""));
        panel.add(panelBoton);

        btnRegistrarPedido.addActionListener(
                e -> registrarPedido()
        );

        return panel;
    }


    // REPARTIDORES

    private JPanel crearPanelRepartidores() {

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createTitledBorder("Registrar repartidor")
        );

        panel.add(new JLabel("Nombre:"));

        txtNombreRepartidor = new JTextField();
        panel.add(txtNombreRepartidor);

        JButton btnRegistrarRepartidor =
                new JButton("Registrar repartidor");

        JPanel panelBoton = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        panelBoton.add(btnRegistrarRepartidor);

        panel.add(new JLabel(""));
        panel.add(panelBoton);

        btnRegistrarRepartidor.addActionListener(
                e -> registrarRepartidor()
        );

        return panel;
    }


    // TABLA PEDIDOS

    private void crearTablaPedidos() {

        modeloTablaPedidos = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Dirección",
                        "Tipo",
                        "Estado"
                },
                0
        );

        tablaPedidos = new JTable(modeloTablaPedidos);

        JScrollPane scroll =
                new JScrollPane(tablaPedidos);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Pedidos registrados"
                )
        );

        add(scroll, BorderLayout.CENTER);
    }


    // REGISTRAR PEDIDO

    private void registrarPedido() {

        String direccion =
                txtDireccion.getText().trim();

        String tipo =
                cmbTipo.getSelectedItem().toString();

        String estado =
                cmbEstado.getSelectedItem().toString();

        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una dirección"
            );

            return;
        }

        boolean guardado =
                controller.registrarPedido(
                        direccion,
                        tipo,
                        estado
                );

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido registrado correctamente"
            );

            txtDireccion.setText("");

            cargarPedidos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el pedido"
            );
        }
    }


    // REGISTRAR REPARTIDOR

    private void registrarRepartidor() {

        String nombre =
                txtNombreRepartidor.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el nombre del repartidor"
            );

            return;
        }

        boolean guardado =
                controller.registrarRepartidor(nombre);

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor registrado correctamente"
            );

            txtNombreRepartidor.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el repartidor"
            );
        }
    }


    // CARGAR PEDIDOS

    private void cargarPedidos() {

        modeloTablaPedidos.setRowCount(0);

        List<Pedido> pedidos =
                controller.listarPedidos();

        for (Pedido pedido : pedidos) {

            modeloTablaPedidos.addRow(
                    new Object[]{
                            pedido.getId(),
                            pedido.getDireccion(),
                            pedido.getTipo(),
                            pedido.getEstado()
                    }
            );
        }
    }
}