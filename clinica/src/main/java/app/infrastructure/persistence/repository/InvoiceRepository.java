package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    List<InvoiceEntity> findByPatientId(Long patientId);
    List<InvoiceEntity> findByStatus(String status);
    Optional<InvoiceEntity> findByInvoiceNumber(String invoiceNumber);
    List<InvoiceEntity> findByInvoiceDateBetween(LocalDate start, LocalDate end);
    List<InvoiceEntity> findByPatientIdAndInvoiceDateBetween(Long patientId, LocalDate start, LocalDate end);
}