import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;

public class Monopoly extends JApplet implements ActionListener 
{
	private int currentPlayer;	  // The identification number of the current player
	private int die1;		  // The number in the die #1
	private int die2;		  // The number in the die #2
	private int dieSum;		  // The sum of both dice
	private int numDoubleRolls;	  // The number of doubles the current player rolled
	private boolean diceRolled;	  // True or false depending if the dice have been rolled
	private boolean beginOfTurn;      // If the current turn is beggining
	private BoardLocation board[];    // Array of locations on the board
	private Player players[];	  // Array of players in the game
	private boolean rentOrTaxPaid; // The payment staus o the current rent or tax
	private boolean demoMode;      // The running mode of the application
	
	private BorderLayout mainLayout;  // Main layout for the applet
	private JPanel optionsPanel;	  // Panel to display options for the player
	private JPanel playerPanel;	  // Sub-panel to display player info
	private JPanel centerPanel;	  // A panel to hold centralized objects
	private JPanel topPanel;  	  // The current square info
	private JPanel bottomPanel;	  // The current player info
	private JPanel infoPanel;         // Panel to display all the board info
	
	private JButton buyProperty;	  // A button that allows the user to buy a property
	private JButton payRent;	  // A button that allows the user to pay a rent
	private JButton payJailFee;	  // A button that allows the user to pay their way out of jail
	private JButton payTax;		  // A button that allows the user to pay a tax
	private JButton rollDice;	  // A button that allows the user to roll the dice
	private JButton buildHouse;  	  // A button that allows the user to build a house
	private JButton buildHotel;	  // A button that allows the user to build a hotel
	private JButton sellHouse;        // A button that allows the user to sell a house
	private JButton sellHotel;        // A button that allows the user to sell a hotel
	private JButton nextPlayer;	  // A button to let the next player take their turn
	private JButton viewPlayers;	  // A button that allows the user to view other players' info
	private JButton viewSquare; 	  // A button that allows the suer to view the current square info
	private JButton endGame;          // A button that allows the user to end the game
	
	private JLabel playerName;	  // A label to display the player's name
	private JLabel playerToken; 	  // A label to display the player's token
	private JLabel playerBalance;	  // A label to display the player's ballance
	private JLabel playerLocation;    // A label to display the player's action
	private JLabel squareName;        // A label to display the square's name
	private JLabel squareLocation;    // A label to display the square's location
	private JTextArea boardInfo;	  // A label to display the info about the locations in the game
	private JScrollPane scroll;	  // A scroll panel to scroll long texts infos
	private Font font;		  // The font of the graphic
	
	public Monopoly()
	// POST: a Monopoly instance is created but the GUI objects are created inside init()
	{
		// Initialize the board
		setBoard();
		players = new Player[4];
		players[0] = new Player();
		players[1] = new Player("Josh", 1, "Battleship", board[0]);
		players[2] = new Player("Ronny", 2, "Scottie Dog", board[0]);
		players[3] = new Player("Elbert", 3, "Top Hat", board[0]);
		
		// Choose a random player to start first
		currentPlayer = (int)(Math.random()*(players.length - 1) + 1);
	    
		//Initialize dice
		die1 = 0;
		die2 = 0;
		dieSum = 0;
		numDoubleRolls = 0;
		beginOfTurn = true;
		rentOrTaxPaid = false;
		demoMode = false;
		setToDemoMode();
	}
	
