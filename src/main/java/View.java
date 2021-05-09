import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;
import java.util.stream.Collectors;

public class View extends JPanel implements Observer {

    HashMap<Unit, Sprite> sprites;

    private class Sprite extends Component{
        int x,y;
        Color color;


        public Sprite(Color color) {
            this.x = 0;
            this.y = 0;
            this.color =color;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public void paint(Graphics g) {
            var g2d = (Graphics2D)g;
            g.setColor(color);
            g2d.drawRect(x-2, y-2, 4,4);
        }
    }

    public View() throws HeadlessException {
        this.sprites = new HashMap<>();
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(500,500));
        //this.setBackground(Color.BLUE);
    }

    private void addSprite(Unit unit, Sprite sprite){
        sprites.put(unit, sprite);
        this.add(sprite);
    }

    private void removeSprite(Unit unit){
        this.remove(sprites.get(unit));
        sprites.remove(unit);
    }

    @Override
    public void update(Observable observable, Object o) {
        Game obsGame = (Game) observable;
        var units = Arrays.stream(obsGame.getUnits()).flatMap(Collection::stream);
        units.forEach(x->{
            var sprite = sprites.get(x);
            if(sprite==null){
                sprite = new Sprite((x.getTeam()==1?Color.RED:Color.BLUE));
                addSprite(x, sprite);
            }
            sprite.setX((int) x.getPosition().x);
            sprite.setY((int) x.getPosition().y);
        });
        var us  = Arrays.stream(obsGame.getUnits()).flatMap(Collection::stream).collect(Collectors.toList());
        for (Unit o1 : sprites.keySet().stream().filter(u -> !us.contains(u)).collect(Collectors.toList())) {
            this.removeSprite(o1);
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (var s :
                sprites.entrySet()) {
            s.getValue().paint(g);
        }
    }
}
