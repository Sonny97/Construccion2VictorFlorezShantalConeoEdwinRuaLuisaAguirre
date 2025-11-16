package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_orders")
public class MedicalOrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_type", nullable = false, length = 20)
    private String orderType; // MEDICATION, PROCEDURE, DIAGNOSTIC_AID
    
    @Column(nullable = false, length = 200)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Column(nullable = false)
    private Double cost;
    
    @Column(length = 100)
    private String dosage; // For medications
    
    @Column(length = 500)
    private String instructions;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceEntity invoice;

    // Constructors
    public MedicalOrderEntity() {}

    public MedicalOrderEntity(String orderType, String name, String description, Double cost, InvoiceEntity invoice) {
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
    public InvoiceEntity getInvoice() { return invoice; }
    public void setInvoice(InvoiceEntity invoice) { this.invoice = invoice; }
}