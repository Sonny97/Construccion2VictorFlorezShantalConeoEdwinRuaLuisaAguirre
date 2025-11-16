package app.domain.ports;

import app.domain.model.Order;
import java.util.List;

public interface OrderPort {
    Order findById(Long id) throws Exception;
    List<Order> findByPatientId(Long patientId) throws Exception;
    List<Order> findByMedicId(Long medicId) throws Exception;
    List<Order> findAll() throws Exception;
    void save(Order order) throws Exception;
    void update(Order order) throws Exception;
    void deleteById(Long id) throws Exception;
}
