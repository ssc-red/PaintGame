//Author: Shams Chandani
//Start Date: October 2, 2023

package Main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
public class main {
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Paint Game");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		File audioFile = new File("src/res/paintGame.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
		clip.loop(100);

		gamePanel.startGameThread();
	}
}
