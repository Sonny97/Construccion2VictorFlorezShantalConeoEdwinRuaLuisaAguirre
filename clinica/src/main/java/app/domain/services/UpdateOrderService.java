package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.Order;
import app.domain.ports.OrderPort;
import java.time.LocalDateTime;

@Service
public class UpdateOrderService {
    
    @Autowired
    private OrderPort orderPort;

    public Order update(Order order) throws Exception {
        // Validar que el ID esté presente
        if (order.getId() == null) {
            throw new Exception("El ID de la orden es obligatorio para actualizar");
        }
        
        // Verificar que la orden existe
        Order existingOrder = orderPort.findById(order.getId());
        if (existingOrder == null) {
            throw new Exception("No se encontró la orden con ID: " + order.getId());
        }
        
        // Si se está completando la orden, establecer la fecha de completado
        if ("COMPLETED".equals(order.getStatus()) && order.getCompletionDate() == null) {
            order.setCompletionDate(LocalDateTime.now());
        }
        
        // Actualizar la orden
        orderPort.update(order);
        return order;
    }
}
