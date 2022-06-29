package org.gemseeker.inventory.data;

import java.time.LocalDate;

/**
 * StockTransfer refers to a transfer of stocks from one warehouse
 * to another. It holds the data of when the transfer occurred,
 * warehouse it came from, and warehouse stocks are being transferred.
 */
public class StockTransfer extends DataEntry {

    private int id;
    private LocalDate date;
    private int warehouseFrom;
    private int warehouseTo;

    public StockTransfer() {
        super("stock_transfers", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getWarehouseFrom() {
        return warehouseFrom;
    }

    public void setWarehouseFrom(int warehouseFrom) {
        this.warehouseFrom = warehouseFrom;
    }

    public int getWarehouseTo() {
        return warehouseTo;
    }

    public void setWarehouseTo(int warehouseTo) {
        this.warehouseTo = warehouseTo;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (date, warehouse_from, warehouse_to) VALUES ('%s', '%d', '%d')",
                tableName, date, warehouseFrom, warehouseTo);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET date='%s', warehouse_from='%d', warehouse_to='%d' WHERE id='%d'",
                tableName, date, warehouseFrom, warehouseTo, id);
    }
}
