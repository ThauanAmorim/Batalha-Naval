package Telas;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class TelaBackGround extends JPanel{	 
	private static final long serialVersionUID = 1L;
		private String pathImage = "";
		
	   public TelaBackGround(String pathImage, Component component) {
	       this.pathImage = pathImage;
	       setSize(component.getWidth(), component.getHeight());
	   }
	 
	   public void paintComponent(Graphics g) {

	       Graphics2D gr = (Graphics2D) g.create();
	       try {	 
	          BufferedImage buffer = ImageIO.read( new File(pathImage) );
	          gr.drawImage(buffer, null, 0, 0 );
	 
	       } catch (IOException ex) {
	          Logger.getLogger(TelaBackGround.class.getName()).log(Level.SEVERE, null, ex);
	       }
	 
	   }
}
