package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * AMIEncoder: Implements Alternate Mark Inversion (AMI) encoding.
 * 
 * AMI encoding is a line encoding technique where each bit is represented by
 * the presence or absence of a transition. It uses three voltage levels where a
 * '0' bit is represented by no transition, and a '1' bit is represented by
 * alternating positive and negative voltage transitions. Specifically, for '1'
 * bit, the signal transitions between positive and negative levels, while for
 * '0' bit, there is no transition and the signal remains at the ground or 0
 * level.
 * 
 * Extends Encoder to draw AMI encoded signal.
 * 
 * Author: Sourashis Das
 */
public class AMIEncoder extends Encoder {

	// Constructors
	public AMIEncoder() {
		super("AMI Encoding", 0); // Default constructor with no scale
	}

	public AMIEncoder(int scale) {
		super("AMI Encoding", scale); // Constructor with scale
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		super.encode(g, data); // Call superclass method to set up drawing

		// Initialize previous bit and state
		prev = '0';
		state = false;

		g.setColor(Color.BLACK); // Set color to black

		// Iterate through each bit in the data
		for (char ch : data.toCharArray()) {
			if (ch == '1') { // If current bit is '1'
				// Draw signal parts based on previous state and bit value
				if (prev == '1' && state == true) {
					g.drawLine(x, up, x, down);
					g.drawLine(x, down, x + length, down);
					state = false;
				} else if (prev == '1' && state == false) {
					g.drawLine(x, up, x, down);
					g.drawLine(x, up, x + length, up);
					state = true;
				} else if (prev == '0' && state == true) {
					g.drawLine(x, level, x, down);
					g.drawLine(x, down, x + length, down);
					state = false;
				} else if (prev == '0' && state == false) {
					g.drawLine(x, level, x, up);
					g.drawLine(x, up, x + length, up);
					state = true;
				}
				// Update x-coordinate
				x = x + length;
			} else { // If current bit is '0'
				// Draw signal parts based on previous state and bit value
				if (prev == '1' && state == false) {
					g.drawLine(x, level, x, down);
					g.drawLine(x, level, x + length, level);
				} else if (prev == '1' && state == true) {
					g.drawLine(x, level, x, up);
					g.drawLine(x, level, x + length, level);
				} else {
					g.drawLine(x, level, x + length, level);
				}
				// Update x-coordinate
				x = x + length;
			}
			// Draw signal bit separator
			g.setColor(new Color(254, 189, 56));
			g.drawLine(x, level + 5, x, level - 5);
			// Reset color to black
			g.setColor(Color.BLACK);
			// Update previous bit
			prev = ch;
		}
		// Draw final vertical line
		g.setColor(Color.BLUE);
		g.drawLine(x, up - 10, x, down + 10);
		// Draw horizontal line to connect with initial position
		g.drawLine(x, level, x1, level);
		// Draw encoding name
		g.drawString(name, x + 5, level);
	}
}
