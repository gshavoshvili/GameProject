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

    

    Map<String, Boolean> inputMap = new HashMap<>();

    void initInputs() {
        inputMap.put("W", false);
        inputMap.put("A", false);
        inputMap.put("S", false);
        inputMap.put("D", false);
        inputMap.put("SPACE", false);
    }
    
    MainCharacter hero = new MainCharacter(this);
    Platform[] platforms = new Platform[] {
        new Platform(this,90, 250),
        new Platform(this,300, 360),
        new Platform(this,250, 120),
    };
    
    int cameraOffset = 0;

    public GameProject() {
        super();
        initInputs();
        
    }

    public void update(long delta) {
       
        hero.update(delta);
        
        // camera offset
        cameraOffset = hero.x - 285;
        if(cameraOffset<0) cameraOffset = 0;
        
    }

    GraphicsContext gc;

    public void render(long delta) {
        gc.clearRect(0, 0, 600, 400);
        
        hero.render(gc, delta);
        for (Platform platform : platforms) {
            platform.render(gc,delta);
        }
        
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
