package be.kdg.artillery;

import be.kdg.artillery.view.menu.mainMenu.MainMenuPresenter;
import be.kdg.artillery.view.menu.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainMenuView menuView = new MainMenuView();
        new MainMenuPresenter(menuView);

        scene = new Scene(menuView);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("stylesheets/stylesheet.css");

        try (InputStream is = Files.newInputStream(Paths.get("res/images/icon.jpg"))){
            primaryStage.getIcons().add(new Image(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setTitle("Artillery Game");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
