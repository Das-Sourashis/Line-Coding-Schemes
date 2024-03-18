package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * ManchesterEncoder: Implements Manchester encoding.
 * 
 * Manchester encoding is a line encoding technique where each bit is
 * represented by a transition. For '0' bit, the signal transitions from high to
 * low in the middle of the bit period, and for '1' bit, the signal transitions
 * from low to high in the middle of the bit period.
 * 
 * Extends Encoder to draw Manchester encoded signal.
 * 
 * Author: Sourashis Das
 */
public class ManchesterEncoder extends Encoder {

	// Constructors
	public ManchesterEncoder() {
		super("Manchester Encoding", 0); // Default constructor with no scale
	}

	public ManchesterEncoder(int scale) {
		super("Manchester Encoding", scale); // Constructor with scale
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		super.encode(g, data); // Call superclass method to set up drawing

		g.setColor(Color.BLACK); // Set color to black
		for (char ch : data.toCharArray()) {
			if (ch == prev) { // If current bit is same as previous bit
				g.drawLine(x, up, x, down); // Draw transition line
			}

			if (ch == '0') { // If current bit is '0'
				g.drawLine(x, up, x + length / 2, up); // Draw half up line
				g.drawLine(x + length / 2, up, x + length / 2, down); // Draw mid-bit vertical line from up to down
				g.drawLine(x + length / 2, down, x + length, down); // Draw half down line
				x = x + length; // Update x-coordinate
			} else { // If current bit is '1'
				g.drawLine(x, down, x + length / 2, down); // Draw half down line
				g.drawLine(x + length / 2, up, x + length / 2, down); // Draw mid-bit vertical line from down to up
				g.drawLine(x + length / 2, up, x + length, up); // Draw half up line
				x = x + length; // Update x-coordinate
			}
			prev = ch; // Update previous bit
			g.setColor(new Color(254, 189, 56)); // Set color for signal bit separator
			g.drawLine(x, level + 5, x, level - 5); // Draw bit separator
			g.setColor(Color.BLACK); // Reset color to black
		}
		g.setColor(Color.BLUE); // Set color to blue
		g.drawLine(x, up - 10, x, down + 10); // Draw final vertical line
		g.drawLine(x, level, x1, level); // Draw horizontal line to connect with initial position
		g.drawString(name, x + 5, level); // Draw encoding name
	}
}
