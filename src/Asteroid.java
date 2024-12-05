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
        xspeed = canvas.random(-2, 2);
        
        
        asteroidImage = img;

    }
    public void display(){
        canvas.image(asteroidImage, x, y, size, size);
       
    }
    public void update(){
        x+= xspeed;
        y += yspeed;
        
    }
    public void reset() {
        int side = (int) canvas.random(4);
        if (side == 0) {             
            x = canvas.random(canvas.width);
            y = -50;
        } else if (side == 1) {      
            x = canvas.random(canvas.width);
            y = canvas.height + 50;
        } else if (side == 2) {      
            x = -50;
            y = canvas.random(canvas.height);
        } else {                    
            x = canvas.width + 50;
            y = canvas.random(canvas.height);
        }

        xspeed = canvas.random(-2, 2);
        yspeed = canvas.random(-2, 2);
        }
        
    public float returnY(){
        return y;
    }
    public float returnX() {
        return x;
    }
    
}


