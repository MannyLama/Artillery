package be.kdg.artillery.model.player;

import be.kdg.artillery.model.Point;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Dit is de hoofdklasse voor de barrel of de loop van de tank.
 * Deze klasse bevat slechts 1 grote methode die zorgt dat de barrel beweegbaar is.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */
public class Barrel extends Parent {
    private Point punt;
    private double width;
    private double height;
    private double velocity;

    public Barrel(double x, double y) {
        punt = new Point(x, y);
        height = 12;
        width = 3;
        velocity = 0;
    }

    public static Rectangle rotateBarrel(Rectangle movingRectangle, double angle) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(40), movingRectangle);
        rotateTransition.setByAngle(angle);
        rotateTransition.setCycleCount(1);
        rotateTransition.setInterpolator(Interpolator.LINEAR);

        if (movingRectangle.getRotate() > 65) {
            movingRectangle.setRotate(64);
        }

        if (movingRectangle.getRotate() < -65) {
            movingRectangle.setRotate(-64);
        }
        if (movingRectangle.getRotate() < 65 || movingRectangle.getRotate() > -65) {
            rotateTransition.play();

        }
        return movingRectangle;
    }


    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getX() {
        return punt.getX();
    }

    public double getY() {
        return punt.getY();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
