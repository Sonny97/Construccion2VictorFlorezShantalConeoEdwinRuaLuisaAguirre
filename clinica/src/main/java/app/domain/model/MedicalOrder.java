package app.domain.model;

public class MedicalOrder {
    private Long id;
    private String orderType; // MEDICATION, PROCEDURE, DIAGNOSTIC_AID
    private String name;
    private String description;
    private Double cost;
    private String dosage; // For medications
    private String instructions;
    private Invoice invoice;

    // Order type constants
    public static final String MEDICATION = "MEDICATION";
    public static final String PROCEDURE = "PROCEDURE";
    public static final String DIAGNOSTIC_AID = "DIAGNOSTIC_AID";

    // Constructors
    public MedicalOrder() {}

    public MedicalOrder(String orderType, String name, String description, Double cost, Invoice invoice) {
        this.orderType = orderType;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.invoice = invoice;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    // Business logic methods
    public boolean isMedication() {
        return MEDICATION.equals(orderType);
    }
    
    public boolean isProcedure() {
        return PROCEDURE.equals(orderType);
    }
    
    public boolean isDiagnosticAid() {
        return DIAGNOSTIC_AID.equals(orderType);
    }
}