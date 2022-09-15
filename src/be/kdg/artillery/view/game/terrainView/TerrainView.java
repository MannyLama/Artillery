package be.kdg.artillery.view.game.terrainView;

import be.kdg.artillery.Main;
import be.kdg.artillery.model.player.Barrel;
import be.kdg.artillery.model.player.Player;
import be.kdg.artillery.model.player.Tank;
import be.kdg.artillery.model.terrainModel.TerrainGenerationModel;
import be.kdg.artillery.view.game.topBar.TopBarView;
import be.kdg.artillery.view.menu.settings.Settings;
import be.kdg.artillery.model.saveLoad.LoadTerrain;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Deze view brengt alle elementen in beeld in het spel.
 * De bovenste menu balk, de info balk eronder en het terrein in een Canvas worden hier gemaakt.
 * Hier wordt ook de y-positie van de tank berekend afhankelijk van het terrein.
 * Ook wordt er d.m.v. het TerrainGenerationModel het terrein gemaakt.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */

public class TerrainView extends BorderPane {
    private TopBarView topBar;
    private Group root;
    private GraphicsContext gc;
    private TerrainGenerationModel tgm;
    private Canvas canvas;
    private Image sun;

    private Rectangle movingBarrelPlayer1;
    private Rectangle movingBarrelPlayer2;
    private ImageView tankPlayer1;
    private ImageView tankPlayer2;

    private Circle movingBullet;
    private boolean bulletisRemoved;

    private MenuBar menuBar;
    private Menu menu;
    private MenuItem backItem = new MenuItem("Back");
    private MenuItem saveItem = new MenuItem("Save Terrain");
    private MenuItem loadItem = new MenuItem("Load Terrain");
    private MenuItem reGenerateTerrain = new MenuItem("Re-generate Terrain");
    private MenuItem quitItem = new MenuItem("Quit game");

    private int zetAan = 0;

    private double richtingGraad;

    private boolean barrelLeft = false;
    private boolean barrelRight = false;

    private boolean sunLoaded = false;
    private boolean bgLoaded = false;
    private boolean magVuren;

    private String musicFile;
    private Media sound;
    private MediaPlayer mediaPlayer;
    private Polyline traject;


    public TerrainView() throws IOException {
        this.initialiseNodes();
        this.layoutNodes();
        this.animate();
        TerrainPresenter terrainPresenter = new TerrainPresenter(this);
    }

