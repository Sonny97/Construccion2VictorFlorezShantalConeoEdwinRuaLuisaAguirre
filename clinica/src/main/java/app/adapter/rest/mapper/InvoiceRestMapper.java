package app.adapter.rest.mapper;

import app.adapter.rest.request.InvoiceRequest;
import app.adapter.rest.response.InvoiceResponse;
import app.domain.model.Invoice;
import app.infrastructure.persistence.entities.InvoiceEntity;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class InvoiceRestMapper {

    public Invoice toDomain(InvoiceRequest request) {
        if (request == null) return null;
        
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setInvoiceDate(request.getInvoiceDate());
        invoice.setTotalAmount(request.getTotalAmount());
        invoice.setStatus("PENDING");
        return invoice;
    }

    public InvoiceResponse toResponse(Invoice invoice) {
        if (invoice == null) return null;
        
        InvoiceResponse response = new InvoiceResponse();
        response.setId(invoice.getId());
        response.setInvoiceNumber(invoice.getInvoiceNumber());
        response.setInvoiceDate(invoice.getInvoiceDate());
        response.setTotalAmount(invoice.getTotalAmount());
        response.setCopaymentAmount(invoice.getCopaymentAmount());
        response.setInsuranceCoverage(invoice.getInsuranceCoverage());
        response.setPatientPayment(invoice.getPatientPayment());
        response.setStatus(invoice.getStatus());
        
        if (invoice.getPatient() != null) {
            response.setPatientId(invoice.getPatient().getId());
            response.setPatientName(invoice.getPatient().getFirstName() + " " + invoice.getPatient().getLastName());
        }
        
        if (invoice.getAppointment() != null) {
            response.setAppointmentId(invoice.getAppointment().getId());
        }
        
        return response;
    }

    public InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null) return null;
        
        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(invoice.getId());
        entity.setInvoiceNumber(invoice.getInvoiceNumber());
        entity.setInvoiceDate(invoice.getInvoiceDate());
        entity.setTotalAmount(invoice.getTotalAmount());
        entity.setCopaymentAmount(invoice.getCopaymentAmount());
        entity.setInsuranceCoverage(invoice.getInsuranceCoverage());
        entity.setPatientPayment(invoice.getPatientPayment());
        entity.setStatus(invoice.getStatus());
        return entity;
    }

    public Invoice toDomain(InvoiceEntity entity) {
        if (entity == null) return null;
        
        Invoice invoice = new Invoice();
        invoice.setId(entity.getId());
        invoice.setInvoiceNumber(entity.getInvoiceNumber());
        invoice.setInvoiceDate(entity.getInvoiceDate());
        invoice.setTotalAmount(entity.getTotalAmount());
        invoice.setCopaymentAmount(entity.getCopaymentAmount());
        invoice.setInsuranceCoverage(entity.getInsuranceCoverage());
        invoice.setPatientPayment(entity.getPatientPayment());
        invoice.setStatus(entity.getStatus());
        return invoice;
    }

    private String generateInvoiceNumber() {
        return "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}