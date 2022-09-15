package be.kdg.artillery.view.menu.loadTerrain;

import be.kdg.artillery.view.menu.mainMenu.MainMenuPresenter;
import be.kdg.artillery.view.menu.mainMenu.MainMenuView;
import be.kdg.artillery.view.menu.newGame.NewGamePresenter;
import be.kdg.artillery.view.menu.newGame.NewGameView;
import be.kdg.artillery.view.menu.settings.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class LoadTerrainPresenter {
    LoadTerrainView view;

    public LoadTerrainPresenter(LoadTerrainView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        //terug naar main menu
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);
                view.getScene().setRoot(mainMenuView);
            }
        });

        view.getBack().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });

        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(newGameView);
                view.getScene().setRoot(newGameView);
                Settings.welkterrain = 10;
            }
        });


        //Laad de voorafingeladen terrains in
        view.getTgm0().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(newGameView);
                view.getScene().setRoot(newGameView);
                Settings.welkterrain = 0;
            }
        });
        view.getTgm1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(newGameView);
                view.getScene().setRoot(newGameView);
                Settings.welkterrain = 1;
            }
        });
        view.getTgm2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(newGameView);
                view.getScene().setRoot(newGameView);
                Settings.welkterrain = 2;
            }
        });
        view.getTgm3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(newGameView);
                view.getScene().setRoot(newGameView);
                Settings.welkterrain = 3;
            }
        });

        //Verander de cursor van de muis
        view.getBack().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });

        view.getTgm0().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getTgm0().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getTgm1().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getTgm1().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getTgm2().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getTgm2().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getTgm3().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getTgm3().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });

    }
}
