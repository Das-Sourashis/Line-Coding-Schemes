package encode_scheme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * LineEncodingGUI: A GUI application for demonstrating various line encoding
 * techniques in COMPUTER NETWORK.
 * 
 * This application provides a graphical user interface for users to input
 * binary data and select different line encoding techniques commonly used in
 * computer networks. Users can encode the input data using the selected
 * technique and visualize the encoded signal.
 * 
 * @Author Sourashis Das
 */

public class LineEncodingGUI extends JFrame implements ActionListener {
	JTextField inpData = new JTextField(12); // Input data field
	JTextField rcvData = new JTextField(12); // Received data field
	JComboBox<String> techniques = null; // Dropdown for selecting encoding technique
	JPanel drawPanel = new JPanel(); // Panel for drawing encoded signals

	// Constructor for LineEncodingGUI
	public LineEncodingGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setTitle("Line Encoding Techniques");
		initComponents(); // Initialize GUI components
	}

	// Method to initialize GUI components
	private void initComponents() {
		JPanel topPanel = new JPanel(); // Top panel for input fields and buttons
		topPanel.add(new JLabel("Input Data:"));
		topPanel.add(inpData);
		String list[] = { "NRZ-I", "NRZ-L", "RZ", "Manchester", "Differential Manchester", "AMI", "Pseudoternary",
				"All" };
		techniques = new JComboBox<String>(list);
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		topPanel.add(new JLabel("Received Data:"));
		topPanel.add(rcvData);
		JButton encodeBtn = new JButton("Encode");
		JButton decodeBtn = new JButton("Decode");
		JButton clrBtn = new JButton("Clear");
		topPanel.add(encodeBtn);
		encodeBtn.addActionListener(this);
		topPanel.add(decodeBtn);
		decodeBtn.addActionListener(this);
		topPanel.add(clrBtn);
		clrBtn.addActionListener(this);
		drawPanel.setBackground(Color.WHITE);
		topPanel.setBackground(Color.LIGHT_GRAY);
		add(drawPanel); // Add drawPanel to JFrame
		add(topPanel, BorderLayout.NORTH); // Add topPanel to JFrame

	}

	// Main method to start the application
	public static void main(String[] args) {
		LineEncodingGUI f = new LineEncodingGUI();
		f.setVisible(true); // Make the frame visible
	}

	// Action listener for button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Encode")) { // If Encode button is clicked
			encode(); // Call encode method
		} else if (e.getActionCommand().equals("Decode")) { // If Decode button is clicked
			decode(); // Call decode method
		} else if (e.getActionCommand().equals("Clear")) { // If Clear button is clicked
			repaint(); // Repaint the panel
		}

	}

	// Method to encode input data
	private void encode() {
		String scheme = techniques.getSelectedItem().toString(); // Get selected encoding technique
		String data = inpData.getText(); // Get input data
		Graphics2D g = (Graphics2D) drawPanel.getGraphics(); // Get graphics object
		g.clearRect(0, 0, 1600, 1600); // Clear previous drawings
		if (checkValidData(data)) { // Check if input data is valid
			EncoderFactory.encode(g, scheme, data); // Call encode method from EncoderFactory
			actual_data aData = new actual_data(); // Create an instance of actual_data class
			aData.actual_data_signal(g, data); // Draw actual data signal
		} else {
			JOptionPane.showMessageDialog(null,
					"Invalid input! Please enter a binary string with length between 1 and 40.", "Invalid input",
					JOptionPane.ERROR_MESSAGE); // Show error message for invalid input
		}

	}

	// Method to check if input data is valid
	private boolean checkValidData(String str) {

		if (str != null && str.length() > 0 && str.length() <= 40) {
			// Check if the string contains only '0' and '1'
			for (char c : str.toCharArray()) {
				if (c != '0' && c != '1') {
					return false; // Found a character that is not '0' or '1'
				}
			}
			return true; // String is valid (contains only '0' and '1')
		} else {
			return false; // String is null or its length is not between 1 and 40
		}
	}

	// Method to decode received data
	private void decode() {
		drawPanel.getGraphics().drawString("TO BE DONE LATTER......", 400, 100); // Display message for decoding (to be
																					// implemented)
	}

}
