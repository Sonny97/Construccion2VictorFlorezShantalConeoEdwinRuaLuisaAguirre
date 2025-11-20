package app.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "item_number"}))
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(nullable = false)
    private Integer itemNumber; // Comienza en 1, único por orden

    @Column(nullable = false, length = 50)
    private String itemType; // "medicamento", "procedimiento", "ayuda"

    @Column(nullable = false, length = 200)
    private String itemName; // Nombre del medicamento, procedimiento, ayuda

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }

    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
}
