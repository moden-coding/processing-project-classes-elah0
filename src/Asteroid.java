import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


public class Asteroid {
    private float x;
    private float y;
    private int size;
    private PApplet canvas;
    private float ymove;
    private float xmove;
    private float speed;
    //private PVector position;// current pos.
   // private PVector velocity;
    //private PVector charXY; // pos of player
   // private float selfX = 0;
   // private float selfY = 0;

    

    

   

    
    
    private PImage asteroidImage;
    

    public Asteroid(float xPos, float yPos, PApplet c, PImage img, float shipX, float shipY){
        x=xPos;
        y=yPos;
        size=100;
        canvas=c;
        speed=20;
        //selfX=xPos;
        //selfY=yPos;

        //velocity = new PVector(0, 0);
        //position = new PVector(x, y);
        
        float distancex = shipX-x;

        float distancey =shipY-y;
        System.out.println("ship y" + y);
        System.out.println("distance y " + distancey);
        float distance =canvas.dist(shipX, shipY, x, y);
        xmove=distancex * speed/distance;
        System.out.println("X speed " + xmove);
        ymove=distancey* speed/distance;
        System.out.println("Y speed " + ymove);
        //distancex/distancey= xspeed/yspeed;
        
        //speed^2 = xspeed^2 + yspeed^2;
        //speed^2 = yspeed*yspeed*distancex*distancey/(distancey*distancey)+ yspeed^2;
        //speed^2 =yspeed^2*(distancex*distancex/distancey*distancey+1);
        //yspeed^2= speed*speed/(distancex*distancex/distancey*distancey+1);
       // yspeed= canvas.sqrt(speed*speed/(distancey/distancex*distancex/distancex+1));
        //xspeed = yspeed*distancex/distancey;
        //xspeed = canvas.random(1, 2);
       // yspeed = (distancey * xspeed) / distancex;
       
        //if (distancex <0){
      //      xspeed =-xspeed;
       // }
       // if (distancey <0){
      //      yspeed =-yspeed;
      //  }
        
        asteroidImage = img;

    }
    public void display(){
       canvas.image(asteroidImage, x, y, size, size);
       
   }
    //public void update(float shipX, float shipY){
       // x+= xspeed;
      //  y += yspeed;
      //  if (y < 0) {  
     //     y=0;
       //   yspeed = -yspeed;  
      // }
     //  if (x < 0 || x > canvas.width) { 
      //      xspeed = -xspeed;  
      //  }
      // if (y > canvas.height) { 
       //     yspeed = -yspeed; 
      // }
      // move(shipX, shipY);
        
    //}
    public void reset(float shipX, float shipY, float score) {
        int side = (int) canvas.random(4);

        x = canvas.random(0, canvas.width - size);
        y = -size;
        speed = 5 + score * 5f;
        
       
        System.out.println(side);
        if (side == 0) {
            x = canvas.random(0, canvas.width-size);
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
        //selfX=x;
       // selfY=y;


        
        //xspeed = canvas.random(1, 2);  
        //float distancex = shipX - x;
        //float distancey = shipY - y;

        
       //yspeed = (distancey * xspeed) / distancex;

        
        //if (distancex < 0) {
          // xspeed = -xspeed;
      //  }
       // if (distancey < 0) {
      // yspeed = -yspeed;
      // }
    }
    public void move(){
        x+=xmove;
        y+=ymove;

    }
       
        
       
       
        
        
        
    public float returnY(){
        return y;
    }
    public float returnX() {
        return x;
    }
    //This function was made with  of chatgpt and the help of ari
    //public void move(float charX, float charY) {
        
       // PVector target = new PVector(charX, charY);  
      //  PVector direction = PVector.sub(target, position);  
      //  direction.normalize();  
       // direction.mult(speed);  
      //  velocity.set(direction);
      //  position.add(velocity);     
      //  x = position.x;
       // y = position.y;
        
        

    }






