package app.adapter.rest.response;

public class OrderItemResponse {
    private String itemType;
    private String itemName;
    private Integer itemNumber;

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
}
