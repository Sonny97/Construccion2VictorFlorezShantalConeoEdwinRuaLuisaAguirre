package app.infrastructure.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import app.adapter.rest.request.OrderItemRequest;
import app.infrastructure.persistence.entities.OrderItemEntity;


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
        // Mapear los ítems del request a entidades
        if (order.getItems() != null) {
            List<OrderItemEntity> itemEntities = new ArrayList<>();
            int itemNumber = 1;
            for (OrderItemRequest itemReq : order.getItems()) {
                OrderItemEntity itemEntity = new OrderItemEntity();
                itemEntity.setOrder(entity);
                itemEntity.setItemNumber(itemNumber++);
                itemEntity.setItemType(itemReq.getItemType());
                itemEntity.setItemName(itemReq.getItemName());
                itemEntities.add(itemEntity);
            }
            entity.setItems(itemEntities);
        }
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
        // Mapear los ítems de la entidad a DTO
        if (entity.getItems() != null) {
            List<OrderItemRequest> itemRequests = new ArrayList<>();
            for (OrderItemEntity itemEntity : entity.getItems()) {
                OrderItemRequest itemReq = new OrderItemRequest();
                itemReq.setItemType(itemEntity.getItemType());
                itemReq.setItemName(itemEntity.getItemName());
                itemRequests.add(itemReq);
            }
            order.setItems(itemRequests);
        }
        return order;
    }
}
