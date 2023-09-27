package Main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Items.PaintManager;
import Player.*;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	public final int screenWidth = 700; 
    public final int screenHeight = 600; 
	
	int FPS=60;

	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	
	public player player = new player(this, keyH);
	PaintManager paintM = new PaintManager(this, player);
	background backG = new background(this, 0);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));;
		this.setBackground(new Color(137, 207, 240));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
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
		player.update();
		paintM.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		paintM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	
}

