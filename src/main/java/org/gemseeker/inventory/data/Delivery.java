package org.gemseeker.inventory.data;

import java.time.LocalDate;

/**
 * Delivery refers to a delivery transaction to a customer. This is where the
 * sales are generated.
 */
public class Delivery extends DataEntry {

    private String id;
    private LocalDate date;         // date delivered
    private int warehouseId;        // warehouse stocks came from
    private String customer;        // name of customer
    private double total;           // total sales

    public Delivery() {
        super("deliveries", "id");
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (id, date, warehouse_id, customer, total) VALUES ('%s', '%s', '%d', '%s', '%f')",
                tableName, id, date, warehouseId, customer, total);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET date='%s', warehouse_id='%d', customer='%s', total='%f' WHERE id='%s'",
                tableName, date, warehouseId, customer, total, id);
    }
}
