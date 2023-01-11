package Colors;

import java.awt.*;
import java.util.HashMap;

public class RandomColorPick {
     private HashMap<Integer, Color> colorPickup = new HashMap<>();

    public RandomColorPick() {
        colorPickup.put(0,Color.red);
        colorPickup.put(1,Color.black);
        colorPickup.put(2,Color.blue);
        colorPickup.put(3,Color.cyan);
        colorPickup.put(4,Color.darkGray);
        colorPickup.put(5,Color.gray);
        colorPickup.put(6,Color.green);
        colorPickup.put(7,Color.lightGray);
        colorPickup.put(8,Color.magenta);
        colorPickup.put(9,Color.orange);
        colorPickup.put(10,Color.pink);
        colorPickup.put(11,Color.yellow);
        colorPickup.put(12,Color.white);
        colorPickup.put(13,Color.orange);
    }

    public Color getColor(int random) {
        return colorPickup.get(random);
    }
}