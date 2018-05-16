package dndBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameBoard extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	
	// the game board is 4000 x 4000 pixels total, and each game square is 100 x 100 pixels
	// game tokens should be saved at a size around 100 x 100. back ground maps should be square. no matter the size, it'll expand to fit the board.


	int ping = 0; // counter that shows duplicate tokens on the board
	int kill = 0; // a "switch" that says whether the delete button is active or not. used to remove tokens.
	int move = 0; // a "switch" that says whether the move button is active or not.
	int mark = 0; // a "switch" that says whether the mark button is active or not. used to mark tokens.
	int borderThickness; // a variable integer used to store the thickness of a buttons border.
	String selected; // a variable string used to store the current choice in the drop down menu.

	String[] folderNames = {"characters1", "goblinsKobolds", "undead", "campSite", "characters2", "orcsTrolls", "dwarves", "familiars", "humanElfNobles", "reptilesAmphibians", "townsFolk", "underGround", "terrain", "dmEssentials", "indoorEffects"}; // folders containing player/character icons
	String[] menuNames = {"Player Characters", "Goblins and Kobolds", "Undead", "Camp Site", "More Player Characters", "Orcs and Trolls", "Dwarves", "Familiars", "Human/Elf Nobles", "Reptiles and Amphibians", "Towns People", "Underdark/ground", "Terrain", "DM Essentials (rando)", "Indoor Effects"}; // labels for drop down menus
	int locx = 200; // x placement of drop down menus
	int locy = 50; // y placement of drop down menus


	Color[] duplicateMarkers = {Color.darkGray, Color.black, Color.magenta, Color.yellow, Color.red, Color.orange, Color.green, Color.pink, Color.blue, Color.cyan}; // array storing colors jbutton border colors


	Color holding; // variable color that holds border values for monsters/characters


	ImageIcon holdingImage; // variable image icon that stores the current icon to be placed on the board.


	Image backGround = ImageIO.read(new File("C:\\Users\\sp1117c\\Downloads\\picsForGameBoard\\testMap.jpg")); // chosen background image. used to give the game board a map layout.
	Image menu = ImageIO.read(new File("C:\\Users\\sp1117c\\Downloads\\picsForGameBoard\\menu.jpg")); // menu button icon
	


public GameBoard() throws IOException {



	JPanel board = new MyBackGround();
	board.setLayout(new GridLayout(40, 40));

	JButton[] allButtons = new JButton[40*40];



	for (int i = 0; i < allButtons.length; i++)	{ // constructs all of the buttons on the game board

		allButtons[i] = new JButton("");
		allButtons[i].setVerticalTextPosition(JButton.CENTER);
		allButtons[i].setHorizontalTextPosition(JButton.CENTER);
		allButtons[i].setFont(new Font("", Font.BOLD, 50));
		allButtons[i].setForeground(Color.RED);
		allButtons[i].addActionListener(this);
		allButtons[i].setPreferredSize(new Dimension(100,100));

		if (i == 0)	{ 
			allButtons[i].setIcon(new ImageIcon(menu));
			allButtons[i].setActionCommand("Menu");
		} // sets menu button color, text, and action
		else	{ 
			allButtons[i].setOpaque(false);
			allButtons[i].setContentAreaFilled(false);
			allButtons[i].setBorder(new LineBorder(new Color(0f,0f,0f,0.1f)));
			allButtons[i].setActionCommand("floor");
		} // sets main buttons to transparent color and directs their clicks to appropriate actions

		board.add(allButtons[i]);

	} // end button construction



	JScrollPane scrollPane = new JScrollPane(board);        // adds buttonPanel to a scroll pane
	scrollPane.getVerticalScrollBar().setUnitIncrement(20); // makes the scroll speed in the pane tolerable...



	add(scrollPane); // adds scroll pane.



} // end method to make panel



