package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class StartButton extends JButton implements ActionListener{
    GamePanel gp;
    public StartButton(GamePanel gp){
        this.gp = gp;
		this.addActionListener(this);
		this.setBounds(605, 250-75/2, 75, 75);
		this.setText("Start");
		this.setFocusable(false);
		this.setFont(new Font("Georgia",Font.BOLD,40));
		this.setForeground(Color.green);
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.green));
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this){
            gp.gameState++;
        }
    }
    
}
