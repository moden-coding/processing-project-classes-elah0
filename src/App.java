import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet {
    PImage RocketShipImg;
    PImage asteroidImg;
    float RocketX;
    float RocketY;
    float Rocketspeed = 2;
    boolean moveLeft = false;
    boolean moveRight = false;
    boolean moveUp = false;
    boolean moveDown = false;
    PImage backgroundImage;
    float playX1 = 390; // Left point
    float playY1 = 400;
    float playX2 = 390; // Bottom point
    float playY2 = 220;
    float playX3 = 550; // Right point
    float playY3 = 290;
    int scene = 0; // 0 = Start Scene, 1 = Game Scene, 2 = Game Over Scene
    int count = 0;
    int lr = 1; // is char facing left or right, 1 is right, -1 is left
    int ud = 1;

   
    

    ArrayList<Asteroid> asteroids;
    ArrayList<Bullet> bullets;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        asteroids = new ArrayList<>();
        bullets = new ArrayList<>();
        RocketShipImg = loadImage("rocketShip.png");
        asteroidImg = loadImage("asteroid.png");
        backgroundImage = loadImage("background1.jpg");
        RocketX = width / 2;
        RocketY = height - 100;
    }

    public void settings() {
        size(900, 800);
    }

    public void draw() {
        if (scene ==0){
            StartScreen(); //call the start screen method which display a play button for users
        }
        if (scene == 1) {
        image(backgroundImage, 0, 0, width, height);
        image(RocketShipImg, RocketX, RocketY, 40, 70);
        if (moveLeft) {
            RocketX -= Rocketspeed;
        }
        if (moveRight) {
            RocketX += Rocketspeed;
        }
        if (moveUp) {
            RocketY -= Rocketspeed;
        }
        if (moveDown) {
            RocketY += Rocketspeed;
        }
        RocketX = constrain(RocketX, 0, width - 40);
        RocketY = constrain(RocketY, 0, height - 70);

        if (random(1) < 0.005) {
            Asteroid asteroid = new Asteroid(random(width), random(-200, -50), this, asteroidImg, RocketX, RocketY);
            asteroids.add(asteroid);

        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<>();

        for (Asteroid a : asteroids) {
            a.display();
            a.update();
            if (asteroidsHitsSpaceship(a.returnX(), a.returnY())) {
                a.reset(RocketX, RocketY);

            }

        }
        for (Bullet b : bullets) {
            b.display();
            b.update();

            for (Asteroid a : asteroids) {
                if (bulletsHitsAsteroid(b.returnX(), b.returnY(), a.returnX(), a.returnY())) {
                    bulletsToRemove.add(b);
                    asteroidsToRemove.add(a);
                    count++;
                    break;
                }

            }

        }
        bullets.removeAll(bulletsToRemove);
        asteroids.removeAll(asteroidsToRemove);
        rocket();
    }

    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            moveLeft = true;
            lr=-1;
            ud=0;
        } else if (keyCode == RIGHT) {
            moveRight = true;
            lr=1;
            ud=0;
        } else if (keyCode == UP) {
            moveUp = true;
            ud=1; 
            lr=0;
        } else if (keyCode == DOWN) {
            moveDown = true;
            ud=-1;
            lr=0;
            
        }

        if (keyCode == ' ') {
        float bulletX = RocketX + 15; // Default X for bullets
        float bulletY = RocketY;
        float bulletAngle =0;

        if (ud == 1) { // Facing up
            bulletY = RocketY - 10;
            bulletAngle = -PI / 2;
        } else if (ud == -1) { // Facing down
            bulletY = RocketY + 70;
            bulletAngle = PI / 2;
        } else if (lr == 1) { // Facing right
            bulletX = RocketX + 40;
            bulletY = RocketY + 30;
            bulletAngle = 0;
        } else if (lr == -1) { // Facing left
            bulletX = RocketX - 30;
            bulletY = RocketY + 30;
            bulletAngle = PI;
        }
            Bullet bullet1 = new Bullet(5, this, 5, RocketX, RocketY, bulletAngle);
            bullets.add(bullet1);
        }
    

    }

    public void keyReleased() {
        if (keyCode == LEFT) {
            moveLeft = false;
        } else if (keyCode == RIGHT) {
            moveRight = false;
        } else if (keyCode == UP) {
            moveUp = false;
        } else if (keyCode == DOWN) {
            moveDown = false;
        }
    }

    public boolean asteroidsHitsSpaceship(float asteroidX, float asteroidY) { // copied this from my previous game but
                                                                              // edited it to work in this game

        return RocketX + 40 > asteroidX && RocketX < asteroidX + 100 &&
                RocketY + 70 > asteroidY && RocketY < asteroidY + 100;
    }
    public boolean bulletsHitsAsteroid(float bulletX, float bulletY, float asteroidX, float asteroidY) {
        return bulletX + 5 > asteroidX && bulletX - 5 < asteroidX + 100 &&
                bulletY > asteroidY && bulletY < asteroidY + 100;
    }
    public void StartScreen(){
        background(0);
        textSize(90);
        textAlign(CENTER, TOP);
        fill(255, 255, 255);
        text("Welcome to Asteroids!", width / 2, 20);
        fill(178, 101, 255);
        triangle(playX1, playY1, playX2, playY2, playX3, playY3);
        textSize(70);
        textAlign(CENTER, CENTER);
        fill(178, 102, 255);
        text("Play", (playX1 + playX3) / 2, playY1 + 30);
        textAlign(LEFT, LEFT);
        
        fill(255, 255, 255);
        textSize(45);
        text("Shoot at the asteroids to save yourself!", 100, 190);
       
    }
    public void mousePressed() {
        if (scene == 0 && mouseInButton()) {
            scene = 1; // Start the game when play button is clicked
        }
    }
    boolean mouseInButton () {
        //Detect if the play button is clicked by checking the color
        if(get(mouseX, mouseY) == color(178,101,255)){
            System.out.println("you found the color");
            return true;
        }
        return false;
        
    }
    public void rocket(){
        noStroke();
        fill(255, 0, 0); // Color for the gun
        if (ud == 1) {
            rect(RocketX + 15, RocketY -10, 5, -30);
        } else if (ud == -1) {
            rect(RocketX + 15, RocketY + 70, 5, 30);

        } else if (ud == 0) {
            rect(RocketX + 5, RocketY + 20, 30 * lr, 5);
        }
        else if (lr == 1) { // Facing right
            rect(RocketX + 40, RocketY + 30, 30, 5);
        } else if (lr == -1) { // Facing left
            rect(RocketX - 30, RocketY + 30, 30, 5);
        }
    }

    }

    

