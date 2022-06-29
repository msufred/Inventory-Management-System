package org.gemseeker.inventory.data;

import java.time.LocalDate;

/**
 * Purchase refers to a single purchase transaction with a supplier.
 */
public class Purchase extends DataEntry {

    private String id;
    private LocalDate date;     // date of purchase
    private int warehouseId;    // warehouse where purchased products will be stored
    private String supplier;    // supplier name
    private double total;       // total amount of purchased products

    public Purchase() {
        super("purchases", "id");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (id, date, warehouse_id, supplier, total) VALUES ('%s', '%d', '%s', '%s', '%f')",
                tableName, id, warehouseId, date, supplier, total);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET date='%s', warehouse_id='%d', supplier='%s', total='%f' WHERE id='%s'",
                tableName, date, warehouseId, supplier, total, id);
    }
}
