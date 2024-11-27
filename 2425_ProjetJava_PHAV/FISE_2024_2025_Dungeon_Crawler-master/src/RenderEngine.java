import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private double cameraX = 0;
    private double cameraY = 0;
    private double mapWidth;
    private double mapHeight;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
        this.setPreferredSize(new Dimension(400, 600)); // Taille de la fenêtre de jeu
    }

    public void setMapSize(double width, double height) {
        this.mapWidth = width;
        this.mapHeight = height;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable) {
        for (Displayable d : displayable) {
            if (!renderList.contains(d)) {
                renderList.add(d);
            }
        }
    }

    public void updateCamera(double x, double y) {
        // Limite la position de la caméra
        double maxX = mapWidth + this.getWidth();
        double maxY = mapHeight - this.getHeight();

        this.cameraX = Math.max(0, Math.min(x, maxX - 96));  // Empêche la caméra de sortir de la carte
        this.cameraY = Math.max(0, Math.min(y, maxY));  // Pareil pour l'axe Y
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-cameraX, -cameraY);

        for (Displayable renderObject : renderList) {
            renderObject.draw(g2d);
        }

        g2d.translate(cameraX, cameraY);
    }

    @Override
    public void update() {
        this.repaint();
    }
}
