package app.adapter.rest.request;

public class OrderItemRequest {
    private String itemType; // "medicamento", "procedimiento", "ayuda"
    private String itemName;

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
}
