package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * DifferentialManchesterEncoder: Implements Differential Manchester encoding.
 * 
 * Differential Manchester encoding is a line encoding technique where each bit
 * is represented by the presence or absence of a transition. In this encoding,
 * no transition at the start of a bit period represents a '1', while a
 * transition at the start of a bit period represents a '0'. Additionally, there
 * is a transition in the middle of each bit period. Specifically, for '0' bit,
 * a transition occurs at the beginning of the bit period, and for '1' bit,
 * there is no transition at the beginning of the bit period; the signal remains
 * in the same state as the previous bit.
 * 
 * Extends Encoder to draw Differential Manchester encoded signal.
 * 
 * Author: Sourashis Das
 */
public class DifferentialManchesterEncoder extends Encoder {

	// Constructors
	public DifferentialManchesterEncoder() {
		super("Differential Manchester Encoding", 0); // Default constructor with no scale
	}

	public DifferentialManchesterEncoder(int scale) {
		super("Differential Manchester Encoding", scale); // Constructor with scale
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		super.encode(g, data); // Call superclass method to set up drawing

		g.setColor(Color.BLACK); // Set color to black
		state = false; // Initialize state to false

		// Iterate through each bit in the data
		for (char ch : data.toCharArray()) {
			if (ch == '0') { // If current bit is '0'
				g.setColor(Color.RED); // Set color to red
				g.drawLine(x, up, x, down); // Draw transition line
				g.setColor(Color.BLACK); // Reset color to black
				state = !state; // Invert state
			}
			// Determine the transition based on the state
			if (state == false) {
				g.drawLine(x, down, x + length / 2, down); // Draw half down line
				g.drawLine(x + length / 2, up, x + length / 2, down); // Draw mid-bit vertical line from down to up
				g.drawLine(x + length / 2, up, x + length, up); // Draw half up line
				x = x + length; // Update x-coordinate
				state = true; // Set state to true
			} else {
				g.drawLine(x, up, x + length / 2, up); // Draw half up line
				g.drawLine(x + length / 2, up, x + length / 2, down); // Draw mid-bit vertical line from up to down
				g.drawLine(x + length / 2, down, x + length, down); // Draw half down line
				x = x + length; // Update x-coordinate
				state = false; // Set state to false
			}
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
