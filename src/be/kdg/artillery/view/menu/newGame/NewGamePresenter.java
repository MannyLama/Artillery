package be.kdg.artillery.view.menu.newGame;

import be.kdg.artillery.model.player.Tank;
import be.kdg.artillery.view.game.terrainView.TerrainView;
import be.kdg.artillery.view.menu.mainMenu.MainMenuView;
import be.kdg.artillery.view.menu.mainMenu.MainMenuPresenter;

import be.kdg.artillery.view.menu.settings.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewGamePresenter{
    NewGameView view;

    public NewGamePresenter(NewGameView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers(){
        //terug naar main menu
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView);
                view.getScene().setRoot(mainMenuView);
                Settings.welkterrain = -1;
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


        //wanneer er op start wordt gedrukt
        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                //spelers tank(kleur) toewijzen
                //speler 1
                if (view.getColorP1().getSelectionModel().getSelectedItem().equals("Red")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.REDTANK))){
                        view.getPlayer1().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP1().getSelectionModel().getSelectedItem().equals("Green")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.GREENTANK))){
                        view.getPlayer1().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP1().getSelectionModel().getSelectedItem().equals("Blue")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.BLUETANK))){
                        view.getPlayer1().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP1().getSelectionModel().getSelectedItem().equals("Yellow")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.YELLOWTANK))){
                        view.getPlayer1().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP1().getSelectionModel().getSelectedItem().equals("Pink")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.PINKTANK))){
                        view.getPlayer1().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //speler2
                if (view.getColorP2().getSelectionModel().getSelectedItem().equals("Red")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.REDTANK))){
                        view.getPlayer2().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP2().getSelectionModel().getSelectedItem().equals("Green")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.GREENTANK))){
                        view.getPlayer2().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP2().getSelectionModel().getSelectedItem().equals("Blue")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.BLUETANK))){
                        view.getPlayer2().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP2().getSelectionModel().getSelectedItem().equals("Yellow")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.YELLOWTANK))){
                        view.getPlayer2().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (view.getColorP2().getSelectionModel().getSelectedItem().equals("Pink")){
                    try (InputStream is = Files.newInputStream(Paths.get(Tank.PINKTANK))){
                        view.getPlayer2().getTank().setTankImage(new ImageView(new Image(is)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //spelers opslaan
                if (view.getTfPlayer1().getText() == null || view.getTfPlayer1().getText().isEmpty()) {
                    view.getPlayer1().setName("Player 1");
                } else {
                    view.getPlayer1().setName(view.getTfPlayer1().getText());
                }

                if (view.getTfPlayer2().getText() == null || view.getTfPlayer2().getText().isEmpty()) {
                    view.getPlayer2().setName("Player 2");
                } else {
                    view.getPlayer2().setName(view.getTfPlayer2().getText());
                }

                //terrein inladen
                try {
                    TerrainView terrainView = new TerrainView();
                    view.getScene().setRoot(terrainView);
                }catch (IOException io){
                    System.out.println(io.toString());
                }

                System.out.println(view.getPlayer1());
                System.out.println(view.getPlayer2());


            }
        });
    }
}
