/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.InputMap;

/**
 *
 * @author User
 */
public class GameProject extends Application {

    int x = 20;
    int y = 20;

    Map<String, Boolean> inputMap = new HashMap<>();

    void initInputs() {
        inputMap.put("W", false);
        inputMap.put("A", false);
        inputMap.put("S", false);
        inputMap.put("D", false);
    }

    public GameProject() {
        super();
        initInputs();
    }

    public void update(long delta) {
        if (inputMap.get("W")) {
            y--;
        }
        if (inputMap.get("A")) {
            x--;
        }
        if (inputMap.get("S")) {
            y++;
        }
        if (inputMap.get("D")) {
            x++;
        }
    }

    GraphicsContext gc;

    public void render(long delta) {
        gc.clearRect(0, 0, 600, 400);
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, 30, 30);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_test.fxml"));

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

        Canvas canvas = (Canvas) scene.lookup("#canvas");
        gc = canvas.getGraphicsContext2D();

        canvas.requestFocus();
        canvas.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                // only add once... prevent duplicates
                System.out.println(code + " pressed");
                inputMap.replace(code, Boolean.TRUE);

            }
        }
        );

        canvas.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                System.out.println(code + " released");
                inputMap.replace(code, Boolean.FALSE);

            }
        });

        AnimationTimer animator = new AnimationTimer() {

            @Override
            public void handle(long arg0) {

                update(arg0);

                render(arg0);

            }
        };

        animator.start();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
