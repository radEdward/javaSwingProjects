package dndBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MonsterScreen extends JFrame implements ActionListener{

	
private static final long serialVersionUID = 1L;


JButton statAdd, statDelete, fullStat, d4, d6, d8, d10, d12, d20;
JLabel diceRolls;
int ping = 0;
int ytrack = 65;

BufferedImage backGround = ImageIO.read(new File("C:\\Users\\sp1117c\\Downloads\\picsForGameBoard\\aboleth.JPG"));
int panelWidth = backGround.getWidth();


JTextArea[] statBlock = new JTextArea[12];
String selected;
String statBlockContent;


	public MonsterScreen() throws IOException{

		 setLayout(null);



		 final JTextArea inputBox = textEntry(30, 65, 125, 20);

		 add(inputBox);



		 diceRolls = menuLabel("", 115, 585, 25, 50);
		 diceRolls.setBackground(Color.lightGray);

		 add(diceRolls);



		 for (int i = 0; i < statBlock.length; i++) {
			 
			 statBlock[i] = textEntry(260, ytrack, 360, 40);
			 
			 ytrack += 45;
			 
			 add(statBlock[i]);
			 
		 }



		 statAdd = testButtons("+", 30, 10, 45, 30);

		 add(statAdd);

		 statAdd.addActionListener(new ActionListener() {
			 											 public void actionPerformed(ActionEvent ae){
			 												 selected = inputBox.getText();
			 												 SQLReader.searchSQL(selected);
											 
			 												 statBlockContent = selected + " - " + " HP : " + SQLReader.getHP() + ",  AC : " + SQLReader.getAC() + ",  Initiative : " + DiceRolling.d20(SQLReader.getDex())
			 												 		          + "\nStr : " + SQLReader.getStr() + "  Dex : " + SQLReader.getDex() + "  Con : "
			 												 		          + SQLReader.getCon() + "  Int : " + SQLReader.getInt() + "  Wis : " + SQLReader.getWis() + "  Cha : " +  SQLReader.getCha();
//			 												 		          + "\nSkills/Saves - " 
//			 												 				  + "\nActions - " + " Attack1: " + SQLReader.getAtk1() + ",  Attack2: " + SQLReader.getAtk2()
//			 												 				  + "\nSpells - " + "";

			 												 statBlock[ping].setText(statBlockContent);

			 												 ping +=1;

			 											 }
		 												});


		 statDelete = testButtons("-", 80, 10, 45, 30);

		 add(statDelete);

		 statDelete.addActionListener(new ActionListener() {
			 											 public void actionPerformed(ActionEvent ae){

			 												 ping -=1;

			 												 statBlockContent = "";
			 												 statBlock[ping].setText(statBlockContent);

			 											 }
		 												});
		 
		 
		 fullStat = testButtons("???", 30, 110, 60, 30);
		 
		 add(fullStat);
		 
		 fullStat.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent ae){

					 JFrame statWindow = new JFrame();

					 statWindow.setSize(backGround.getWidth(),backGround.getHeight());
					 statWindow.setVisible(true);
					 
					 JPanel display = new MyBackGround();
					 
					 statWindow.add(display);

				 }
				});



		 d4 = testButtons("d4", 105, 165, 50, 50);
		 d6 = testButtons("d6", 105, 235, 50, 50);
		 d8 = testButtons("d8", 105, 305, 50, 50);
		 d10 = testButtons("d10", 102, 375, 55, 50);
		 d12 = testButtons("d12", 102, 445, 55, 50);
		 d20 = testButtons("d20", 102, 515, 55, 50);
		 
		 add(d4);
		 add(d6);
		 add(d8);
		 add(d10);
		 add(d12);
		 add(d20);



	}



public JButton testButtons(String name, int locx, int locy, int sizex, int sizey) { // method for making JButtons when necessary

		JButton button = new JButton(name);
		button.setLocation(locx, locy);
		button.setSize(sizex, sizey);
		button.addActionListener(this);

		return button;

	} // end button method



	public JTextArea textEntry(int locx, int locy, int sizex, int sizey) {
		
		final JTextArea inputBox = new JTextArea();
		inputBox.setLocation(locx, locy);
		inputBox.setSize(sizex, sizey);
		inputBox.setFont(new Font("", Font.BOLD, 12));
		inputBox.setBackground(Color.lightGray);

		return inputBox;
		
	}



	public JLabel menuLabel(String text, int locx, int locy, int sizex, int sizey) { // method for making labels for buttons, menus, etc

		JLabel menuLabel = new JLabel();

		menuLabel.setText(text);
		menuLabel.setLocation(locx, locy);
		menuLabel.setSize(sizex, sizey);
		menuLabel.setFont(new Font("", Font.BOLD, 12));
		menuLabel.setOpaque(true);

		return menuLabel;

	} // end labels method



public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == d4) {
			diceRolls.setText(Integer.toString(DiceRolling.d4(0)));
			revalidate();
			repaint();
		}
		if (e.getSource() == d6) {
			diceRolls.setText(Integer.toString(DiceRolling.d6(0)));
			revalidate();
			repaint();
		}
		if (e.getSource() == d8) {
			diceRolls.setText(Integer.toString(DiceRolling.d8(0)));
			revalidate();
			repaint();
		}
		if (e.getSource() == d10) {
			diceRolls.setText(Integer.toString(DiceRolling.d10(0)));
			revalidate();
			repaint();
		}
		if (e.getSource() == d12) {
			diceRolls.setText(Integer.toString(DiceRolling.d12(0)));
			revalidate();
			repaint();
		}
		if (e.getSource() == d20) {
			diceRolls.setText(Integer.toString(DiceRolling.d20(0)));
			revalidate();
			repaint();
		}
		
	}



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



	public static void main(String[] args) throws IOException {
		
		MonsterScreen combatWindow = new MonsterScreen();
		combatWindow.setSize(700, 700);
		combatWindow.setVisible(true);
		combatWindow.getContentPane().setBackground(Color.darkGray);
		
		combatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}



}
