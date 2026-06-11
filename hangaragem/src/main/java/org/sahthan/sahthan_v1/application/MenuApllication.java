package org.sahthan.sahthan_v1.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sahthan.sahthan_v1.Launcher;

import java.io.IOException;
import java.net.URL;

public class MenuApllication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        URL fxmlLocation = Launcher.class.getResource(
                "/org/sahthan/sahthan_v1/Menu/Menu.fxml");

        if (fxmlLocation == null) {
            throw new IllegalStateException(
                    "Arquivo Menu.fxml não foi encontrado!");
        }

        FXMLLoader fxmlLoader =
                new FXMLLoader(fxmlLocation);

        Scene scene =
                new Scene(fxmlLoader.load());

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}