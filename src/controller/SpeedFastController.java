package controller;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import model.Entrega;
import model.Pedido;
import model.Repartidor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SpeedFastController {

    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final EntregaDAO entregaDAO;

    public SpeedFastController() {

        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();
        entregaDAO = new EntregaDAO();
    }

    public boolean registrarPedido(String direccion, String tipo, String estado) {

        Pedido pedido = new Pedido(
                direccion,
                tipo,
                estado
        );

        return pedidoDAO.guardar(pedido);
    }

    public List<Pedido> listarPedidos() {

        return pedidoDAO.listarTodos();
    }

    public boolean registrarRepartidor(String nombre) {

        Repartidor repartidor = new Repartidor(nombre);

        return repartidorDAO.guardar(repartidor);
    }

    public List<Repartidor> listarRepartidores() {

        return repartidorDAO.listarTodos();
    }

    public boolean registrarEntrega(int idPedido, int idRepartidor) {

        Entrega entrega = new Entrega(
                idPedido,
                idRepartidor,
                LocalDate.now(),
                LocalTime.now()
        );

        return entregaDAO.guardar(entrega);
    }
}
