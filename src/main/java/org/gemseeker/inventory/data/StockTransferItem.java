package org.gemseeker.inventory.data;

import java.time.LocalDate;

public class StockTransferItem extends DataEntry {

    private int id;             // id of this item
    private int transferId;     // stock_transfer id
    private int stockId;        // stock id
    private LocalDate date;     // date of transfer

    public StockTransferItem() {
        super("stock_transfer_items", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (transfer_id, stock_id, date) VALUES ('%d', '%d', '%s')",
                tableName, transferId, stockId, date);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET transfer_id='%d', stock_id='%d', date='%s' WHERE id='%d'",
                tableName, transferId, stockId, date, id);
    }
}
