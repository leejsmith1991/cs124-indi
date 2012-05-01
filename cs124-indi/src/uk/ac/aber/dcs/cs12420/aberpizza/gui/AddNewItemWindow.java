package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;

public class AddNewItemWindow extends JFrame implements ActionListener,
		MouseListener {
	private static final long serialVersionUID = -4708376627751121406L;

	private ItemType type;
	private JTextField nameText, smallText, medText, largeText, priceText;
	private JTextArea descriptionText;
	private JButton submit;
	private JLabel warningMessage;
	private JPanel namePane, pricePane, submitPane, descriptionPane;

	private ArrayList<String> existing;

	private Font f = new Font("Arial", Font.PLAIN, 12);

	public AddNewItemWindow(ItemType type) {
		this.type = type;
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("Create new " + type.toString().toLowerCase());

		JPanel topPanel = new JPanel(new BorderLayout());
		namePane = getNamePane();
		topPanel.add(namePane, BorderLayout.NORTH);

		if (type ==ItemType.PIZZA){
			pricePane = getPriceMultiPane();
		} else {
			pricePane = getPriceSinglePane();
		}
		
		topPanel.add(pricePane, BorderLayout.SOUTH);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));
		topPanel.setPreferredSize(new Dimension(650, namePane.getHeight() + pricePane.getHeight() + 15));
		this.add(topPanel, BorderLayout.NORTH);

		descriptionPane = getDescriptionPane();
		this.add(descriptionPane, BorderLayout.CENTER);

		submitPane = getSubmitPane();
		this.add(submitPane, BorderLayout.SOUTH);

		int height = namePane.getHeight() + pricePane.getHeight()
				+ descriptionPane.getHeight() + submitPane.getHeight();
		this.setSize(new Dimension(650, height));
		this.setResizable(false);
		this.setVisible(true);

	}

	private JPanel getNamePane() {
		JPanel thisPane = new JPanel();

		JLabel nameLabel = new JLabel("Enter name of new "
				+ type.toString().toLowerCase() + ":        ", SwingConstants.RIGHT);
		nameLabel.setPreferredSize(new Dimension(200, 25));
		thisPane.add(nameLabel);

		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(200, 25));
		thisPane.add(nameText);
		thisPane.setSize(new Dimension(650, 40));
		return thisPane;
	}

	private JPanel getPriceSinglePane(){
		JPanel thisPane = new JPanel(new GridLayout(1, 2));

			JLabel priceLabel = new JLabel("Enter price for "
					+ type.toString().toLowerCase() + ": �        ",
					SwingConstants.RIGHT);
			priceLabel.setPreferredSize(new Dimension(315, 25));
			thisPane.add(priceLabel);

			priceText = new JTextField();
			priceText.setFont(f);
			thisPane.add(priceText);
			priceText.setPreferredSize(new Dimension(315, 25));
			
			thisPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			thisPane.setSize(new Dimension(650, 35));
			
			return thisPane;
	}

	private JPanel getPriceMultiPane() {
		JPanel thisPane = new JPanel(new GridLayout(3, 2));

		JLabel smallLabel = new JLabel("Enter price for small "
				+ type.toString().toLowerCase() + ": �        ", SwingConstants.RIGHT);
		smallLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(smallLabel);

		smallText = new JTextField();
		smallText.setBorder(BorderFactory.createEmptyBorder(0,0,1,0));
		smallText.setPreferredSize(new Dimension(315, 25));
		thisPane.add(smallText);

		JLabel medLabel = new JLabel("Enter price for medium "
				+ type.toString().toLowerCase() + ": �        ", SwingConstants.RIGHT);
		medLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(medLabel);

		medText = new JTextField();
		medText.setPreferredSize(new Dimension(315, 25));
		medText.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		medText.setBorder(BorderFactory.createEmptyBorder(1,0,1,0));
		thisPane.add(medText);

		JLabel largeLabel = new JLabel("Enter price for large "
				+ type.toString().toLowerCase() + ": �        ", SwingConstants.RIGHT);
		largeLabel.setPreferredSize(new Dimension(315, 25));
		thisPane.add(largeLabel);
		
		largeText = new JTextField();
		largeText.setBorder(BorderFactory.createEmptyBorder(1,0,0,0));
		largeText.setPreferredSize(new Dimension(315, 25));
		
		thisPane.add(largeText);
		
		thisPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		thisPane.setSize(new Dimension(650, 100));
		return thisPane;
	}

	private JPanel getDescriptionPane() {

		JPanel thisPane = new JPanel(new BorderLayout());

		JLabel descLabel = new JLabel("Enter Description of the "
				+ type.toString().toLowerCase(), SwingConstants.LEFT);
		descLabel.setPreferredSize(new Dimension(650, 25));

		descriptionText = new JTextArea();
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
		thisPane.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		thisPane.setSize(new Dimension(650, 300));
		return thisPane;
	}

	private JPanel getSubmitPane() {
		JPanel thisPane = new JPanel(new BorderLayout());

		submit = new JButton("Add " + type.toString().toLowerCase()
				+ " to system");
		submit.setPreferredSize(new Dimension(200, 50));
		submit.addActionListener(this);
		submit.addMouseListener(this);
		thisPane.add(submit, BorderLayout.NORTH);

		warningMessage = new JLabel();
		warningMessage.setPreferredSize(new Dimension(650, 25));
		thisPane.add(warningMessage, BorderLayout.SOUTH);
		thisPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		thisPane.setSize(new Dimension(650, 80));
		return thisPane;
	}

	private void createNew() throws NumberFormatException, IOException {
		existing = new ArrayList<String>();
		Scanner sc;
		String fileName = "";
		if (type == ItemType.PIZZA) {
			sc = new Scanner(new BufferedInputStream(new FileInputStream(
					"pizzas.txt")));
			fileName = "pizzas";
			while (sc.hasNextLine()) {
				existing.add(sc.nextLine());
			}
		} else if (type == ItemType.SIDE) {
			sc = new Scanner(new BufferedInputStream(new FileInputStream(
					"sides.txt")));
			fileName = "sides";
			while (sc.hasNextLine()) {
				existing.add(sc.nextLine());
			}
		} else {
			sc = new Scanner(new BufferedInputStream(new FileInputStream(
					"drinks.txt")));
			fileName = "drinks";
			while (sc.hasNextLine()) {
				existing.add(sc.nextLine());
			}
		}

		if (nameText.getText().equals("") || smallText.getText().equals("")
				|| medText.getText().equals("")
				|| largeText.getText().equals("")
				|| descriptionText.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Fields not Complete, Fill All fields to continue");
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
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"Invalid entry for Prices");
				}

				existing.add(smallPrice.toString());
				existing.add(medPrice.toString());
				existing.add(largePrice.toString());
			} else {
				BigDecimal itemPrice = new BigDecimal("0.00");
				try {
					itemPrice = new BigDecimal(priceText.getText());
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"Invalid entry for Prices");
				}
				existing.add(itemPrice.toString());
			}
			if (descriptionText.getText().contains("\n")) {
				descriptionText.setText(descriptionText.getText().replaceAll(
						"\n", ". "));
			}
			existing.add(descriptionText.getText());

			saveFile(fileName);
		}
	}

	public void saveFile(String fileName) throws IOException {
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(fileName + ".txt"));
		for (int i = 0; i < existing.size(); i++) {
			bw.write(existing.get(i));
			bw.newLine();
		}
		bw.close();
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Submit")) {
			try {
				createNew();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getComponent() == submit) {
			// warningMessage.setText("Warning, Changes will be appiled upon restart");
		}

	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getComponent() == submit) {
			// warningMessage.setText("");
		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub

	}
}
