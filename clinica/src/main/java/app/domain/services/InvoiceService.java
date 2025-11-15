package app.domain.services;

import app.domain.model.Invoice;
import app.domain.model.MedicalInsurance;
import app.domain.model.MedicalOrder;
import app.domain.model.Patient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceService {

    private static final Double FIXED_COPAYMENT = 50000.0;
    private static final Double ANNUAL_CAP = 1000000.0;

    public Invoice calculateInvoicePayments(Invoice invoice, MedicalInsurance insurance, Double annualCopaymentTotal) {
        if (insurance != null && insurance.isPolicyValid()) {
            if (annualCopaymentTotal >= ANNUAL_CAP) {
                // Patient has reached annual cap - insurance covers everything
                invoice.setCopaymentAmount(0.0);
                invoice.setInsuranceCoverage(invoice.getTotalAmount());
                invoice.setPatientPayment(0.0);
            } else {
                // Normal copayment calculation
                invoice.setCopaymentAmount(FIXED_COPAYMENT);
                invoice.setInsuranceCoverage(invoice.getTotalAmount() - FIXED_COPAYMENT);
                invoice.setPatientPayment(FIXED_COPAYMENT);
            }
        } else {
            // No valid insurance - patient pays full amount
            invoice.setCopaymentAmount(0.0);
            invoice.setInsuranceCoverage(0.0);
            invoice.setPatientPayment(invoice.getTotalAmount());
        }
        return invoice;
    }

    public Double calculateTotalAmount(List<MedicalOrder> medicalOrders) {
        return medicalOrders.stream()
                .mapToDouble(order -> order.getCost() != null ? order.getCost() : 0.0)
                .sum();
    }

    public void validateInvoice(Invoice invoice) {
        if (invoice.getTotalAmount() == null || invoice.getTotalAmount() <= 0) {
            throw new IllegalArgumentException("Invoice total amount must be greater than 0");
        }
        if (invoice.getPatient() == null) {
            throw new IllegalArgumentException("Patient is required for invoice");
        }
        if (invoice.getInvoiceDate() == null) {
            throw new IllegalArgumentException("Invoice date is required");
        }
    }
}