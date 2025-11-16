package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.Order;
import app.domain.ports.OrderPort;
import java.util.List;

@Service
public class ListOrdersService {
    
    @Autowired
    private OrderPort orderPort;

    public List<Order> listAll() throws Exception {
        return orderPort.findAll();
    }
    
    public List<Order> listByPatient(Long patientId) throws Exception {
        if (patientId == null) {
            throw new Exception("El ID del paciente es obligatorio");
        }
        return orderPort.findByPatientId(patientId);
    }
    
    public List<Order> listByMedic(Long medicId) throws Exception {
        if (medicId == null) {
            throw new Exception("El ID del médico es obligatorio");
        }
        return orderPort.findByMedicId(medicId);
    }
    
    public Order findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El ID de la orden es obligatorio");
        }
        Order order = orderPort.findById(id);
        if (order == null) {
            throw new Exception("No se encontró la orden con ID: " + id);
        }
        return order;
    }
}
