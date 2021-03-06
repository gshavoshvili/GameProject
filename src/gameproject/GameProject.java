/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import gameproject.enemies.Enemy;
import gameproject.enemies.OrangeEnemy;
import gameproject.enemies.RedEnemy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.util.Duration;

/**
 *
 * @author User
 */
public class GameProject extends Application {

    public enum Direction {
        LEFT, RIGHT, NONE
    }
    
    public enum GameState {
        RUNNING, DIED, WON
    }
    
    public GameState state = GameState.RUNNING;

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
    public ArrayList<Platform> platforms = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();


    public ArrayList<Bullet> bullets = new ArrayList<>();
    public boolean audioPlaying = false;

    public final double CANVAS_WIDTH = 600;
    Background bck = new Background();
    
    String musicFile;
        
    Media sound;
    MediaPlayer mediaPlayer;
        

    BufferedImage winImg;
    BufferedImage deathImg;
    
    Image winScreen;
    Image deathScreen;
    
    public int cameraOffset = 0;

    public GameProject() throws IOException {
        super();
        initInputs();
        TerrainGenerator.drawFromString(this, gc, 
                  "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------/"
                + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------/"
                + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------/" 
                + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------/"
                + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------/"
                + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------/"
                + "---------------------------------------------------------gggggg----------------------------------------------------------------------g----------------------d------------/"
                + "--------------------------------------------------gggg-------------------------------------------------r---------r-------------------d----------------------d------------/"
                + "------------------------------------------ggggg--------------------------------------------------------gggg---gggg-----------------g-d----------------------d------------/"
                + "------------------------r---------------------------------------------------r----------------------------------------r-----------g-d-d------------------o---d------------/"
                + "gggggggggggggggggggggggggggggggggggggggggg-----------------gggggg-------gggggggggggggggggggggggggggggg---------------gggggggggggggggggggggggggggggggggggggggggggggggggggg/"
                + "dddddddddddddddddddddddddddddddddddddddddd---------------------------dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
                , 0, 400 - 12*32);

        winImg = ImageIO.read(getClass().getResourceAsStream("resources/Win.png"));
        deathImg = ImageIO.read(getClass().getResourceAsStream("resources/You-Died.jpg"));
        
        winScreen = SwingFXUtils.toFXImage(winImg, null);
        deathScreen = SwingFXUtils.toFXImage(deathImg, null);
        
        musicFile = "src/GameProject/resources/testYourMight.mp3";
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        
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

        if(hero.position.x > 4500 && hero.position.x < 20000){
            if(!audioPlaying){
                mediaPlayer.seek(Duration.millis(14000));
                mediaPlayer.play();
                audioPlaying = true;
            }
            
        }
        
        
    }

    public double alpha = 0;
    public double alphaDeltaDeath = 0.01;
    public double alphaDeltaWin = 0.04;

    public void render(long delta) {
        gc.clearRect(0, 0, 600, 400);
        bck.drawBackground(gc);


        
        for (Platform platform : platforms) {
            platform.render(gc);
        }
        for (Enemy enemy : enemies) {
            enemy.render(gc);
        }
        for (Bullet bullet : bullets) {
            bullet.render(gc);
        }
        
        HealthBar hb = new HealthBar(this, gc);
        
        if ( state != state.DIED ) {
            hero.render(gc);
        }else{
                gc.save();
                gc.setGlobalAlpha(alpha);
                gc.drawImage(deathScreen, 0, 0);
                gc.restore();
                if (alpha < 1) alpha += alphaDeltaDeath;
                
        }
        if( state == state.WON ){
                gc.save();
                gc.setGlobalAlpha(alpha);
                gc.drawImage(winScreen, 0, 0);
                gc.restore();
                if (alpha < 1) alpha += alphaDeltaWin;
        }
        
        
        
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

        stage.setTitle("GameProject");
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
                if (state == GameState.RUNNING){
                    update(arg0);
                }
                
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
