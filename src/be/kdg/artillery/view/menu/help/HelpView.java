package be.kdg.artillery.view.menu.help;

import be.kdg.artillery.Main;
import be.kdg.artillery.view.menu.Title;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelpView extends Parent {
    private boolean imageLoaded;
    private ImageView img;
    public final Title HELPTITLE = new Title("H E L P  &  I N F O", 425, 70);

    private Button back;

    private Text about;
    private Text controls;
    private Text shoot;
    private Text enter;

    private Text moveUp;
    private Text up;

    private Text moveDown;
    private Text down;

    private FileInputStream fis;
    private InputStreamReader isr;
    private BufferedReader br;

    public HelpView() throws IOException {
        initialisenodes();
        layoutnodes();
    }

    private void initialisenodes() throws IOException {
        imageLoaded = false;
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bgMenu.jpg"))) {
            img = new ImageView(new Image(is));
            imageLoaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image.");
        }
        back = new Button("Back");
        back.setStyle("-fx-background-color: rgb(199, 167, 167)");

        fis = new FileInputStream("src/files/help.txt");
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);

        about = new Text();
        about.setFont(Font.font("Arial", 25));
        about.setFill(Color.WHEAT);

        controls = new Text("Controls");
        controls.setFont(Font.font("Arial", 20));
        controls.setUnderline(true);
        controls.setFill(Color.WHITE);
        shoot = new Text("Shoot: ");
        shoot.setFont(Font.font("Arial", 20));
        shoot.setFill(Color.WHITE);
        enter = new Text("ENTER");
        enter.setFont(Font.font("Arial", 20));
        enter.setFill(Color.DARKRED);
        moveUp = new Text("Move barrel right:");
        moveUp.setFont(Font.font("Arial", 20));
        moveUp.setFill(Color.WHITE);
        up = new Text("Arrow-up");
        up.setFont(Font.font("Arial", 20));
        up.setFill(Color.DARKRED);
        moveDown = new Text("Move barrel left: ");
        moveDown.setFont(Font.font("Arial", 20));
        moveDown.setFill(Color.WHITE);
        down = new Text("Arrow-down");
        down.setFont(Font.font("Arial", 20));
        down.setFill(Color.DARKRED);

        String readLine = "";
        String text;
        readLine = br.readLine();
        while(readLine != null){
            text = readLine;
            about.setText(text);
            readLine = br.readLine();
        }
    }

    private void layoutnodes() {
        if (imageLoaded) {
            img.setFitHeight(Main.HEIGHT);
            img.setFitWidth(Main.WIDTH);
            getChildren().add(img);
        }

        HELPTITLE.setTranslateX(75);
        HELPTITLE.setTranslateY(100);

        back.setTranslateX(10);
        back.setTranslateY(10);

        about.setTranslateX(75);
        about.setTranslateY(220);

        controls.setTranslateX(75);
        controls.setTranslateY(300);
        shoot.setTranslateX(75);
        shoot.setTranslateY(340);
        enter.setTranslateX(250);
        enter.setTranslateY(340);
        moveUp.setTranslateX(75);
        moveUp.setTranslateY(380);
        up.setTranslateX(250);
        up.setTranslateY(380);
        moveDown.setTranslateX(75);
        moveDown.setTranslateY(420);
        down.setTranslateX(250);
        down.setTranslateY(420);

        getChildren().add(about);
        getChildren().addAll(back, HELPTITLE, controls, shoot, enter, moveUp, up, moveDown, down
        );
    }

    public Button getBack() {
        return back;
    }
}
