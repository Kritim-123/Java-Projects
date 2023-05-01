import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.scene.text.Font;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Author : Kritim Bastola
 */


public class Words {
    // Pane (https://openjfx.io/javadoc/18/javafx.graphics/javafx/scene/layout/Pane.html)
    // which represents the floating words part of the game
    private final Pane wordsPane;
    // List of all available words
    private final List<String> words;
    // List of all JavaFX floating words currently on the screen
    private final List<WordBox> activeWords;
    // List of all keys that have been pressed since the last correct word
    private final List<KeyCode> typed;
    // JavaFX Label which shows the score on the screen
    private final Label scoreLabel;
    // Keeps track of the number of correct words
    private int score = 0;
    // JavaFX Label which shows what the user has typed since the last correct word
    private final Label typedLabel;
    // Width/height of the screen
    private final double width;
    private final double height;

    public Timeline timeline;



    public Words(String path, double width, double height,
                 Label scoreLabel, Label typedLabel) throws FileNotFoundException {
        wordsPane = new Pane();
        wordsPane.setPrefWidth(width);
        wordsPane.setPrefHeight(height);

        this.words = Utils.readWords(path);

        activeWords = new ArrayList<>();
        typed = new ArrayList<>();

        this.scoreLabel = scoreLabel;
        this.typedLabel = typedLabel;

        this.width = width;
        this.height = height;

        this.timeline = new Timeline();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Pane getWordsPane() {
        return wordsPane;
    }

    public WordBox getFirstActiveWord(){
        return activeWords.get(0);
    }


    /**
     * Removes the wordBox from the wordsPane as well as
     * removing it from activeWords.
     * @param wordBox WordBox to remove
     * Makes the word fade away (Extra-credit work)
     */
    public void removeWord(WordBox wordBox) {


        Label label = wordBox.getLabel();
        Pane wordsPane = (Pane) label.getParent();



        FadeTransition ft = new FadeTransition(Duration.seconds(1), label);


        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);


        ft.setOnFinished(event -> {
            wordsPane.getChildren().remove(label);
            activeWords.remove(wordBox);
        });


        ft.play();


    }

    /**
     * Creates a random floating word.
     * Choses a random word from the list of words.
     * Then chooses a starting point on any edge of the screen.
     * Then creates a Timeline (https://openjfx.io/javadoc/18/javafx.graphics/javafx/animation/Timeline.html)
     * that moves the WordBox from its starting point to a random ending
     * point over 10 seconds.
     *
     * Makes the word move in circle. (Extra-credit work)
     */
    public void createWord() {

        String word = words.get((int) (Math.random() * words.size())); //Random word

        WordBox newWord = new WordBox(0, word, Color.WHITE);


        activeWords.add(newWord);

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 16);

        Label label = newWord.getLabel();


        label.setFont(font);



        wordsPane.getChildren().add(label); // added the word to wordsPane


        //-----------making button so that user can choose how long a word will last on screen---


//        ComboBox<Integer> comboBoxDuration = new ComboBox<>();
//        comboBoxDuration.getItems().addAll(1, 2, 3, 4);
//        comboBoxDuration.setValue(10);
//
//        AtomicInteger selectedValueDuration = new AtomicInteger(10);
//
//        comboBoxDuration.setOnAction(event -> {
//            selectedValueDuration.set(comboBoxDuration.getValue());
//        });
//
//        Label durationOfWord = new Label(" Cycle Duration");
//        durationOfWord.setFont(new Font(15));
//        VBox timeBox = new VBox(durationOfWord, comboBoxDuration);
//        wordsPane.getChildren().add(timeBox);



    //----------------------------------------
        // To make the word go in circular motion


        double centerX = width / 2;
        double centerY = height / 2;
        double radius = 100;

//        Circle circle = new Circle(centerX, centerY, radius);


        double angle = 0;




        Duration duration = Duration.seconds( 0.5);


        KeyValue keyValueX = new KeyValue(label.layoutXProperty(), centerX + radius);
        KeyValue keyValueY = new KeyValue(label.layoutYProperty(), centerY);
        KeyFrame keyFrame = new KeyFrame(duration, keyValueX, keyValueY);


        timeline.getKeyFrames().add(keyFrame);


        for (int i = 1; i <= 60; i++) {
            angle += Math.PI / 30;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            KeyValue keyValueX1 = new KeyValue(label.layoutXProperty(), x);
            KeyValue keyValueY1 = new KeyValue(label.layoutYProperty(), y);
            KeyFrame keyFrame1 = new KeyFrame(duration.multiply(i), keyValueX1, keyValueY1);
            timeline.getKeyFrames().add(keyFrame1);
        }

        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
//        timeline.setOnFinished(event -> removeWord(getFirstActiveWord()));


        timeline.play();


    }


    /**
     * Adds the keyCode to typed if it is a letter key.
     * Removes the first element of typed if it is the backspace key.
     * Either way it checks for a correct word and updates the typedLabel.
     * @param keyCode KeyCode to add to the state
     */
    public void addTypedLetter(KeyCode keyCode) {

        if(keyCode == KeyCode.BACK_SPACE && typed.size()!= 0){
            typed.remove(typed.size() -1);
        }
        else if (keyCode!= KeyCode.BACK_SPACE){
            typed.add(keyCode);
        }

        String codes = "";


        for(KeyCode keycode: typed){
                codes+= keycode.toString();
        }

        typedLabel.setText(codes);


    }

    /**
     * Checks if the given String is equal to any of the currently
     * active words. If it is then it updates the score and scoreLabel.
     * It also removes the wordBox and clears the typed list.
     *
     */

    public void  checkForCorrectWord(){

        StringBuilder theTyped = new StringBuilder();

        for(KeyCode c: typed){
            theTyped.append(c.getChar());
        }


        for(WordBox word: activeWords){


            if(word.getWord().equalsIgnoreCase(theTyped.toString())){

                typed.clear();
                score++;
                removeWord(word);
                break;
            }
        }

        scoreLabel.setText(String.valueOf(score));
    }

    /**
     * End the games
     * Shows "Game over" and Score
     */
    public void endGame(long startTime, long stopTime){
        wordsPane.getChildren().clear();
        Label finalScore = new Label("Score: "+ score);

        finalScore.setLayoutX(450);
        Font scoreFont = new Font("MRK Maston Pro", 20);
        finalScore.setFont(scoreFont);

        wordsPane.getChildren().add(finalScore);

        activeWords.clear();
        typed.clear();
        Label gameOver = new Label("GAME OVER!");
        Font font = new Font("Times New Roman", 60);
        gameOver.setFont(font);
        gameOver.setLayoutX(50);
        gameOver.setLayoutY(50);
        wordsPane.getChildren().add(gameOver);



        double timeDuration = (stopTime - startTime)/ 60000.00;


        double speed = (double) score / timeDuration;

        double roundedNum = Double.parseDouble(String.format("%.2f", speed));

        Label Speed = new Label();
        Speed.setText("Your speed is: "+ roundedNum+ " words per minute.");
        Speed.setFont(new Font(20));
        Speed.setLayoutY(250);
        Speed.setLayoutX(200);
        wordsPane.getChildren().add(Speed);

    }

//    public void timeToRemove(long startTime, long endTime, int duration){
//      float time = (float) (endTime - startTime) / 1000;
//
//      if(time >= duration){
//          removeWord(getFirstActiveWord());
//      }
//
//    }
}
