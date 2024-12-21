import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
    private int size;
    private float speed;
    private PApplet canvas;
    private float x;
    private float y;
    private float angle;
    private float ymove;
    private float xmove;
    private String bulletType;
    

    public Bullet(int size, PApplet c, float speed, float xPos, float yPos, float angle){
        this.size=10;
        canvas=c;
        this.speed = 5;
        this.x=xPos +15;
        y=yPos;
        this.angle=angle;

        bulletType = "old";

        // float distancex = xPos-x;

        // float distancey =yPos-y;
        // System.out.println("ship y" + y);
        // System.out.println("distance y " + distancey);
        // float distance =canvas.dist(xPos, yPos, x, y);
        // xmove=distancex * speed/distance;
        // System.out.println("X speed " + xmove);
        // ymove=distancey* speed/distance;
        // System.out.println("Y speed " + ymove);

    }
    public Bullet(int size, PApplet c, float speed, float xPos, float yPos, float mouseX, float mouseY){
        this.size=10;
        canvas=c;
        this.speed = 5;
        this.x = xPos;
        this.y = yPos;

        bulletType = "new";
        
        float distancex = mouseX-xPos;

        float distancey =mouseY-yPos;
        System.out.println("ship y" + y);
        System.out.println("distance y " + distancey);
        float distance =canvas.dist(xPos, yPos, mouseX, mouseY);
        xmove=distancex * speed/distance;
        System.out.println("X speed " + xmove);
        ymove=distancey* speed/distance;
        System.out.println("Y speed " + ymove);
    }
    public void display(){
        canvas.fill(224, 224, 224);
        canvas.rect(x-size/2, y, size/2, size/2);
       
    }
    public void update(){
        if(bulletType.equals("old")){
            x += canvas.cos(angle) * speed;
            y += canvas.sin(angle) * speed;
        }else if(bulletType.equals("new")){
            x += xmove;
            y += ymove;
        }
       
    }
    public float returnY(){
        return y;
    }
    public float returnX() {
        return x;
    }
}
