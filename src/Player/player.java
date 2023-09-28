package Player;

import java.awt.Color;
import java.awt.Graphics2D;

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
	int jumpsBegin;
    String jumpD;
    boolean released = true;
    
    public Color color;
    
    public player(GamePanel gp, KeyHandler keyH) {
    	this.gp = gp;
    	this.keyH = keyH;
    	
    	getDefaultValues();
        
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
    	jumpsBegin = 5;
    	jumps = jumpsBegin;
    	
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
        color = Color.white;
    }
    public void update() {
    	//Jump
    	if(keyH.spacePressed==true && released==true && jumps>0) {
    		jump = true;
    		gravity = 0;
    		released = false;
    		jumps--;
    	}
    	else if(keyH.spacePressed==false) {
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
    	
    	//Gravity
    	if(y>gp.screenHeight/2 + height/2) {
    		y -= gravity;
    		gravity += gravityIncrease;
    	}
    	else if(y<gp.screenHeight/2 + height/2){
    		y = gp.screenHeight/2 + height/2;
    		gravity = 0;
			gp.paintM.paint.clear();
    		jump = false;
    		jumps = jumpsBegin;
    	}
    	else if(y==gp.screenHeight/2 + height/2) {
    		gravity = 0;
			gp.paintM.paint.clear();
    		jump = false;
    		jumps = jumpsBegin;
    	}
    }
    public void draw(Graphics2D g2) {
    	g2.setColor(color);
        g2.fillOval(screenX, screenY, width, height);

		g2.setColor(Color.black);

		g2.fillRect(x-screenX, 0, screenX, gp.screenHeight);
		g2.fillRect(x+platformW, 0, screenX, gp.screenHeight);
    	
        if(y<gp.screenHeight) {
        	g2.setColor(new Color(144, 238, 144));
        	if(y>=gp.screenHeight/2 + height/2) {
            	g2.fillRect(x, y, platformW, platformH);
        	}
        	else {
            	g2.fillRect(x, gp.screenHeight/2 + height/2, platformW, platformH);
        	}
		}
    	
    	g2.setColor(Color.black);
    	
    	jumpD = "Jumps Left: " + jumps;
    	
    	g2.drawString(jumpD, 10, 20);
    }
}
