package Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.xml.sax.SAXException;

import Items.PaintManager;
import Main.GamePanel;
import Main.KeyHandler;

public class player {
	GamePanel gp;
    KeyHandler keyH;
    
    public int x, y;
    public int screenX, screenY;
    public int width, height;
    
    public int right, left, top, bottom;
    
    public int platformW, platformH;
    
    int leftB, rightB;
    
    int minSpeedY, minSpeedX, maxSpeedY, maxSpeedX;
    double gravity, gravityIncrease;
    boolean jump = false;
    
    public int jumps;
	public int superJumps;
	int jumpsBegin;

    String jumpD;
	String superJumpD;

	boolean superJump = false;
	int superJumpSec;
	int superJumpCounter = 0;
	double superJumpSpeedDefaultX;
	double superJumpSpeedDefaultY;
	double superJumpSpeedX;
	double superJumpSpeedY;

    boolean released = true;

	public int score;
	String scoreD;

	public ArrayList<String> color = new ArrayList<String>();

	BufferedImage playerWhite, playerRed, playerBlue, playerYellow, playerSuper;

	BufferedImage redPaintSplash, redPaintSplashLeft, redPaintSplashRight;
	BufferedImage bluePaintSplash, bluePaintSplashLeft, bluePaintSplashRight;
	BufferedImage yellowPaintSplash, yellowPaintSplashLeft, yellowPaintSplashRight;

	public BufferedImage redPaintBall1, redPaintBall2, redPaintBall3, redPaintBall4, redPaintBall5;
	public BufferedImage bluePaintBall1, bluePaintBall2, bluePaintBall3, bluePaintBall4, bluePaintBall5;
	public BufferedImage yellowPaintBall1, yellowPaintBall2, yellowPaintBall3, yellowPaintBall4, yellowPaintBall5;
	public BufferedImage superPaintBall1;

	public ArrayList<PaintSplash> paintSplashs = new ArrayList<PaintSplash>();
    
