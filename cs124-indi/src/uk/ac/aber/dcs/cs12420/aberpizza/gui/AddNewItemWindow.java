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
	private JTextField nameText, smallText, medText, largeText;
	private JTextArea descriptionText;
	private JButton submit;
	private JLabel warningMessage;
	private JPanel namePane, pricePane, submitPane, descriptionPane;
	//private SpringLayout mainLayout;

	
	private ArrayList<String> existing;

	private Font f = new Font("Arial", Font.PLAIN, 12);

	public AddNewItemWindow(ItemType type) {
		this.type = type;
		this.setLayout(null);
		this.setTitle("Create new " + type.toString().toLowerCase());

		namePane = getNamePane();
		this.add(namePane);
		
		pricePane = getPricePane();
		this.add(pricePane);
		
		descriptionPane = getDescriptionPane();
		this.add(descriptionPane);
		
		submitPane = getSubmitPane();
		this.add(submitPane);

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(400, 550);
	}

	private JPanel getNamePane(){
		JPanel thisPane = new JPanel(null);
		
		JLabel nameLabel = new JLabel("Enter name of new " + type.toString().toLowerCase(), SwingConstants.RIGHT);
		nameLabel.setBounds(5,5,150,25);
		thisPane.add(nameLabel);
		
		nameText = new JTextField();
		nameText.setBounds(160,5,150,25);
		thisPane.add(nameText);
		thisPane.setSize(320,25);
		
		return thisPane;
	}
	
	private JPanel getPricePane(){
		JPanel thisPane = new JPanel(new GridLayout(3,2));
		
		JLabel smallLabel = new JLabel("Enter price for small " + type.toString().toLowerCase() + ": £", SwingConstants.RIGHT);
		smallLabel.setFont(f);
		thisPane.add(smallLabel);
		
		smallText = new JTextField();
		smallText.setFont(f);
		thisPane.add(smallText);
		
		JLabel medLabel = new JLabel("Enter price for medium " + type.toString().toLowerCase() + ": £" , SwingConstants.RIGHT);
		medLabel.setFont(f);
		thisPane.add(medLabel);
		
		medText = new JTextField();
		medText.setFont(f);
		thisPane.add(medText);
		
		JLabel largeLabel = new JLabel("Enter price for large " + type.toString().toLowerCase() + ": £", SwingConstants.RIGHT);
		largeLabel.setFont(f);
		thisPane.add(largeLabel);
		
		largeText = new JTextField();
		largeText.setFont(f);
		thisPane.add(largeText);
		
		return thisPane;
	}

	private JPanel getDescriptionPane(){
		SpringLayout thisLayout = new SpringLayout();
		
		JPanel thisPane = new JPanel();
		thisPane.setSize(new Dimension(400, 250));
		
		JLabel descLabel = new JLabel("Enter Description of the " + type.toString().toLowerCase(), SwingConstants.LEFT);
		descLabel.setFont(f);
		descLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		descriptionText = new JTextArea();
		descriptionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setFont(f);
		
		JScrollPane descScroll = new JScrollPane(descriptionText);
		
		descScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		descScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		descScroll.setSize(new Dimension(350, 200));
		
		thisLayout.putConstraint(SpringLayout.NORTH, thisPane, 10, SpringLayout.NORTH, descLabel);
		thisLayout.putConstraint(SpringLayout.SOUTH, descLabel, 10, SpringLayout.NORTH, descriptionText);
		
		thisPane.add(descLabel);
		thisPane.add(descScroll);
		thisPane.setLayout(thisLayout);
		
		return thisPane;
	}

	private JPanel getSubmitPane(){
		JPanel thisPane = new JPanel(new GridLayout(2,1));
		
		submit = new JButton("Add " + type.toString().toLowerCase() + " to system");
		submit.setFont(f);
		submit.addActionListener(this);
		submit.addMouseListener(this);
		thisPane.add(submit);
		
		warningMessage = new JLabel();
		warningMessage.setFont(f);
		thisPane.add(warningMessage);
		return thisPane;
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
		
		
		if (nameText.getText().equals("") || smallText.getText().equals("") || medText.getText().equals("") || largeText.getText().equals("") || descriptionText.getText().equals("")){
			//TODO throw new InvalidArgumentException("Fields not Complete");
			System.out.println("Failed, Ensure Fields are Complete");
		} else {
			existing.add(nameText.getText());
			existing.add(smallText.getText());
			existing.add(medText.getText());
			existing.add(largeText.getText());
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
