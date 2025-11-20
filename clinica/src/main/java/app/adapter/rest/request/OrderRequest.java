package app.adapter.rest.request;

import java.util.List;
import app.adapter.rest.request.OrderItemRequest;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {
    private Long patientId;
    private Long medicId;
    private String description;
    private String orderType;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime completionDate;
    private String notes;
    private List<OrderItemRequest> items;


    // Getters y Setters
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getMedicId() { return medicId; }
    public void setMedicId(Long medicId) { this.medicId = medicId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public LocalDateTime getCompletionDate() { return completionDate; }
    public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
