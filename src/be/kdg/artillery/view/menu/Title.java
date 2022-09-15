package be.kdg.artillery.view.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//Klasse dat in elke menu item terug komt
public class Title extends StackPane {
    public Title(String name, int width, int height) {
        Rectangle bg = new Rectangle(width, height);
        bg.setStroke(Color.WHITE);
        bg.setStrokeWidth(2);
        bg.setFill(null);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", 50));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
    }
}
