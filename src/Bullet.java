import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
    private int size;
    private float speed;
    private PApplet canvas;
    private float x;
    private float y;
    private float angle;
    

    public Bullet(int size, PApplet c, float speed, float xPos, float yPos, float angle){
        this.size=10;
        canvas=c;
        this.speed = 5;
        this.x=xPos +15;
        y=yPos;
        this.angle=angle;
       
        
        
        
        
        
        

    }
    public void display(){
        canvas.fill(224, 224, 224);
        canvas.rect(x-size/2, y, size/2, size/2);
       
    }
    public void update(){
        x += canvas.cos(angle) * speed;
        y += canvas.sin(angle) * speed;
    }
    public float returnY(){
        return y;
    }
    public float returnX() {
        return x;
    }
}
