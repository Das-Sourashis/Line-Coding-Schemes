package encode_scheme;

import java.awt.Graphics2D;

/**
 * EncoderFactory: Factory class for creating different types of encoders based
 * on the selected scheme.
 * 
 * This class provides methods for creating encoders based on the selected line
 * encoding scheme. It uses a factory pattern to instantiate the appropriate
 * encoder based on the scheme chosen by the user.
 * 
 * @author Sourashis Das
 */
public class EncoderFactory {

	/**
	 * encode: Encodes the input data using the specified encoding scheme.
	 * 
	 * @param g      The Graphics2D object to draw the encoded signal.
	 * @param scheme The selected line encoding scheme.
	 * @param data   The input binary data to be encoded.
	 */
	public static void encode(Graphics2D g, String scheme, String data) {
		createEncoder(scheme).encode(g, data);
	}

	/**
	 * createEncoder: Creates an encoder instance based on the specified encoding
	 * scheme.
	 * 
	 * @param scheme The selected line encoding scheme.
	 * @return An instance of the corresponding encoder for the specified scheme.
	 */
	public static Encoder createEncoder(String scheme) {
		if (scheme.equals("Manchester")) {
			return new ManchesterEncoder();
		} else if (scheme.equals("Differential Manchester")) {
			return new DifferentialManchesterEncoder();
		} else if (scheme.equals("NRZ-L")) {
			return new NRZLEncoder();
		} else if (scheme.equals("NRZ-I")) { // Note: NRZ-I encoder is not working
			return new NRZIEncoder();
		} else if (scheme.equals("RZ")) {
			return new RZEncoder();
		} else if (scheme.equals("AMI")) {
			return new AMIEncoder();
		} else if (scheme.equals("Pseudoternary")) {
			return new PseudoternaryEncoder();
		} else if (scheme.equals("All")) {
			return new AllEncoder();
		} else {
			return null;
		}
	}
}