	@Override
	public void init()
	// POST: initializes the data members with the default values
	{
		mainLayout = new BorderLayout();
		setLayout(mainLayout);
		
		//Initialize all the panels
		optionsPanel = new JPanel();
		infoPanel = new JPanel();
		playerPanel = new JPanel();
		centerPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		//Set the panel layouts
		centerPanel.setLayout(new FlowLayout());
		topPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
		infoPanel.setLayout(new FlowLayout());		
		optionsPanel.setLayout(new GridLayout(6, 2));
		playerPanel.setLayout(new GridLayout(2, 2));
		
		//Initialize all the buttons
		buyProperty = new JButton("Buy Property");
		payRent = new JButton("Pay Rent");
		payTax = new JButton("Pay Tax");
		payJailFee = new JButton("Pay Jail Fee");
		rollDice = new JButton("Roll Dice");
		buildHouse = new JButton("Build House");
		buildHotel = new JButton("Build Hotel");
		sellHouse = new JButton("Sell House");
		sellHotel = new JButton("Sell Hotel");
		nextPlayer = new JButton("Next Player");
		viewPlayers = new JButton("View Players");
		viewSquare = new JButton("Square Info");
		endGame = new JButton("End Game");
		
		//add an ActionListener to all buttons
		buyProperty.addActionListener(this);
		payRent.addActionListener(this);
		payTax.addActionListener(this);
		rollDice.addActionListener(this);
		buildHouse.addActionListener(this);
		buildHotel.addActionListener(this);
		sellHouse.addActionListener(this);
		sellHotel.addActionListener(this);
		payJailFee.addActionListener(this);
		nextPlayer.addActionListener(this);
		viewPlayers.addActionListener(this);
		viewSquare.addActionListener(this);
		endGame.addActionListener(this);

		//Initialize all player labels. The current Strings are test strings.
		//Pass player info as arguments to display correct info
		playerName = new JLabel(players[currentPlayer].getPlayerName());
		playerToken = new JLabel(players[currentPlayer].getToken());
		playerBalance = new JLabel("$ " + players[currentPlayer].getBalance());
		playerLocation = new JLabel(board[players[currentPlayer].getLocation()
						.getLocation()].getName());
		
		// Initialize all square labels. The current Strings are test strings.
		// Pass boardLocation arguments to display correct info
		squareName = new JLabel("Square Name: " + board[players[currentPlayer].getLocation()
													.getLocation()].getName() + "     ");
		squareLocation = new JLabel("Location: " + board[0].getLocation());
		
		setBoardInfo();
		scroll = new JScrollPane(boardInfo, 
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		// Add buttons to appropriate panels
		optionsPanel.add(buyProperty);
		optionsPanel.add(payRent);
		optionsPanel.add(payTax);
		optionsPanel.add(payJailFee);
		optionsPanel.add(viewPlayers);
		optionsPanel.add(viewSquare);
		centerPanel.add(rollDice);
		optionsPanel.add(buildHouse);
		optionsPanel.add(buildHotel);
		optionsPanel.add(sellHouse);
		optionsPanel.add(sellHotel);
		optionsPanel.add(nextPlayer);
		optionsPanel.add(endGame);

		//add labels to appropriate panels
		playerPanel.add(playerName);
		playerPanel.add(playerToken);
		playerPanel.add(playerBalance);
		playerPanel.add(playerLocation);
		topPanel.add(squareName);
		topPanel.add(squareLocation);
		infoPanel.add(scroll);

		//Add sub panel to main panel
		bottomPanel.add(playerPanel);

		//add all panels to the GUI
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		add(optionsPanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);

		font = new Font("SansSarif", Font.BOLD, 20);
		disableOptionButtons();
		updatePlayerAndSquareInfo();
	}
	
	@Override
	public void paint(Graphics g)
	// PRE: g is initialized
	// POST: The dice result is painted on g
	{
		super.paint(g);
		g.setFont(font);
		
		g.drawString("Dice Roll: " + die1 + " + " + die2 + " = " + dieSum , 400, 250);
	}
	
	public void updatePlayerAndSquareInfo()
	{
		// Update the board information panel
		squareName.setText("Square Name: " + board[players[currentPlayer].getLocation()
													.getLocation()].getName() + "     ");
		squareLocation.setText("Location: " + board[players[currentPlayer].getLocation()
													.getLocation()].getLocation());
		
		// Update the player's information panel
		playerName.setText(players[currentPlayer].getPlayerName());
		playerToken.setText(players[currentPlayer].getToken());
		playerBalance.setText("$ " + players[currentPlayer].getBalance());
		playerLocation.setText(board[players[currentPlayer].getLocation()
						.getLocation()].getName());
	}
	
	public void actionPerformed(ActionEvent e)
	// POST: Accordingly to the source of the event, a decision is performed
	//		 to procede the game.
	{
		BoardLocation boardLoc;
		updateOptions();
		// setBoardInfo();
			
		if(e.getSource() == rollDice && diceRolled == false)
		// Executes if the Roll Dice button has been clicked and the
		// dice have not been rolled yet.
		{
			beginOfTurn = false;
			die1 = (int)(Math.random()*6+1);
			die2 = (int)(Math.random()*6+1);
			dieSum = die1+die2;
			
			if(die1 == die2)
			// If the player rolls a double, He/She can roll again.
			{
				if(players[currentPlayer].isInJail())
				{
					players[currentPlayer].free();
					this.nextPlayer.setEnabled(true);
				}
				diceRolled = false;
				numDoubleRolls++;
			}
			else
			{
				diceRolled = true;
			}
			
			if(numDoubleRolls == 3)
			// If the player rolls 3 doubles, must go to jail
			{
				numDoubleRolls = 0; // Reset
				diceRolled = true;  // Reset
				players[currentPlayer].sendToJail();
				players[currentPlayer].setLocation(board[10]); // Jail location
			}
			else
			{
				boardLoc = players[currentPlayer].getLocation();
					
				if(boardLoc.getLocation() + dieSum > 39)
				{
					players[currentPlayer].addMoney(200);
				}
					
			players[currentPlayer].setLocation( board[
			(boardLoc.getLocation() + dieSum) % this.board.length]);
			}        
		}
		
		if(e.getSource() == nextPlayer && diceRolled == true)
		// Executes if the Next Player button is clicked and the
		// dice have been rolled.
		{
			numDoubleRolls = 0;
			beginOfTurn = true;
			currentPlayer++;
			
			if(currentPlayer > 3)
			// Conditional statement makes sure the currentPlayer
			// variable is updated correctly
			{
				currentPlayer = 1;
			}
			
			diceRolled = false; // Reset
		}
		
		if(e.getSource() == viewPlayers)
		// Executes if the player clicks the View Players button
		{
			showPlayersStatus();
		}
		
		if(e.getSource() == this.buyProperty)
		// 
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].buyProperty((Property)board[boardLoc.getLocation()]);
		}
		
