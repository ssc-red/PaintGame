package Player;

import Main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PaintSplash {
    GamePanel gp;

    BufferedImage image;

    int x, y;

    int width, height;

    String direction;

    public PaintSplash(String direction, GamePanel gp){
        this.gp = gp;
        this.direction = direction;
        switch(this.direction){
            case "down":
                this.width = 40;
                this.height = 80;
                this.x = gp.player.screenX+gp.player.width/4+(-gp.player.x);
                this.y = gp.player.y-(gp.player.screenY);
                switch(gp.player.color.get(gp.player.color.size()-1)){
                    case "red":
                        this.image = gp.player.redPaintSplash;
                        break;
                    case "blue":
                        this.image = gp.player.bluePaintSplash;
                        break;
                    case "yellow":
                        this.image = gp.player.yellowPaintSplash;
                        break;
                    default:
                        gp.player.paintSplashs.remove(this);
                        break;
                }
                break;
            case "left":
                this.width = 64;
                this.height = 80;
                this.x = gp.player.screenX-gp.player.width+(-gp.player.x);
                this.y = gp.player.y-(gp.player.screenY);
                switch(gp.player.color.get(gp.player.color.size()-1)){
                    case "red":
                        this.image = gp.player.redPaintSplashLeft;
                        break;
                    case "blue":
                        this.image = gp.player.bluePaintSplashLeft;
                        break;
                    case "yellow":
                        this.image = gp.player.yellowPaintSplashLeft;
                        break;
                    default:
                        gp.player.paintSplashs.remove(this);
                        break;
                }
                break;
            case "right":
                this.width = 64;
                this.height = 80;
                this.x = gp.player.screenX+gp.player.width+(-gp.player.x);
                this.y = gp.player.y-(gp.player.screenY);
                switch(gp.player.color.get(gp.player.color.size()-1)){
                    case "red":
                        this.image = gp.player.redPaintSplashRight;
                        break;
                    case "blue":
                        this.image = gp.player.bluePaintSplashRight;
                        break;
                    case "yellow":
                        this.image = gp.player.yellowPaintSplashRight;
                        break;
                    default:
                        gp.player.paintSplashs.remove(this);
                        break;
                }
                break;
        }
    }
    public boolean draw(Graphics2D g2){
        if(gp.player.x+this.x+this.width>0 && gp.player.x+this.x<gp.screenWidth && gp.player.y-this.y+this.height>0 && gp.player.y-this.y<gp.screenHeight){
            g2.drawImage(image, gp.player.x+this.x, gp.player.y-this.y, this.width, this.height, null);
        } 
        else{
            return true;
        }
        return false;
    }
}
