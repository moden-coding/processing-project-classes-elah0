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
    
    ArrayList<Asteroid> asteroids;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        asteroids = new ArrayList<>();
        RocketShipImg = loadImage("rocketShip.png");
        asteroidImg = loadImage("asteroid.png");
        RocketX = width / 2; 
        RocketY = height - 100;
    }

    public void settings(){
        size(900, 800);
    }

    public void draw(){
        background(0);
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

        if (random(1) < 0.04 && asteroids.size() <10) {
            float x, y;
            int edge = (int) random(4);
            
        }


      
    
        
        
        for(Asteroid a: asteroids){
            a.display();
            a.update();
            if (a.returnY() > height){
                a.reset();

            }
           
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

        
            if(key == ' '){
                
                    int x= (int)random(width -100);
                    int y=(int)random(-200, -50);
    
                    Asteroid asteroid=new Asteroid (x, y, this, asteroidImg);
                    asteroids.add(asteroid);
                
                
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

}
