package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.Order;
import app.domain.ports.OrderPort;
import java.time.LocalDateTime;

@Service
public class CreateOrderService {
    
    @Autowired
    private OrderPort orderPort;

    public Order create(Order order) throws Exception {
        // Validar que los campos requeridos estén presentes
        if (order.getPatientId() == null) {
            throw new Exception("El ID del paciente es obligatorio");
        }
        
        if (order.getMedicId() == null) {
            throw new Exception("El ID del médico es obligatorio");
        }
        
        if (order.getDescription() == null || order.getDescription().isEmpty()) {
            throw new Exception("La descripción de la orden es obligatoria");
        }
        
        if (order.getOrderType() == null || order.getOrderType().isEmpty()) {
            throw new Exception("El tipo de orden es obligatorio");
        }
        
        // Establecer valores por defecto
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PENDING");
        }
        
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }
        
        // Guardar la orden
        orderPort.save(order);
        return order;
    }
}
