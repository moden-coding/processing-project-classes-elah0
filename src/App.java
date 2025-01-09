import processing.core.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends PApplet {
    PImage RocketShipImg;
    PImage asteroidImg;
    PImage planetImg;
    float RocketX;
    float RocketY;
    float Rocketspeed = 2;
    boolean moveLeft = false;
    boolean moveRight = false;
    boolean moveUp = false;
    boolean moveDown = false;
    PImage backgroundImage;
    PImage livesImage;
    PImage explosionImage;
    float playX1 = 390; // Left point
    float playY1 = 400;
    float playX2 = 390; // Bottom point
    float playY2 = 220;
    float playX3 = 550; // Right point
    float playY3 = 290;
    int scene = 0; // 0 = Start Scene, 1 = Game Scene, 2 = Game Over Scene
    int count = 0;
    int lr = 1; // is char facing left or right, 1 is right, -1 is left
    int ud = 1;
    int lives =3;
    int frames =100;
    int highScore = 0;
    float changeX =0;
    float changeY =0;
    int speed =0;
    float bulletAngle =0;

   
    

    ArrayList<Asteroid> asteroids; //Create the array list to store asteroids
    ArrayList<Bullet> bullets; //create the array list to store bullets

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        readHighScore();

        asteroids = new ArrayList<>();
        bullets = new ArrayList<>();
        RocketShipImg = loadImage("rocketShip.png"); //load all the images
        asteroidImg = loadImage("asteroid.png");
        backgroundImage = loadImage("background1.jpg");
        livesImage = loadImage("heart.png");
        explosionImage = loadImage("Explosion.png");
        RocketX = width / 2;
        RocketY = height - 100;
        
    }

    public void settings() {
        size(900, 800);
    }

    public void draw() {
        if (scene ==0){
            StartScreen(); //call the start screen method which display a play button for users
        }
        if (scene == 1) {
        image(backgroundImage, 0, 0, width, height);
        image(RocketShipImg, RocketX, RocketY, 40, 70);
        lives();
        
        if (moveLeft) { //movement
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

        
        if (frames !=0 && frameCount % frames ==0){ //create asteroids by frames
            asteroidMaker();
        }

        

        ArrayList<Bullet> bulletsToRemove = new ArrayList<>(); //array lists to remove bullets and asteroids
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<>();
        

        for (Asteroid a : asteroids) {
            a.display();
            a.move();
            if (asteroidsHitsSpaceship(a.returnX(), a.returnY())) { //if asteroid is hit reset it and take away a life
                a.reset(RocketX, RocketY, count);
                lives--;
                System.out.println("ship hit");
               

            }
            
            //float distancex = RocketX - a.returnX();
           // float distancey = RocketY - a.returnY();
           // float slope = distancex/distancey;
           //changeX = (float)Math.sqrt(speed*speed/(1+slope*slope));
          //  if(distancex < 0){
          //      changeX = -changeX;
          //  }
           //changeY = slope*changeX;
           
        }
            
            

        
        
        for (Bullet b : bullets) {
            b.display();
            b.update();
            

            for (Asteroid a : asteroids) {
                if (bulletsHitsAsteroid(b.returnX(), b.returnY(), a.returnX(), a.returnY())) { //if the bullet hits asteroids remove it
                    bulletsToRemove.add(b);
                    asteroidsToRemove.add(a);
                    count++;
                    break;
                }

            }

        }


            

        bullets.removeAll(bulletsToRemove);
        asteroids.removeAll(asteroidsToRemove);
        rocket();
        fill(255);
        textSize(30);
        text("Your score: " + count , 40, 50); //print score
        text("Highscore: " +highScore,40, 90); //print highscore
    }
    else if( scene ==2){ 
        //display the game over screen
        EndScreen();

    }
    Highscore();

    }

    public void keyPressed() {
        //movement with arrow keys and wasd
        if (keyCode == LEFT || keyCode == 'A') { 
            moveLeft = true;
            lr=-1; //move the gun
            ud=0;
        } else if (keyCode == RIGHT || keyCode == 'D') {
            moveRight = true;
            lr=1;
            ud=0;
        } else if (keyCode == UP || keyCode == 'W') {
            moveUp = true;
            ud=1; 
            lr=0;
        } else if (keyCode == DOWN || keyCode == 'S') {
            moveDown = true;
            ud=-1;
            lr=0;
            
        }

        if (keyCode == ' ') { //shoot when you hit space bar
        float bulletX = RocketX + 15; 
        float bulletY = RocketY;
        float bulletAngle =0;

        if (ud == 1) { // Facing up
            bulletY = RocketY - 10;
            bulletAngle = -PI / 2;
        } else if (ud == -1) { // Facing down
            bulletY = RocketY + 70;
            bulletAngle = PI / 2;
        } else if (lr == 1) { // Facing right
            bulletX = RocketX + 40;
            bulletY = RocketY + 30;
            bulletAngle = 0;
        } else if (lr == -1) { // Facing left
            bulletX = RocketX - 30;
            bulletY = RocketY + 30;
            bulletAngle = PI;
        }
            Bullet bullet1 = new Bullet(5, this, 5, RocketX, RocketY, bulletAngle);
            bullets.add(bullet1);
        }

        
    
        
        if (keyCode == ENTER && scene==2){
            ResetGame();
        }

    }

    public void keyReleased() { //stop movement when key released
        if (keyCode == LEFT || keyCode == 'A') {
            moveLeft = false;
        } else if (keyCode == RIGHT || keyCode == 'D') {
            moveRight = false;
        } else if (keyCode == UP || keyCode == 'W') {
            moveUp = false;
        } else if (keyCode == DOWN || keyCode == 'S') {
            moveDown = false;
        }
    }

    public boolean asteroidsHitsSpaceship(float asteroidX, float asteroidY) { // copied this from my previous game but
                                                                              // edited it to work in this game
                                                                              //detects collision 

        return RocketX + 40 > asteroidX && RocketX < asteroidX + 100 &&
                RocketY + 70 > asteroidY && RocketY < asteroidY + 100;
    }
    public boolean bulletsHitsAsteroid(float bulletX, float bulletY, float asteroidX, float asteroidY) { //same logic from my old game, detects collision
        return bulletX + 5 > asteroidX && bulletX - 5 < asteroidX + 100 &&
                bulletY > asteroidY && bulletY < asteroidY + 100;
    }
    public void StartScreen(){ //creates the starting screen 
        background(0);
        textSize(90);
        textAlign(CENTER, TOP);
        fill(255, 255, 255);
        text("Welcome to Asteroids!", width / 2, 20);
        fill(178, 101, 255);
        triangle(playX1, playY1, playX2, playY2, playX3, playY3);
        textSize(70);
        textAlign(CENTER, CENTER);
        fill(178, 102, 255);
        text("Play", (playX1 + playX3) / 2, playY1 + 30);
        textAlign(LEFT, LEFT);
        
        fill(255, 255, 255);
        textSize(45);
        text("Shoot at the asteroids to save yourself!", 100, 190);
       
    }
    public void mousePressed() {
        if (scene == 0 && mouseInButton()) {
            scene = 1; // Start the game when play button is clicked
        }
        if(scene == 1){
            Bullet bullet2 = new Bullet(5, this, 5, RocketX, RocketY, mouseX, mouseY);
            bullets.add(bullet2);
        }
       
    }
    boolean mouseInButton () {
        //Detect if the play button is clicked by checking the color
        if(get(mouseX, mouseY) == color(178,101,255)){
            System.out.println("you found the color");
            return true;
        }
        return false;
        
    }
    public void rocket(){
        noStroke();
        fill(255, 0, 0); // Color for the gun
        if (ud == 1) {
            rect(RocketX + 15, RocketY -10, 5, -30);
        } else if (ud == -1) {
            rect(RocketX + 15, RocketY + 70, 5, 30);

        } else if (ud == 0) {
            rect(RocketX + 5, RocketY + 20, 30 * lr, 5);
        }
        else if (lr == 1) { // Facing right
            rect(RocketX + 40, RocketY + 30, 30, 5);
        } else if (lr == -1) { // Facing left
            rect(RocketX - 30, RocketY + 30, 30, 5);
        }
    }
    public void ResetGame(){ //restart the game
        scene = 1;
        count=0;
        RocketX = width /2;
        RocketY= height-100;
        lr=1;
        ud=1;
        bullets.clear();
        asteroids.clear();
        lives =3;
    }
    public void EndScreen(){ //game over screen
        background(178, 102, 255);
        image(explosionImage, 300, 150, 200,200);
        fill(0);
        textSize(90);
        text("Game Over!", 230, 300);
        fill(14, 2, 2);
        textSize(40);
        text("Press enter to play again", 230, 500);
        textSize(30);
        fill(255, 255, 255);
            
        text("Your Score: " + count, 345, 400); 
        text("High Score: " + highScore, 345, 430);
    }
    public void lives (){ //create lives
        if (lives == 3){
            image(livesImage, 800, 10, 80,80);
            image(livesImage, 740, 10, 80,80);
            image(livesImage, 680, 10, 80,80);
        }
        else if (lives ==2){
        image(livesImage, 740, 10, 80,80);
        image(livesImage, 680, 10, 80,80);
        }
        else if (lives ==1){
        image(livesImage, 680, 10, 80,80);
        } 
        else if (lives ==0){
            scene =2;
            return;
        }
        
    

    }

    public void asteroidMaker(){ //creates asteroids
        Asteroid asteroid = new Asteroid(random(width), random(-200, -50), this, asteroidImg, RocketX, RocketY);
        asteroids.add(asteroid);
    }
    public void Highscore(){
        if (scene == 2) {  // losing Scene
            if (count > highScore) {
                highScore = count; // Update high score if the current score is higher
                saveHighScore();  //call methods that writes high score to file
            }
    }
    
    }
    public void readHighScore(){ //uses file reading for highscore
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
               highScore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    public void saveHighScore(){ //writes the score to file
        try (PrintWriter writer = new PrintWriter("highscore.txt")){
            writer.println(highScore); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }
    
}