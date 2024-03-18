package encode_scheme;

import java.awt.Graphics2D;

/**
 * AllEncoder: Combines multiple encoding schemes into one.
 * 
 * This class integrates different encoding schemes, including NRZ-L, NRZ-I, RZ,
 * Manchester, Differential Manchester, AMI, and Pseudoternary encoding. It
 * encodes the given data using each scheme and draws their corresponding
 * signals.
 * 
 * Author: Sourashis Das
 */
public class AllEncoder extends Encoder {

	// Constructor
	public AllEncoder() {
		super("ALL Encoding", 0); // Default constructor with ALL encoding
	}

	// Override encode method
	public void encode(Graphics2D g, String data) {
		int separation = 80; // Separation between each encoding scheme

		// Create instances of each encoding scheme with different scales
		NRZLEncoder nrzl = new NRZLEncoder(separation * 0);
		NRZIEncoder nrzi = new NRZIEncoder(separation * 1);
		RZEncoder rz = new RZEncoder(separation * 2);
		ManchesterEncoder man = new ManchesterEncoder(separation * 3);
		DifferentialManchesterEncoder dman = new DifferentialManchesterEncoder(separation * 4);
		AMIEncoder ami = new AMIEncoder(separation * 5);
		PseudoternaryEncoder ptn = new PseudoternaryEncoder(separation * 6);

		// Encode data using each scheme
		nrzl.encode(g, data);
		nrzi.encode(g, data);
		rz.encode(g, data);
		man.encode(g, data);
		dman.encode(g, data);
		ami.encode(g, data);
		ptn.encode(g, data);
	}
}
