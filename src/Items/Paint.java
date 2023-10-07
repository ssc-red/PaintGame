package Items;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import Player.player;

public class Paint {
	int jumpsGiven;
	int x, y;
	int width, height;
	
	int randX, randY;
	
	int right, left, top, bottom;
	
	String color;
	int type;
	
	Random rand = new Random();

	int seperation = 6;

	BufferedImage image1, image2, image3, image4, image5;

	int frame;
	int counter;
	int fps;
	
	Paint(int type, player player, int randForX, ArrayList<Paint> paint){
		this.frame = 0;
		this.type = type;
		this.fps = 5;
		if(this.type == 1) {
			this.height = 30;
			this.width = 30;
			switch(randForX) {
			case 0:
				this.color = "red";
				break;
			case 1:
				this.color = "blue";
				break;
			case 2:
				this.color = "yellow";
				break;
			case 3:
				this.color = "red";
				break;
			case 4:
				this.color = "blue";
				break;
			case 5:
				this.color = "yellow";
				break;
			}
			switch(color) {
				case "red":
					this.image1 = player.redPaintBall1;
					this.image2 = player.redPaintBall2;
					this.image3 = player.redPaintBall3;
					this.image4 = player.redPaintBall4;
					this.image5 = player.redPaintBall5;
					break;
				case "blue":
					this.image1 = player.bluePaintBall1;
					this.image2 = player.bluePaintBall2;
					this.image3 = player.bluePaintBall3;
					this.image4 = player.bluePaintBall4;
					this.image5 = player.bluePaintBall5;
					break;
				case "yellow":
					this.image1 = player.yellowPaintBall1;
					this.image2 = player.yellowPaintBall2;
					this.image3 = player.yellowPaintBall3;
					this.image4 = player.yellowPaintBall4;
					this.image5 = player.yellowPaintBall5;
					break;
			}
			this.jumpsGiven = 1;
			this.randY = (rand.nextInt(400)+100)+player.y;
			switch(randForX){
			case 0:
				this.randX = rand.nextInt(player.platformW/seperation)+50;
				break;
			case 1:
				this.randX = rand.nextInt(player.platformW/seperation)+1400/seperation;
				break;
			case 2:
				this.randX = rand.nextInt(player.platformW/seperation)+2*1400/seperation;
				break;
			case 3:
				this.randX = rand.nextInt(player.platformW/seperation)+3*1400/seperation;
				break;
			case 4:
				this.randX = rand.nextInt(player.platformW/seperation)+4*1400/seperation;
				break;
			case 5:
				this.randX = rand.nextInt(player.platformW/seperation)+(5*1400/seperation)-player.width*2;
				break;
			}
		}
		else if(this.type == 2) {
			this.height = 40;
			this.width = 40;
			this.color = "super";
			this.image1 = player.superPaintBall1;
			this.jumpsGiven = 1;
			randForX = rand.nextInt(6);
			if(player.score<1000){
				this.randY = (rand.nextInt(1000)+1000)+player.y;
			}
			else if(player.score<2000){
				this.randY = (rand.nextInt(3000)+1000)+player.y;
			}
			else if(player.score<5000){
				this.randY = (rand.nextInt(5000)+2000)+player.y;
			}
			switch(randForX){
			case 0:
				this.randX = rand.nextInt(player.platformW/seperation)+50;
				break;
			case 1:
				this.randX = rand.nextInt(player.platformW/seperation)+1400/seperation;
				break;
			case 2:
				this.randX = rand.nextInt(player.platformW/seperation)+2*1400/seperation;
				break;
			case 3:
				this.randX = rand.nextInt(player.platformW/seperation)+3*1400/seperation;
				break;
			case 4:
				this.randX = rand.nextInt(player.platformW/seperation)+4*1400/seperation;
				break;
			case 5:
				this.randX = rand.nextInt(player.platformW/seperation)+(5*1400/seperation)-player.width*2;
				break;
			}
		}	

		for(Paint p: paint){
			if(p != this && p.x+p.width<this.x+this.width*4 && p.x*2>this.x-this.width && p.y+p.height<this.y+this.height*4 && p.y*2>this.y-this.height) {
				paint.remove(this);
			}
		}
		if(this.randX>1400){
			this.randX -= 1400-this.randX+50;
			System.out.println(1400-this.randX);
		}
	}
}

