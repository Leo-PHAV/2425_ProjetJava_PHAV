import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    CardLayout cardLayout;
    JPanel mainPanel;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception {
        // Création de la fenêtre principale
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Création des panneaux de menu et de jeu
        createMenuPanel();
        createGamePanel();

        displayZoneFrame.add(mainPanel);
        cardLayout.show(mainPanel, "Menu");

        displayZoneFrame.setVisible(true);
    }

    private void createMenuPanel() {
        Menu menuPanel = new Menu(e -> startGame());
        mainPanel.add(menuPanel, "Menu");
    }

    private void createGamePanel() throws Exception {
        DynamicSprite hero = new DynamicSprite(70, 65,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, renderEngine);


        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        double mapWidth = level.getWidth();
        double mapHeight = level.getHeight();
        renderEngine.setMapSize(mapWidth, mapHeight);

        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(renderEngine, BorderLayout.CENTER);

        mainPanel.add(gamePanel, "Jeu");
        displayZoneFrame.addKeyListener(gameEngine);
    }

    private void startGame() {
        cardLayout.show(mainPanel, "Jeu");
        displayZoneFrame.requestFocus();
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
