import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero;
    private RenderEngine renderEngine;

    public GameEngine(DynamicSprite hero, RenderEngine renderEngine) {
        this.hero = hero;
        this.renderEngine = renderEngine;
    }

    @Override
    public void update() {
        renderEngine.updateCamera(hero.x - 200, hero.y - 300);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}