package be.kdg.artillery.model.terrainModel;

import be.kdg.artillery.Main;
import be.kdg.artillery.model.Point;

import java.util.Random;

/**
 * Dit is de klasse waarin het terrein zelf willekeurig wordt gegenereerd.
 * Deze gebruikt de Point klasse die simpelweg een x en y waarde bijhoudt.
 * Er is ook een methode die ervoor zorgt dat het terrein wat "smoother" wordt.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */
public class TerrainGenerationModel {
    public final static int TERRAINBREEDTE = Main.WIDTH;
    public final static int STIJGINGSGRAAD = 2;
    public final static int MAXIMUMHOOGTETERRAIN = 200; //ideale waarden tussen 200 en 400
    public final static int MINIMUMHOOGTETERRAIN = Main.HEIGHT - 200;

    private Random rand = new Random();
    private Point[] punten;
    private int berekingsgraad = rand.nextInt(2);

    /**
     * De constructor maakt de berekening van de punten voor het terrein te genereren.
     */
    public TerrainGenerationModel() {
        punten = new Point[TERRAINBREEDTE];


        int randomgetal;

        for (int i = 0; i < TERRAINBREEDTE; i++) {
            randomgetal = rand.nextInt(STIJGINGSGRAAD);
            if (i == 0) {
                //random hoogte van het startpunt links aanmaken
                punten[i] = new Point(0, rand.nextInt(200) + 350);
            } else {
                //Naar beneden
                if (berekingsgraad == 0) {
                    punten[i] = new Point(i, randomgetal + (int) punten[i - 1].getY());

                    if (punten[i].getY() > MINIMUMHOOGTETERRAIN) {
                        punten[i].setY(MINIMUMHOOGTETERRAIN);
                        berekingsgraad = 1;
                    } else if (i % (rand.nextInt(10) + 10) == 0) {
                        berekingsgraad = rand.nextInt(2);
                    }
                }

                //Naar boven
                if (berekingsgraad == 1) {
                    punten[i] = new Point(i, (-randomgetal) + (int) punten[i - 1].getY());

                    if (punten[i].getY() < MAXIMUMHOOGTETERRAIN) {
                        punten[i].setY(MAXIMUMHOOGTETERRAIN);
                        berekingsgraad = 0;
                    } else if (i % (rand.nextInt(10) + 10) == 0) {
                        berekingsgraad = rand.nextInt(2);
                    }
                }
            }
        }
    }

    public static void smoothnerMaker(TerrainGenerationModel tgm) {
        Point[] punten = tgm.getPunten();

        for (int i = 0; i < TERRAINBREEDTE; i++) {

            double getal1x;
            double getal1y;

            double getal2x;
            double getal2y;

            double getal3x;
            double getal3y;

            double getal4x;
            double getal4y;

            if (i % 4 == 1) {

                getal1x = punten[i - 1].getX();
                getal1y = punten[i - 1].getY();

                getal2x = punten[i].getX();
                getal2y = punten[i].getY();

                getal3x = punten[i + 1].getX();
                getal3y = punten[i + 1].getY();

                getal4x = punten[i + 1].getX();
                getal4y = punten[i + 1].getY();

                getal1x = (getal1x + getal2x + getal3x + getal4x) / 4;
                getal1y = (getal1y + getal2y + getal3y + getal4y) / 4;

                punten[i - 1].setX((int) getal1x);
                punten[i - 1].setY((int) getal1y);
                punten[i].setX((int) getal1x);
                punten[i].setY((int) getal1y);
                punten[i + 1].setX((int) getal1x);
                punten[i + 1].setY((int) getal1y);
                punten[i + 2].setX((int) getal1x);
                punten[i + 2].setY((int) getal1y);
            }
        }
    }

    public Point[] getPunten() {
        return punten;
    }

    public void setPunten(Point[] punten) {
        this.punten = punten;
    }
}


