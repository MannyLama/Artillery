package be.kdg.artillery.view.game.topBar;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TopBarPresenter {
    TopBarView view;

    public TopBarPresenter(TopBarView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getStorm().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.defineWindspeed();
            }
        });
    }
}
