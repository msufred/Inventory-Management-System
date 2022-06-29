package org.gemseeker.inventory.data;

public abstract class DataEntry {

    protected String tableName;
    protected String keyColumn;

    public DataEntry(String tableName, String keyColumn) {
        this.tableName = tableName;
        this.keyColumn = keyColumn;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public abstract String insertSQL();
    public abstract String updateSQL();

}
