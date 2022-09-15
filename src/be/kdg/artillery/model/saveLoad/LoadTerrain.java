package be.kdg.artillery.model.saveLoad;

import be.kdg.artillery.model.Point;
import be.kdg.artillery.model.terrainModel.TerrainGenerationModel;

import java.io.*;

public class LoadTerrain {
    public static Point[] loadTerrain(int welkTerrain) throws IOException {
        Point[] puntenVanTerrain = new Point[TerrainGenerationModel.TERRAINBREEDTE];

        switch (welkTerrain) {
            case 0:

                try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("res/savedTerrains/0.bin")))) {
                    for (int i = 0; i < puntenVanTerrain.length; i++) {
                        double x = is.readDouble();
                        double y = is.readDouble();
                        puntenVanTerrain[i] = new Point(x,y);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Het bestand terrain.bin kan niet geopend worden!");
                }
                return puntenVanTerrain;
            case 1:
                try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("res/savedTerrains/1.bin")))) {
                    for (int i = 0; i < puntenVanTerrain.length; i++) {
                        double x = is.readDouble();
                        double y = is.readDouble();
                        puntenVanTerrain[i] = new Point(x,y);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Het bestand terrain.bin kan niet geopend worden!");
                }
                return puntenVanTerrain;
            case 2:
                try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("res/savedTerrains/2.bin")))) {
                    for (int i = 0; i < puntenVanTerrain.length; i++) {
                        double x = is.readDouble();
                        double y = is.readDouble();
                        puntenVanTerrain[i] = new Point(x,y);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Het bestand terrain.bin kan niet geopend worden!");
                }

                return puntenVanTerrain;
            case 3:
                try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("res/savedTerrains/3.bin")))) {
                    for (int i = 0; i < puntenVanTerrain.length; i++) {
                        double x = is.readDouble();
                        double y = is.readDouble();
                        puntenVanTerrain[i] = new Point(x,y);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Het bestand terrain.bin kan niet geopend worden!");
                }
                return puntenVanTerrain;
            default:
                try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("res/savedTerrains/latest.bin")))) {
                    for (int i = 0; i < puntenVanTerrain.length; i++) {
                        double x = is.readDouble();
                        double y = is.readDouble();
                        puntenVanTerrain[i] = new Point(x,y);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Het bestand terrain.bin kan niet geopend worden!");
                }
                return puntenVanTerrain;
        }
    }
}
