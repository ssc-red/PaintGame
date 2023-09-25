package Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class background {
	BufferedImage background;
	GamePanel gp;
	int multiple1 = 1;
	int multiple2 = 2;
	
	public background(GamePanel gp, int image) {
		this.gp = gp;
		try {
			switch(image) {
			case 0:
				background = ImageIO.read(new File("src/res/clouds.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update() {
		
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(background, gp.player.x, gp.player.y-gp.screenHeight*2*multiple1, gp.player.platformW, gp.screenHeight*3, null);
		g2.drawImage(background, gp.player.x, gp.player.y-gp.screenHeight*2*multiple2, gp.player.platformW, gp.screenHeight*3, null);
	}
}	
