package Main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Items.PaintManager;
import Player.*;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	public final int screenWidth = 700; 
    public final int screenHeight = 600; 
	
	public int FPS=60;   

	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	
	public player player = new player(this, keyH);
	public PaintManager paintM = new PaintManager(this, player);
	public background backG = new background(this, 2);

	final int titleScreen = 0;

	final int playScreen = 1;
	public int gameState = 0;

	int selectorX, selectorY;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));;
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		selectorX = 200;
		selectorY = 300;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long Timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            
            currentTime = System.nanoTime();
            Timer+=(currentTime-lastTime);
            delta+=(currentTime-lastTime)/drawInterval;

            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(Timer>=1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                Timer=0;
            }
        }

	}
	
	public void update() {
		switch(gameState){
			case titleScreen:
				if(keyH.downPressed==true){
					if(selectorY<420){
						selectorY += 60;
					}
					else{
						selectorY = 300;
					}
					keyH.downPressed = false;
				}
				if(keyH.upPressed==true){
					if(selectorY>300){
						selectorY -= 60;
					}
					else{
						selectorY = 420;
					}
					keyH.upPressed = false;
				}
				if(keyH.enterPressed==true && selectorY==300){
					gameState++;
					keyH.enterPressed = false;
				}
				
				break;
			case playScreen:
				backG.update();
				player.update();
				paintM.update();
				break;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		switch(gameState){
			case titleScreen:
				g2.drawImage(backG.background, -200, 0, player.platformW, screenHeight, null);
				g2.setFont(new Font("PLAIN", Font.PLAIN, 100));
				g2.drawString("Paint Game", 90,150);
				g2.setFont(new Font("PLAIN", Font.ROMAN_BASELINE, 50));
				g2.setColor(Color.red);
				g2.drawString("Start", 250,300);
				g2.setColor(Color.blue);
				g2.drawString("Settings", 250,360);
				g2.setColor(Color.yellow);
				g2.drawString("Tutorial", 250,420);

				g2.drawImage(player.superPaintBall1, selectorX, selectorY-40, 40, 40, null);
				break;
			case playScreen:
				backG.draw(g2);
				paintM.draw(g2);
				player.draw(g2);
				break;
		}
		g2.dispose();
	}
}

