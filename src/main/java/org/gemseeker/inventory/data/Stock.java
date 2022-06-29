package org.gemseeker.inventory.data;

/**
 * Stock refers to the product item on specific warehouse.
 * It holds the products total quantity, current in-stock, and
 * number of stock sold.
 */
public class Stock extends DataEntry {

    private int id;
    private int productId;
    private int warehouseId;
    private int quantityTotal;      // total quantity (upon buying from supplier)
    private int inStock;            // remaining quantity
    private int quantityOut;        // quantity sold

    public Stock() {
        super("stocks", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(int quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getQuantityOut() {
        return quantityOut;
    }

    public void setQuantityOut(int quantityOut) {
        this.quantityOut = quantityOut;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (product_id, warehouse_id, quantity_total, in_stock, quantity_out) VALUES " +
                "('%d', '%d', '%d', '%d', '%d')", tableName, productId, warehouseId, quantityTotal, inStock, quantityOut);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET product_id='%d', warehouse_id='%d', quantity_total='%d', in_stock='%d', " +
                "quantity_out='%d' WHERE id='%d'", tableName, productId, warehouseId, quantityTotal, inStock, quantityOut,
                id);
    }
}
