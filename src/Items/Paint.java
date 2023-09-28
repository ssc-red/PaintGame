package Items;

import java.awt.Color;

import java.util.Random;

import Player.player;

public class Paint {
	int jumpsGiven;
	int x, y;
	int width, height;
	
	int randX, randY;
	int randColor;
	
	int right, left, top, bottom;
	
	Color color;
	int type;
	
	Random rand = new Random();

	int seperation = 5;
	
	Paint(int type, player player){
		this.type = type;
		if(this.type == 1) {
			this.height = 30;
			this.width = 30;
			this.randColor = rand.nextInt(3);
			switch(this.randColor) {
			case 0:
				this.color = color.red;
				break;
			case 1:
				this.color = color.blue;
				break;
			case 2:
				this.color = color.yellow;
				break;
			}
			this.jumpsGiven = 1;
		}
		this.randY = (rand.nextInt(400)+100)+player.y;
		this.randX = rand.nextInt(4); 
		
		switch(this.randX){
		case 0:
			this.randX = rand.nextInt(player.platformW/seperation);
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
			this.randX = rand.nextInt(player.platformW/seperation)+4*1400/seperation-player.width;
			break;
		}
		
	}
}
