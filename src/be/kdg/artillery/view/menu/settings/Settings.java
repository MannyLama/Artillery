package be.kdg.artillery.view.menu.settings;

import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;

/**
 * Created by Manfred on 17/03/16.
 */
public class Settings {
    public static CheckBox smoothTerrain = new CheckBox();
    //-1 = randomTerrain  , 0 = terrain nr 0, 1 = terrain nr 1, ... , 10 = latestSavedTerrain
    public static int welkterrain = -1;
    public static double volume = 0.5;
    public static Color terrainColor = Color.rgb(180, 130, 90);
    public static int bgChoice = 0;
}
