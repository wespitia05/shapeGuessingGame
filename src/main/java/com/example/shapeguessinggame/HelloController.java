/*
    Name: William Espitia
    Class: CSC 311
    Assignment: Homework 2 Guessing Game
 */


package com.example.shapeguessinggame;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import java.sql.Connection;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class HelloController {
    @FXML
    private TextField textFieldTotalGuessCount;
    @FXML
    private TextField textFieldCorrectGuessCount;
    @FXML
    private ListView listViewGuesses;
    @FXML
    private Shape square;
    @FXML
    private Shape circle;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton rectangleRB;
    @FXML
    private RadioButton circleRB;
    @FXML
    private Button guessButton;
    private int totalGuessCount = 0;
    private int correctGuessCount = 0;
    private holdGuessInfo info;

    // This method will be our initialize method
    public void initialize () {
        System.out.println ("Initialize called");
        // get the Observable List Reference
        ObservableList<String> items = listViewGuesses.getItems();
        // set tg as our new ToggleGroup
        tg = new ToggleGroup();
        // place rectangle and circle radio buttons under tg ToggleGroup
        rectangleRB.setToggleGroup(tg);
        circleRB.setToggleGroup(tg);
        // assigns holdGuessInfo to "info" with three arguments:
        // 0, empty String, empty String
        info = new holdGuessInfo(0, "", "");

        /*
        create database
         */
        /*String dbFilePath = ".//GuessInformation.accdb";
        String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
        File dbFile = new File(dbFilePath);
        if (!dbFile.exists()) {
            try (Database db =
                         DatabaseBuilder.create(Database.FileFormat.V2010, new File(dbFilePath))) {
                System.out.println("The database file has been created.");
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        }
         */
        /*
        create table
         */
        /*try {
            Connection conn = DriverManager.getConnection(databaseURL);
            String sql;
            sql = "CREATE TABLE GuessInformation ([Guess Number] INT, [Correct Shape] nvarchar(255), [Guessed Shape] nvarchar(255))";
            Statement createTableStatement = conn.createStatement();
            createTableStatement.execute(sql);
            conn.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
         */
    }
    // this method will animate our green square
    public void trueSquareAnimation () {
        // lets us know that the trueSquareAnimation was called
        System.out.println ("trueSquareAnimation called");

        // fillTransition will act on the square shape for a total of 2 seconds
        FillTransition fillTransition =
                new FillTransition(Duration.seconds(2), square);
        // sets the transition color to green
        fillTransition.setToValue(Color.GREEN);
        // cycle will only happen once
        fillTransition.setCycleCount(1);
        // once it reaches green it will go back to gray
        fillTransition.setAutoReverse(true);

        // translateTransition will move the square shape for a total of 2 seconds
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(2), square);
        // moves the square to the right 100
        translateTransition.setByX(100);
        // cycle will only happen once
        translateTransition.setCycleCount(1);
        // once it reaches position 100 it will come back to position 0
        translateTransition.setAutoReverse(true);

        // fadeTransition will make the square shape go from invisible to visible in 2 seconds
        FadeTransition fadeTransition =
                new FadeTransition(Duration.seconds(2), square);
        // starting fade value is 0.0 (invisible)
        fadeTransition.setFromValue(0.0);
        // ending fade value is 1.0 (visible)
        fadeTransition.setToValue(1.0);
        // cycle will only happen once
        fadeTransition.setCycleCount(1);
        // once it becomes visible it will go back to being invisible
        fadeTransition.setAutoReverse(true);

        // parallelTransition will run all the 3 animations simultaneously
        ParallelTransition parallelTransition = new
                ParallelTransition(fillTransition, translateTransition, fadeTransition);
        // cycle will happen twice (once there once back)
        parallelTransition.setCycleCount(2);
        // once all 3 animations reach their peak, they will go back to their original spot
        parallelTransition.setAutoReverse(true);
        // once the animation is finished then the button will no longer be disabled
        parallelTransition.setOnFinished(e -> guessButton.setDisable(false));
        // this plays the animation for all 3 animations
        parallelTransition.play();
    }
    // this method will animate our red square
    public void falseSquareAnimation() {
        // lets us know that the falseSquareAnimation was called
        System.out.println ("falseSquareAnimation called");
        // fillTransition will act on the square shape for a total of 2 seconds
        FillTransition fillTransition =
                new FillTransition(Duration.seconds(2), square);
        // sets the transition color to red
        fillTransition.setToValue(Color.RED);
        // cycle will only happen once
        fillTransition.setCycleCount(1);
        // once it reaches green it will go back to gray
        fillTransition.setAutoReverse(true);

        // translateTransition will move the square shape for a total of 2 seconds
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(2), square);
        // moves the square to the right 100
        translateTransition.setByX(100);
        // cycle will only happen once
        translateTransition.setCycleCount(1);
        // once it reaches position 100 it will come back to position 0
        translateTransition.setAutoReverse(true);

        // fadeTransition will make the square shape go from invisible to visible in 2 seconds
        FadeTransition fadeTransition =
                new FadeTransition(Duration.seconds(2), square);
        // starting fade value is 0.0 (invisible)
        fadeTransition.setFromValue(0.0);
        // ending fade value is 1.0 (visible)
        fadeTransition.setToValue(1.0);
        // cycle will only happen once
        fadeTransition.setCycleCount(1);
        // once it becomes visible it will go back to being invisible
        fadeTransition.setAutoReverse(true);

        // parallelTransition will run all the 3 animations simultaneously
        ParallelTransition parallelTransition = new
                ParallelTransition(fillTransition, translateTransition, fadeTransition);
        // cycle will happen twice (once there once back)
        parallelTransition.setCycleCount(2);
        // once all 3 animations reach their peak, they will go back to their original spot
        parallelTransition.setAutoReverse(true);
        // once the animation is finished then the button will no longer be disabled
        parallelTransition.setOnFinished(e -> guessButton.setDisable(false));
        // this plays the animation for all 3 animations
        parallelTransition.play();
    }
    // this method will animate our green square
    public void trueCircleAnimation () {
        // lets us know that the trueCircleAnimation was called
        System.out.println ("trueCircleAnimation called");
        // fillTransition will act on the circle shape for a total of 2 seconds
        FillTransition fillTransition =
                new FillTransition(Duration.seconds(2), circle);
        // sets the transition color to green
        fillTransition.setToValue(Color.GREEN);
        // cycle will only happen once
        fillTransition.setCycleCount(1);
        // once it reaches green it will go back to gray
        fillTransition.setAutoReverse(true);

        // translateTransition will move the circle shape for a total of 2 seconds
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(2), circle);
        // moves the circle to the right 100
        translateTransition.setByX(100);
        // cycle will only happen once
        translateTransition.setCycleCount(1);
        // once it reaches position 100 it will come back to position 0
        translateTransition.setAutoReverse(true);

        // fadeTransition will make the circle shape go from invisible to visible in 2 seconds
        FadeTransition fadeTransition =
                new FadeTransition(Duration.seconds(2), circle);
        // starting fade value is 0.0 (invisible)
        fadeTransition.setFromValue(0.0);
        // ending fade value is 1.0 (visible)
        fadeTransition.setToValue(1.0);
        // cycle will only happen once
        fadeTransition.setCycleCount(1);
        // once it becomes visible it will go back to being invisible
        fadeTransition.setAutoReverse(true);

        // parallelTransition will run all the 3 animations simultaneously
        ParallelTransition parallelTransition = new
                ParallelTransition(fillTransition, translateTransition, fadeTransition);
        // cycle will happen twice (once there once back)
        parallelTransition.setCycleCount(2);
        // once all 3 animations reach their peak, they will go back to their original spot
        parallelTransition.setAutoReverse(true);
        // once the animation is finished then the button will no longer be disabled
        parallelTransition.setOnFinished(e -> guessButton.setDisable(false));
        // this plays the animation for all 3 animations
        parallelTransition.play();
    }
    // this method will animate our red square
    public void falseCircleAnimation () {
        // lets us know that the falseCircleAnimation was called
        System.out.println ("falseCircleAnimation called");
        // fillTransition will act on the circle shape for a total of 2 seconds
        FillTransition fillTransition =
                new FillTransition(Duration.seconds(2), circle);
        // sets the transition color to red
        fillTransition.setToValue(Color.RED);
        // cycle will only happen once
        fillTransition.setCycleCount(1);
        // once it reaches green it will go back to gray
        fillTransition.setAutoReverse(true);

        // translateTransition will move the circle shape for a total of 2 seconds
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(2), circle);
        // moves the circle to the right 100
        translateTransition.setByX(100);
        // cycle will only happen once
        translateTransition.setCycleCount(1);
        // once it reaches position 100 it will come back to position 0
        translateTransition.setAutoReverse(true);

        // fadeTransition will make the circle shape go from invisible to visible in 2 seconds
        FadeTransition fadeTransition =
                new FadeTransition(Duration.seconds(2), circle);
        // starting fade value is 0.0 (invisible)
        fadeTransition.setFromValue(0.0);
        // ending fade value is 1.0 (visible)
        fadeTransition.setToValue(1.0);
        // cycle will only happen once
        fadeTransition.setCycleCount(1);
        // once it becomes visible it will go back to being invisible
        fadeTransition.setAutoReverse(true);

        // parallelTransition will run all the 3 animations simultaneously
        ParallelTransition parallelTransition = new
                ParallelTransition(fillTransition, translateTransition, fadeTransition);
        // cycle will happen twice (once there once back)
        parallelTransition.setCycleCount(2);
        // once all 3 animations reach their peak, they will go back to their original spot
        parallelTransition.setAutoReverse(true);
        // once the animation is finished then the button will no longer be disabled
        parallelTransition.setOnFinished(e -> guessButton.setDisable(false));
        // this plays the animation for all 3 animations
        parallelTransition.play();
    }
    // this method will randomly select either rectangle or circle as our shape
    private String selectRandomShape () {
        // set ran as the new random method
        Random ran = new Random();
        // integer randomShape will get a randomly generated integer (0 or 1)
        int randomShape = ran.nextInt(2);
        // if the randomly generated integer is 0 then it will return rectangle
        if (randomShape == 0) {
            return "RECTANGLE";
        }
        // if the randomly generated integer is 1 when it will return circle
        else {
            return "CIRCLE";
        }
    }
    // this method will serve multiple purposes as our guess button
    // once the guess button is called it will see if your guess matches the randomly selected shape
    // and proceed with the animation, during the animation the guess button will be disabled and
    // your guess along with the correct guess and number of guesses will be stored each time
    // into the database
    public void handleGuessButton () {
        // tells us that the guess button was pressed
        System.out.println ("handleGuessButton called");
        // name of the database
        String dbFilePath = ".//GuessInformation.accdb";
        // pathway to the database
        String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
        Connection conn = null;
        try {
            // opens the connection to the database
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // set selectedTB as the radio button that will be currently selected
        RadioButton selectedTB = (RadioButton) tg.getSelectedToggle();
        String randomShape = selectRandomShape();
        // if no radio button is selected then a message will appear asking the user to
        // please select a shape before guessing
        if (selectedTB == null) {
            System.out.println("Please select a shape before guessing.");
            return;
        }
        // disables the guess button
        guessButton.setDisable(true);
        boolean guessedCorrectly = false;
        // if the selected radio button equals rectangle and the random shape chosen
        // equals rectangle as well, then the true square animation will be called
        // and the guessedCorrectly variable will set to true
        if (selectedTB.getText().equals("Rectangle") && randomShape.equals("RECTANGLE")) {
            trueSquareAnimation();
            guessedCorrectly = true;
        }
        // if the selected radio button equals circle and the random shape chosen
        // equals circle as well, then the true circle animation will be called
        // and the guessedCorrectly variable will set to true
        else if (selectedTB.getText().equals("Circle") && randomShape.equals("CIRCLE")) {
            trueCircleAnimation();
            guessedCorrectly = true;
        }
        // if the selected radio button equals rectangle and the random shape chosen
        // equals circle, then the false square animation will be called
        // and the guessedCorrectly variable will false
        else if (selectedTB.getText().equals("Rectangle") && randomShape.equals("CIRCLE")) {
            falseSquareAnimation();
            guessedCorrectly = false;
        }
        // if the selected radio button equals circle and the random shape chosen
        // equals rectangle, then the false square animation will be called
        // and the guessedCorrectly variable will false
        else if (selectedTB.getText().equals("Circle") && randomShape.equals("RECTANGLE")) {
            falseCircleAnimation();
            guessedCorrectly = false;
        }
        // increase totalGuessCount by 1
        totalGuessCount++;
        // sets the textFieldTotalGuessCount equal to the number that totalGuessCount is on currently
        textFieldTotalGuessCount.setText(String.valueOf(totalGuessCount));
        // if you guessed correctly then the correctGuessCount will increase by one and set the value
        // of the textFieldCorrectGuessCount equal to the number that correctGuessCount is currently on
        if (guessedCorrectly) {
            correctGuessCount++;
            textFieldCorrectGuessCount.setText(String.valueOf(correctGuessCount));
        }
        // stores totalGuessCount in guessNumber variable
        info.setGuessNumber(totalGuessCount);
        // stores randomShape in correctShape variable
        info.setCorrectShape(randomShape);
        // gets the text of the radio button selected and stores it in the guessedShape variable
        info.setGuessedShape(selectedTB.getText().toUpperCase());
        insertData(conn, info.getGuessNumber(), info.getCorrectShape(), info.getGuessedShape());
        // lets us know that the data was inserted into the database successfully
        System.out.println ("Data inserted into database successfully");
    }
    // action handler for the rectangle radio button
    public void handleRectangleRadioButton () {
        // lets us know that the rectangle radio button was pressed
        System.out.println ("handleRectangleRadioButton called");
    }
    // action handler for the circle radio button
    public void handleCircleRadioButton () {
        // lets us know that the circle radio button was pressed
        System.out.println ("handleCircleRadioButton called");
    }
    // this method will insert the data into the database and be called upon when
    // the guess button is pressed
    public static void insertData(Connection conn, int guessNumber, String correctShape, String guessedShape) {
        // the three ? characters will be filled with data by the preparedStatement class
        String sql = "INSERT INTO GuessInformation ([Guess Number], [Correct Shape], [Guessed Shape]) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            // declares that conn has a valid connection to the database
            preparedStatement = conn.prepareStatement(sql);
            // replaces the first ? in the SQL string with data from the first variable
            preparedStatement.setInt(1, guessNumber);
            // replaces the second ? in the SQL string with data from the second variable
            preparedStatement.setString(2, correctShape);
            // replaces the third ? in the SQL string with data from the third variable
            preparedStatement.setString(3, guessedShape);
            // once all data is inserted, it will execute the preparedStatement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // this method will show the data from the database in the listView
    public void handleShowGuessesFromDBButton () {
        // lets us know that the show guesses from DB button was pressed
        System.out.println ("handleShowGuessFromDBButton called");
        // name of the database
        String dbFilePath = ".//GuessInformation.accdb";
        // pathway to the database
        String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
        Connection conn = null;
        try {
            // opens connection to the database
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            // sets table name equal to GuessInformation
            String tableName = "GuessInformation";
            Statement stmt = conn.createStatement();
            // ResultSet contains all rows of data from the database
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            // loops through the ResultSet
            while (result.next()) {
                // gets data from guess number column and stores in the information
                int guessNumber = result.getInt("Guess Number");
                // gets data from correct shape column and stores the information
                String correctShape = result.getString("Correct Shape");
                // gets data from guessed shape column and stores the information
                String guessedShape = result.getString("Guessed Shape");
                // adds the information into the list view
                listViewGuesses.getItems().add(guessNumber + ", correct: " + correctShape + ", guessed: " + guessedShape);
            }
            // lets the user know the data was successfully displayed from the database
            System.out.println ("Data successfully displayed from the database");
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }
    // this method will close the application when "exit" is pressed from the menu
    public void handleExitMenuItem () {
        // lets us know that the close menu option was pressed
        System.out.println ("handleExitMenuItem called");
        // closes the application
        Platform.exit();
        // lets us know the application was closed successfully
        System.out.println ("Application closed successfully");
    }
    // setter getter method for holding the guess information
    public static class holdGuessInfo {
        private int guessNumber;
        private String correctShape;
        private String guessedShape;
        // guess info constructor with 3 parameters
        public holdGuessInfo (int gn, String cs, String gs) {
            guessNumber = gn;
            correctShape = cs;
            guessedShape = gs;
        }
        // sets guessNumber equal to gn
        public void setGuessNumber (int gn) {
            guessNumber = gn;
        }
        // sets correctShape equal to cs
        public void setCorrectShape (String cs) {
            correctShape = cs;
        }
        // sets guessedShape equal to gs
        public void setGuessedShape (String gs) {
            guessedShape = gs;
        }
        // returns guessNumber
        public int getGuessNumber () {
            return guessNumber;
        }
        // returns correctShape
        public String getCorrectShape () {
            return correctShape;
        }
        // returns correctShape
        public String getGuessedShape () {
            return guessedShape;
        }
    }
    // this method will handle the new game menu item
    // will clear the database, clear the listView,
    // reset the totalGuessCount and correctGuessCount to 0 and
    // set the text field for both back to 0
    public void handleNewGameMenuItem () {
        // lets us know that the new game menu item was pressed
        System.out.println ("handleNewGameMenuItem called");
        // clears the database
        clearDataBase();
        // clears the listView
        listViewGuesses.getItems().clear();
        // resets the totalGuessCount back to 0
        totalGuessCount = 0;
        // sets the textField for the guess count back to 0
        textFieldTotalGuessCount.setText("0");
        // resets the correctGuessCount back to 0
        correctGuessCount = 0;
        // sets the textField for the correct guess count back to 0
        textFieldCorrectGuessCount.setText("0");
    }
    // this method will clear the database when called upon
    public void clearDataBase() {
        // name of the database
        String dbFilePath = ".//GuessInformation.accdb";
        // pathway to the database
        String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
        Connection conn = null;
        try {
            // opens the connection to the database
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // creates SQL string to delete all records from the table
        String sql = "DELETE FROM GuessInformation";
        PreparedStatement preparedStatement = null;
        try {
            // declares that conn has a valid connection to the database
            preparedStatement = conn.prepareStatement(sql);
            // run the delete statement, it will return the number of rows deleted
            int rowsDeleted = preparedStatement.executeUpdate();
            // lets us know in the output box how many rows were deleted
            System.out.println ("Number of rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}