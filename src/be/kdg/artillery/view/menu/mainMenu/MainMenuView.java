package be.kdg.artillery.view.menu.mainMenu;

import be.kdg.artillery.Main;
import be.kdg.artillery.view.menu.Title;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainMenuView extends Parent {
    public Title menuTitle = new Title("M a i n   M e n u", 365, 70);

    private MenuBox mainMenuBox;

    private MenuItem[] menuItems;
    private MenuItem newGame;
    private MenuItem loadTerrain;
    private MenuItem settings;
    private MenuItem exitButton;
    private MenuItem help;

    private ImageView img;
    private boolean imageLoaded;

    private ImageView tankImage;

    public MainMenuView() {
        initialisenodes();
        layoutnodes();
    }

    private void initialisenodes() {
        imageLoaded = false;
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bgMenu.jpg"))) {
            img = new ImageView(new Image(is));
            imageLoaded = true;
        } catch (IOException e) {
            System.out.println("Couldn't load image.");
        }

        try (InputStream is = Files.newInputStream(Paths.get("res/images/menuTank.png"))) {
            tankImage = new ImageView(new Image(is));
        } catch (IOException e) {
            System.out.println("Couldn't load tank image.");
        }

        //alle menu items
        newGame = new MenuItem("New Game");
        loadTerrain = new MenuItem("Load Terrains");
        settings = new MenuItem("Settings");
        exitButton = new MenuItem("Exit");
        help = new MenuItem("Help & Info");

        menuItems = new MenuItem[]{
                newGame,
                loadTerrain,
                settings,
                help,
                exitButton
        };

        mainMenuBox = new MenuBox(
                menuItems[0],
                menuItems[1],
                menuItems[2],
                menuItems[3],
                menuItems[4]
        );
    }

    private void layoutnodes() {
        if (imageLoaded) {
            img.setFitWidth(Main.WIDTH);
            img.setFitHeight(Main.HEIGHT);
            getChildren().add(img);
        }

        tankImage.setTranslateX(250);
        tankImage.setTranslateY(295);

        menuTitle.setTranslateX(75);
        menuTitle.setTranslateY(100);
        mainMenuBox.setTranslateX(75);
        mainMenuBox.setTranslateY(300);

        getChildren().addAll(menuTitle, mainMenuBox, tankImage);
    }

    class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {         //MenuItem... staat voor 0 tot meer items die kunnen worden meegegeven
            getChildren().add(createSeperator());   //maakt een lijn boven de eerste menu item

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeperator()); //maakt een lijn onder alle menu items
            }
        }

        //lijn boven/onder elke menu item
        private Line createSeperator() {
            Line sep = new Line();
            sep.setEndX(200);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    class MenuItem extends StackPane {
        private final Rectangle menuItemBg;
        private final Text menuItemText;
        private final LinearGradient gradient;

        public Rectangle getMenuItemBg() {
            return menuItemBg;
        }

        public Text getMenuItemText() {
            return menuItemText;
        }

        public LinearGradient getGradient() {
            return gradient;
        }

        public MenuItem(String name) {
            gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.RED),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.3, Color.BLACK),
                    new Stop(1, Color.RED));

            menuItemBg = new Rectangle(200, 30);
            menuItemBg.setOpacity(0.4);

            menuItemText = new Text(name);
            menuItemText.setFill(Color.DARKGRAY);
            menuItemText.setFont(Font.font("Arial", 25));
            setAlignment(Pos.BOTTOM_LEFT);
            getChildren().addAll(menuItemBg, menuItemText);
        }
    }

    // PUBLIC GETTERS
    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public MenuItem getExitButton() {
        return exitButton;
    }

    public MenuItem getSettings() {
        return settings;
    }

    public MenuItem getLoadTerrain() {
        return loadTerrain;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getHelp() {
        return help;
    }

    /*public void setHelp(MenuItem help) {
        this.help = help;
    }*/
}
