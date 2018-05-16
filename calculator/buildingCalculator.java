package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class buildingCalculator implements ActionListener{



    double runningTotal = 0; // running total of calculations
    int ping = 0;            // differentiates between storing an initial value, or operating it with another value (running total)
    
    String displayTotal = "";   // total shown on the calculator
    String operator = "";       // a variable string that keeps track of what operation should be performed
    String formatOption = "%."; // a changing string that will determine how to round off values shown
    String decimalPlaces = "";  // holds the number of decimal places required in format option

    JPanel titlePanel, displayPanel, buttonPanel;                      // initialize different panels
    JLabel totalLabel, displayTotalShown;                              // initialize different labels
    JButton oneButton, twoButton, threeButton, fourButton, fiveButton, // initialize all of the buttons
    		sixButton, sevenButton, eightButton, nineButton, zeroButton, 
    		resetButton, plusButton, minusButton, multiplyButton, divideButton, equalButton,
    		decimalButton, backSpaceButton;


public JPanel createContentPane (){ 

	JPanel totalGUI = new JPanel(); // base panel
	totalGUI.setLayout(null);
	
	displayPanel = new JPanel(); // sets the panel for the number display
	displayPanel.setLayout(null);
	displayPanel.setLocation(10, 20);
	displayPanel.setSize(260, 30);
	displayPanel.setBackground(Color.WHITE);
	totalGUI.add(displayPanel);

	displayTotalShown = new JLabel(""+displayTotal); // sets the number display to show on the display Panel
	displayTotalShown.setLocation(65, 0);
	displayTotalShown.setSize(120, 30);
	displayTotalShown.setHorizontalAlignment(0);
	displayPanel.add(displayTotalShown);

	buttonPanel = new JPanel(); // sets the panel for the buttons
	buttonPanel.setLayout(null);
	buttonPanel.setLocation(10, 60);
	buttonPanel.setSize(260, 380);
	totalGUI.add(buttonPanel);
	
	
	// sets labels, sizes, and locations for buttons. also adds ActionListeners to the buttons to send out appropriate signals when they're activated.
	oneButton = calcButtons("1", 20, 0, "number", 45, 30);
	buttonPanel.add(oneButton);
	
	twoButton = calcButtons("2", 70, 0, "number", 45, 30);
	buttonPanel.add(twoButton);
	
	threeButton = calcButtons("3", 120, 0, "number", 45, 30);
	buttonPanel.add(threeButton);
	
	fourButton = calcButtons("4", 20, 40, "number", 45, 30);
	buttonPanel.add(fourButton);
	
	fiveButton = calcButtons("5", 70, 40, "number", 45, 30);
	buttonPanel.add(fiveButton);
	
	sixButton = calcButtons("6", 120, 40, "number", 45, 30);
	buttonPanel.add(sixButton);
	
	sevenButton = calcButtons("7", 20, 80, "number", 45, 30);
	buttonPanel.add(sevenButton);
	
	eightButton = calcButtons("8", 70, 80, "number", 45, 30);
	buttonPanel.add(eightButton);
	
	nineButton = calcButtons("9", 120, 80, "number", 45, 30);
	buttonPanel.add(nineButton);
	
	zeroButton = calcButtons("0", 70, 120, "number", 45, 30);
	buttonPanel.add(zeroButton);
	
	
	plusButton = calcButtons("+", 200, 0, "arithmetic", 45, 30); // arithmetic buttons
	buttonPanel.add(plusButton);
	
	minusButton = calcButtons("-", 200, 40, "arithmetic", 45, 30);
	buttonPanel.add(minusButton);
	
	multiplyButton = calcButtons("*", 200, 80, "arithmetic", 45, 30);
	buttonPanel.add(multiplyButton);
	
	divideButton = calcButtons("/", 200, 120, "arithmetic", 45, 30);
	buttonPanel.add(divideButton);
	
	equalButton = calcButtons("=", 200, 160, "equals", 45, 30);
	buttonPanel.add(equalButton);
	
	
	resetButton = calcButtons("clear", 78, 230, "format", 75, 30); // formatting buttons
	buttonPanel.add(resetButton);
	
	decimalButton = calcButtons(".", 120, 120, "format", 45, 30);
	buttonPanel.add(decimalButton);
	
	backSpaceButton = calcButtons("<~~", 190, 230, "format", 55, 30);
	buttonPanel.add(backSpaceButton);
	
	totalGUI.setOpaque(true);
	return totalGUI;

} // end building buttons



public void actionPerformed(ActionEvent e) { // catches button presses from above and performs the correct action



	if (e.getActionCommand().equals("number"))
	{
		displayTotal = displayTotal + ((JButton)e.getSource()).getText();				//the appropriate number is added to the display and stored
		displayTotalShown.setText(""+displayTotal);
	}



	//else if(e.getSource() == plusButton || e.getSource() == minusButton || e.getSource() == multiplyButton || e.getSource() == divideButton) { // arithmetic logic
	else if(e.getActionCommand().equals("arithmetic")) { // arithmetic logic

		if (ping > 0) {
			runningTotal = getResult(runningTotal, displayTotal);        // gets result from current running total operated with what's displayed on calculator
			displayTotalShown.setText(""+Double.toString(runningTotal)); // displays new total
			displayTotal = "";                                           // resets displayTotal to accept new values
		}
		if (ping == 0) {
			runningTotal = Double.parseDouble(displayTotal);             // if ping is 0, the clear button was just hit or no numbers have been entered yet. the current displayed value is set to runningTotal.
			displayTotalShown.setText(""+Double.toString(runningTotal)); // displays new total
			displayTotal = "";                                           // resets displayTotal to accept new values. increments ping to tell the calculator to begin operating values input.
			ping++;
		}				

    	if(e.getSource() == plusButton)	    {operator = "+";} // sets operator to value to tell get result what operation to perform
    	if(e.getSource() == minusButton)	{operator = "-";}
    	if(e.getSource() == multiplyButton)	{operator = "*";}
    	if(e.getSource() == divideButton)	{operator = "/";}

    	formatOption = "%."; // sets the format string back to default value
		decimalPlaces = "";  // sets decimal places back to default value

	} // end arithmetic logic



    else if(e.getSource() == equalButton) { //sets logic for equal button. result of operations input is displayed. ping is reset.

    	displayTotal = Double.toString(getResult(runningTotal, displayTotal));

    	displayTotalShown.setText(""+displayTotal);

    	ping = 0;
    	formatOption = "%.";
		decimalPlaces = "";
    }



	else if(e.getSource() == resetButton) { // sets logic for clear button

        displayTotal = "";										//displayTotal reset
        runningTotal = 0;										//runningTotal reset
        displayTotalShown.setText(""+displayTotal);				//sets a blank screen on calculator
        ping = 0;												//ping resets
        operator = "";											//operator resets

        formatOption = "%.";
		decimalPlaces = "";

	}



	else if(e.getSource() == decimalButton) { // sets logic for decimal button

    	if(displayTotal.contains("."))	{displayTotal = displayTotal + "";}
    	else	{displayTotal = displayTotal + ".";}

	}



    else if(e.getSource() == backSpaceButton) { // sets logic for backspace button

    	displayTotal = displayTotal.substring(0, displayTotal.length() - 1);
    	displayTotalShown.setText(""+displayTotal);

    }



} // end action handling method



public double getResult(double x, String y)	{ // takes in runningTotal and number displayed, and operates them to get a result. 

	if (operator.equals("+")) {
		decimalPlaces = String.valueOf( Math.max(noOfDecimals(x), noOfDecimals(Double.parseDouble(y)))); // truncates decimals to a reasonable point.
		formatOption = formatOption + decimalPlaces + "f";                                               // builds a string of the form "%.nf" where n is the number of desired decimal places
		x = x + Double.parseDouble(y);                                                                   // performs the operation
		return Double.parseDouble(String.format(formatOption, x));                                       // returns the value of the operation, rounded off appropriately
	}

	if (operator.equals("-")) {
		decimalPlaces = String.valueOf( Math.max(noOfDecimals(x), noOfDecimals(Double.parseDouble(y))));
		formatOption = formatOption + decimalPlaces + "f";
		x = x - Double.parseDouble(y);
		return Double.parseDouble(String.format(formatOption, x));
	}

	if (operator.equals("*")) { 
		decimalPlaces = String.valueOf(noOfDecimals(x) + noOfDecimals(Double.parseDouble(y)));
		formatOption = formatOption + decimalPlaces + "f";
		x = x * Double.parseDouble(y);
		return Double.parseDouble(String.format(formatOption, x));
	}

	if (operator.equals("/")) {
		decimalPlaces = String.valueOf(noOfDecimals(x) + noOfDecimals(Double.parseDouble(y)));
		formatOption = formatOption + decimalPlaces + "f";
		x = x / Double.parseDouble(y);
		return Double.parseDouble(String.format(formatOption, x));
	}

	return Double.parseDouble(y); // returns only number entered if no operator is chosen

} // end result calculation method



public int noOfDecimals(double x) { // method to count decimal places in a double

	String input = String.valueOf(x);
	return input.length() - input.indexOf(".") - 1;

}



public JButton calcButtons(String name, int locx, int locy, String actionCommand, int sizex, int sizey) { // method for making JButtons when necessary

	JButton button = new JButton(name);
	button.addActionListener(this);
	button.setLocation(locx, locy);
	button.setSize(sizex, sizey);
	button.setActionCommand(actionCommand);

	return button;

} // end button method



public static void main(String[] args) { // main method
	JFrame frame = new JFrame("Calculator");

    //creation of the pane and its contents
    buildingCalculator calc = new buildingCalculator();
    frame.setContentPane(calc.createContentPane());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(480, 380);
    frame.setVisible(true);
}



} // end buildingCalculator class