//Author: Shams Chandani
//Start Date: October 2, 2023

package Main;

import java.awt.Color;

import javax.swing.JFrame;
public class main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Paint Game");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
	}
}
