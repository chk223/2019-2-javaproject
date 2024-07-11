package DoYouWantChristmasPresent;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("DoYouWantChristmasPresent?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		MainPanel mainPanel = new MainPanel();

		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setVisible(true);

	} // main

} // Main