package app.adapter.rest.response;

import app.domain.model.Order;
import app.adapter.rest.response.OrderResponse;
import app.adapter.rest.response.OrderItemResponse;
import app.adapter.rest.request.OrderItemRequest;
import java.util.ArrayList;
import java.util.List;

public class OrderResponseMapper {
    public static OrderResponse toResponse(Order order) {
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
        // Mapear los ítems
        if (order.getItems() != null) {
            List<OrderItemResponse> itemResponses = new ArrayList<>();
            int itemNumber = 1;
            for (OrderItemRequest itemReq : order.getItems()) {
                OrderItemResponse itemResp = new OrderItemResponse();
                itemResp.setItemType(itemReq.getItemType());
                itemResp.setItemName(itemReq.getItemName());
                itemResp.setItemNumber(itemNumber++);
                itemResponses.add(itemResp);
            }
            response.setItems(itemResponses);
        }
        return response;
    }
}
