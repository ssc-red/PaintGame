package Items;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

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
		}
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
				if(this.right>1400)
				break;
		}
		for(Paint p: paint){
			if(p != this && p.x+p.width<this.x+this.width*2 && p.x>this.x-this.width && p.y+p.height<this.y+this.height*2 && p.y>this.y-this.height) {
				
			}
		}
	}
}

