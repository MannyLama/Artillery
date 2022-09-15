package be.kdg.artillery.model.saveLoad;

import be.kdg.artillery.view.game.terrainView.TerrainView;
import be.kdg.artillery.view.menu.settings.Settings;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

public class SaveTerrain {
    public static void saveTerrain(TerrainView terrainView) throws FileNotFoundException {
        String file = "res/savedTerrains/latest.bin";


        MediaPlayer mediaPlayer;
        Media soundFile;

        String musicFile = "res/sound/saved.mp3";
        soundFile = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(soundFile);
        mediaPlayer.setVolume(Settings.volume);
        mediaPlayer.play();

        try (DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("res/savedTerrains/latest.bin")))) {
            for (int i = 0; i < terrainView.getTgm().getPunten().length; i++) {
                os.writeDouble(terrainView.getTgm().getPunten()[i].getX());
                os.writeDouble(terrainView.getTgm().getPunten()[i].getY());

            }
            System.out.println(file);

        } catch (IOException e) {
            System.out.println("Fout bij schrijven naar latest.bin ");
        }
    }
}
