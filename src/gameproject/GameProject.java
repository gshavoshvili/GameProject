/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class GameProject extends Application {

    enum Direction {
        LEFT, RIGHT, NONE
    }

    Map<String, Boolean> inputMap = new HashMap<>();
    GraphicsContext gc;

    final void initInputs() {
        inputMap.put("W", false);
        inputMap.put("A", false);
        inputMap.put("S", false);
        inputMap.put("D", false);
        inputMap.put("SPACE", false);
        inputMap.put("LMB", false);
        inputMap.put("DIGIT1", false);
        inputMap.put("DIGIT2", false);
    }


    public MainCharacter hero = new MainCharacter(this, new Vector(285, 20), 30, 30);
    Platform[] platforms = new Platform[]{
        new Platform(this, new Vector(90, 250), 32, 32, new GrassTile()),
        new Platform(this, new Vector(300, 360), 90, 30, new GrassTile()),
        new Platform(this, new Vector(360, 300), 90, 30, new GrassTile()),
        new Platform(this, new Vector(250, 120), 90, 30, new GrassTile())};

    public ArrayList<Enemy> enemies = new ArrayList<>();
    final void initEnemies(){
        enemies.add(new Enemy(this, new Vector(150, 250), 32, 32));
    };

    public ArrayList<Bullet> bullets = new ArrayList<>();

    //Tile tile
//    TerrainGenerator tg = new TerrainGenerator();

    final double CANVAS_WIDTH = 600;
    Background bck = new Background();

    int cameraOffset = 0;

    public GameProject() {
        super();
        initInputs();
        initEnemies();
        initPlatforms();
        TerrainGenerator.drawFromString(this, gc, "gggggggggggggggggggg/!dddddddddddddddddddddd", 0, 332);

    }

    public void update(long delta) {

        hero.update(delta);

        // Using iterator directly to avoid ConcurrentModificationException

        Iterator<Bullet> iter = bullets.iterator();

        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            bullet.update(delta);
            if (bullet.shouldDestroy)
                iter.remove();
        }

        for (Enemy enemy : enemies) {
            enemy.update(delta);
        }



        // camera offset
        cameraOffset = (int) hero.position.x - 285;
        if (cameraOffset < 0) {
            cameraOffset = 0;
        }

    }



    public void render(long delta) {
        gc.clearRect(0, 0, 600, 400);
        bck.drawBackground(gc);


        hero.render(gc);
        for (Platform platform : platforms) {
            platform.render(gc);
        }
        for (Enemy enemy : enemies) {
            enemy.render(gc);
        }
        for (Bullet bullet : bullets) {
            bullet.render(gc);
        }


        //tile.DrawTile(gc);

    }

    public void onMouseMove(MouseEvent e) {
        // Move the method out to use for move AND drag

            // TODO let angle be from hero center, not corner
            // TODO angle doesn't recalculate on jump or move

            // don't forget camera offset for mouse position
            hero.mousePositionOnScreen = new Vector(e.getX(), e.getY());
            hero.calculateMouseAngle();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXML_test.fxml"));

        Scene scene = new Scene(root, CANVAS_WIDTH, 400);

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

        canvas.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            System.out.println(code + " released");
            inputMap.replace(code, Boolean.FALSE);
        });

        canvas.setOnMouseMoved((MouseEvent e)-> {
            onMouseMove(e);
        });
        canvas.setOnMouseDragged((MouseEvent e)-> {
            onMouseMove(e);
        });


        canvas.setOnMousePressed((MouseEvent e) -> {
            //TODO maybe keep momentum
            if (e.getButton() == MouseButton.PRIMARY) {
                inputMap.put("LMB", true);
            }

        });

        canvas.setOnMouseReleased((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                inputMap.put("LMB", false);
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