    private void initialiseNodes() throws IOException {

        topBar = new TopBarView();
        root = new Group();
        canvas = new Canvas(Main.WIDTH, Main.HEIGHT);

        //menubar bovenaan het scherm
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        menu.getItems().addAll(backItem, saveItem, loadItem, reGenerateTerrain, quitItem);
        menuBar.getMenus().addAll(menu);

        gc = canvas.getGraphicsContext2D();
        tgm = new TerrainGenerationModel();


        switch (Settings.welkterrain) {
            case 0:
                tgm.setPunten(LoadTerrain.loadTerrain(0));
                break;
            case 1:
                tgm.setPunten(LoadTerrain.loadTerrain(1));
                break;
            case 2:
                tgm.setPunten(LoadTerrain.loadTerrain(2));
                break;
            case 3:
                tgm.setPunten(LoadTerrain.loadTerrain(3));
                break;
            case 10:
                tgm.setPunten(LoadTerrain.loadTerrain(10));
                break;
        }

        if (Settings.smoothTerrain.isSelected()) {
            TerrainGenerationModel.smoothnerMaker(tgm);
            System.out.println("smoothner enabled");
        }

        tankPlayer1 = Player.player1.getTank().getTankImage();
        tankPlayer2 = Player.player2.getTank().getTankImage();

        //Grootte van de kogel - 2.3 beste waarde
        movingBullet = new Circle(2.3);
        magVuren = false;
        bulletisRemoved = true;
        //zetten we op -180 om te kunnen kijken of zijn waarde veranderd is, zoniet een exeption kunnen sturen.
        richtingGraad = -180;


        //Tank coordinaten berkenen op basis van terrain generation
        Player.player1.getTank().getPosition().setY(tgm.getPunten()[(int) Player.player1.getTank().getPosition().getX()].getY());
        Player.player1.getTank().getPosition().setX(Tank.startxPosPlayer1);
        Player.player1.getTank().setBarrelPos();

        Player.player2.getTank().getPosition().setY(tgm.getPunten()[(int) Player.player2.getTank().getPosition().getX()].getY());
        Player.player2.getTank().getPosition().setX(Tank.startxPosPlayer2);
        Player.player2.getTank().setBarrelPos();

        movingBarrelPlayer1 = new Rectangle(Player.player1.getTank().getBarrelTank().getX(),
                Player.player1.getTank().getBarrelTank().getY() + 113,
                Player.player1.getTank().getBarrelTank().getWidth(),
                Player.player1.getTank().getBarrelTank().getHeight());
        movingBarrelPlayer2 = new Rectangle(Player.player2.getTank().getBarrelTank().getX(),
                Player.player2.getTank().getBarrelTank().getY() + 113,
                Player.player2.getTank().getBarrelTank().getWidth(),
                Player.player2.getTank().getBarrelTank().getHeight());
        movingBarrelPlayer1.setFill(Color.BLACK);
        movingBarrelPlayer2.setFill(Color.BLACK);


        //Tank initialiseren
        tankPlayer1.setX(Player.player1.getTank().getPosition().getX() - 15);
        tankPlayer1.setY(Player.player1.getTank().getPosition().getY() - 16 + 113);
        tankPlayer2.setX(Player.player2.getTank().getPosition().getX() - 15);
        tankPlayer2.setY(Player.player2.getTank().getPosition().getY() - 16 + 113);

        switch (Settings.bgChoice) {
            case 0:
                //Lucht1
                gc.setFill(new LinearGradient(0, 0, 0, 0.70, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.CORNFLOWERBLUE),
                        new Stop(1, Color.CADETBLUE)));
                gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT - (Main.HEIGHT / 9));
                break;
            case 1:
                //lucht2
                gc.setFill(new LinearGradient(0, 0, 0, 0.70, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.PALEVIOLETRED),
                        new Stop(1, Color.LIGHTBLUE)));
                gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT - (Main.HEIGHT / 9));
                break;
            case 2:
                //lucht3
                gc.setFill(new LinearGradient(0, 0, 0, 0.70, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.GREEN),
                        new Stop(1, Color.ORANGE)));
                gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT - (Main.HEIGHT / 9));
                break;
            case 3:
                //lucht4
                gc.setFill(new LinearGradient(0, 0, 0, 0.70, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.DODGERBLUE),
                        new Stop(1, Color.BEIGE)));
                gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT - (Main.HEIGHT / 9));
                break;
        }

        //zon op scherm plaatsen
        try (InputStream is = Files.newInputStream(Paths.get("res/images/sun.png"))) {
            sun = new Image(is);
            sunLoaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of the sun.");
        }

        //Background music
        musicFile = "res/sound/bgSound.mp3";
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(Settings.volume);
    }

    private void layoutNodes() {
        if (sunLoaded) {
            gc.drawImage(sun, 50, 50);
        }

        //Terrain maken
        gc.beginPath();
        gc.lineTo(0, Main.HEIGHT);
        for (int i = 0; i < TerrainGenerationModel.TERRAINBREEDTE; i++) {
            gc.lineTo(tgm.getPunten()[i].getX(), tgm.getPunten()[i].getY());
        }
        gc.lineTo(Main.WIDTH, Main.HEIGHT);
        gc.stroke();
        gc.setFill(Settings.terrainColor);
        gc.fill();
        gc.closePath();


        root.getChildren().add(canvas);
        setTop(menuBar);
        setCenter(topBar);
        setBottom(root);

        getChildren().add(movingBarrelPlayer1);
        getChildren().add(movingBarrelPlayer2);
        getChildren().add(tankPlayer1);
        getChildren().add(tankPlayer2);

    }

    private void animate() {
        mediaPlayer.play();

        AnimationTimer animationTimerBarrel = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //speler 0
                if (zetAan == 0) {
                    calculateZetAan();
                }
                //speler 1
                if (zetAan == 1) {
                    if (barrelLeft) {
                        Barrel.rotateBarrel(movingBarrelPlayer1, -3);
                    }
                    if (barrelRight) {
                        Barrel.rotateBarrel(movingBarrelPlayer1, 3);
                    }
                }
                //speler 2
                if (zetAan == 2) {
                    if (barrelLeft) {
                        Barrel.rotateBarrel(movingBarrelPlayer2, -3);
                    }
                    if (barrelRight) {
                        Barrel.rotateBarrel(movingBarrelPlayer2, 3);
                    }
                }
            }
        };
        animationTimerBarrel.start();
    }

    public void calculateZetAan() {
        if (zetAan == 0) {
            zetAan = 1;
            magVuren = true;
            topBar.updateTopBar(zetAan);
            getChildren().remove(getTraject());
        } else if (zetAan == 1) {
            zetAan = 2;
            magVuren = true;
            topBar.updateTopBar(zetAan);
            Player.player1.setMagSoundEffectInslag(true);
            getChildren().remove(getTraject());

        } else if (zetAan == 2) {
            zetAan = 1;
            magVuren = true;
            topBar.updateTopBar(zetAan);
            Player.player2.setMagSoundEffectInslag(true);
            getChildren().remove(getTraject());

        }
        topBar.defineWindspeed();
    }

    public Rectangle getMovingBarrelPlayer1() {
        return movingBarrelPlayer1;
    }

    public Rectangle getMovingBarrelPlayer2() {
        return movingBarrelPlayer2;
    }

    public int getZetAan() {
        return zetAan;
    }

    public void setBarrelLeft(boolean barrelLeft) {
        this.barrelLeft = barrelLeft;
    }

    public void setBarrelRight(boolean barrelRight) {
        this.barrelRight = barrelRight;
    }

    public TerrainGenerationModel getTgm() {
        return tgm;
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuItem getBackItem() {
        return backItem;
    }

    public MenuItem getSaveItem() {
        return saveItem;
    }

    public MenuItem getLoadItem() {
        return loadItem;
    }

    public MenuItem getQuitItem() {
        return quitItem;
    }

    public TopBarView getTopBar() {
        return topBar;
    }

    public Circle getMovingBullet() {
        return movingBullet;
    }

    public ImageView getTankPlayer1() {
        return tankPlayer1;
    }

    public ImageView getTankPlayer2() {
        return tankPlayer2;
    }

    public boolean isMagVuren() {
        return magVuren;
    }

    public void setMagVuren(boolean magVuren) {
        this.magVuren = magVuren;
    }

    public boolean isBulletisRemoved() {
        return bulletisRemoved;
    }

    public void setBulletisRemoved(boolean bulletisRemoved) {
        this.bulletisRemoved = bulletisRemoved;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public MenuItem getReGenerateTerrain() {
        return reGenerateTerrain;
    }

    public Polyline getTraject() {
        return traject;
    }

    public void setTraject(Polyline traject) {
        this.traject = traject;
    }
}