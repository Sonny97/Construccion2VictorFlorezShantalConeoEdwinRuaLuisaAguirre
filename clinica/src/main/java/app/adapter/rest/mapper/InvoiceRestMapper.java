package app.adapter.rest.mapper;

import app.adapter.rest.request.InvoiceRequest;
import app.adapter.rest.response.InvoiceResponse;
import app.adapter.rest.response.MedicalOrderResponse;
import app.domain.model.Invoice;
import app.domain.model.Patient;
import app.domain.model.Appointment; // ← NUEVO IMPORT
import app.infrastructure.persistence.entities.InvoiceEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InvoiceRestMapper {
    @Autowired
    private MedicalOrderRestMapper medicalOrderRestMapper;

    public Invoice toDomain(InvoiceRequest request) {
        if (request == null)
            return null;

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setInvoiceDate(request.getInvoiceDate());
        invoice.setTotalAmount(request.getTotalAmount());
        invoice.setStatus("PENDING");

        // Establecer paciente
        if (request.getPatientId() != null) {
            Patient patient = new Patient();
            patient.setId(request.getPatientId());
            invoice.setPatient(patient);
        }

        // NUEVO: Establecer appointment
        if (request.getAppointmentId() != null) {
            Appointment appointment = new Appointment();
            appointment.setId(request.getAppointmentId());
            invoice.setAppointment(appointment);
            System.out.println("✅ DEBUG - Appointment set in invoice with ID: " + request.getAppointmentId());
        } else {
            System.out.println("ℹ️ DEBUG - No appointmentId in request");
        }

        return invoice;
    }

    public InvoiceResponse toResponse(Invoice invoice) {
        if (invoice == null)
            return null;

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

        // NUEVO: Incluir appointmentId en la respuesta
        if (invoice.getAppointment() != null) {
            response.setAppointmentId(invoice.getAppointment().getId());
        }
        if (invoice.getMedicalOrders() != null && !invoice.getMedicalOrders().isEmpty()) {
            List<MedicalOrderResponse> medicalOrderResponses = invoice.getMedicalOrders().stream()
                    .map(medicalOrderRestMapper::toResponse)
                    .collect(Collectors.toList());
            response.setMedicalOrders(medicalOrderResponses);
            System.out.println("✅ DEBUG - Mapped " + medicalOrderResponses.size() + " medical orders to response");
        } else {
            System.out.println("ℹ️ DEBUG - No medical orders to map");
        }

        return response;
    }

    public InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null)
            return null;

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
        if (entity == null)
            return null;

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