    public player(GamePanel gp, KeyHandler keyH) {
    	this.gp = gp;
    	this.keyH = keyH;
    	
    	getDefaultValues();
		loadImages();
        
        screenX = gp.screenWidth/2 - width/2;
        screenY = gp.screenHeight/2 - height/2;
    }
    public void getDefaultValues() {
    	//speed
    	minSpeedY = 6;
    	minSpeedX = 3;
    	
    	maxSpeedY = 10;
    	maxSpeedX = 7;
    	
    	//gravity settings
    	gravity = 0;
    	gravityIncrease = 0.12;
    	
    	//jump regulation
    	jumpsBegin = 100;
    	jumps = jumpsBegin;
		superJumpSec = 2;

		superJumpSpeedDefaultX = 1;
		superJumpSpeedDefaultY = 2;
		superJumpSpeedX = superJumpSpeedDefaultX;
		superJumpSpeedY = superJumpSpeedDefaultY;
    	
    	//ball size
    	width = 60;
    	height = 60;
    	
    	//platform size
    	platformW = 1400;
    	platformH = 500;
    	
    	//location
    	x = 0 - (platformW/2 - gp.screenWidth/2 - width/2);
        y = gp.screenHeight/2 + height/2;
        
        //bounderies
        rightB = gp.screenWidth/2 - width/2;
        leftB = -platformW + gp.screenWidth/2 + width/2;
        System.out.println(rightB + " " + leftB);
        
        //hitbox
        right = gp.screenWidth/2 - width/2+width+10;
        left = gp.screenWidth/2 - width/2-10;
        top = gp.screenHeight/2 - height/2-10;
        bottom = gp.screenHeight/2 - height/2+height+10;
        
        //color
        color.add("white");
    }
	public void loadImages(){
		try {
			playerWhite = ImageIO.read(new File("src/res/paperball.png"));
			playerRed = ImageIO.read(new File("src/res/paperballRed.png"));
			playerBlue = ImageIO.read(new File("src/res/paperballBlue.png"));
			playerYellow = ImageIO.read(new File("src/res/paperballYellow.png"));
			playerSuper = ImageIO.read(new File("src/res/paperballSuper.png"));
			
			redPaintSplash = ImageIO.read(new File("src/res/PaintSplash/redPaintSplash.png"));
			redPaintSplashLeft = ImageIO.read(new File("src/res/PaintSplash/redPaintSplashLeft.png"));
			redPaintSplashRight = ImageIO.read(new File("src/res/PaintSplash/redPaintSplashRight.png"));

			bluePaintSplash = ImageIO.read(new File("src/res/PaintSplash/bluePaintSplash.png"));
			bluePaintSplashLeft = ImageIO.read(new File("src/res/PaintSplash/bluePaintSplashLeft.png"));
			bluePaintSplashRight = ImageIO.read(new File("src/res/PaintSplash/bluePaintSplashRight.png"));

			yellowPaintSplash = ImageIO.read(new File("src/res/PaintSplash/yellowPaintSplash.png"));
			yellowPaintSplashLeft = ImageIO.read(new File("src/res/PaintSplash/yellowPaintSplashLeft.png"));
			yellowPaintSplashRight = ImageIO.read(new File("src/res/PaintSplash/yellowPaintSplashRight.png"));

			redPaintBall1 = ImageIO.read(new File("src/res/paintballs/redPaintBall1.png"));
			redPaintBall2 = ImageIO.read(new File("src/res/paintballs/redPaintBall2.png"));
			redPaintBall3 = ImageIO.read(new File("src/res/paintballs/redPaintBall3.png"));
			redPaintBall4 = ImageIO.read(new File("src/res/paintballs/redPaintBall4.png"));
			redPaintBall5 = ImageIO.read(new File("src/res/paintballs/redPaintBall5.png"));

			bluePaintBall1 = ImageIO.read(new File("src/res/paintballs/bluePaintBall1.png"));
			bluePaintBall2 = ImageIO.read(new File("src/res/paintballs/bluePaintBall2.png"));
			bluePaintBall3 = ImageIO.read(new File("src/res/paintballs/bluePaintBall3.png"));
			bluePaintBall4 = ImageIO.read(new File("src/res/paintballs/bluePaintBall4.png"));
			bluePaintBall5 = ImageIO.read(new File("src/res/paintballs/bluePaintBall5.png"));		
			
			yellowPaintBall1 = ImageIO.read(new File("src/res/paintballs/yellowPaintBall1.png"));
			yellowPaintBall2 = ImageIO.read(new File("src/res/paintballs/yellowPaintBall2.png"));
			yellowPaintBall3 = ImageIO.read(new File("src/res/paintballs/yellowPaintBall3.png"));
			yellowPaintBall4 = ImageIO.read(new File("src/res/paintballs/yellowPaintBall4.png"));
			yellowPaintBall5 = ImageIO.read(new File("src/res/paintballs/yellowPaintBall5.png"));

			superPaintBall1 = ImageIO.read(new File("src/res/paintballs/superPaintBall1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void update() {
    	//Jump
    	if(keyH.spacePressed==true && released==true) {
			if(superJumps>0){
				superJump = true;
				gravity = 0;
				released = false;
				superJumps--;
				superJumpCounter = 0;
				if(keyH.rightPressed==true) {
					paintSplashs.add(new PaintSplash("left", gp));
				}
				else if(keyH.leftPressed==true) {
					paintSplashs.add(new PaintSplash("right", gp));
				}
				else{
					paintSplashs.add(new PaintSplash("down", gp));
				}
			}
			else if(jumps>0){
				jump = true;
				gravity = 0;
				released = false;
				jumps--;
				if(keyH.rightPressed==true) {
					paintSplashs.add(new PaintSplash("left", gp));
				}
				else if(keyH.leftPressed==true) {
					paintSplashs.add(new PaintSplash("right", gp));
				}
				else{
					paintSplashs.add(new PaintSplash("down", gp));
				}
				if(color.size()>1){
					color.remove(color.get(color.size()-1));
				}
			}
    	}
    	else if(keyH.spacePressed==false && superJump==false) {
    		released = true;
    	}
    	if(jump==true) {
    		if(gravity<1) {
    			y += maxSpeedY;
    			if(keyH.rightPressed==true && x>leftB) {
        			x -= maxSpeedX;
        		}
        		if(keyH.leftPressed==true && x<rightB) {
        			x += maxSpeedX;
        		}
    		}
    		else {
    			y += minSpeedY;
    			if(keyH.rightPressed==true && x>leftB) {
        			x -= minSpeedX;
        		}
        		if(keyH.leftPressed==true && x<rightB) {
        			x += minSpeedX;
        		}
    		}
    	}
		if(superJump==true){
			y += superJumpSpeedY;
			superJumpSpeedY+=0.2;
			gravity = 0;
			if(keyH.rightPressed==true && x>leftB) {
        		x -= superJumpSpeedX;
        	}
        	if(keyH.leftPressed==true && x<rightB) {
        		x += superJumpSpeedX;
        	}
			superJumpSpeedX+=0.2;
			superJumpCounter++;
			if(superJumpSec*gp.FPS<superJumpCounter){
				superJump=false;
				color.remove(color.get(color.indexOf("super")));
				superJumpSpeedX = superJumpSpeedDefaultX;
				superJumpSpeedY = superJumpSpeedDefaultY;
			}
		}	
    	
    	//Gravity
		
		if(y-gravity>gp.screenHeight/2 + height/2) {
			y -= gravity;
			gravity += gravityIncrease;
		}
		else if(y>gp.screenHeight/2 + height/2){
			y -= y-gravity;
		}
		else if(y<gp.screenHeight/2 + height/2){
			y = gp.screenHeight/2 + height/2;
			gravity = 0;
			// gp.paintM.paint.clear();
			// paintSplashs.clear();
			jump = false;
			superJump = false;
			jumps = jumpsBegin;
			superJumps = 0;
			color.clear();
			color.add("white");
			score = 0;
			gp.paintM = new PaintManager(gp, this);
		}
		
		//Score
		if(score<y/10){
			score = y/10;
		}
    }
    public void draw(Graphics2D g2) {
		if(y<gp.screenHeight) {
        	g2.setColor(new Color(85, 60, 42));
        	if(y>=gp.screenHeight/2 + height/2) {
            	g2.fillRect(x, y, platformW, platformH);
        	}
        	else {
            	g2.fillRect(x, gp.screenHeight/2 + height/2, platformW, platformH);
        	}
		}

		int drawLeft = 0;
		for(PaintSplash ps: paintSplashs){
			boolean l = ps.draw(g2);
			if(l==true){
				drawLeft = paintSplashs.indexOf(ps);
				paintSplashs.remove(ps);
				break;
			}
		}
		for(PaintSplash ps: paintSplashs){
			if(paintSplashs.indexOf(ps)>=drawLeft){
				boolean l = ps.draw(g2);
			}
		}

		BufferedImage image = playerWhite;
		switch(color.get(color.size()-1)){
			case "red":
				image = playerRed;
				break;
			case "blue":
				image = playerBlue;
				break;
			case "yellow":
				image = playerYellow;
				break;
			case "super":
				image = playerSuper;
				break;
			default:
				image = playerWhite;
		}
		// if(color.get(color.size()-1).equalsIgnoreCase("red")){
		// 	image = playerRed;
		// }
		// else if(color.get(color.size()-1).equalsIgnoreCase("blue")){
		// 	image = playerBlue;
		// }
		// else if(color.get(color.size()-1).equalsIgnoreCase("yellow")){
		// 	image = playerYellow;
		// }
		// else{
		// 	image = playerWhite;
		// }
    	
        g2.drawImage(image, screenX, screenY, width, height, null);
    	
        

    	if(x>50){
			g2.setColor(Color.white);
		}
		else{
			g2.setColor(Color.black);
		}
    	
    	jumpD = "Jumps Left: " + jumps;
		scoreD = "Height: " + score + "cm";

		g2.setFont(new Font("PLAIN", Font.PLAIN, 15));
    	
    	g2.drawString(jumpD, 10, 20);
		g2.drawString(scoreD, 10, 40);
		if(superJumps>0){
			superJumpD = "SuperJumps Left: " + superJumps;
			g2.drawString(superJumpD, 10, 60);
		}
    }
}
