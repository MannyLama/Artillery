package be.kdg.artillery.view.menu.help;

import be.kdg.artillery.view.menu.mainMenu.MainMenuPresenter;
import be.kdg.artillery.view.menu.mainMenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class HelpPresenter {
    HelpView view;

    public HelpPresenter(HelpView helpview) {
        this.view = helpview;
        addEventHandlers();
    }

    private void addEventHandlers() {
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

        view.getBack().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.setCursor(Cursor.DEFAULT);
            }
        });
    }

}
