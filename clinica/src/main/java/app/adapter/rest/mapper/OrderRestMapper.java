package app.adapter.rest.mapper;

import org.springframework.stereotype.Component;
import app.adapter.rest.request.OrderRequest;
import app.adapter.rest.response.OrderResponse;
import app.domain.model.Order;

@Component
public class OrderRestMapper {

    public Order toDomain(OrderRequest request) {
        if (request == null) return null;
        
        Order order = new Order();
        order.setPatientId(request.getPatientId());
        order.setMedicId(request.getMedicId());
        order.setDescription(request.getDescription());
        order.setOrderType(request.getOrderType());
        order.setStatus(request.getStatus());
        order.setOrderDate(request.getOrderDate());
        order.setCompletionDate(request.getCompletionDate());
        order.setNotes(request.getNotes());
        
        return order;
    }

    public OrderResponse toResponse(Order order) {
        if (order == null) return null;
        
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setPatientId(order.getPatientId());
        response.setMedicId(order.getMedicId());
        response.setDescription(order.getDescription());
        response.setOrderType(order.getOrderType());
        response.setStatus(order.getStatus());
        response.setOrderDate(order.getOrderDate());
        response.setCompletionDate(order.getCompletionDate());
        response.setNotes(order.getNotes());
        
        return response;
    }
}
