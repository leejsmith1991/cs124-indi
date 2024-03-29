package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;

/**
 * Allows a user to add a new entry to the Pizzas, Sides or Drinks files Pizzas
 * will have 3 Text Fields where the user can enter different values for the
 * different sizes of pizza, Small, Medium, and Large, Sides and Drinks only
 * have one field where the user can enter the price of the item<br>
 * <br>
 * 
 * Implements <code>ActionListener</code> to listen to button presses from the
 * JFrame
 * 
 * @author Lee Smith
 * 
 */

public class AddNewItemWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -4708376627751121406L;

	private ItemType type;
	private JTextField nameText, smallText, medText, largeText, priceText;
	private JTextArea descriptionText;
	private JButton submit;
	private JLabel warningMessage;
	private JPanel namePane, pricePane, submitPane, descriptionPane;

	private String fileName = "";

	private ArrayList<String> existing = new ArrayList<String>();;

	private Font f = new Font("Arial", Font.PLAIN, 12);

	/**
	 * Constructs a new JFrame and layouts of JPanel components from the private
	 * methods that layout the individual panels. Also parameter sets the type
	 * of window that will be displayed, for a Pizza will allow 3 fields for
	 * price entry, for Sides and Drinks will allow only one field
	 * 
	 * @param type
	 */

	public AddNewItemWindow(ItemType type) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		this.type = type;
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("Create new " + type.toString().toLowerCase());

		JPanel topPanel = new JPanel(new BorderLayout());
		namePane = getNamePane();
		topPanel.add(namePane, BorderLayout.NORTH);

		if (type == ItemType.PIZZA) {
			pricePane = getPriceMultiPane();
		} else {
			pricePane = getPriceSinglePane();
		}

		topPanel.add(pricePane, BorderLayout.SOUTH);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		topPanel.setPreferredSize(new Dimension(650, namePane.getHeight()
				+ pricePane.getHeight() + 15));
		this.add(topPanel, BorderLayout.NORTH);

		descriptionPane = getDescriptionPane();
		this.add(descriptionPane, BorderLayout.CENTER);

		submitPane = getSubmitPane();
		this.add(submitPane, BorderLayout.SOUTH);

		int height = namePane.getHeight() + pricePane.getHeight()
				+ descriptionPane.getHeight() + submitPane.getHeight();
		this.setSize(new Dimension(650, height));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Creates a JPanel with a label and text field where the user can enter the
	 * name of the new item to be created
	 * 
	 * @see JPanel
	 * @see JLabel
	 * @see JTextField
	 * @return JPanel
	 */

	private JPanel getNamePane() {
		JPanel thisPane = new JPanel();

		JLabel nameLabel = new JLabel("Enter name of new "
				+ type.toString().toLowerCase() + ":        ",
				SwingConstants.RIGHT);
		nameLabel.setPreferredSize(new Dimension(200, 25));
		thisPane.add(nameLabel);

		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(200, 25));
		nameText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		thisPane.add(nameText);
		thisPane.setSize(new Dimension(650, 40));
		return thisPane;
	}

	/**
	 * Creates a JPanel with 1 label and 1 text field box where the user can
	 * input the price of the item to be created.
	 * 
	 * @see JPanel
	 * @see JLabel
	 * @see JTextField
	 * @return JPanel
	 */
	private JPanel getPriceSinglePane() {
		JPanel thisPane = new JPanel(new GridLayout(1, 2));

		JLabel priceLabel = new JLabel("Enter price for "
				+ type.toString().toLowerCase() + ": �        ",
				SwingConstants.RIGHT);
		priceLabel.setPreferredSize(new Dimension(200, 25));
		thisPane.add(priceLabel);

		priceText = new JTextField();
		priceText.setFont(f);
		priceText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		thisPane.add(priceText);
		priceText.setPreferredSize(new Dimension(200, 25));

		thisPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		thisPane.setSize(new Dimension(650, 35));

		return thisPane;
	}

	/**
	 * Creates a JPanel with 3 labels (Small:, Medium:, Large:) and 3 txt fields
	 * where the user can enter the Small Medium and Large prices respectively.
	 * 
	 * @see JPanel
	 * @see JLabel
	 * @see JTextField
	 * @return JPanel
	 */

	private JPanel getPriceMultiPane() {
		JPanel thisPane = new JPanel(new GridLayout(3, 1, 0, 5));

		JLabel smallLabel = new JLabel("Enter price for small "
				+ type.toString().toLowerCase() + ": �        ",
				SwingConstants.RIGHT);
		smallLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(smallLabel);

		smallText = new JTextField();
		smallText.setPreferredSize(new Dimension(200, 25));
		smallText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		thisPane.add(smallText);

		JLabel medLabel = new JLabel("Enter price for medium "
				+ type.toString().toLowerCase() + ": �        ",
				SwingConstants.RIGHT);
		medLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(medLabel);

		medText = new JTextField();
		medText.setPreferredSize(new Dimension(315, 25));
		medText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		thisPane.add(medText);

		JLabel largeLabel = new JLabel("Enter price for large "
				+ type.toString().toLowerCase() + ": �        ",
				SwingConstants.RIGHT);
		largeLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(largeLabel);

		largeText = new JTextField();
		largeText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		largeText.setPreferredSize(new Dimension(315, 25));

		thisPane.add(largeText);

		thisPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		thisPane.setSize(new Dimension(650, 100));
		return thisPane;
	}

	/**
	 * Creates a JPanel where the user can enter the description of the new item
	 * 
	 * @see JPanel
	 * @see JTextArea
	 * @see JLabel
	 * @return JPanel
	 */
	private JPanel getDescriptionPane() {

		JPanel thisPane = new JPanel(new BorderLayout());

		JLabel descLabel = new JLabel("Enter Description of the "
				+ type.toString().toLowerCase(), SwingConstants.LEFT);
		descLabel.setPreferredSize(new Dimension(650, 25));

		descriptionText = new JTextArea("");
		descriptionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setFont(f);

		JScrollPane descScroll = new JScrollPane(descriptionText);

		descScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		descScroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		descScroll.setPreferredSize(new Dimension(550, 200));

		thisPane.add(descLabel, BorderLayout.NORTH);
		thisPane.add(descScroll, BorderLayout.SOUTH);
		thisPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		thisPane.setSize(new Dimension(650, 300));
		return thisPane;
	}

	/**
	 * Creates a JPanel with a add item button
	 * 
	 * @see ActionListener
	 * @see JPanel
	 * @see JButton
	 * @return
	 */

	private JPanel getSubmitPane() {
		JPanel thisPane = new JPanel(new BorderLayout());

		submit = new JButton("Add to System");
		submit.setPreferredSize(new Dimension(200, 50));
		submit.addActionListener(this);
		thisPane.add(submit, BorderLayout.NORTH);

		warningMessage = new JLabel("");
		warningMessage.setPreferredSize(new Dimension(650, 25));
		thisPane.add(warningMessage, BorderLayout.SOUTH);
		thisPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		thisPane.setSize(new Dimension(650, 80));
		return thisPane;
	}

	/**
	 * Amends the resource file to be allow the new item to be added to the end
	 * of the resource file. <br>
	 * <br>
	 * This happens by scanning the resource file adding line entries to an
	 * <code>ArrayList</code> then adding the new entries to the bottom of the
	 * list then running the save file method.
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */

	private void createNew() throws Exception, IOException {

		getFileContent();
		try {
		if (nameText.getText().equals("") || smallText.getText().equals("")
				|| medText.getText().equals("")
				|| largeText.getText().equals("")
				|| descriptionText.getText().equals("")) {
			throw new Exception();
		} else {
			existing.add(nameText.getText());
			if (type == ItemType.PIZZA) {
				BigDecimal smallPrice = new BigDecimal("0.00");
				BigDecimal medPrice = new BigDecimal("0.00");
				BigDecimal largePrice = new BigDecimal("0.00");
				try {
					smallPrice = new BigDecimal(smallText.getText());
					medPrice = new BigDecimal(medText.getText());
					largePrice = new BigDecimal(largeText.getText());
					existing.add(smallPrice.toString());
					existing.add(medPrice.toString());
					existing.add(largePrice.toString());
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"Invalid entry for Prices");
				}
			} else {
				BigDecimal itemPrice = new BigDecimal("0.00");
				try {
					itemPrice = new BigDecimal(priceText.getText());
					existing.add(itemPrice.toString());
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"Invalid entry for Prices");
				}
			}
			
			if (descriptionText.getText().contains("\n")) {
				descriptionText.setText(descriptionText.getText().replaceAll(
						"\n", ". "));
			}
			existing.add(descriptionText.getText());

			saveFile(fileName);
		}
		} catch (Exception ef){
			JOptionPane.showMessageDialog(null, "An Error Occured");
		}
	}

	/**
	 * Saves the file using the <code>ArrayList</code> created in
	 * <code>createNew()</code> method
	 * 
	 * @param fileName
	 * @throws IOException
	 */

	public void saveFile(String fileName) throws IOException {
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(fileName + ".txt"));
		for (int i = 0; i < existing.size(); i++) {
			bw.write(existing.get(i));
			if (i < existing.size() - 1) {
				bw.newLine();
			}
		}
		bw.close();
		JOptionPane.showMessageDialog(null, "Content Saved");
		this.dispose();
	}

	public void getFileContent() {
		Scanner sc;
		try {
			if (type == ItemType.PIZZA) {
				sc = new Scanner(new BufferedInputStream(new FileInputStream(
						"pizzas.txt")));
				setFileName("pizzas");
				while (sc.hasNextLine()) {
					existing.add(sc.nextLine());
				}
			} else if (type == ItemType.SIDE) {
				sc = new Scanner(new BufferedInputStream(new FileInputStream(
						"sides.txt")));
				setFileName("sides");
				while (sc.hasNextLine()) {
					existing.add(sc.nextLine());
				}
			} else {
				sc = new Scanner(new BufferedInputStream(new FileInputStream(
						"drinks.txt")));
				setFileName("drinks");
				while (sc.hasNextLine()) {
					existing.add(sc.nextLine());
				}
			}
		} catch (FileNotFoundException fnf) {
			JOptionPane.showMessageDialog(null,
					"Resource File Missing, Seek Adminitative Help");
			this.dispose();
		}
	}

	/**
	 * Overrides the <code>actionPerformed(ActionEvent arg0)</code> method in
	 * the ActionListener interface
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Add to System")) {
			try {
				createNew();
				this.dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "An Error Occurred");
			}
		}
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
}
