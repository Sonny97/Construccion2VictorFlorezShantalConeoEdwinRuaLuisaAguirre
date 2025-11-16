package app.adapter.in.rest.controllers;

import app.adapter.rest.mapper.OrderRestMapper;
import app.adapter.rest.request.OrderRequest;
import app.adapter.rest.response.OrderResponse;
import app.application.usecase.OrderUseCase;
import app.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderUseCase orderUseCase;

    @Autowired
    private OrderRestMapper orderRestMapper;

    /**
     * Crear una nueva orden médica
     * Roles permitidos: MEDIC, NURSE
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - CREATE ORDER");
            System.out.println("📦 Request received - Patient ID: " + request.getPatientId() 
                             + ", Medic ID: " + request.getMedicId());

            Order order = orderRestMapper.toDomain(request);
            Order createdOrder = orderUseCase.createOrder(order);

            System.out.println("✅ SUCCESS - Order created with ID: " + createdOrder.getId());
            return new ResponseEntity<>(orderRestMapper.toResponse(createdOrder), HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtener todas las órdenes
     * Roles permitidos: MEDIC, NURSE, ADMINISTRATIVE, HUMAN_RESOURCES
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE', 'ADMINISTRATIVE', 'HUMAN_RESOURCES')")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET ALL ORDERS");

            List<Order> orders = orderUseCase.listAllOrders();
            List<OrderResponse> response = orders.stream()
                    .map(orderRestMapper::toResponse)
                    .collect(Collectors.toList());

            System.out.println("✅ SUCCESS - Retrieved " + orders.size() + " orders");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener una orden por ID
     * Roles permitidos: MEDIC, NURSE
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET ORDER BY ID: " + id);

            Order order = orderUseCase.getOrderById(id);
            OrderResponse response = orderRestMapper.toResponse(order);

            System.out.println("✅ SUCCESS - Order found: " + id);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtener órdenes por paciente
     * Roles permitidos: MEDIC, NURSE
     */
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<List<OrderResponse>> getOrdersByPatient(@PathVariable Long patientId) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET ORDERS BY PATIENT ID: " + patientId);

            List<Order> orders = orderUseCase.listOrdersByPatient(patientId);
            List<OrderResponse> response = orders.stream()
                    .map(orderRestMapper::toResponse)
                    .collect(Collectors.toList());

            System.out.println("✅ SUCCESS - Retrieved " + orders.size() + " orders for patient " + patientId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtener órdenes por médico
     * Roles permitidos: MEDIC, HUMAN_RESOURCES
     */
    @GetMapping("/medic/{medicId}")
    @PreAuthorize("hasAnyRole('MEDIC', 'HUMAN_RESOURCES')")
    public ResponseEntity<List<OrderResponse>> getOrdersByMedic(@PathVariable Long medicId) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - GET ORDERS BY MEDIC ID: " + medicId);

            List<Order> orders = orderUseCase.listOrdersByMedic(medicId);
            List<OrderResponse> response = orders.stream()
                    .map(orderRestMapper::toResponse)
                    .collect(Collectors.toList());

            System.out.println("✅ SUCCESS - Retrieved " + orders.size() + " orders for medic " + medicId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualizar una orden existente
     * Roles permitidos: MEDIC, NURSE
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDIC', 'NURSE')")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Long id, 
            @RequestBody OrderRequest request) {
        try {
            System.out.println("🎯 ENTRY POINT HIT - UPDATE ORDER ID: " + id);

            Order order = orderRestMapper.toDomain(request);
            order.setId(id);
            Order updatedOrder = orderUseCase.updateOrder(order);

            System.out.println("✅ SUCCESS - Order updated: " + id);
            return ResponseEntity.ok(orderRestMapper.toResponse(updatedOrder));

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
