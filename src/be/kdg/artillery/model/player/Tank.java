package be.kdg.artillery.model.player;

import be.kdg.artillery.model.Point;
import javafx.scene.image.ImageView;

/**
 * De Tank klasse bewaart de tank in de gekozen kleur, vaste X-positie
 * en zet deze op het terrein. Hier worden de gegevens zoals health en de positie van de Barrel
 * bepaald.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */

public class Tank {
    public static final int startxPosPlayer1 = 200;
    public static final int startxPosPlayer2 = 1080;

    public static final String REDTANK = "res/images/tankColors/redTank.png";
    public static final String GREENTANK = "res/images/tankColors/greenTank.png";
    public static final String BLUETANK = "res/images/tankColors/blueTank.png";
    public static final String YELLOWTANK = "res/images/tankColors/yellowTank.png";
    public static final String PINKTANK = "res/images/tankColors/pinkTank.png";

    private int health;
    private ImageView tankImage;
    private Point position;
    private Barrel barrel;
    private double barrelPosX;
    private double barrelPosY;

    public Tank() {
        position = new Point();
        this.health = 100;
        barrel = new Barrel(barrelPosX, barrelPosY);
        barrelPosX = position.getX();
        barrelPosY = position.getY();

    }

    public void setBarrelPos(){
        barrelPosX = position.getX()-1;
        barrelPosY = position.getY()-23;
        barrel = new Barrel(barrelPosX, barrelPosY);

    }
    public Barrel getBarrelTank() {
        return barrel;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public Point getPosition() {
        return position;
    }
    public ImageView getTankImage() {
        return tankImage;
    }
    public void setTankImage(ImageView tankImage) {
        this.tankImage = tankImage;
    }
}
