package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * NRZLEncoder: Implements NRZ-L encoding.
 * 
 * NRZ-L (Non-Return-to-Zero Level) encoding is a line encoding technique where
 * the signal level remains constant during the bit duration. A '0' bit is
 * represented by a low signal level, and a '1' bit is represented by a high
 * signal level.
 * 
 * Extends Encoder to draw NRZ-L encoded signal.
 * 
 * Author: Sourashis Das
 */
public class NRZLEncoder extends Encoder {

	// Constructors
	public NRZLEncoder() {
		super("NRZ-L Encoding", 0); // Default constructor with no scale
	}

	public NRZLEncoder(int scale) {
		super("NRZ-L Encoding", scale); // Constructor with scale
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		super.encode(g, data); // Call superclass method to set up drawing

		g.setColor(Color.BLACK);
		for (char ch : data.toCharArray()) {
			if (ch != prev)
				g.drawLine(x, up, x, down); // Draw vertical line if there is a change in bit
			if (ch == '0') {
				g.drawLine(x, down, x + length, down); // Draw horizontal line for '0' bit at lower level
				x = x + length; // Update x-coordinate
			} else {
				g.drawLine(x, up, x + length, up); // Draw horizontal line for '1' bit at upper level
				x = x + length; // Update x-coordinate
			}
			prev = ch; // Update previous bit
			g.setColor(new Color(254, 189, 56)); // Set color for transition lines
			g.drawLine(x, level + 5, x, level - 5); // Draw transition lines
			g.setColor(Color.BLACK); // Reset color to black
		}
		g.setColor(Color.BLUE); // Set color to blue
		g.drawLine(x, up - 10, x, down + 10); // Draw final vertical line
		g.drawLine(x, level, x1, level); // Draw horizontal line to connect with initial position
		g.drawString(name, x + 5, level); // Draw encoding name
	}
}
