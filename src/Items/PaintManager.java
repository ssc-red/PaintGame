package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;
import Player.player;

public class PaintManager {
	public ArrayList<Paint> paint;
	
	GamePanel gp;
	player player;
	
	int paintsBelow = 0;
	int paintFrequency;
	int paints = 0;

	int randForX = 0;

	int superPaintsBelow = 0;
	int superPaintsFrequency;
	int superPaints = 0;
	
	public PaintManager(GamePanel gp, player player) {
		this.gp = gp;
		this.player = player;
		
		paint = new ArrayList<Paint>();
		
		paintFrequency = 10; 
		superPaintsFrequency = 1;
	}
	public void update() {
		paintsBelow = 0;
		superPaintsBelow = 0;
		if(gp.player.score>500){
			paintFrequency = 8;
		}
		else if(gp.player.score>2000){
			paintFrequency = 5;
		}
		else if(gp.player.score>5000){
			paintFrequency = 2;
		}
		for(Paint p : paint) {
			p.x = player.x+p.randX;
			p.y = player.y-p.randY;
			p.right = p.x+p.width;
			p.left = p.x;
			p.top = p.y;
			p.bottom = p.y+p.height;
			
			if(p.right>0 && p.left<gp.screenWidth && p.bottom>0 && p.top<gp.screenHeight) {
				p.counter++;
				if(p.counter<=60/p.fps){
					p.frame = 1;
				}
				else if(p.counter<=60/p.fps*2){
					p.frame = 2;
				}
				else if(p.counter<=60/p.fps*3){
					p.frame = 3;
				}
				else if(p.counter<=60/p.fps*4){
					p.frame = 4;
				}
				else if(p.counter<=60/p.fps*5){
					p.frame = 5;
				}
				else{
					p.counter = 0;
				}
				if(p.right<player.right && p.left>player.left && p.bottom<player.bottom && p.top>player.top) {
					switch(p.type){
						case 1:
							player.jumps += p.jumpsGiven;
							paints--;
							break;
						case 2:
							player.superJumps += p.jumpsGiven;
							superPaints--;
					}
					player.color.add(p.color);
					paint.remove(p);
					break;
				}
			}
			if(p.bottom>player.screenY) {
				switch(p.type){
					case 1:
						paintsBelow++;
						break;
					case 2:
						superPaintsBelow++;
						break;
				}
			}
		}
		while(paint.size()-superPaints-paintsBelow<paintFrequency) {
			switch(randForX){
				case 5:
					randForX = 0;
					break;
				default:
					randForX++;
					break;
			}
			paint.add(new Paint(1, player, randForX, paint));
			paints++;
			paintsBelow--;
		}
		while(paint.size()-paints-superPaintsBelow<superPaintsFrequency && gp.player.score>500) {
			switch(randForX){
				case 5:
					randForX = 0;
					break;
				default:
					randForX++;
					break;
			}
			paint.add(new Paint(2, player, randForX, paint));
			superPaints++;
			superPaintsBelow--;
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image;
		for(Paint p : paint) {
			if(p.x+p.width>0 && p.x<gp.screenWidth && p.bottom>0 && p.y<gp.screenHeight) {
				switch(p.type){
					case 1:
						switch(p.frame){
							case 1:
								image = p.image1;
								break;
							case 2:
								image = p.image2;
								break;
							case 3:
								image = p.image3;
								break;
							case 4:
								image = p.image4;
								break;
							case 5:
								image = p.image5;
								break;
							default:
								image = p.image1;
						}
						break;	
					case 2:
						image = p.image1;
						break;
					default:
						image = p.image1;
				}
				g2.drawImage(image, p.x, p.y, p.width, p.height, null);
			}
		}
	}
}
