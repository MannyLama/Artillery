package be.kdg.artillery.view.game.terrainView;

import be.kdg.artillery.Main;
import be.kdg.artillery.model.player.Player;
import be.kdg.artillery.model.projectile.Projectile;
import be.kdg.artillery.view.menu.loadTerrain.LoadTerrainPresenter;
import be.kdg.artillery.view.menu.loadTerrain.LoadTerrainView;
import be.kdg.artillery.view.menu.newGame.NewGamePresenter;
import be.kdg.artillery.view.menu.newGame.NewGameView;
import be.kdg.artillery.model.saveLoad.SaveTerrain;
import be.kdg.artillery.view.menu.settings.Settings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TerrainPresenter {
    TerrainView view;

    private String musicFile;
    private Media sound;
    private MediaPlayer mediaPlayer;

    private String musicFile2;
    private Media sound2;
    private MediaPlayer mediaPlayer2;

    public TerrainPresenter(TerrainView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        //menu items menu bar bovenaan
        view.getBackItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getMediaPlayer().stop();
                NewGameView newGameView = new NewGameView();
                NewGamePresenter mainMenuPresenter = new NewGamePresenter(newGameView);
                view.getScene().setRoot(newGameView);
                Settings.welkterrain = -1;
            }
        });

        view.getSaveItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    SaveTerrain.saveTerrain(view);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        view.getLoadItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getMediaPlayer().stop();
                LoadTerrainView loadTerrainView = new LoadTerrainView();
                LoadTerrainPresenter loadTerrainPresenter = new LoadTerrainPresenter(loadTerrainView);
                view.getScene().setRoot(loadTerrainView);
            }
        });

        view.getReGenerateTerrain().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Settings.welkterrain = -1;
                try {
                    TerrainView terrainView = new TerrainView();
                    TerrainPresenter presenter = new TerrainPresenter(terrainView);
                    view.getScene().setRoot(terrainView);
                }catch (IOException io){
                    System.out.println(io.toString());
                }
            }
        });

        view.getQuitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        /* ********************************************************************** */
        //Tank Controls KEY PRESSED
        Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        view.setBarrelRight(true);
                        break;
                    case DOWN:
                        view.setBarrelLeft(true);
                        break;
                    case ENTER:
                        if (view.isMagVuren()) {
                            Projectile.Fire(view, view.getMovingBullet());
                            musicFile = "res/sound/bam.mp3";
                            sound = new Media(new File(musicFile).toURI().toString());
                            mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.setVolume(Settings.volume);
                            mediaPlayer.play();
                            view.setMagVuren(false);
                        }
                        break;
                }
            }
        });

        //Tank Controls KEY RELEASED
        Main.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        view.setBarrelRight(false);
                        break;
                    case DOWN:
                        view.setBarrelLeft(false);
                        break;
                    case ENTER:
                        break;
                }
            }
        });

        //On window close event
        Main.scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Are you sure you want to quit this AWESOME game?");
                alert.setTitle("Warning!");
                alert.getButtonTypes().clear();
                ButtonType no = new ButtonType("No!");
                ButtonType yes = new ButtonType("Yes...");
                alert.getButtonTypes().addAll(no, yes);
                alert.showAndWait();
                if (alert.getResult() == null || alert.getResult().equals(no)) {
                    event.consume();
                }
            }
        });

        //Checkt of het projectiel de tank raakt.
        view.getMovingBullet().boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                if (view.getZetAan() == 1) {
                    if (view.getTankPlayer2().intersects(newValue)) {
                        if(Player.player1.isMagSoundEffectInslag()){
                            musicFile2 = "res/sound/tank_bam.mp3";
                            sound2 = new Media(new File(musicFile2).toURI().toString());
                            mediaPlayer2 = new MediaPlayer(sound2);
                            mediaPlayer2.setVolume(Settings.volume);
                            mediaPlayer2.play();
                            Player.player1.setMagSoundEffectInslag(false);
                        }

                        Player.player2.getTank().setHealth(Player.player2.getTank().getHealth() - 10);
                        view.getTopBar().updateTopBar(view.getZetAan());
                        view.getChildren().remove(view.getMovingBullet());
                        view.setBulletisRemoved(true);
                    }
                }
                if (view.getZetAan() == 2) {
                    if (view.getTankPlayer1().intersects(newValue)) {
                        if(Player.player2.isMagSoundEffectInslag()){
                            musicFile2 = "res/sound/tank_bam.mp3";
                            sound2 = new Media(new File(musicFile2).toURI().toString());
                            mediaPlayer2 = new MediaPlayer(sound2);
                            mediaPlayer2.setVolume(Settings.volume);
                            mediaPlayer2.play();
                            Player.player2.setMagSoundEffectInslag(false);
                        }

                        Player.player1.getTank().setHealth(Player.player1.getTank().getHealth() - 10);
                        view.getTopBar().updateTopBar(view.getZetAan());
                        view.getChildren().remove(view.getMovingBullet());
                        view.setBulletisRemoved(true);
                    }
                }
            }
        });

    }
}
