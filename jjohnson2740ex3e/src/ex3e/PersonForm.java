package ex3e;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonForm extends JFrame {

	private JPanel contentPane;
	
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField ageTextField;
	private JTextField phoneTextField;
	private JLabel outputLabel;
//	private PrintWriter outputFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonForm frame = new PersonForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonForm() {
		
		/* Was having issues opening file in Windowbuilder. This block of code seemed to be duplicate to the one below.  
		Commented out the next section here, and after, was able to get the file to open in Windowbuilder. */
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 520, 210);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);

		setTitle("Ex3E: File Output Converted to Static Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 56, 14);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(74, 8, 96, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 36, 56, 14);
		contentPane.add(lblAddress);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(74, 33, 96, 20);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(10, 61, 56, 14);
		contentPane.add(lblAge);
		
		ageTextField = new JTextField();
		ageTextField.setText("0");
		ageTextField.setBounds(74, 58, 96, 20);
		ageTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_ageTextField_focusGained(arg0);
			}
		});
		contentPane.add(ageTextField);
		ageTextField.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 86, 56, 14);
		contentPane.add(lblPhone);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(74, 83, 96, 20);
		contentPane.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		/* The html/end html tags here is a workaround that makes it possible to have multiple lines in the label.) */
		outputLabel = new JLabel("<html></html>");
		outputLabel.setBounds(180, 8, 300, 88);
		outputLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		outputLabel.setHorizontalAlignment(JLabel.LEFT);
		outputLabel.setVerticalAlignment(JLabel.TOP);
		contentPane.add(outputLabel);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_personButton_actionPerformed(e);
			}
		});
		saveButton.setBounds(71, 114, 89, 23);
		contentPane.add(saveButton);
		
		/* Write to file Part 1 of 3: Open the file. 
		 * Update this code to now call the Person class and tell IT to open the file. "Person.openFile("PersonData.txt");"
		 * Since the openFile static method of the Person class is boolean it returns true/false.
		 * We can use that to fire off an error if it doesn't work.
		 * Fully updated code below
		*/
//	    this.outputFile = new PrintWriter("PersonData.txt");
		boolean fileIsOpen = Person.openFile("PersonData.txt");
		if (!fileIsOpen) {
			outputLabel.setText("The file could not be opened.");
		}
		
	    //created listener for windowClosing window event. The do_this_windowClosing function is at the bottom of this class.
	    addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
	}
	
	protected void do_ageTextField_focusGained(FocusEvent arg0) {
		ageTextField.selectAll();
	}
	
	protected void do_personButton_actionPerformed(ActionEvent e) {
		int age = Integer.parseInt(ageTextField.getText());
		// Create person object with each save button click.
		Person person = new Person(nameTextField.getText(),
				addressTextField.getText(),
				age,
				phoneTextField.getText());
		//finds "first" </html> tag and replaces it with blank string.
		String strOutput = outputLabel.getText().replaceFirst("</html>", ""); 
		/* sends person object content to toString method and adds ending br and ending html tag (see above note on JLabel).*/
		outputLabel.setText(strOutput + person.toString() + "<br></html>" );
		
		/* Call the write method of the person class (Part 2 of 3).
		Pass it the reference to the PrintWriter object (outputFile). */
		person.write();
		
		this.nameTextField.setText("");
		this.addressTextField.setText("");
		this.ageTextField.setText("0");
		this.phoneTextField.setText("");
		this.nameTextField.requestFocus();
	}

	protected void do_this_windowClosing(WindowEvent arg0) {
		/*  Write to file (Part 3 of 3): Close the file we are writing to. 
		 * Now updated to tell the Person class to close the file.*/
//        this.outputFile.close();
		Person.closeFile();
	}
}
