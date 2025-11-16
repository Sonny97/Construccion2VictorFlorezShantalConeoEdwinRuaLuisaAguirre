package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrderEntity, Long> {
    List<MedicalOrderEntity> findByInvoiceId(Long invoiceId);
    List<MedicalOrderEntity> findByOrderType(String orderType);
}