		if(e.getSource() == this.payRent)
		{
			boardLoc = players[currentPlayer].getLocation();
			
			if(boardLoc.getLocationType().equals("Railroad"))
			{
				players[currentPlayer].deductMoney(
				 ((Railroad)boardLoc).rentDue(players[currentPlayer]));
			}
			else if(boardLoc.getLocationType().equals("Utility"))
			{
				players[currentPlayer].deductMoney(
				((Utility)boardLoc).rentDue(players[currentPlayer], die1, die2));
			}
			else if(boardLoc.getLocationType().equals("Lot"))
			{
				players[currentPlayer].deductMoney(
				((Lot)boardLoc).rentDue());
			}
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
					
			rentOrTaxPaid = true;
		}
			
		if(e.getSource() == this.payTax)
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].deductMoney( ((TaxSquare)(boardLoc)).taxDue());
				
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
				rentOrTaxPaid = true;
		}
				
		if(e.getSource() == this.payJailFee)
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].deductMoney( ((CornerSquare)(boardLoc)).getJailFee());
				
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
				
			players[currentPlayer].free();
			this.nextPlayer.setEnabled(true);
			rentOrTaxPaid = true;
		}
				
		if(e.getSource() == this.buildHouse)
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].deductMoney( ((Lot)boardLoc).getImproveFee() );
			((Lot)boardLoc).setNumHouses(((Lot)boardLoc).getNumHouses() + 1);
				
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
		}
				
		if(e.getSource() == this.buildHotel)
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].deductMoney( ((Lot)boardLoc).getImproveFee() );
			((Lot)boardLoc).setNumHotels(((Lot)boardLoc).getNumHotels() + 1);
			((Lot)boardLoc).setNumHouses(0);
				
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
		}
				
		if(e.getSource() == this.sellHouse)
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].addMoney( ((Lot)boardLoc).getImproveFee()/2 );
			((Lot)boardLoc).setNumHouses(((Lot)boardLoc).getNumHouses() - 1);
				
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
		}
				
		if(e.getSource() == this.sellHotel)
		{
			boardLoc = players[currentPlayer].getLocation();
			players[currentPlayer].addMoney( 5*((Lot)boardLoc).getImproveFee()/2 );
			((Lot)boardLoc).setNumHouses(((Lot)boardLoc).getNumHouses() - 1);
				
			if(players[currentPlayer].getBalance() < 0)
			{
				players[currentPlayer].setToBankrupt();
			}
		}
				
		if(e.getSource() == this.endGame)
		{
			System.exit(0);
		}
				
		if(e.getSource() == this.viewSquare)
		{
			JOptionPane.showMessageDialog(null,
			players[currentPlayer].getLocation().toString());
		}
				
		updatePlayerAndSquareInfo();
		updateOptions();
		updateBoardInfo();
		repaint();
	}
	
	public void updateOptions()
	{
		int chanceAmount;
		disableOptionButtons();
		
		if(beginOfTurn)
		{
			
			return;
		}
			
		if(rentOrTaxPaid)
		{
			this.nextPlayer.setEnabled(true);
			rentOrTaxPaid = false;
			return;
		}
			
		for(String option : board[players[currentPlayer].getLocation().
			getLocation()].getPossibleActions(players[currentPlayer]))
		{
			if(option.equals("Proceed"))
			{
				disableOptionButtons();
				this.nextPlayer.setEnabled(true);
				 return;
			}
			else if(option.equals("Pay utility rent"))
			{
				disableOptionButtons();
				this.payRent.setEnabled(true);
				return;
			}
			else if(option.equals("Pay the tax"))
			{
				disableOptionButtons();
				this.payTax.setEnabled(true);
				return;
			}
			else if(option.equals("Pay railroad rent"))
			{
				disableOptionButtons();
				this.payRent.setEnabled(true);
				return;
			}
			else if(option.equals("Buy property"))
			{
				this.buyProperty.setEnabled(true);
				this.nextPlayer.setEnabled(true);
			}
			else if(option.equals("Pay lot rent"))
			{
				disableOptionButtons();
				this.payRent.setEnabled(true);
				return;
			}
			else if(option.equals("Roll doubles"))
			{
				this.rollDice.setEnabled(true);
				 
			}
			else if(option.equals("Pay the jail fine"))
			{
				disableOptionButtons();
				this.payJailFee.setEnabled(true);
			}
			else if(option.equals("Go to jail"))
			{
				players[currentPlayer].sendToJail();
				players[currentPlayer].setLocation(board[10]);
				disableOptionButtons();
				return;
			}
			else if(option.equals("Proceed with the chance result"))
			{
				chanceAmount = ((CardSquare)players[currentPlayer].getLocation())
									.obtainCardMoneyAmount();
				if(chanceAmount > 0)
				{
					players[currentPlayer].addMoney(chanceAmount);
				}
				else
				{
					players[currentPlayer].deductMoney(-chanceAmount);
				}
									
				updatePlayerAndSquareInfo();
				disableOptionButtons();
				this.nextPlayer.setEnabled(true);
				return;
				
			}
			else if(option.equals("Build house"))
			{
				this.buildHouse.setEnabled(true);
				this.nextPlayer.setEnabled(true);
			}
			else if(option.equals("Build hotel"))
			{
				this.buildHotel.setEnabled(true);
				this.nextPlayer.setEnabled(true);
			}
			else if(option.equals("Sell house"))
			{
				this.sellHouse.setEnabled(true);
				this.nextPlayer.setEnabled(true);
			}
			else if(option.equals("Sell hotel"))
			{
				this.sellHotel.setEnabled(true);
				this.nextPlayer.setEnabled(true);
			}
		}
	}
		
	public void disableOptionButtons()
	{
		buyProperty.setEnabled(false);
		payRent.setEnabled(false);
		payJailFee.setEnabled(false);
		payTax.setEnabled(false);
		buildHouse.setEnabled(false);
		buildHotel.setEnabled(false);
		sellHouse.setEnabled(false);
		sellHotel.setEnabled(false);
		nextPlayer.setEnabled(false);
	}
	
	public void showPlayersStatus()
	// POST: string representation of the the current state of each player.
	//		  (money, location, and number of properties owned)
	{
		String playersInfo = "";
		for (int i = 1; i < players.length; i++)			// Goes through whole players array
		{
			playersInfo += players[i].toString();
		}
		
		JOptionPane.showMessageDialog(null, playersInfo);
	}
	
	public void setBoardInfo()
	// POST: the central label boardInfo is set to show the game information about the locations
	{
		String boardInformation = "";
		for(BoardLocation bl : board)
		{
			boardInformation += bl.toString() + "\n";
		}
		
		boardInfo = new JTextArea();
		boardInfo.setRows(33);
		boardInfo.setText(boardInformation);
		boardInfo.setEditable(false);
		boardInfo.setCaretPosition(boardInfo.getDocument().getLength());
	}
	
	public void updateBoardInfo()
	// POST: the central label boardInfo is set to show the game information about the locations
	{
		String boardInformation = "";
		
		for(BoardLocation bl : board)
		{
			boardInformation += bl.toString() + "\n";
		}

		 boardInfo.setText(boardInformation);
	}
		
	public void setBoard()
	// POST: All the 40 board locations are initialized accordingly to the Monopoly board
	{
		board = new BoardLocation[40];
		board[0] = new CornerSquare("GO", 0);
		board[1] = new Lot("MEDITERRANEAN AVE", 1, 60, "Dark Purple");
		board[2] = new CardSquare("COMMUNITY CHEST", 2);
		board[3] = new Lot("BALTIC AVE", 3, 60, "Dark Purple");
		board[4] = new TaxSquare("INCOME TAX", 4);
		board[5] = new Railroad("READING RAILROAD", 5, 200);
		board[6] = new Lot("ORIENTAL AVE", 6, 100, "Light Blue");
		board[7] = new CardSquare("CHANCE", 7);
		board[8] = new Lot("VERMONT AVE", 8, 100, "Light Blue");
		board[9] = new Lot("CONNECTICUT AVE", 9, 120, "Light Blue");
		board[10] = new CornerSquare("JAIL/JUST VISITING", 10);
		board[11] = new Lot("ST. CHARLES PLACE", 11, 140, "Light Purple");
		board[12] = new Utility("ELECTRIC COMPANY", 12, 150);
		board[13] = new Lot("STATES AVE", 13, 140, "Light Purple");
		board[14] = new Lot("VIRGINIA AVE", 14, 160, "Light Purple");
		board[15] = new Railroad("PENNSYLVANIA RAILROAD", 15, 200);
		board[16] = new Lot("ST. JAMES PLACE", 16, 180, "Orange");
		board[17] = new CardSquare("COMMUNITY CHEST", 17);
		board[18] = new Lot("TENNESSEE AVE", 18, 180, "Orange");
		board[19] = new Lot("NEW YORK AVE", 19, 200, "Orange");
		board[20] = new CornerSquare("FREE PARKING", 20);
		board[21] = new Lot("KENTUCKY AVE", 21, 220, "Red");
		board[22] = new CardSquare("CHANCE", 22);
		board[23] = new Lot("INDIANA AVE", 23, 220, "Red");
		board[24] = new Lot("ILLINOIS AVE", 24, 240, "Red");
		board[25] = new Railroad("B & O RAILROAD", 25, 200);
		board[26] = new Lot("ATLANTIC AVE", 26, 260, "Yellow");
		board[27] = new Lot("VENTNOR AVE", 27, 260, "Yellow");
		board[28] = new Utility("WATER WORKS", 28, 150);
		board[29] = new Lot("MARVIN GARDENS", 29, 280, "Yellow");
		board[30] = new CornerSquare("GO TO JAIL", 30);
		board[31] = new Lot("PACIFIC AVE", 31, 300, "Green");
		board[32] = new Lot("NORTH CAROLINA AVE", 32, 300, "Green");
		board[33] = new CardSquare("COMMUNITY CHEST", 33);
		board[34] = new Lot("PENNSYLVANIA AVE", 34, 320, "Green");
		board[35] = new Railroad("SHORT LINE RAILROAD", 35, 200);
		board[36] = new CardSquare("CHANCE", 36);
		board[37] = new Lot("PARK PLACE", 37, 350, "Dark Blue");
		board[38] = new TaxSquare("LUXURY TAX", 38);
		board[39] = new Lot("BOARDWALK", 39, 400, "Dark Blue");
	}
	
	public void setToDemoMode()
	// POST: the game status is set in a way to allow rapid verification for test purposes.
	//		 When the player money not be enough, the property will not be bought
	{
		if(!demoMode)
		{
			return;
		}
		
		this.players[1].buyProperty((Lot)board[1]);
		this.players[1].buyProperty((Lot)board[3]);
		this.players[1].buyProperty((Lot)board[6]);
		this.players[1].buyProperty((Lot)board[8]);
		this.players[1].buyProperty((Lot)board[9]);
		this.players[1].buyProperty((Lot)board[11]);
		this.players[1].buyProperty((Lot)board[13]);
		this.players[1].buyProperty((Lot)board[14]);
		this.players[1].buyProperty((Railroad)board[5]);
		this.players[1].buyProperty((Utility)board[12]);
		
		this.players[2].buyProperty((Railroad)board[15]);
		this.players[2].buyProperty((Lot)board[16]);
		this.players[2].buyProperty((Lot)board[18]);
		this.players[2].buyProperty((Lot)board[19]);
		this.players[2].buyProperty((Lot)board[21]);
		this.players[2].buyProperty((Lot)board[23]);
		this.players[2].buyProperty((Lot)board[24]);
		this.players[2].buyProperty((Railroad)board[25]);
		this.players[2].buyProperty((Lot)board[26]);
		
		this.players[3].buyProperty((Lot)board[27]);
		this.players[3].buyProperty((Utility)board[28]);
		this.players[3].buyProperty((Lot)board[29]);
		this.players[3].buyProperty((Lot)board[31]);
		this.players[3].buyProperty((Lot)board[32]);
		this.players[3].buyProperty((Lot)board[34]);
		this.players[3].buyProperty((Railroad)board[35]);
		this.players[3].buyProperty((Lot)board[37]);
		this.players[3].buyProperty((Lot)board[39]);
		
		this.players[1].setLocation(board[30]);
		this.players[1].sendToJail();
	}
	
	/* Just for test purposes */
	public void printBoardInfo()
	{
		for(BoardLocation bl : board)
		{
			System.out.println(bl.toString());
		}
	}
}
