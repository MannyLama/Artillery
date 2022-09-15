package be.kdg.artillery.view.menu.newGame;

import be.kdg.artillery.Main;
import be.kdg.artillery.model.player.Player;
import be.kdg.artillery.model.player.Tank;
import be.kdg.artillery.view.menu.Title;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

public class NewGameView extends Parent {
    private ImageView img;
    private boolean imageLoaded;
    public final Title NEWGAMETITLE = new Title("N E W  G A M E", 365, 70);
    private Text player1txt;
    private Text player2txt;
    private TextField tfPlayer1;
    private TextField tfPlayer2;
    private ComboBox<String> colorP1;
    private ComboBox<String> colorP2;
    private ObservableList<String> colors1;
    private ObservableList<String> colors2;

    private Button back;
    private Button start;

    public NewGameView() {
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

        Player.player1 = new Player("Player 1", Tank.startxPosPlayer1);
        Player.player2 = new Player("Player 2", Tank.startxPosPlayer2);

        player1txt = new Text(Player.player1.getName());
        player1txt.setFont(Font.font("Arial", 30));
        player1txt.setFill(Color.WHITE);
        player2txt = new Text(Player.player2.getName());
        player2txt.setFont(Font.font("Arial", 30));
        player2txt.setFill(Color.WHITE);

        //textfield p1
        tfPlayer1 = new TextField();
        tfPlayer2 = new TextField();
        tfPlayer1.setAlignment(Pos.CENTER);
        tfPlayer1.setPrefWidth(150);

        //textfield p2
        tfPlayer2.setAlignment(Pos.CENTER);
        tfPlayer2.setPrefWidth(150);
        tfPlayer1.setPromptText("Player 1");
        tfPlayer2.setPromptText("Player 2");

        //combobox p1 & p2
        colorP1 = new ComboBox<>();
        colorP2 = new ComboBox<>();
        colorP1.setPrefWidth(150);
        colorP2.setPrefWidth(150);

        colors1 = FXCollections.observableArrayList("Red", "Green", "Blue", "Yellow", "Pink");
        colors2 = FXCollections.observableArrayList("Red", "Green", "Blue", "Yellow", "Pink");

        colorP1.setItems(colors1);
        colorP1.setEditable(false);
        colorP1.getSelectionModel().select(0);
        colorP2.setItems(colors2);
        colorP2.setEditable(false);
        colorP2.getSelectionModel().select(0);

        back = new Button("Back");
        back.setStyle("-fx-background-color: rgb(199, 167, 167)");
        start = new Button("Start game!");
        start.setId("startButton");
    }

    private void layoutnodes() {
        if (imageLoaded) {
            img.setFitHeight(Main.HEIGHT);
            img.setFitWidth(Main.WIDTH);
            getChildren().add(img);
        }

        NEWGAMETITLE.setTranslateX(75);
        NEWGAMETITLE.setTranslateY(100);

        player1txt.setTranslateX(75);
        player1txt.setTranslateY(250);
        player2txt.setTranslateX(450);
        player2txt.setTranslateY(250);

        tfPlayer1.setTranslateX(75);
        tfPlayer1.setTranslateY(280);
        tfPlayer2.setTranslateX(450);
        tfPlayer2.setTranslateY(280);

        colorP1.setTranslateX(75);
        colorP1.setTranslateY(310);
        colorP2.setTranslateX(450);
        colorP2.setTranslateY(310);

        back.setTranslateX(10);
        back.setTranslateY(10);
        start.setTranslateX(450);
        start.setTranslateY(550);
        start.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        start.setPrefSize(375, 75);

        getChildren().addAll(NEWGAMETITLE,
                player1txt, player2txt,
                tfPlayer1, tfPlayer2,
                colorP1, colorP2,
                back, start
        );
    }

    //getters speler info
    public TextField getTfPlayer1() {
        return tfPlayer1;
    }

    public TextField getTfPlayer2() {
        return tfPlayer2;
    }

    public Player getPlayer2() {
        return Player.player2;
    }

    public Player getPlayer1() {
        return Player.player1;
    }

    public Text getPlayer1txt() {
        return player1txt;
    }

    public Text getPlayer2txt() {
        return player2txt;
    }

    public ComboBox<String> getColorP1() {
        return colorP1;
    }

    public ComboBox<String> getColorP2() {
        return colorP2;
    }

    public ObservableList<String> getColors1() {
        return colors1;
    }

    public ObservableList<String> getColors2() {
        return colors2;
    }

    //getters back- en startknop
    public Button getBack() {
        return back;
    }

    public Button getStart() {
        return start;
    }
}
