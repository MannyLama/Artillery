package be.kdg.artillery.view.menu.settings;

import be.kdg.artillery.view.menu.mainMenu.MainMenuPresenter;
import be.kdg.artillery.view.menu.mainMenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SettingsPresenter {
    SettingsView view;

    public SettingsPresenter(SettingsView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        //terug naar main menu
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Settings.bgChoice = (int) view.getBgSlider().getValue();
                Settings.volume = view.getFxSlider().getValue();
               // TerrainPresenter.volume = view.getFxSlider().getValue();

                if (view.getColorTgm().getSelectionModel().getSelectedItem().equals("RosyBrown")) {
                    Settings.terrainColor = Color.rgb(180, 130, 90);
                }
                if (view.getColorTgm().getSelectionModel().getSelectedItem().equals("Black")) {
                    Settings.terrainColor = Color.BLACK;
                }
                if (view.getColorTgm().getSelectionModel().getSelectedItem().equals("White")) {
                    Settings.terrainColor = Color.WHITE;
                }
                if (view.getColorTgm().getSelectionModel().getSelectedItem().equals("Green")) {
                    Settings.terrainColor = Color.GREEN;
                }
                if (view.getColorTgm().getSelectionModel().getSelectedItem().equals("Pink")) {
                    Settings.terrainColor = Color.PINK;
                }

                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);
                view.getScene().setRoot(mainMenuView);
            }
        });

        view.getBg0().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getBgSlider().setValue(0);
            }
        });
        view.getBg1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getBgSlider().setValue(1);
            }
        });
        view.getBg2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getBgSlider().setValue(2);
            }
        });
        view.getBg3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getBgSlider().setValue(3);
            }
        });


        view.getBack().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getBack().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getBg0().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getBg0().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getBg1().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getBg1().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getBg2().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getBg2().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
        view.getBg3().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.HAND);
            }
        });
        view.getBg3().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
    }
}
