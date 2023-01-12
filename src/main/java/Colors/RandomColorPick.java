package Colors;

import java.awt.*;
import java.util.HashMap;

public class RandomColorPick {
     private final static HashMap<Integer, Color> colorPickup = new HashMap<>();

    public static void Colors() {
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

    public static Color getColor(int random) {
        Colors(); //Instantiate the hashmap to not get null error
        return colorPickup.get(random);
    }

}
