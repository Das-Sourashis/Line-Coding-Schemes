package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * NRZIEncoder: Implements NRZ-I encoding.
 * 
 * NRZ-I (Non-Return-to-Zero Inverted) encoding is a line encoding technique where a transition 
 * occurs at the beginning of a '0' bit, and there is no transition for a '1' bit. 
 * 
 * Extends Encoder to draw NRZ-I encoded signal.
 * 
 * Author: Sourashis Das
 */

public class NRZIEncoder extends Encoder {

	// Constructors
	public NRZIEncoder() {
		super("NRZ-I Encoding", 0); // Default constructor with no scale
	}

	public NRZIEncoder(int scale) {
		super("NRZ-I Encoding", scale); // Constructor with scale
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		super.encode(g, data); // Call superclass method to set up drawing

		g.setColor(Color.BLACK);
		prev = '0'; // Initialize prev to '0' as the initial state

		// Iterate through each character in the data
		for (char ch : data.toCharArray()) {
			if (ch == '0') { // If current bit is '0'
				g.setColor(Color.RED); // Set color to red
				g.drawLine(x, up, x, down); // Draw vertical line

				g.setColor(Color.BLACK); // Reset color to black
				if (prev == '0') { // If previous state was '0'
					g.drawLine(x, up, x + length, up); // Draw horizontal line at upper level
					x = x + length; // Update x-coordinate
					prev = '1'; // Update previous state to '1'
				} else if (prev == '1') { // If previous state was '1'
					prev = '0'; // Update previous state to '0'
					g.drawLine(x, down, x + length, down); // Draw horizontal line at lower level
					x = x + length; // Update x-coordinate
				}

			} else if (ch == '1') { // If current bit is '1'
				if (prev == '0') // If previous state was '0'
					g.drawLine(x, down, x + length, down); // Draw horizontal line at lower level
				else
					g.drawLine(x, up, x + length, up); // Draw horizontal line at upper level
				x = x + length; // Update x-coordinate
			}
			g.setColor(new Color(254, 189, 56)); // Set color for transition lines
			g.drawLine(x, level + 5, x, level - 5); // Draw transition line
			g.setColor(Color.BLACK); // Reset color to black
		}

		g.setColor(Color.BLUE); // Set color to blue
		g.drawLine(x, up - 10, x, down + 10); // Draw final vertical line
		g.drawLine(x, level, x1, level); // Draw horizontal line to connect with initial position
		g.drawString(name, x + 5, level); // Draw encoding name
	}
}
