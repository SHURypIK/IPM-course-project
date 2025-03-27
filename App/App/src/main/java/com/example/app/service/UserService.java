package com.example.app.service;

import com.example.app.SceneManager;
import com.example.app.model.Admin;
import com.example.app.model.User;
import com.example.app.repositories.AdminRepo;
import com.example.app.repositories.UserRepo;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class UserService {

    public static void fill(GridPane pane){

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Логин"), 0, 0);
        pane.add(new Label("Удалить"), 1, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            users = UserRepo.get();
            admins = AdminRepo.get();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");

            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(event -> alert.close());
            pause.play();
        }
        users = User.removeAdmins(users,admins);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            int rowIndex = i + 1;

            Label label = new Label(user.getLogin());
            pane.add(label, 0, rowIndex);


            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(user);
                    updatePage();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(ex.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");

                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(event -> alert.close());
                    pause.play();
                }
            });
            pane.add(deleteButton, 1, rowIndex);

        }
    }

    public static void delete(User user) throws Exception {
        UserRepo.delete(user.getLogin());
    }

    public static void save(User user) throws Exception {
        UserRepo.add(user);
        updatePage();
    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("user.fxml");
        Stage stage = SceneManager.getPrimaryStage();
        stage.setMaximized(false);
        stage.setMaximized(true);
    }
}
