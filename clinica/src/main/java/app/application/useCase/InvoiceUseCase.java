package app.application.usecase;

import app.domain.model.Invoice;
import app.domain.model.MedicalInsurance;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import app.domain.model.Appointment;
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

    public Invoice createInvoice(Invoice invoice, Long patientId, Long appointmentId,
            List<MedicalOrder> medicalOrders) {
        System.out.println("🎯 DEBUG - InvoiceUseCase.createInvoice called");
        System.out.println(
                "   invoice patient: " + (invoice.getPatient() != null ? invoice.getPatient().getId() : "null"));
        System.out.println("   patientId param: " + patientId);
        System.out.println("   appointmentId: " + appointmentId);

        // Find patient
        System.out.println("🔍 DEBUG - Finding patient with id: " + patientId);
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> {
                    System.out.println("❌ Patient not found with id: " + patientId);
                    return new RuntimeException("Patient not found with id: " + patientId);
                });
        System.out.println("✅ Patient found: " + patientEntity.getFirstName() + " " + patientEntity.getLastName());

        // Asegurar que el invoice tenga el paciente - ESTA ES LA CLAVE
        if (invoice.getPatient() == null) {
            System.out.println("⚠️ WARNING: Invoice patient is null, setting from repository");
            Patient patient = new Patient();
            patient.setId(patientEntity.getId());
            patient.setFirstName(patientEntity.getFirstName());
            patient.setLastName(patientEntity.getLastName());
            invoice.setPatient(patient);
            System.out.println("✅ Patient set in invoice: " + patient.getId() + " - " + patient.getFirstName());
        }

        // Find appointment (optional)
        AppointmentEntity appointmentEntity = null;
        if (appointmentId != null) {
            appointmentEntity = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));
            System.out.println("✅ Appointment found: " + appointmentId);
        }

        // Get medical insurance
        MedicalInsurance insurance = getMedicalInsuranceForPatient(patientId);
        System.out.println("🔍 DEBUG - Medical insurance: " + (insurance != null ? "FOUND" : "NOT FOUND"));
        if (insurance != null) {
            System.out.println("   Insurance active: " + insurance.getIsPolicyActive());
            System.out.println("   Policy valid: " + insurance.isPolicyValid());
        }

        // Calculate annual copayment total
        Double annualCopaymentTotal = calculateAnnualCopaymentTotal(patientId);
        System.out.println("💰 DEBUG - Annual copayment total: " + annualCopaymentTotal);

        // Calculate payments based on insurance
        System.out.println("🔧 DEBUG - Calculating invoice payments...");
        invoice = invoiceService.calculateInvoicePayments(invoice, insurance, annualCopaymentTotal);
        System.out.println("✅ Payments calculated:");
        System.out.println("   Total: " + invoice.getTotalAmount());
        System.out.println("   Copayment: " + invoice.getCopaymentAmount());
        System.out.println("   Insurance: " + invoice.getInsuranceCoverage());
        System.out.println("   Patient pays: " + invoice.getPatientPayment());

        // Validate invoice
        System.out.println("🔍 DEBUG - Validating invoice...");
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

        System.out.println("💾 DEBUG - Saving invoice entity...");
        InvoiceEntity savedInvoice = invoiceRepository.save(entity);
        System.out.println("✅ Invoice saved with ID: " + savedInvoice.getId());

        // Save medical orders
        if (medicalOrders != null && !medicalOrders.isEmpty()) {
            System.out.println("📋 DEBUG - Saving " + medicalOrders.size() + " medical orders...");
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
            System.out.println("✅ Medical orders saved");
        }

        // Convert back to domain model
        System.out.println("🔄 DEBUG - Converting back to domain model...");
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

        List<InvoiceEntity> annualInvoices = invoiceRepository.findByPatientIdAndInvoiceDateBetween(patientId,
                startOfYear, endOfYear);

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

        // Patient
        if (entity.getPatient() != null) {
            Patient patient = new Patient();
            patient.setId(entity.getPatient().getId());
            patient.setFirstName(entity.getPatient().getFirstName());
            patient.setLastName(entity.getPatient().getLastName());
            invoice.setPatient(patient);
        }

        // Appointment
        if (entity.getAppointment() != null) {
            Appointment appointment = new Appointment();
            appointment.setId(entity.getAppointment().getId());
            invoice.setAppointment(appointment);
        }

        // Load medical orders - CORREGIDO para establecer relación con invoice
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

                    // NUEVO: Establecer relación con invoice
                    Invoice orderInvoice = new Invoice();
                    orderInvoice.setId(entity.getId()); // Usar el ID de la factura principal
                    order.setInvoice(orderInvoice);

                    return order;
                })
                .collect(Collectors.toList());

        invoice.setMedicalOrders(medicalOrders);

        return invoice;
    }
}