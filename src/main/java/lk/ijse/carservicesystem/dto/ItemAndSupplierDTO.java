package lk.ijse.carservicesystem.dto;

public class ItemAndSupplierDTO {
    private String itemId;
    private String supplierId;

    public ItemAndSupplierDTO() {
    }

    public ItemAndSupplierDTO(String itemId, String supplierId) {
        this.itemId = itemId;
        this.supplierId = supplierId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ItemAndSupplier{" +
                "itemId='" + itemId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }
}
