package app.domain.services;

public class Billing {
    private String billingId;       // Unique billing ID
    private String patientId;       // Patient linked to the billing
    private double totalAmount;     // Total amount of the bill
    private double copay;           // Copay if insurance is active
    private boolean insuranceActive; // Insurance status (true = active, false = inactive)

    // Constructor
    public Billing(String billingId, String patientId, double totalAmount, double copay, boolean insuranceActive) {
        this.billingId = billingId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.copay = copay;
        this.insuranceActive = insuranceActive;
    }

    // Getters and setters
    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCopay() {
        return copay;
    }

    public void setCopay(double copay) {
        this.copay = copay;
    }

    public boolean isInsuranceActive() {
        return insuranceActive;
    }

    public void setInsuranceActive(boolean insuranceActive) {
        this.insuranceActive = insuranceActive;
    }
}
