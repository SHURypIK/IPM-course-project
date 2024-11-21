module com.example.app {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.commons.codec;
    requires com.google.gson;


    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.controllers;
    exports com.example.app.model;
    opens com.example.app.controllers to javafx.fxml;
    opens com.example.app.model to com.google.gson;
    opens com.example.app.model.Enums to com.google.gson;
}