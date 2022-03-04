/*  -------------------------------------------------------
 *  main.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Course:	 COSC3117
 *  ID:      169657520
 *  Email:   mferlaino@algomau.ca
 *  Date:	 Nov. 27, 2019
 *  ------------------------------------------------------- */

// Imports
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        God geneticEngine = new God();
        MarioGame game = new MarioGame();
        Human supremeHuman = geneticEngine.findOptimalHuman();


        System.out.println("Moves Made: " + supremeHuman.getDirections().size());
        System.out.println("Optimal Map: ");
        game.print(supremeHuman.getMap());

        System.out.println("Directions: ");
        ArrayList<String> directions = supremeHuman.getDirections();
        for (String direction : directions)
            System.out.print(direction + " --> ");
        System.out.println("exit");
    }
}
