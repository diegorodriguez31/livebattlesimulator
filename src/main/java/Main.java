import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(2);
        var unit1a = new Unit(new Vector2(0,0), 100,0, 5, 20, 2, 20);
        var unit1b = new Unit(new Vector2(100,0), 100,0, 5, 20, 2, 20);
        var unit1c = new Unit(new Vector2(150,0), 100,0, 5, 20, 2, 20);
        var unit1d = new Unit(new Vector2(200,0), 100,0, 5, 20, 2, 20);
        var unit1e = new Unit(new Vector2(300,0), 100,0, 5, 20, 2, 20);
        var unit2a = new Unit(new Vector2(200,200), 200,1, 12, 100, 1, 10);
        var unit2b = new Unit(new Vector2(100,200), 200,1, 12, 100, 1, 10);
        var unit2d = new Unit(new Vector2(150,200), 200,1, 12, 100, 1, 10);
        var unit2c = new Unit(new Vector2(10,200), 200,1, 12, 100, 1, 10);
        g.addUnit(unit1a);
        g.addUnit(unit1b);
        g.addUnit(unit1c);
        g.addUnit(unit1d);
        g.addUnit(unit1e);
        g.addUnit(unit2a);
        g.addUnit(unit2b);
        g.addUnit(unit2c);
        g.addUnit(unit2d);

        JFrame frame = new JFrame();
        var v = new View();
        frame.add(v);
        g.addObserver(v);
        v.setVisible(true);
        frame.pack();
        frame.setSize(new Dimension(500,500));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        g.start();
    }
}
