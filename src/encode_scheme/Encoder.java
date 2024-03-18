package encode_scheme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Encoder: Abstract parent class for implementing line encoding techniques.
 * 
 * This abstract class serves as a blueprint for implementing various line
 * encoding techniques. It provides common properties and methods that are
 * shared among different encoding techniques.
 * 
 * @author Sourashis Das
 */

public abstract class Encoder {

	String name; // Name of the encoding technique

	// Constructor for Encoder class
	public Encoder(String name, int scale1) {
		this.name = name;
		level = 170 + scale1; // scale is needed for drawing all in a single frame.
		up = 150 + scale1;
		down = 190 + scale1;
		x = x1;
	}

	// Properties for drawing the signal
	int x1 = 200, x = 0;
	int level = 170;
	int y = 1100;
	int up = 150;
	int down = 190;
	int length = 0; /* change frequency of signal */
	char prev = ' ';/* store previous character */
	boolean state = true;/* for inversion type encoding */

	/**
	 * encode: Draws common elements for all encoding schemes.
	 * 
	 * This method draws common elements such as the computers, connection between
	 * computers, and basic signal lines.
	 * 
	 * @param g    The Graphics2D object to draw the signal.
	 * @param data The data to be encoded.
	 */
	public void encode(Graphics2D g, String data) {
		length = data.length();
		length = (y - x1) / length;
		g.setBackground(Color.WHITE);
		Image i = new ImageIcon("computer.jpg").getImage();// get computer image
		g.drawImage(i, 90, 50, 100, 100, null);
		g.drawImage(i, 1130, 50, 100, 100, null);
		g.setStroke(new BasicStroke(2)); // Thickness of line
		g.setColor(Color.CYAN);
		g.drawLine(150, 130, 1150, 130);// connection between computers
		g.setColor(Color.BLUE);
		g.drawLine(x, up - 10, x, down + 10);
		g.setFont(new Font("Arial", Font.BOLD, 16));

	}

}
