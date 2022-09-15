package be.kdg.artillery.view.menu.settings;

import be.kdg.artillery.Main;
import be.kdg.artillery.view.menu.Title;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingsView extends Parent {
    public final Title SETTINGSTITLE = new Title("S E T T I N G S", 350, 70);
    private ImageView img;
    private boolean imageLoaded;
    private Text fxSound;
    private Button back;
    private Slider fxSlider;
    private Text bgColor;
    private Slider bgSlider;
    private ImageView bg0;
    private ImageView bg1;
    private ImageView bg2;
    private ImageView bg3;
    private boolean bg0Loaded;
    private boolean bg1Loaded;
    private boolean bg2Loaded;
    private boolean bg3Loaded;

    private Text colorTerrain;
    private ComboBox<String> colorTgm;
    private ObservableList<String> colorTgmList;


    public SettingsView() {
        initialisenodes();
        layoutnodes();
    }

    private void initialisenodes() {
        imageLoaded = false;
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bgMenu.jpg"))) {
            img = new ImageView(new Image(is));
            imageLoaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image.");
        }

        fxSound = new Text("Volume");
        fxSound.setFont(Font.font("Arial", 30));
        fxSound.setFill(Color.WHITE);

        //EFFECTS SLIDER
        fxSlider = new Slider();
        fxSlider.setPrefSize(300, 50);
        fxSlider.setMin(0);
        fxSlider.setMax(1);
        fxSlider.setValue(0.5);
        fxSlider.setShowTickLabels(true);
        fxSlider.setShowTickMarks(true);
        fxSlider.setMajorTickUnit(0.5);

        //CHECKBOX VOOR SMOOTHER TERRAIN
        Settings.smoothTerrain.setText("Smoother terrain?");
        Settings.smoothTerrain.setFont(Font.font("Arial", 15));
        Settings.smoothTerrain.setTextFill(Color.WHITE);

        //BACKGROUND SELECTOR
        bgColor = new Text();
        bgColor.setText("Choose  your  favorite  background  color:");
        bgColor.setFont(Font.font("Arial", 25));
        bgColor.setFill(Color.WHITE);

        //BACKGROUNDSLIDER
        bgSlider = new Slider();
        bgSlider.setPrefSize(475, 50);
        bgSlider.setMin(0);
        bgSlider.setMax(3);
        bgSlider.setValue(Settings.bgChoice);
        bgSlider.setShowTickLabels(true);
        bgSlider.setShowTickMarks(true);
        bgSlider.setMinorTickCount(0);
        bgSlider.setMajorTickUnit(1);
        bgSlider.setBlockIncrement(1);

        back = new Button("Back");
        back.setStyle("-fx-background-color: rgb(199, 167, 167)");

        colorTgmList = FXCollections.observableArrayList("RosyBrown", "Black", "White", "Green", "Pink");
        colorTgm = new ComboBox<>();
        colorTgm.setItems(colorTgmList);
        colorTgm.getSelectionModel().select(0);

        colorTerrain = new Text("Choose a nice color for the Terrain");
        colorTerrain.setFont(Font.font("Arial", 20));
        colorTerrain.setFill(Color.WHITE);

        try (InputStream is = Files.newInputStream(Paths.get("res/images/bg/bg0.png"))) {
            bg0 = new ImageView(new Image(is));
            bg0Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of bg0.");
        }
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bg/bg1.png"))) {
            bg1 = new ImageView(new Image(is));
            bg1Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of bg1.");
        }
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bg/bg2.png"))) {
            bg2 = new ImageView(new Image(is));
            bg2Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of bg2.");
        }
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bg/bg3.png"))) {
            bg3 = new ImageView(new Image(is));
            bg3Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of bg3.");
        }
    }

    private void layoutnodes() {
        if (imageLoaded) {
            img.setFitWidth(Main.WIDTH);
            img.setFitHeight(Main.HEIGHT);
            getChildren().add(img);
        }

        if (bg0Loaded) {
            bg0.setX(650);
            bg0.setY(350);
            bg0.setFitWidth(220);
            bg0.setFitHeight(120);
        }
        if (bg1Loaded) {
            bg1.setX(900);
            bg1.setY(350);
            bg1.setFitWidth(220);
            bg1.setFitHeight(120);
        }
        if (bg2Loaded) {
            bg2.setX(650);
            bg2.setY(500);
            bg2.setFitWidth(220);
            bg2.setFitHeight(120);
        }
        if (bg3Loaded) {
            bg3.setX(900);
            bg3.setY(500);
            bg3.setFitWidth(220);
            bg3.setFitHeight(120);
        }

        SETTINGSTITLE.setTranslateX(75);
        SETTINGSTITLE.setTranslateY(100);

        fxSound.setTranslateX(75);
        fxSound.setTranslateY(300);

        fxSlider.setTranslateX(200);
        fxSlider.setTranslateY(280);

        bgColor.setTranslateX(650);
        bgColor.setTranslateY(250);

        bgSlider.setTranslateX(650);
        bgSlider.setTranslateY(270);

        Settings.smoothTerrain.setTranslateX(75);
        Settings.smoothTerrain.setTranslateY(500);

        back.setTranslateX(10);
        back.setTranslateY(10);

        colorTgm.setPrefWidth(150);
        colorTgm.setPrefHeight(40);
        colorTgm.setTranslateX(345);
        colorTgm.setTranslateY(380);

        colorTerrain.setX(185);
        colorTerrain.setY(360);

        getChildren().addAll(
                SETTINGSTITLE,
                fxSound,
                fxSlider,
                bgColor,
                bgSlider,
                bg0,
                bg1,
                bg2,
                bg3,
                back,
                Settings.smoothTerrain,
                colorTgm,
                colorTerrain);
    }

    public Button getBack() {
        return back;
    }

    public Slider getBgSlider() {
        return bgSlider;
    }

    public Slider getFxSlider() {
        return fxSlider;
    }

    public ComboBox<String> getColorTgm() {
        return colorTgm;
    }

    public ImageView getBg0() {
        return bg0;
    }

    public ImageView getBg1() {
        return bg1;
    }

    public ImageView getBg2() {
        return bg2;
    }

    public ImageView getBg3() {
        return bg3;
    }
}
