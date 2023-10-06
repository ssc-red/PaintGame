package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean leftPressed, rightPressed, spacePressed;
    public boolean upPressed, downPressed, enterPressed;

    @Override
    public void keyPressed(KeyEvent e) {
    	int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }

        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }

        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }   
    
}
