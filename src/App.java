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
    float playX1 = 360; // Left point
    float playY1 = 350;
    float playX2 = 360; // Bottom point
    float playY2 = 250;
    float playX3 = 440; // Right point
    float playY3 = 300;
    int scene = 0; // 0 = Start Scene, 1 = Game Scene, 2 = Game Over Scene
    int count = 0;

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

        if (random(1) < 0.01) {
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
                    break;
                }

            }

        }
        bullets.removeAll(bulletsToRemove);
        asteroids.removeAll(asteroidsToRemove);


    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            moveLeft = true;
        } else if (keyCode == RIGHT) {
            moveRight = true;
        } else if (keyCode == UP) {
            moveUp = true;
        } else if (keyCode == DOWN) {
            moveDown = true;
        }

        if (keyCode == ' ') {
            Bullet bullet1 = new Bullet(5, this, 5, RocketX, RocketY);
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
        textSize(60);
        textAlign(CENTER, TOP);
        text("Welcome to Asteroids!", width / 2, 20);
        fill(0,0,102);
        triangle(playX1, playY1, playX2, playY2, playX3, playY3);
        textSize(50);
        textAlign(CENTER, CENTER);
        fill(0,0,101);
        text("Play", (playX1 + playX3) / 2, playY1 + 30);
        textAlign(LEFT, LEFT);
        
        fill(0);
        textSize(18);
        text("Shoot at the asteroids to save yourself!", 110, 150);
       
    }

    }

    

