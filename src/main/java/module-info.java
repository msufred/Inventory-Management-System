module org.gemseeker.inventory_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;
    requires java.sql;
    requires reactive.streams;
    requires io.reactivex.rxjava2;
    requires rxjavafx;

    opens org.gemseeker.inventory to javafx.fxml;
    exports org.gemseeker.inventory;
}