package org.gemseeker.inventory.data;

import java.time.LocalDate;

public class Product extends DataEntry {

    private int id;
    private String sku;             // SKU or barcode
    private String name;            // product name
    private String description;     // optional product description
    private String unit;            // kilo(s), grams etc
    private double unitPrice;       // price per unit
    private double retailPrice;     // retail price
    private String supplier;        // name of supplier
    private LocalDate datePurchased; // date purchased

    public Product() {
        super("products", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (sku, name, description, unit, unit_price, retail_price, " +
                "supplier, date_purchased) VALUES (" +
                "'%s', '%s', '%s', '%s', '%f', '%f', '%s', '%s'",
                tableName, sku, name, description, unit, unitPrice, retailPrice, supplier, datePurchased);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET " +
                "sku='%s', name='%s', description='%s', unit='%s', unit_price='%f', retail_price='%s', " +
                "supplier='%s', date_purchased='%s' " +
                "WHERE id='%d'", tableName, sku, name, description, unit, unitPrice, retailPrice,
                supplier, datePurchased, id);
    }
}
