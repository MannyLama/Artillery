package be.kdg.artillery.model;

/**
 * Deze klasse houdt simpelweg een x en y waarde bij en is cruciaal voor het
 * terrein te kunnen maken in TerrainGenerationModel.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */
public class Point {
    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return x + "/" + y +"/";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
