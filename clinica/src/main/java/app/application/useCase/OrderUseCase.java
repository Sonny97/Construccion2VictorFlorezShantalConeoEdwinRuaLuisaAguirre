package app.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.Order;
import app.domain.services.CreateOrderService;
import app.domain.services.ListOrdersService;
import app.domain.services.UpdateOrderService;
import java.util.List;

@Service
public class OrderUseCase {

    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    private ListOrdersService listOrdersService;

    @Autowired
    private UpdateOrderService updateOrderService;

    // Crear una nueva orden
    public Order createOrder(Order order) throws Exception {
        return createOrderService.create(order);
    }

    // Listar todas las órdenes
    public List<Order> listAllOrders() throws Exception {
        return listOrdersService.listAll();
    }

    // Listar órdenes por paciente
    public List<Order> listOrdersByPatient(Long patientId) throws Exception {
        return listOrdersService.listByPatient(patientId);
    }

    // Listar órdenes por médico
    public List<Order> listOrdersByMedic(Long medicId) throws Exception {
        return listOrdersService.listByMedic(medicId);
    }

    // Obtener una orden por ID
    public Order getOrderById(Long id) throws Exception {
        return listOrdersService.findById(id);
    }

    // Actualizar una orden
    public Order updateOrder(Order order) throws Exception {
        return updateOrderService.update(order);
    }
}
