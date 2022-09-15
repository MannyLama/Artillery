package be.kdg.artillery.view.game.topBar;

import be.kdg.artillery.Main;
import be.kdg.artillery.model.player.Player;
import be.kdg.artillery.model.projectile.Wind;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TopBarView extends StackPane {
    private final int TOPBARWIDTH = Main.WIDTH;
    private final int TOPBARHEIGHT = Main.WIDTH / 15;
    private Rectangle topBar;

    //player info topbar
    private Text displayPlayer1Name;
    private Text displayPlayer2Name;
    private Text displayHealthp1;
    private Text displayHealthp2;
    private Text wind;
    private double windSpeed;
    private CheckBox metTraject;
    private CheckBox storm;

    private Slider powerSlider;

    public TopBarView() {
        initialisenodes();
        layoutnodes();
        new TopBarPresenter(this);
    }

    private void initialisenodes() {
        powerSlider = new Slider();
        powerSlider = new Slider();
        powerSlider.setMaxWidth(300);
        powerSlider.setMin(70);
        powerSlider.setMax(120);
        powerSlider.setValue(70);
        powerSlider.setShowTickLabels(true);
        powerSlider.setShowTickMarks(true);
        powerSlider.setMajorTickUnit(50);
        powerSlider.setMinorTickCount(5);
        powerSlider.setBlockIncrement(25);

        //player names
        topBar = new Rectangle(TOPBARWIDTH, TOPBARHEIGHT, Color.DIMGREY);
        displayPlayer1Name = new Text(Player.player1.getName());
        displayPlayer1Name.setFill(Color.WHITESMOKE);
        displayPlayer1Name.setFont(Font.font("Arial", 20));
        displayPlayer2Name = new Text(Player.player2.getName());
        displayPlayer2Name.setFill(Color.WHITESMOKE);
        displayPlayer2Name.setFont(Font.font("Arial", 20));

        //Health text
        displayHealthp1 = new Text("Health: " + String.valueOf(Player.player1.getTank().getHealth()));
        displayHealthp1.setFill(Color.WHITESMOKE);
        displayHealthp2 = new Text("Health: " + String.valueOf(Player.player2.getTank().getHealth()));
        displayHealthp2.setFill(Color.WHITESMOKE);

        //wind plaatsen op TopBar
        windSpeed = Wind.windCalculation();
        if (windSpeed >= 0) {
            wind = new Text("Wind speed =  <-- " + Math.abs(Math.round(windSpeed * 50)));
        } else {
            wind = new Text("Wind speed =  --> " + Math.abs(Math.round(windSpeed * 50)));
        }
        wind.setFill(Color.WHITE);

        //checkbox voor traject te tonen
        metTraject = new CheckBox();
        metTraject.setText("Show path?");
        metTraject.setFont(Font.font(15));
        metTraject.setTextFill(Color.WHITE);

        storm = new CheckBox("Laat het stormen");
        storm.setTextFill(Color.WHITE);
        storm.setFont(Font.font("Arial", 13));

    }

    private void layoutnodes() {
        //player names
        setAlignment(displayPlayer1Name, Pos.TOP_LEFT);
        setAlignment(displayPlayer2Name, Pos.TOP_RIGHT);
        setMargin(displayPlayer1Name, new Insets(10, 0, 10, 25));
        setMargin(displayPlayer2Name, new Insets(10, 50, 10, 0));

        //health text
        setAlignment(displayHealthp1, Pos.CENTER_LEFT);
        setAlignment(displayHealthp2, Pos.CENTER_RIGHT);
        setMargin(displayHealthp1, new Insets(10, 0, 10, 25));
        setMargin(displayHealthp2, new Insets(10, 50, 10, 0));

        //power slider
        setAlignment(powerSlider, Pos.BOTTOM_CENTER);
        setAlignment(wind, Pos.TOP_CENTER);

        //traject checkbox
        metTraject.setTranslateY(-11);
        setAlignment(metTraject, Pos.CENTER);

        //storm checkbox
        setAlignment(storm, Pos.BOTTOM_LEFT);


        getChildren().addAll(
                topBar,
                displayPlayer1Name,
                displayPlayer2Name,
                displayHealthp1,
                displayHealthp2,
                powerSlider,
                wind,
                metTraject,
                storm
        );
    }

    public void defineWindspeed() {
        windSpeed = Wind.windCalculation();
        if (storm.isSelected()) {
            windSpeed *= 3.5;
            if (windSpeed >= 0) {
                wind.setText("Wind speed =  <-- " + Math.abs(Math.round(windSpeed * 50)));
            } else {
                wind.setText("Wind speed =  --> " + Math.abs(Math.round(windSpeed * 50)));
            }

        } else {
            if (windSpeed >= 0) {
                wind.setText("Wind speed =  <-- " + Math.abs(Math.round(windSpeed * 50)));
            } else {
                wind.setText("Wind speed =  --> " + Math.abs(Math.round(windSpeed * 50)));
            }
        }
    }

    public void updateTopBar(int playerTurn) {
        displayHealthp1.setText("Health: " + String.valueOf(Player.player1.getTank().getHealth()));
        displayHealthp2.setText("Health: " + String.valueOf(Player.player2.getTank().getHealth()));

        //kleurt speler in TopBar GOLD als hij aan de beurt is
        if (playerTurn == 1) {
            displayPlayer2Name.setFill(Color.BLACK);
            displayPlayer2Name.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

            displayPlayer1Name.setFill(Color.GOLD);
            displayPlayer1Name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        }
        if (playerTurn == 2) {
            displayPlayer1Name.setFill(Color.BLACK);
            displayPlayer1Name.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

            displayPlayer2Name.setFill(Color.GOLD);
            displayPlayer2Name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        }
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Slider getPowerSlider() {
        return powerSlider;
    }

    public void setPowerSliderValue(double value) {
        this.powerSlider.adjustValue(value);
    }

    public Text getDisplayPlayer1Name() {
        return displayPlayer1Name;
    }

    public void setDisplayPlayer1Name(Text displayPlayer1Name) {
        this.displayPlayer1Name = displayPlayer1Name;
    }

    public Text getDisplayPlayer2Name() {
        return displayPlayer2Name;
    }

    public void setDisplayPlayer2Name(Text displayPlayer2Name) {
        this.displayPlayer2Name = displayPlayer2Name;
    }

    public CheckBox getMetTraject() {
        return metTraject;
    }

    public CheckBox getStorm() {
        return storm;
    }
}
