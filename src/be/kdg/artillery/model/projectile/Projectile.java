package be.kdg.artillery.model.projectile;

import be.kdg.artillery.model.Point;
import be.kdg.artillery.model.player.Player;
import be.kdg.artillery.view.game.terrainView.TerrainView;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Dit is de hoofdklasse voor het projectiel dat uit de tank komt wanneer er geschoten wordt.
 * De x- en y-waarden van het projectiel worden hier bijgehouden en ook wordt hier de
 * boog berekend en wind op toegepast. Ook wordt er hier bepaald wie aan zet is.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */
public class Projectile {
    private double gravity;
    private Point position;
    private double velocity;

    public Projectile() {
        this.position = new Point(0, 0);
        this.gravity = -9.81;
        this.velocity = -1;
    }

    public static void Fire(TerrainView terrainview, Circle movingBullet) {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(movingBullet);
        Path path = new Path();
        Projectile projectile = new Projectile();
        Rectangle movingBarrel;
        Point puntje = new Point(0, 0);
        double windspeed = terrainview.getTopBar().getWindSpeed();
        boolean moetStoppen = false;

        ArrayList<Double> trajectpunten = new ArrayList<>();

        Point vi;
        Point vf;
        Point d;
        double richtingGraad = -180;
        int zetAan = terrainview.getZetAan();

        if (zetAan == 1) {
            movingBarrel = terrainview.getMovingBarrelPlayer1();
            richtingGraad = movingBarrel.getRotate();
            puntje.setX(Player.player1.getTank().getPosition().getX());
            puntje.setY(Player.player1.getTank().getPosition().getY() - 18 + 113);

            //set the power van de slider naar de player
            Player.player1.getTank().getBarrelTank().setVelocity(terrainview.getTopBar().getPowerSlider().getValue());

            //set velocity
            projectile.setVelocity(Player.player1.getTank().getBarrelTank().getVelocity());

            movingBullet.setCenterX(puntje.getX());
            movingBullet.setCenterY(puntje.getY());

            //Teller aantal schoten
            Player.player1.setAantelKeerGeschoten(Player.player1.getAantelKeerGeschoten() + 1);
        }

        if (zetAan == 2) {
            movingBarrel = terrainview.getMovingBarrelPlayer2();
            richtingGraad = movingBarrel.getRotate();
            puntje.setX(Player.player2.getTank().getPosition().getX());
            puntje.setY(Player.player2.getTank().getPosition().getY() - 18 + 113);
            //set the power van de slider naar de player
            Player.player2.getTank().getBarrelTank().setVelocity(terrainview.getTopBar().getPowerSlider().getValue());
            //set velocity
            projectile.setVelocity(Player.player2.getTank().getBarrelTank().getVelocity());
            //Teller aantal schoten
            Player.player2.setAantelKeerGeschoten(Player.player2.getAantelKeerGeschoten() + 1);
            movingBullet.setCenterX(puntje.getX());
            movingBullet.setCenterY(puntje.getY());
        } else if (richtingGraad == -180)
            throw new IndexOutOfBoundsException("Er is iets misgelopen met de richtingsgraag, richtingsgraad= " + richtingGraad);

        try {
            if (terrainview.isBulletisRemoved()) {
                terrainview.getChildren().add(movingBullet);
                terrainview.setBulletisRemoved(false);
            }
        } catch (IllegalArgumentException il) {
            terrainview.getChildren().removeAll(movingBullet);
            terrainview.getChildren().add(movingBullet);
            terrainview.setBulletisRemoved(false);
        }

        vi = new Point(puntje.getX(), puntje.getY());
        vf = new Point(0, 0);
        d = new Point(0, 0);

        double totalTime = 0;
        double t = 0;


        //in radialen
        vi.setX(Math.cos((-richtingGraad + 90) * Math.PI / 180) * projectile.getVelocity());
        vi.setY(Math.sin((-richtingGraad + 90) * Math.PI / 180) * projectile.getVelocity());

        //hoelang moet de boog zijn/ hoever moet die getekend worden
        totalTime = Math.abs((vi.getY() * 2) / projectile.getGravity());

        if (terrainview.getTopBar().getMetTraject().isSelected()) {
            trajectpunten.add(puntje.getX());
            trajectpunten.add(puntje.getY());
        }

        path.getElements().add(new MoveTo(puntje.getX(), puntje.getY()));


        //totaltime zorgt voor hoe diep het gaat
        while (t <= totalTime + 4) {
            //0.05
            t += 0.05;
            if (!moetStoppen) {
                vf.setY(vi.getY() + projectile.getGravity() * t);
                d.setY((Math.pow(vf.getY(), 2) - Math.pow(vi.getY(), 2)) / (2 * projectile.getGravity()));

                if (windspeed >= 0) {
                    d.setX(vi.getX() * t - Math.pow(windspeed * t, 2));
                } else {
                    d.setX(vi.getX() * t + Math.pow(windspeed * t, 2));
                }

                path.getElements().add(new LineTo(puntje.getX() + d.getX(), puntje.getY() - d.getY()));

                if (terrainview.getTopBar().getMetTraject().isSelected()) {
                    //traject punten waarden geven
                    trajectpunten.add(puntje.getX() + d.getX());
                    trajectpunten.add(puntje.getY() - d.getY());
                }
            }
        }
        pathTransition.setNode(movingBullet);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(totalTime / 5));
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                terrainview.calculateZetAan();
            }
        });

        if (terrainview.getTopBar().getMetTraject().isSelected()) {
            double[] trajectPuntendouble = new double[trajectpunten.size()];

            //Arraylist[] omvormen tot double[]
            for (int i = 0; i < trajectpunten.size(); i++) {
                trajectPuntendouble[i] = trajectpunten.get(i);
            }

            terrainview.setTraject(new Polyline(trajectPuntendouble));
            terrainview.getChildren().add(terrainview.getTraject());
        }
    }

    private double getGravity() {
        return gravity;
    }

    private double getVelocity() {
        return velocity;
    }

    private void setVelocity(double velocity) {
        this.velocity = velocity;
    }

}
