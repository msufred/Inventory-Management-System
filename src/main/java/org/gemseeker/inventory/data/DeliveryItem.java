package org.gemseeker.inventory.data;

import java.time.LocalDate;

/**
 * Refers to the items of a delivery transaction. It holds data of the
 * product delivered, date, quantity, discount (percentage), and total
 * (product's retail price minus the discount)
 */
public class DeliveryItem extends DataEntry {

    private int id;
    private int deliveryId;
    private LocalDate date;
    private int productId;
    private int quantity;
    private double discount;
    private double total;

    public DeliveryItem() {
        super("delivery_items", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (delivery_id, date, product_id, quantity, discount, total) VALUES " +
                "('%d', '%s', '%d', '%d', '%f', '%f')", tableName, deliveryId, date, productId, quantity, discount, total);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET delivery_id='%d', date='%s', product_id='%d', quantity='%d', " +
                "discount='%f', total='%f' WHERE id='%d'",
                tableName, deliveryId, date, productId, quantity, discount, total);
    }
}
