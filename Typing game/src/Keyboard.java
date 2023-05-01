import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;

public class Keyboard {
    // 2 Dimensional list representing the rows of keys on the keyboard
    // Letter keys only
    private final List<List<KeyCode>> keyCodes;
    // Map that is used to access the keys JavaFX representation
    private final Map<KeyCode, WordBox> keyCodeToWordBox;
    // JavaFX control that represents the keyboard on the screen
    private final VBox keyboard;
    // Color that the keys are by default
    private static final Color from = Color.color(0.9, 0.9, 0.9);
    // Color that the keys become when pressed
    private static final Color to = Color.color(0.3, 0.3, 0.8);

    public Keyboard(double width, double height, double spacing) {
        keyCodes = initializeKeys();
        keyCodeToWordBox = new HashMap<>();

        keyboard = initializeKeyboard(width, height, keyCodes, spacing);
    }

    public VBox getKeyboard() {
        return keyboard;
    }

    /**
     * First checks if the given keyCode exists in the keyCodeToWordBox.
     * If it does then it starts a FillTransition (  )
     * to go from the from color to the to color.
     * If the keyCode does not exist then it does nothing.
     * @param keyCode KeyCode to lookup in the map and flash
     */
    public void startFillTransition(KeyCode keyCode) {

        if(keyCodeToWordBox.containsKey(keyCode)){

            WordBox wordBox = keyCodeToWordBox.get(keyCode);

            Rectangle rectangle = wordBox.getRect();

            FillTransition transition =  new FillTransition(Duration.seconds(1), rectangle, from, to);
            transition.play();
            FillTransition endtransition = new FillTransition(Duration.seconds(1), rectangle, to, from);
            endtransition.play();


        }

    }

    /**
     * Simply creates the 2D list that represents the keyboard.
     * Each row is an element of the outer list and each inner list
     * contains all the letter keys in that row. Only contains
     * 3 rows. All letters are uppercase.
     * @return 2D list representing the letters on the keyboard
     */
    private List<List<KeyCode>> initializeKeys() {

        List<List<KeyCode>> keyCodes = new ArrayList<>();

        // first row of keys
        List<KeyCode> row1 = Arrays.asList(KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T,
                KeyCode.Y, KeyCode.U, KeyCode.I, KeyCode.O, KeyCode.P);
        keyCodes.add(row1);

        // second row of keys
        List<KeyCode> row2 = Arrays.asList(KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.G,
                KeyCode.H, KeyCode.J, KeyCode.K, KeyCode.L);
        keyCodes.add(row2);

        // third row of keys
        List<KeyCode> row3 = Arrays.asList(KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B,
                KeyCode.N, KeyCode.M);
        keyCodes.add(row3);

        return keyCodes;


    }

    /**
     * Creates the JavaFX control that visualized the keyboard on the screen
     * Also initializes the keyCodeToWordBox map as it goes.
     * It deduces the size of each key using the 2D list and the
     * width parameter. Then creates a VBox and sets its width/height
     * and centers it. Then loops over the 2D list and creates JavaFX
     * controls, WordBox, to represent each key and adds them to HBoxes.
     * The adds the row HBox to the VBox. It also adds the WordBox to the
     * map. Then it moves on to the next row.
     * @param width Width of the screen
     * @param height Height of the screen
     * @param keyCodes 2D list that holds all the letters on the keyboard
     * @param spacing Space between each key
     * @return JavaFX control that visualizes the keyboard on the screen
     */
    private VBox initializeKeyboard(double width, double height, List<List<KeyCode>> keyCodes, double spacing) {

        VBox keyboard = new VBox(spacing);
        keyboard.setAlignment(Pos.CENTER);


        double keyHeight = height / keyCodes.size();

        for(List<KeyCode> keycode : keyCodes){
            HBox horizontalKey = new HBox(spacing);
            horizontalKey.setAlignment(Pos.CENTER);
            for(KeyCode eachKey: keycode){
                WordBox wordBox = new WordBox(keyHeight, eachKey.toString(), from);
                horizontalKey.getChildren().add(wordBox.getWordBox());

                keyCodeToWordBox.put(eachKey, wordBox);

            }

            keyboard.getChildren().add(horizontalKey);
        }

        return keyboard;



    }
}