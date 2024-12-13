import processing.core.PApplet;
import processing.core.PImage;


public class Asteroid {
    private float x;
    private float y;
    private int size;
    private PApplet canvas;
    private float yspeed;
    private float xspeed;
    
    
    private PImage asteroidImage;
    

    public Asteroid(float xPos, float yPos, PApplet c, PImage img, float shipX, float shipY){
        x=xPos;
        y=yPos;
        size=100;
        canvas=c;
        
        float distancex = shipX - x;
        float distancey =shipY-y;
        xspeed = canvas.random(1, 2);
        yspeed = (distancey * xspeed) / distancex;
       
        if (distancex <0){
            xspeed =-xspeed;
        }
        if (distancey <0){
            yspeed =-yspeed;
        }
        
        asteroidImage = img;

    }
    public void display(){
        canvas.image(asteroidImage, x, y, size, size);
       
    }
    public void update(){
        x+= xspeed;
        y += yspeed;
        if (y < 0) {  
            y=0;
            yspeed = -yspeed;  
        }
        if (x < 0 || x > canvas.width) { 
            xspeed = -xspeed;  
        }
        if (y > canvas.height) { 
            yspeed = -yspeed; 
        }
        
        
    }
    public void reset(float shipX, float shipY) {
        x = canvas.random(0, canvas.width - size);
        y = -size;
        
        int side = (int) canvas.random(4);
        System.out.println(side);
        if (side == 0) {
            x = canvas.random(0, canvas.width - size);
            y = canvas.height;
        } else if (side == 1) {
            x = canvas.random(0, canvas.width - size);
            y = canvas.height;
        } else if (side == 2) {
            x = canvas.width;
            y = canvas.random(0, canvas.height - size);
        } else {
            x = canvas.width;
            y = canvas.random(0, canvas.height - size);
        }

        
        xspeed = canvas.random(1, 2);  
        float distancex = shipX - x;
        float distancey = shipY - y;

        
        yspeed = (distancey * xspeed) / distancex;

        
        if (distancex < 0) {
            xspeed = -xspeed;
        }
        if (distancey < 0) {
            yspeed = -yspeed;
        }
       
       
        
        }
        
    public float returnY(){
        return y;
    }
    public float returnX() {
        return x;
    }
    
}


