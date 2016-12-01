import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import sun.security.util.Length;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.lang.Thread;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;

public class WhiteboardTurtleClient implements MouseListener {

    Date date = new Date();
    TextField result;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    public static String[] cmdInput;

    JTextField lengthenter, error;
    JPanel titlePanel, buttonPanel, displayPanel;
    JLabel dateLabel;
    JButton northButton, southButton, eastButton, westButton, liftButton, lowerButton, resetButton, lengthButton;
	/*
	public void connect() throws IOException{
		try {
			socket = new Socket(cmdInput[0], Integer.parseInt(cmdInput[1]));
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("Connected to Server.");
		} catch (ConnectException e){
			System.out.println("Error: Couldn't connect to server");
			System.exit(1);
		}
	}
	*/

    public JPanel createContentPane(){
        // Create a bottom JPanel to contain everything
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // Create a panel to have titles
        titlePanel = new JPanel();
        titlePanel.setName("TitlePanel");
        titlePanel.setLayout(null);
        titlePanel.setLocation(10,0);
        titlePanel.setSize(480,30);
        // Must be added to the totalGUI JPanel
        totalGUI.add(titlePanel);



        //Title
        dateLabel = new JLabel("The Whiteboard");
        dateLabel.setName("TitleName");
        dateLabel.setLocation(0,0);
        dateLabel.setSize(480,30);
        dateLabel.setHorizontalAlignment(0);
        titlePanel.add(dateLabel);

        //button panel
        buttonPanel = new JPanel();
        buttonPanel.setName("ButtonPanel");
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10,30);
        buttonPanel.setSize(480,130);
        totalGUI.add(buttonPanel);

        //title panel

        //buttons and text fields for gui
        //north button
        northButton = new JButton("North");
        northButton.setName("North\n");
        northButton.setLocation(0, 0);
        northButton.setSize(120, 30);
        northButton.addMouseListener(this);
        buttonPanel.add(northButton);

        //South button
        southButton = new JButton("South");
        southButton.setName("South\n");
        southButton.setLocation(120, 0);
        southButton.setSize(120, 30);
        southButton.addMouseListener(this);
        buttonPanel.add(southButton);

        //east button
        eastButton = new JButton("East");
        eastButton.setName("East\n");
        eastButton.setLocation(240, 0);
        eastButton.setSize(120, 30);
        eastButton.addMouseListener(this);
        buttonPanel.add(eastButton);

        //west button
        westButton = new JButton("West");
        westButton.setName("West\n");
        westButton.setLocation(360, 0);
        westButton.setSize(120, 30);
        westButton.addMouseListener(this);
        buttonPanel.add(westButton);

        //lift button
        liftButton = new JButton("Lift");
        liftButton.setName("Lift\n");
        liftButton.setLocation(0, 35);
        liftButton.setSize(160, 30);
        liftButton.addMouseListener(this);
        buttonPanel.add(liftButton);

        //lower button
        lowerButton = new JButton("Lower");
        lowerButton.setName("Lower\n");
        lowerButton.setLocation(150, 35);
        lowerButton.setSize(155, 30);
        lowerButton.addMouseListener(this);
        buttonPanel.add(lowerButton);

        //length text field
        lengthenter = new JTextField("Enter Length Here", 20);
        lengthenter.setName("LengthText\n");
        lengthenter.setLocation(300, 35);
        lengthenter.setSize(130, 30);
        lengthenter.addMouseListener(this);
        buttonPanel.add(lengthenter);

        //for errors
        error= new JTextField("Welcome to Turtle Graphics");
        error.setName("Error");
        error.setLocation(0, 100);
        error.setSize(480,30);
        error.setEditable(false);

        buttonPanel.add(error);


        //button to send entered length
        lengthButton = new JButton("Send");
        lengthButton.setName("Length\n");
        lengthButton.setLocation(425, 35);
        lengthButton.setSize(60, 30);
        lengthButton.addMouseListener(this);
        buttonPanel.add(lengthButton);

        //clears whiteboard
        resetButton = new JButton("Clear");
        resetButton.setName("Reset\n");
        resetButton.setLocation(0, 70);
        resetButton.setSize(480, 30);
        resetButton.addMouseListener(this);
        buttonPanel.add(resetButton);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }


    public void mouseClicked(MouseEvent e) {
        String f = e.getComponent().getName();

        try {
            //connecting to server
            Socket connectSocket = new Socket("127.0.0.1", 23669);
            PrintWriter x = new PrintWriter(connectSocket.getOutputStream(), true);
            BufferedReader r = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
            if(f.equals(lengthButton.getName())){
                f=lengthenter.getText();
            }

            x.printf("%s", f);

            String arg = "";

            System.out.println(f);
            connectSocket.close();

        } catch (Exception n) {
            System.out.println("error");
        }
    }

    private static void createGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Whiteboard");

        // Create the content panel
        WhiteboardTurtleClient demo = new WhiteboardTurtleClient();
        frame.setContentPane(demo.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520,350);
        frame.setVisible(true);
    }

    public static void main(String[] args){
		/*if(args.length != 2){
			System.out.println("Error: Proper usage -> 'javaGUI [server] [port]");
			System.exit(1);
		}*/
        cmdInput = args;
        createGUI();
    }
}