public void actionPerformed(ActionEvent e) {

	if (e.getActionCommand().equals("Menu")) { // when menu button is clicked, a new window pops up with the DM controls.

		JFrame controlMenu = new JFrame();

		controlMenu.setSize(800,500);
		controlMenu.setVisible(true);

		JPanel options = new JPanel(); // panel for placing objects in control frame
		options.setLayout(null);



		for (int i = 1; i <= folderNames.length; i++) { // loop for adding drop down menus

				final JComboBox icons = dropDownMenu(folderNames[i - 1], locx, locy, 180, 30); // drop down menu for the character tokens
				JLabel iconsMenuLabel = menuLabel(menuNames[i - 1], locx, locy - 40, 180, 30); // label for character drop down menu
				options.add(icons);
				options.add(iconsMenuLabel);

				locx += 200; // moves combo boxes to respective x-coordinates

				if (i % 3 == 0) { locx = 200; // sets x location back
								  locy += 90;} // moves combo boxes to respective y-coordinates

		}



		JButton place = dmButtons("place", 50, 30, "place", 90, 30); // main control buttons
		JButton move = dmButtons("move", 50, 90, "move", 90, 30);
		JButton mark = dmButtons("mark", 50, 150, "mark", 90, 30);
		JButton killButton = dmButtons("kill", 50, 210, "kill", 90, 30);

		options.add(place);
		options.add(move);
		options.add(mark);
		options.add(killButton);

		controlMenu.add(options);

		ping = 0; // resets ping and kill switch in the event they weren't already
		kill = 0;

	}



	if (e.getActionCommand().equals("place")) { // reads in correct image icon based on what choice is made in the drop down menus, and allows image to be placed on the board

		try {
			holdingImage = new ImageIcon(ImageIO.read(new File("C:\\Users\\sp1117c\\Downloads\\picsForGameBoard\\" + selected)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		holding = new Color(0f,0f,0f,0.1f);
		ping = 0;
		kill = 0;

		System.out.println(selected);

	}



	if (e.getActionCommand().equals("move")) {
		ping = 0;
		kill = 0;
		mark = 0;
		move = 1; } // move button



	if (e.getActionCommand().equals("mark")) {
		mark = 1;
		kill = 0;
		move = 0; } // mark button



	if (e.getActionCommand().equals("kill")) {

		if (kill == 1) { ((JButton)e.getSource()).setBorder(new LineBorder(holding));
						 kill = 0; }
		
		else { holdingImage = null;
			   holding = new Color(0f,0f,0f,0.1f);
			   ping = 0;
			   kill = 1;
			   mark = 0;
			   ((JButton)e.getSource()).setBorder(new LineBorder(Color.red));}

	} // delete button



	if (e.getActionCommand().equals("floor")) {

		if (kill == 0 && ping > 0 && move == 0 && mark == 0) {
			((JButton)e.getSource()).setIcon(holdingImage);
			((JButton)e.getSource()).setBorder(new LineBorder(duplicateMarkers[ping], 3));
			ping++;	}
		if (kill == 0 && ping == 0 && move == 0 && mark == 0) {
			((JButton)e.getSource()).setIcon(holdingImage);
			((JButton)e.getSource()).setBorder(new LineBorder(holding, borderThickness));
			ping++;	}
		if (kill == 1) {
			((JButton)e.getSource()).setIcon(holdingImage);
			((JButton)e.getSource()).setBorder(new LineBorder(holding));
			((JButton)e.getSource()).setText(""); }
		if (move == 1) {
			holdingImage = (ImageIcon) ((JButton)e.getSource()).getIcon();
			holding = ((LineBorder)(((JButton)e.getSource()).getBorder())).getLineColor();
			borderThickness = ((LineBorder)(((JButton)e.getSource()).getBorder())).getThickness();
			((JButton)e.getSource()).setIcon(null);
			move = 0; }
		if (mark == 2) {
			((JButton)e.getSource()).setText("");
			mark = 0; }
		if (mark == 1) {
			((JButton)e.getSource()).setText("!");
			mark ++; }
		if (ping == duplicateMarkers.length - 1) {
			ping = 0; }

	} // action for tiles being clicked

} // end action handling method



public JButton dmButtons(String name, int locx, int locy, String actionCommand, int sizex, int sizey) { // method for making JButtons when necessary

	JButton button = new JButton(name);
	button.addActionListener(this);
	button.setLocation(locx, locy);
	button.setSize(sizex, sizey);
	button.setBackground(Color.WHITE);
	button.setActionCommand(actionCommand);

	return button;

} // end button method



public class MyBackGround extends JPanel { // class that paints the background behind the JButtons

	private static final long serialVersionUID = 1L;

	public MyBackGround()	{
		setBackground(new Color(0, true));
	}


	@Override
	public void paintComponent(Graphics g)	{
		g.drawImage(backGround, 0, 0, getWidth(), getHeight(), this);
		super.paintComponent(g);
	}

} // end background painting class



public JComboBox dropDownMenu(final String fileFolder, int locx, int locy, int sizex, int sizey) { // method for generating drop down menus (combo boxes) with appropriate action listeners, and linking them to correct player/character tokens

	  File folder = new File("C:\\Users\\sp1117c\\Downloads\\picsForGameBoard\\" + fileFolder); // sets file path for icons
	  File[] listOfFiles = folder.listFiles();
	  String[] choices = new String[listOfFiles.length];
	  for (int i = 0; i < listOfFiles.length; i++) { // for loop to generate array of file names. the array is passed to the combo boxes as options.

		  choices[i] = listOfFiles[i].getName();

	  }


		final JComboBox comboBox = new JComboBox(choices); // constructing drop down menus
		comboBox.setLocation(locx, locy);
		comboBox.setSize(sizex, sizey);
		comboBox.setBackground(Color.WHITE);

		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        selected = fileFolder + "\\" + (String) ((JComboBox) actionEvent.getSource()).getSelectedItem();
		      }
		    };

		comboBox.addActionListener(actionListener);

		return comboBox;

} // end combo box method



public JLabel menuLabel(String text, int locx, int locy, int sizex, int sizey) { // method for making labels for buttons, menus, etc

	JLabel menuLabel = new JLabel();

	menuLabel.setText(text);
	menuLabel.setLocation(locx, locy);
	menuLabel.setSize(sizex, sizey);
	menuLabel.setFont(new Font("", Font.BOLD, 12));

	return menuLabel;

} // end labels method



public static void main(String[] args) throws IOException { // main method

	GameBoard frame = new GameBoard();
	frame.setSize(1300, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	} //end main method

} //end GameBoard class
