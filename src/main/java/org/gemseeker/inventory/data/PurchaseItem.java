package org.gemseeker.inventory.data;

import java.time.LocalDate;

/**
 * PurchaseItem refers to the item of a purchase transaction. The date of
 * purchase and this item must be the same.
 */
public class PurchaseItem extends DataEntry {

    private int id;
    private String purchaseId;      // purchase id
    private int productId;          // product id
    private LocalDate date;         // date purchased

    public PurchaseItem() {
        super("purchase_items", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (purchase_id, product_id, date) VALUES ('%s', '%d', '%s')",
                tableName, purchaseId, productId, date);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET purchase_id='%s', product_id='%d', date='%s' WHERE id='%d'",
                tableName, purchaseId, productId, date, id);
    }
}
