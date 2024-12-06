import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
    private int size;
    private float speed;
    private PApplet canvas;
    private float x;
    private float y;

    public Bullet(int size, PApplet c, float speed, float xPos, float yPos){
        this.size=10;
        canvas=c;
        speed =5;
        size =5;
        x=xPos;
        y=yPos;
        
        
        
        
        

    }
    public void display(){
        canvas.fill(224, 224, 224);
        canvas.rect(x-size/2, y, size/2, size*2);
    }
    public void update(){
       y+=speed;
    }
}
