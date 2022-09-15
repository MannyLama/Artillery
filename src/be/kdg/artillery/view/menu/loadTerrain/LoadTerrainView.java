package be.kdg.artillery.view.menu.loadTerrain;

import be.kdg.artillery.Main;
import be.kdg.artillery.view.menu.Title;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadTerrainView extends Parent {
    private ImageView img;
    private boolean imageLoaded;
    public final Title TERRAINTITLE = new Title("T E R R A I N S", 365, 70);

    private Text terraintext;
    private Text or;


    private ImageView tgm0;
    private ImageView tgm1;
    private ImageView tgm2;
    private ImageView tgm3;
    private boolean tgm0Loaded;
    private boolean tgm1Loaded;
    private boolean tgm2Loaded;
    private boolean tgm3Loaded;

    private Button back;
    private Button start;


    public LoadTerrainView() {
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

        back = new Button("Back");
        back.setStyle("-fx-background-color: rgb(199, 167, 167)");

        String terrains = "../terrain.txt";

        terraintext = new Text();
        terraintext.setFont(Font.font("Arial", 20));
        terraintext.setFill(Color.WHITE);
        terraintext.setText("play with a preloaded terrain!");

        start = new Button("Start the game with the latest saved game!");
        start.setId("startButton");

        or = new Text("Or");

        tgm0Loaded = false;
        tgm1Loaded = false;
        tgm2Loaded = false;
        tgm3Loaded = false;

        try (InputStream is = Files.newInputStream(Paths.get("res/savedTerrains/0.png"))) {
            tgm0 = new ImageView(new Image(is));
            tgm0Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of tgm0.");
        }
        try (InputStream is = Files.newInputStream(Paths.get("res/savedTerrains/1.png"))) {
            tgm1 = new ImageView(new Image(is));
            tgm1Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of tgm1.");
        }
        try (InputStream is = Files.newInputStream(Paths.get("res/savedTerrains/2.png"))) {
            tgm2 = new ImageView(new Image(is));
            tgm2Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of tgm2.");
        }
        try (InputStream is = Files.newInputStream(Paths.get("res/savedTerrains/3.png"))) {
            tgm3 = new ImageView(new Image(is));
            tgm3Loaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image of tgm3.");
        }

    }

    private void layoutnodes() {
        if (imageLoaded) {
            img.setFitHeight(Main.HEIGHT);
            img.setFitWidth(Main.WIDTH);
            getChildren().add(img);
        }
        if (tgm0Loaded){
            tgm0.setX(700);
            tgm0.setY(100);
            tgm0.setFitWidth(370);
            tgm0.setFitHeight(100);
        }
        if(tgm1Loaded){
            tgm1.setX(700);
            tgm1.setY(250);
            tgm1.setFitWidth(370);
            tgm1.setFitHeight(100);
        }
        if(tgm2Loaded){
            tgm2.setX(700);
            tgm2.setY(400);
            tgm2.setFitWidth(370);
            tgm2.setFitHeight(100);
        }
        if (tgm3Loaded){
            tgm3.setX(700);
            tgm3.setY(550);
            tgm3.setFitWidth(370);
            tgm3.setFitHeight(100);
        }
        TERRAINTITLE.setTranslateX(75);
        TERRAINTITLE.setTranslateY(100);

        back.setTranslateX(10);
        back.setTranslateY(10);

        terraintext.setTranslateX(700);
        terraintext.setTranslateY(70);

        start.setTranslateX(75);
        start.setTranslateY(Main.HEIGHT/2);
        start.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
        start.setPrefSize(450, 75);

        or.setFont(Font.font("Arial", FontWeight.NORMAL,25));
        or.setX(575);
        or.setY(Main.HEIGHT/2+45);
        or.setFill(Color.WHITE);

        getChildren().addAll(TERRAINTITLE,start, back, terraintext, or, tgm0, tgm1, tgm2,tgm3);
    }

    public Button getBack() {
        return back;
    }

    public ImageView getTgm0() {
        return tgm0;
    }

    public ImageView getTgm1() {
        return tgm1;
    }

    public ImageView getTgm2() {
        return tgm2;
    }

    public ImageView getTgm3() {
        return tgm3;
    }

    public Button getStart() {
        return start;
    }
}
