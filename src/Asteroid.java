import processing.core.PApplet;
import processing.core.PImage;


public class Asteroid {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private float speed;
    private int health;
    private int color;
    private PImage asteroidImage;
    

    public Asteroid(int xPos, int yPos, PApplet c, PImage img){
        x=xPos;
        y=yPos;
        size=100;
        canvas=c;
        speed = canvas.random(2, 5);
        health =3;
        color=canvas.color(255, 5, 10);
        asteroidImage = img;

    }
    public void display(){
        if(health == 2){
            color=canvas.color(100, 255, 0);
        }
        else if (health==1){
            color=canvas.color(255, 0, 0);
        }
        canvas.fill(color);
        canvas.image(asteroidImage, x, y, size, size);
       
    }
    public void update(){
        y += speed;
        
    }
    public void reset() {
        x = (int)canvas.random(canvas.width - 100);
        y = (int)canvas.random(-200, -50);
        speed = canvas.random(2, 5);  
        health = 3;  
        color = canvas.color(255, 5, 10); 
    }
    public int returnY(){
        return y;
    }
}

