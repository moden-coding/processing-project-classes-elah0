import processing.core.*;
import java.util.ArrayList;



public class App extends PApplet{
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
    
    ArrayList<Asteroid> asteroids;
    ArrayList<Bullet>bullets;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        asteroids = new ArrayList<>();
        bullets  = new ArrayList<>();
        RocketShipImg = loadImage("rocketShip.png");
        asteroidImg = loadImage("asteroid.png");
        backgroundImage = loadImage("background1.jpg");
        RocketX = width / 2; 
        RocketY = height - 100;
    }

    public void settings(){
        size(900, 800);
    }

    public void draw(){
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

        if (random(1) < 0.04) {
            Asteroid asteroid = new Asteroid(random(width), random(-200, -50), this, asteroidImg, RocketX, RocketY);
            asteroids.add(asteroid);
            
          
            
        }


      
    
        
        
        for(Asteroid a: asteroids){
            a.display();
            a.update();
            if (asteroidsHitsSpaceship(a.returnX(), a.returnY())){
                a.reset();

            }
           
        }
        for(Bullet b: bullets){
           b.display();
           b.update();
           
        }
        

    }
    public void keyPressed() {
        if (keyCode == LEFT) {
            moveLeft = true;
        } else if (keyCode == RIGHT) {
            moveRight = true;
        } else if (keyCode == UP) {
            moveUp = true;
        }else if (keyCode == DOWN) {
            moveDown = true;
        }

        if (keyCode == ' '){
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
    public boolean asteroidsHitsSpaceship(float asteroidX , float asteroidY ) { //copied this from my previous game but edited it to work in this game
       
            return RocketX + 40 > asteroidX && RocketX < asteroidX + 100 &&
                   RocketY + 70 > asteroidY && RocketY < asteroidY + 100;
        }
    }

