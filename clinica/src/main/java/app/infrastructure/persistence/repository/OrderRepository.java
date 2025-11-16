package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.infrastructure.persistence.entities.OrderEntity;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
    List<OrderEntity> findByPatientId(Long patientId);
    
    List<OrderEntity> findByMedicId(Long medicId);
    
    List<OrderEntity> findByStatus(String status);
    
    List<OrderEntity> findByOrderType(String orderType);
}
