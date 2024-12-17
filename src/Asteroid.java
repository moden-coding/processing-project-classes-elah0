import processing.core.PApplet;
import processing.core.PImage;


public class Asteroid {
    private float x;
    private float y;
    private int size;
    private PApplet canvas;
    private float yspeed;
    private float xspeed;
    private float speed;
   

    
    
    private PImage asteroidImage;
    

    public Asteroid(float xPos, float yPos, PApplet c, PImage img, float shipX, float shipY){
        x=xPos;
        y=yPos;
        size=100;
        canvas=c;
        speed=15;
        
        float distancex = x-shipX;
        float distancey =y-shipY;
        //distancex/distancey= xspeed/yspeed;
        
        //speed^2 = xspeed^2 + yspeed^2;
        //speed^2 = yspeed*yspeed*distancex*distancey/(distancey*distancey)+ yspeed^2;
        //speed^2 =yspeed^2*(distancex*distancex/distancey*distancey+1);
        //yspeed^2= speed*speed/(distancex*distancex/distancey*distancey+1);
        yspeed= canvas.sqrt(speed*speed/(distancey/distancex*distancex/distancex+1));
        xspeed = yspeed*distancex/distancey;
        //xspeed = canvas.random(1, 2);
       // yspeed = (distancey * xspeed) / distancex;
       
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
    public void update(float dx, float dy){
        x+= xspeed;
        y += yspeed;
        if (x < 0 || x > canvas.width - size) {
            xspeed = -xspeed;
        }
        if (y < 0 || y > canvas.height - size) {
            yspeed = -yspeed;
        }
        
    }
    public void reset(float shipX, float shipY) {
        x = canvas.random(0, canvas.width - size);
        y = -size;
        
        int side = (int) canvas.random(4);
        System.out.println(side);
        if (side == 0) { // Top
            x = canvas.random(0, canvas.width - size);
            y = -size;
        } else if (side == 1) { // Bottom
            x = canvas.random(0, canvas.width - size);
            y = canvas.height + size;
        } else if (side == 2) { // Left
            x = -size;
            y = canvas.random(0, canvas.height - size);
        } else { // Right
            x = canvas.width + size;
            y = canvas.random(0, canvas.height - size);
        }


        
        //xspeed = canvas.random(1, 2);  
        //float distancex = shipX - x;
        //float distancey = shipY - y;

        
       // yspeed = (distancey * xspeed) / distancex;

        
       // if (distancex < 0) {
       //     xspeed = -xspeed;
       // }
       // if (distancey < 0) {
       //yspeed = -yspeed;
       // }
       
       
        
       
       
        
        }
        
    public float returnY(){
        return y;
    }
    public float returnX() {
        return x;
    }
    
}


