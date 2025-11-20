package app.domain.model;

import java.util.List;
import app.adapter.rest.request.OrderItemRequest;
import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long patientId;
    private Long medicId;
    private String description;
    private String orderType;  // MEDICATION, LAB_TEST, IMAGING, etc.
    private String status;     // PENDING, COMPLETED, CANCELLED
    private LocalDateTime orderDate;
    private LocalDateTime completionDate;
    private String notes;
        private List<OrderItemRequest> items;

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }


    // Constructors
    public Order() {}

    public Order(Long patientId, Long medicId, String description, 
                String orderType, String status, LocalDateTime orderDate, String notes) {
        this.patientId = patientId;
        this.medicId = medicId;
        this.description = description;
        this.orderType = orderType;
        this.status = status;
        this.orderDate = orderDate;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedicId() {
        return medicId;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
