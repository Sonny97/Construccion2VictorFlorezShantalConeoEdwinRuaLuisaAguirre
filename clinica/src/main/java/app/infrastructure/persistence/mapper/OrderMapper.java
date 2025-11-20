package app.infrastructure.persistence.mapper;

import app.domain.model.Order;
import app.infrastructure.persistence.entities.OrderEntity;
import app.infrastructure.persistence.entities.PatientEntity;

public class OrderMapper {
    
    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;
        
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        if (order.getPatientId() != null) {
            PatientEntity patient = new PatientEntity();
            patient.setId(order.getPatientId());
            entity.setPatient(patient);
        }
        entity.setMedicId(order.getMedicId());
        entity.setDescription(order.getDescription());
        entity.setOrderType(order.getOrderType());
        entity.setStatus(order.getStatus());
        entity.setOrderDate(order.getOrderDate());
        entity.setCompletionDate(order.getCompletionDate());
        entity.setNotes(order.getNotes());
        return entity;
    }
    
    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        
        Order order = new Order();
        order.setId(entity.getId());
        // Mapear PatientEntity a patientId
        if (entity.getPatient() != null) {
            order.setPatientId(entity.getPatient().getId());
        }
        order.setMedicId(entity.getMedicId());
        order.setDescription(entity.getDescription());
        order.setOrderType(entity.getOrderType());
        order.setStatus(entity.getStatus());
        order.setOrderDate(entity.getOrderDate());
        order.setCompletionDate(entity.getCompletionDate());
        order.setNotes(entity.getNotes());
        return order;
    }
}
