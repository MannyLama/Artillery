package be.kdg.artillery.model.projectile;

import java.util.Random;

/**
 * Dit is slechts een klasse om een random waarde te bekomen uit een berekening van -1.25 to 1.25.
 * Deze wordt toegepast in de Projectile klasse.
 * Dit bepaald de sterkte van de wind.
 *
 * @author Gilles & Manfred
 * @version 1.0
 */
public class Wind {
    public static double windCalculation(){
        Random rand = new Random();
        double wind = (rand.nextDouble() * 2.5 - 1.25);
        return wind;
    }
}
