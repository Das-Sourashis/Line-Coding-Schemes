package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * RZEncoder: Implements Return-to-Zero (RZ) encoding.
 * 
 * RZ encoding is a line encoding technique where each bit is represented by a
 * signal that returns to zero at the midpoint of the bit period. For '0' bit,
 * the signal remains at the low level during the first half of the bit period
 * and returns to zero during the second half. For '1' bit, the signal remains
 * at the high level during the first half of the bit period and returns to zero
 * during the second half.
 * 
 * Extends Encoder to draw RZ encoded signal.
 * 
 * Author: Sourashis Das
 */
public class RZEncoder extends Encoder {

	// Constructors
	public RZEncoder() {
		super("RZ Encoding", 0); // Default constructor with no scale
	}

	public RZEncoder(int scale) {
		super("RZ Encoding", scale); // Constructor with scale
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		super.encode(g, data); // Call superclass method to set up drawing

		g.setColor(Color.BLACK); // Set color to black

		// Iterate through each bit in the data
		for (char ch : data.toCharArray()) {
			if (ch == '0') { // If current bit is '0'
				g.drawLine(x, level, x, down); // Draw vertical line from level to down
				g.drawLine(x, down, x + length / 2, down); // Draw half down line
				g.drawLine(x + length / 2, level, x + length / 2, down); // Draw mid-bit vertical line from level to
																			// down
				g.drawLine(x + length / 2, level, x + length, level); // Draw half level line
				x = x + length; // Update x-coordinate
			} else { // If current bit is '1'
				g.drawLine(x, level, x, up); // Draw vertical line from level to up
				g.drawLine(x, up, x + length / 2, up); // Draw half up line
				g.drawLine(x + length / 2, level, x + length / 2, up); // Draw mid-bit vertical line from level to up
				g.drawLine(x + length / 2, level, x + length, level); // Draw half level line
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
