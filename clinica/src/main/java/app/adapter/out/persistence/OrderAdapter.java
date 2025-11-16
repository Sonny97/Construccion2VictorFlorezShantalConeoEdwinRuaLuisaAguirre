package app.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.Order;
import app.domain.ports.OrderPort;
import app.infrastructure.persistence.entities.OrderEntity;
import app.infrastructure.persistence.mapper.OrderMapper;
import app.infrastructure.persistence.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderAdapter implements OrderPort {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findById(Long id) throws Exception {
        OrderEntity entity = orderRepository.findById(id).orElse(null);
        return entity != null ? OrderMapper.toDomain(entity) : null;
    }

    @Override
    public List<Order> findByPatientId(Long patientId) throws Exception {
        List<OrderEntity> entities = orderRepository.findByPatientId(patientId);
        return entities.stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByMedicId(Long medicId) throws Exception {
        List<OrderEntity> entities = orderRepository.findByMedicId(medicId);
        return entities.stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() throws Exception {
        List<OrderEntity> entities = orderRepository.findAll();
        return entities.stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Order order) throws Exception {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(entity);
        order.setId(savedEntity.getId());
    }

    @Override
    public void update(Order order) throws Exception {
        if (!orderRepository.existsById(order.getId())) {
            throw new Exception("Orden no encontrada con ID: " + order.getId());
        }
        orderRepository.save(OrderMapper.toEntity(order));
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (!orderRepository.existsById(id)) {
            throw new Exception("Orden no encontrada con ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
