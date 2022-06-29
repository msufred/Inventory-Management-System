package org.gemseeker.inventory.data;

public class Warehouse extends DataEntry {

    private int id;
    private String name;
    private String address; // optional

    public Warehouse() {
        super("warehouses", "id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String insertSQL() {
        return String.format("INSERT INTO %s (name, address) VALUES ('%s', '%s')",
                tableName, name, address);
    }

    @Override
    public String updateSQL() {
        return String.format("UPDATE %s SET name='%s', address='%s' WHERE id='%d'",
                tableName, name, address, id);
    }
}
