module com.chathumal.smapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.chathumal.smapp to javafx.fxml;
    exports com.chathumal.smapp;
}
