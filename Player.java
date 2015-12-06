import java.util.ArrayList;

public class Player
{
    private int balance;                      // The actual balance of the player
    private ArrayList<Property> properties;   // The properties owned by the player
    private BoardLocation location;           // The location where the player is
    private final String token;               // The token that represents the player
    private int numRailRoads;                 // The number of rail roads owned by the player
    private int numUtilities;                 // The number of utilities owned by the player
    private String playerName;				  // The player's name	
    private int playerNumber;                 // The player's identification number
    private boolean inJail;					  // If the player is in the jail
    private boolean bankrupt;
    
	public Player()
	// POST: a new Player object is created with balance = $ 1500
	//       no properties, empty location, " " token, numRailRoads = 0,
	//       numUtilities = 0, playerNumber = 0, and playerName = ""
	//		 and inJail = false
	{
	    balance = 1500;
	    properties = new ArrayList<Property>();
	    location = new BoardLocation();
	    token = " ";
	    numRailRoads = 0;
	    numUtilities = 0;
	    playerNumber = 0;
	    playerName = " ";
	    inJail = false;
        bankrupt = false;
	}
	
	public Player(String name, int number, String token, BoardLocation location)
	// PRE:  name, number and token have to be initialized
	// POST: A Player object has been constructed with data members token
	//		set to the values passed in the corresponding parameters
	//		all players start with money = 1500, this.location = location,
	//		numUtilities = 0, numRailroads = 0, and numProperties = 0,
	//		and inJail = false
	{
	    playerName = name;
	    playerNumber = number;
	    this.token = token;
	    balance = 1500;
	    numRailRoads = 0;
	    numUtilities = 0;
	    properties = new ArrayList<Property>();
	    this.location = location;
	    inJail = false;
        bankrupt = false;
	}
        
        public boolean isBankrupt()
        {
            return this.bankrupt;
        }
        
        public void setToBankrupt()
        {
            this.bankrupt = true;
        }
        
	public int getBalance()
	// POST: FCTVAL = The current player balance
	{
	    return balance;
	}
	
	public void addMoney(int amount)
	// PRE: amount >= 0
	// POST: The value amount is added to the player balance
	{
	    balance += amount;
	}
	
	public void deductMoney(int amount)
	// PRE: amount >= 0
	// POST: The value amount is deducted from the player balance
	{
	    balance -= amount;
	}
        
	public BoardLocation getLocation()
	// POST: FCTVAL = the current player location
	{
	    return location;
	}
	
	public void setLocation(BoardLocation newLocation)
	// PRE: newLocation is initialized
	// POST: the player location is set to newLocation
	{
	    location = newLocation;
	}
	
	public Property getProperty(int i)
	// PRE: i >= 0
	// POST: FCTVAL = the i-th property in the players property list
	{
	    return properties.get(i);
	}
	
	public void buyProperty(Property newProperty)
	// PRE: newProperty is initialized, newProperty.getBuyPrice() <= this.amount
	//		and newProperty.getOwner() = 0
	// POST: newProperty is added to properties by some price
	//       this.numRailRoads and this.numUtilities are updated
	{
		if(newProperty.getOwner() == 0 && this.balance >= newProperty.getPrice())
		// Validates the condition that newProperty is a Bank property
		// and the player has enough money. Otherwise nothing happens.
		{
			properties.add(newProperty);
			newProperty.setOwnership(this.playerNumber);
			this.deductMoney(newProperty.getPrice());
			
			if(newProperty.getLocationType() == "Railroad")	// Verifies the type of the
		   																// location to update the counters
		    {
		   	 	this.numRailRoads++;
		    }
		    else if(newProperty.getLocationType() == "Utility")
		    {
		   		this.numUtilities++;
		    }
		}
	}
	
	public void sellProperty(int i)
	// PRE: i >= 0 and i < properties.length;
	// POST: the i-th property is removed from properties
	//	     the property is sold by the half of the original value to the bank
	//       this.numRailRoads and this.numUtilities are updated
	{
	   this.properties.get(i).setOwnership(0);
	   this.addMoney(this.properties.get(i).getPrice()/2);
	   if(this.properties.get(i).getLocationType() == "Railroad")	// Verifies the type of the
	   																// location to update the counters
	   {
	   		this.numRailRoads--;
	   }
	   else if(this.properties.get(i).getLocationType() == "Utility")
	   {
	   		this.numUtilities--;
	   }
	   properties.remove(i);
	}
	
	public String getToken()
	// POST: FCTVAL = token
	{
	    return token;
	}
	
	public int getNumOfRailRoads()
	// POST: FCTVAL = numRailRoads
	{
	    return numRailRoads;
	}
	
	public int getNumOfUtilities()
	// POST: FCTVAL = numUtilities
	{
	    return numUtilities;
	}
	
	public String listProperties()
	// POST: FCTVAL = a String with all the players properties separeted by commas
	{
	    String propertiesString = "";
	    for(Property p : properties)
	    {
	        propertiesString += p.getName() + ", ";
	    }
	    
	    return propertiesString;
	}
	
	public String getPlayerName()
	// POST: FCTVAL = playerName
	{
		return playerName;
	}
	
	public int getPlayerNumber()
	// POST: FCTVAL = playerNumber
	{
		return playerNumber;
	}
	
	public boolean isInJail()
	// POST: FCTVAL = inJail
	{
		return this.inJail;
	}
	
	public void sendToJail()
	// POST: inJail is set to true
	{
		this.inJail = true;
	}
	
	public void free()
	// POST: inJail is set to false
	{
		this.inJail = false;
	}
	
	@Override
	public String toString()
	// POST: FCTVAL = a String with the player properties
	{
	    return "Player: " + playerName + "; " +
	    		"Player Number: " + playerNumber + "; " +
	    		"Token: " + token + "; " +
	            "Balance: $" + balance + "\n" + 
	            "Current location: " + location.getName() + "; " +
	            "Number of railroads: " + numRailRoads + "; " + 
	            "Number of utilities: " + numUtilities + "; " + 
	             (inJail? "Is in jail" : "Not in jail") + "\n" +
	            "Properties: " + listProperties() + "\n\n";
	}
}
