package be.kdg.artillery.view.menu.mainMenu;

import be.kdg.artillery.view.menu.help.HelpPresenter;
import be.kdg.artillery.view.menu.help.HelpView;
import be.kdg.artillery.view.menu.loadTerrain.LoadTerrainPresenter;
import be.kdg.artillery.view.menu.loadTerrain.LoadTerrainView;
import be.kdg.artillery.view.menu.newGame.NewGamePresenter;
import be.kdg.artillery.view.menu.newGame.NewGameView;
import be.kdg.artillery.view.menu.settings.SettingsPresenter;
import be.kdg.artillery.view.menu.settings.SettingsView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainMenuPresenter {
    private MainMenuView menuView;

    public MainMenuPresenter(MainMenuView menuview) {
        this.menuView = menuview;
        addEventHandlers();
    }

    private void addEventHandlers() {
        // MENUITEMS MOUSE EVENTS
        for (int i = 0; i < menuView.getMenuItems().length; i++) {
           menuView.getMenuItems()[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   ((MainMenuView.MenuItem)event.getSource()).getMenuItemBg().setFill(((MainMenuView.MenuItem) event.getSource()).getGradient());
                   ((MainMenuView.MenuItem)event.getSource()).getMenuItemText().setFill(Color.WHITE);
                   menuView.setCursor(Cursor.HAND);
               }
           });

            menuView.getMenuItems()[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainMenuView.MenuItem)event.getSource()).getMenuItemBg().setFill(Color.BLACK);
                    ((MainMenuView.MenuItem)event.getSource()).getMenuItemText().setFill(Color.DARKGRAY);
                    menuView.setCursor(Cursor.DEFAULT);
                }
            });

            menuView.getMenuItems()[i].setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainMenuView.MenuItem)event.getSource()).getMenuItemBg().setFill(Color.NAVY);
                }
            });

            menuView.getMenuItems()[i].setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((MainMenuView.MenuItem)event.getSource()).getMenuItemBg().setFill(((MainMenuView.MenuItem) event.getSource()).getGradient());
                }
            });

            //NEW GAME WINDOW
            menuView.getNewGame().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    NewGameView newGameView = new NewGameView();
                    NewGamePresenter newGamePresenter = new NewGamePresenter(newGameView);
                    menuView.getScene().setRoot(newGameView);
                }
            });


            //LOAD TERRAIN WINDOW
            menuView.getLoadTerrain().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    LoadTerrainView loadTerrainView = new LoadTerrainView();
                    LoadTerrainPresenter loadTerrainPresenter = new LoadTerrainPresenter(loadTerrainView);

                    menuView.getScene().setRoot(loadTerrainView);
                }
            });

            //SOUND SETTINGS WINDOW
            menuView.getSettings().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    SettingsView soundSettingsView = new SettingsView();
                    SettingsPresenter settingsPresenter = new SettingsPresenter(soundSettingsView);

                    menuView.getScene().setRoot(soundSettingsView);
                }
            });

            //EXIT GAME VANUIT MAIN MENU
            menuView.getExitButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.exit(0);
                }
            });


            //HELP WINDOW
            menuView.getHelp().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    HelpView helpview = null;
                    try {
                        helpview = new HelpView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    HelpPresenter helppresenter = new HelpPresenter(helpview);
                    menuView.getScene().setRoot(helpview);
                }
            });
        }
    }
}
