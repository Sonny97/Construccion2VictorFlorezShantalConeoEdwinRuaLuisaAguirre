package app.adapter.rest.mapper;

import app.adapter.rest.request.MedicalOrderRequest;
import app.adapter.rest.response.MedicalOrderResponse;
import app.domain.model.MedicalOrder;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicalOrderRestMapper {

    public MedicalOrder toDomain(MedicalOrderRequest request) {
        if (request == null) return null;
        
        MedicalOrder order = new MedicalOrder();
        order.setOrderType(request.getOrderType());
        order.setName(request.getName());
        order.setDescription(request.getDescription());
        order.setCost(request.getCost());
        order.setDosage(request.getDosage());
        order.setInstructions(request.getInstructions());
        return order;
    }

    public MedicalOrderResponse toResponse(MedicalOrder order) {
        if (order == null) return null;
        
        MedicalOrderResponse response = new MedicalOrderResponse();
        response.setId(order.getId());
        response.setOrderType(order.getOrderType());
        response.setName(order.getName());
        response.setDescription(order.getDescription());
        response.setCost(order.getCost());
        response.setDosage(order.getDosage());
        response.setInstructions(order.getInstructions());
        
        if (order.getInvoice() != null) {
            response.setInvoiceId(order.getInvoice().getId());
        }
        
        return response;
    }

    public MedicalOrderEntity toEntity(MedicalOrder order) {
        if (order == null) return null;
        
        MedicalOrderEntity entity = new MedicalOrderEntity();
        entity.setId(order.getId());
        entity.setOrderType(order.getOrderType());
        entity.setName(order.getName());
        entity.setDescription(order.getDescription());
        entity.setCost(order.getCost());
        entity.setDosage(order.getDosage());
        entity.setInstructions(order.getInstructions());
        return entity;
    }

    public MedicalOrder toDomain(MedicalOrderEntity entity) {
        if (entity == null) return null;
        
        MedicalOrder order = new MedicalOrder();
        order.setId(entity.getId());
        order.setOrderType(entity.getOrderType());
        order.setName(entity.getName());
        order.setDescription(entity.getDescription());
        order.setCost(entity.getCost());
        order.setDosage(entity.getDosage());
        order.setInstructions(entity.getInstructions());
        return order;
    }
}