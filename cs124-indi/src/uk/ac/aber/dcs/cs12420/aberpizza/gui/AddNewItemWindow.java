package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ItemType;

public class AddNewItemWindow extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = -4708376627751121406L;

	private ItemType type;
	private JTextField nameText, priceText;
	private JTextArea descriptionText;
	private JLabel nameLabel, priceLabel, descriptionLabel, warningMessage;
	private JButton submit;
	private JPanel namePane, pricePane, submitPane, descriptionPane, warningPane;
	private SpringLayout mainLayout;

	
	private ArrayList<String> existing;

	Font f = new Font("Arial", Font.PLAIN, 12);

	public AddNewItemWindow(ItemType type) {
		this.type = type;

		this.setTitle("Create new " + type.toString().toLowerCase());

		Container mainPane = this.getContentPane();
		mainLayout = new SpringLayout();

		namePane = new JPanel(new GridLayout(1, 2, 5, 5));
		namePane.add(getNameLabel());
		namePane.add(getNameText());

		pricePane = new JPanel(new GridLayout(1, 2, 5, 5));
		pricePane.add(getPriceLabel());
		pricePane.add(getPriceText());

		mainPane.add(namePane);
		mainPane.add(pricePane);
		mainLayout.putConstraint(SpringLayout.NORTH, namePane, 10,
				SpringLayout.NORTH, this);
		mainLayout.putConstraint(SpringLayout.NORTH, pricePane, 10,
				SpringLayout.SOUTH, namePane);

		descriptionPane = new JPanel(new BorderLayout());
		descriptionPane.add(getDescriptionLabel(), BorderLayout.NORTH);
		descriptionPane.add(getDescriptionText(), BorderLayout.SOUTH);

		mainLayout.putConstraint(SpringLayout.NORTH, descriptionPane, 10,
				SpringLayout.SOUTH, pricePane);

		mainPane.add(descriptionPane);

		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.addMouseListener(this);
		submit.setSize(new Dimension(200, 20));
		submitPane = new JPanel();
		submit.setSize(150, 50);
		submitPane.add(submit);
		warningPane = new JPanel();
		warningMessage = new JLabel();

		warningPane.setSize(new Dimension(512, 200));
		warningPane.add(warningMessage);
				
		mainLayout.putConstraint(SpringLayout.NORTH, submitPane, 10,
				SpringLayout.SOUTH, descriptionPane);
		mainLayout.putConstraint(SpringLayout.NORTH, warningPane, 10,
				SpringLayout.SOUTH, submitPane);
		mainPane.add(submitPane);
		mainPane.add(warningPane);
		mainPane.setLayout(mainLayout);

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(namePane.getWidth() + 18, 550);
	}

	private JLabel getNameLabel() {
		nameLabel = new JLabel("Enter the name of the "
				+ type.toString().toLowerCase() + " :", SwingConstants.LEFT);
		nameLabel.setFont(f);
		return nameLabel;
	}

	private JTextField getNameText() {
		nameText = new JTextField("", 15);
		nameText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nameText.setFont(f);
		return nameText;
	}

	private JLabel getPriceLabel() {
		priceLabel = new JLabel("Enter the price of the "
				+ type.toString().toLowerCase() + " :", SwingConstants.LEFT);
		priceLabel.setFont(f);
		return priceLabel;
	}

	private JTextField getPriceText() {
		priceText = new JTextField("", 15);
		priceText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		priceText.setFont(f);
		return priceText;
	}

	private JLabel getDescriptionLabel() {
		descriptionLabel = new JLabel("Enter Description for "
				+ type.toString().toLowerCase() + " : ");
		descriptionLabel.setFont(f);
		descriptionLabel.setVerticalAlignment(SwingConstants.NORTH);
		return descriptionLabel;
	}

	private JScrollPane getDescriptionText() {
		descriptionText = new JTextArea("");
		descriptionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setRows(1);
		
		
		JScrollPane descScroll = new JScrollPane(descriptionText);
		descScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		descScroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		descScroll.setPreferredSize(new Dimension(340, 100));
		descScroll.setLocation(0, 0);

		descriptionText.setFont(f);
		return descScroll;
	}

	private void createNew() throws NumberFormatException, IOException {
		existing = new ArrayList<String>();
		Scanner sc;
		String fileName = "";
		if (type == ItemType.PIZZA) {
			sc = new Scanner(new BufferedInputStream(new FileInputStream("pizzas.txt")));
			fileName = "pizzas";
			while (sc.hasNextLine()){
				existing.add(sc.nextLine());
			}
		} else if (type == ItemType.SIDE){
			sc = new Scanner(new BufferedInputStream(new FileInputStream("sides.txt")));
			fileName = "sides";
			while (sc.hasNextLine()){
				existing.add(sc.nextLine());
			}
		} else {
			sc = new Scanner(new BufferedInputStream(new FileInputStream("drinks.txt")));
			fileName = "drinks";
			while (sc.hasNextLine()){
				existing.add(sc.nextLine());
			}
		}
		
		
		if (nameText.getText().equals("") || priceText.getText().equals("") || descriptionText.getText().equals("")){
			//throw new InvalidArgumentException("Fields not Complete");
			System.out.println("Failed, Ensure Fields are Complete");
		} else {
			existing.add(nameText.getText());
			existing.add(priceText.getText());
			if (descriptionText.getText().contains("\n")){
				descriptionText.setText(descriptionText.getText().replaceAll("\n", ". "));
			}
			existing.add(descriptionText.getText());

			saveFile(fileName);
		}
	}
	
	public void saveFile(String fileName) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName + ".txt"));
		for (int i = 0; i < existing.size(); i++){
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
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getComponent() == submit){
			warningMessage.setText("Warning, Changes will be appiled upon restart");
		}
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getComponent() == submit){
			warningMessage.setText("");
		}
		
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
