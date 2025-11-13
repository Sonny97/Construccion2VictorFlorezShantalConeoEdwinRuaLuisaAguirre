package app.application.usecase;

import app.domain.model.Invoice;
import app.domain.model.MedicalInsurance;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.services.InvoiceService;
import app.domain.services.MedicalInsuranceService;
import app.infrastructure.persistence.entities.*;
import app.infrastructure.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceUseCase {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private MedicalInsuranceService medicalInsuranceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicalInsuranceRepository medicalInsuranceRepository;

    @Autowired
    private MedicalOrderRepository medicalOrderRepository;

    public Invoice createInvoice(Invoice invoice, Long patientId, Long appointmentId, List<MedicalOrder> medicalOrders) {
        // Find patient
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        // Find appointment (optional)
        AppointmentEntity appointmentEntity = null;
        if (appointmentId != null) {
            appointmentEntity = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));
        }

        // Get medical insurance
        MedicalInsurance insurance = getMedicalInsuranceForPatient(patientId);

        // Calculate annual copayment total
        Double annualCopaymentTotal = calculateAnnualCopaymentTotal(patientId);

        // Calculate payments based on insurance
        invoice = invoiceService.calculateInvoicePayments(invoice, insurance, annualCopaymentTotal);

        // Validate invoice
        invoiceService.validateInvoice(invoice);

        // Convert to entity and save
        InvoiceEntity entity = new InvoiceEntity();
        entity.setInvoiceNumber(invoice.getInvoiceNumber());
        entity.setInvoiceDate(invoice.getInvoiceDate());
        entity.setTotalAmount(invoice.getTotalAmount());
        entity.setCopaymentAmount(invoice.getCopaymentAmount());
        entity.setInsuranceCoverage(invoice.getInsuranceCoverage());
        entity.setPatientPayment(invoice.getPatientPayment());
        entity.setStatus(invoice.getStatus());
        entity.setPatient(patientEntity);
        entity.setAppointment(appointmentEntity);

        InvoiceEntity savedInvoice = invoiceRepository.save(entity);

        // Save medical orders
        if (medicalOrders != null && !medicalOrders.isEmpty()) {
            List<MedicalOrderEntity> orderEntities = medicalOrders.stream()
                    .map(order -> {
                        MedicalOrderEntity orderEntity = new MedicalOrderEntity();
                        orderEntity.setOrderType(order.getOrderType());
                        orderEntity.setName(order.getName());
                        orderEntity.setDescription(order.getDescription());
                        orderEntity.setCost(order.getCost());
                        orderEntity.setDosage(order.getDosage());
                        orderEntity.setInstructions(order.getInstructions());
                        orderEntity.setInvoice(savedInvoice);
                        return orderEntity;
                    })
                    .collect(Collectors.toList());
            
            medicalOrderRepository.saveAll(orderEntities);
        }

        // Convert back to domain model
        return convertToDomain(savedInvoice);
    }

    public List<Invoice> getInvoicesByPatientId(Long patientId) {
        List<InvoiceEntity> entities = invoiceRepository.findByPatientId(patientId);
        return entities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    public Invoice getInvoiceById(Long invoiceId) {
        InvoiceEntity entity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        return convertToDomain(entity);
    }

    private MedicalInsurance getMedicalInsuranceForPatient(Long patientId) {
        return medicalInsuranceRepository.findByPatientId(patientId)
                .map(entity -> {
                    MedicalInsurance insurance = new MedicalInsurance();
                    insurance.setId(entity.getId());
                    insurance.setCompanyName(entity.getCompanyName());
                    insurance.setPolicyNumber(entity.getPolicyNumber());
                    insurance.setIsPolicyActive(entity.getIsPolicyActive());
                    insurance.setPolicyExpiryDate(entity.getPolicyExpiryDate());
                    return insurance;
                })
                .orElse(null);
    }

    private Double calculateAnnualCopaymentTotal(Long patientId) {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        LocalDate endOfYear = LocalDate.now().withDayOfYear(365);
        
        List<InvoiceEntity> annualInvoices = invoiceRepository.findByPatientIdAndInvoiceDateBetween(patientId, startOfYear, endOfYear);
        
        return annualInvoices.stream()
                .mapToDouble(invoice -> invoice.getCopaymentAmount() != null ? invoice.getCopaymentAmount() : 0.0)
                .sum();
    }

    private Invoice convertToDomain(InvoiceEntity entity) {
        Invoice invoice = new Invoice();
        invoice.setId(entity.getId());
        invoice.setInvoiceNumber(entity.getInvoiceNumber());
        invoice.setInvoiceDate(entity.getInvoiceDate());
        invoice.setTotalAmount(entity.getTotalAmount());
        invoice.setCopaymentAmount(entity.getCopaymentAmount());
        invoice.setInsuranceCoverage(entity.getInsuranceCoverage());
        invoice.setPatientPayment(entity.getPatientPayment());
        invoice.setStatus(entity.getStatus());
        
        if (entity.getPatient() != null) {
            Patient patient = new Patient();
            patient.setId(entity.getPatient().getId());
            patient.setFirstName(entity.getPatient().getFirstName());
            patient.setLastName(entity.getPatient().getLastName());
            invoice.setPatient(patient);
        }
        
        // Load medical orders
        List<MedicalOrderEntity> orderEntities = medicalOrderRepository.findByInvoiceId(entity.getId());
        List<MedicalOrder> medicalOrders = orderEntities.stream()
                .map(orderEntity -> {
                    MedicalOrder order = new MedicalOrder();
                    order.setId(orderEntity.getId());
                    order.setOrderType(orderEntity.getOrderType());
                    order.setName(orderEntity.getName());
                    order.setDescription(orderEntity.getDescription());
                    order.setCost(orderEntity.getCost());
                    order.setDosage(orderEntity.getDosage());
                    order.setInstructions(orderEntity.getInstructions());
                    return order;
                })
                .collect(Collectors.toList());
        
        invoice.setMedicalOrders(medicalOrders);
        
        return invoice;
    }
}