package be.kdg.artillery.model.player;

/**
 * In deze klasse worden de gegevens van de beide spelers bewaard.
 * Er wordt een tank gemaakt maar deze wordt in een aparte klasse beschreven.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */
public class Player {
    public static Player player1;
    public static Player player2;

    private String name;
    private Tank tank;
    private int aantelKeerGeschoten;
    private boolean magSoundEffectInslag;

    public Player(String name, int xPos) {
        tank = new Tank();
        this.name = name;
        tank.getPosition().setX(xPos);
        aantelKeerGeschoten = 0;
        magSoundEffectInslag = true;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", tank=" + tank +
                ", health=" + tank.getHealth() +
                ", xPos=" + tank.getPosition().getX() +
                ", yPos=" + tank.getPosition().getY() +
                '}';
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tank getTank() {
        return tank;
    }

    public int getAantelKeerGeschoten() {
        return aantelKeerGeschoten;
    }

    public void setAantelKeerGeschoten(int aantelKeerGeschoten) {
        this.aantelKeerGeschoten = aantelKeerGeschoten;
    }

    public boolean isMagSoundEffectInslag() {
        return magSoundEffectInslag;
    }

    public void setMagSoundEffectInslag(boolean magSoundEffectInslag) {
        this.magSoundEffectInslag = magSoundEffectInslag;
    }
}

