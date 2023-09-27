package Items;

import java.awt.Graphics2D;

import java.util.ArrayList;

import Main.GamePanel;
import Player.player;

public class PaintManager {
	public ArrayList<Paint> paint;
	
	GamePanel gp;
	player player;
	
	int paintsAbove = 0;
	int paintFrequency;
	
	public PaintManager(GamePanel gp, player player) {
		this.gp = gp;
		this.player = player;
		
		paint = new ArrayList<Paint>();
		
		paintFrequency = 3; 
		
	}
	public void update() {
		paintsAbove = 0;
		for(Paint p : paint) {
			p.x = player.x+p.randX;
			p.y = player.y-p.randY;
			p.right = p.x+p.width;
			p.left = p.x;
			p.top = p.y;
			p.bottom = p.y+p.height;
			
			if(p.right>0 && p.left<gp.screenWidth && p.bottom>0 && p.top<gp.screenHeight) {
				if(p.right<player.right && p.left>player.left && p.bottom<player.bottom && p.top>player.top) {
					player.jumps += p.jumpsGiven;
					player.color = p.color;
					paint.remove(p);
					break;
				}
			}
			if(p.bottom<player.screenY) {
				paintsAbove++;
			}
		}
		while(paintsAbove<paintFrequency) {
			paint.add(new Paint(1, player));
			paintsAbove++;
		}
	}
	public void draw(Graphics2D g2) {
		for(Paint p : paint) {
			if(p.x+p.width>0 && p.x<gp.screenWidth && p.bottom>0 && p.y<gp.screenHeight) {
				g2.setColor(p.color);
				g2.fillRect(p.x, p.y, p.width, p.height);
			}
		}
	}
}
