package encode_scheme;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * actual_data: Class to draw the actual data signal between two computers.
 * 
 * 
 * This class extends the Encoder class to draw the actual data signal between
 * two computers .
 * 
 * It overrides the encode method to customize the drawing of the actual data
 * signal.
 * 
 * @author Sourashis Das
 */
public class actual_data extends Encoder {

	// Constructor for actual_data class
	public actual_data() {
		super("NRZ", -80); // Call superclass constructor with name and scale
	}

	/**
	 * actual_data_signal: Method to draw the actual data signal.
	 * 
	 * @param g    The Graphics2D object to draw the signal.
	 * @param data The input binary data to be encoded.
	 */
	public void actual_data_signal(Graphics2D g, String data) {
		g.setColor(Color.BLACK); // Set color to black for drawing signal boundaries
		g.drawLine(x1, up - 10, x1, down + 10); // Draw initial vertical line

		g.setColor(Color.GREEN); // Set color to green for drawing signal

		prev = ' '; // Initialize previous character as blank
		length = data.length(); // Get length of the data
		length = (y - x) / length; // Calculate length of each segment

		// Iterate through each character in the data
		for (char ch : data.toCharArray()) {
			if (ch != prev)
				g.drawLine(x, up, x, level); // Draw vertical line if there is a change in signal
			if (ch == '0') {
				g.drawLine(x, level, x + length, level); // Draw horizontal line for '0' bit
				x = x + length; // Update x-coordinate
			} else {
				g.drawLine(x, up, x + length, up); // Draw horizontal line for '1' bit
				x = x + length; // Update x-coordinate
			}
			prev = ch; // Update previous character

			g.setColor(Color.RED); // Set color to red for drawing signal transitions
			g.drawLine(x - length, level + 5, x - length, level - 5); // Draw transition line
			g.setColor(Color.GREEN); // Reset color to green for next segment
		}

		g.drawLine(x, up - 10, x, down + 10); // Draw final vertical line
		g.setColor(Color.BLACK); // Reset color to black
		g.drawLine(x1, level, x, level); // Draw horizontal line to connect with initial position
		g.drawString(name, x + 5, level); // Draw encoding name
	}
}
