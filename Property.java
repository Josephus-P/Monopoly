public class Property extends BoardLocation
{
	protected int buyPrice;    // The property price 
	protected boolean isOwned; // The property status of be owned
	protected int owner;       // The player indentification number. Banker = 0
        
	public Property()
	// POST: A new Property object is created with name = "", location = 0, locationType = "",
	//		 buyPrice = 0, isOwned = false, and owner = 0
	{
		super();
		buyPrice = 0;	
		isOwned = false;
		owner = 0;
		this.locationType = "";
	}
	
	public Property(String name, int location, int buyPrice)
	// PRE: name is initialized, location >= 0, and buyPrice >= 0
	// POST: a new property is created with this.name = name, this.location = location,
	//		this.buyPrice = buyPrice, isOwned = false and owner = 0
	{
	    super(name, location);
	    this.buyPrice = buyPrice;
	    isOwned = false;
	    owner = 0;
	    this.locationType = "";
	}
	
	public int getPrice()
	// POST: FCTVAL = buyPrice
	{
	    return buyPrice;
	}
	
	public int getOwner()
	// POST: FCTVAL = owner
	{
		return owner;
	}
	
	public boolean isOwned()
	// POST: FCTVAL = isOwned
	{
	    return isOwned;
	}
	
	public void setOwnership(int playerNumber)
	// PRE: player is initialized
	// POST: isOwned is set to true and owner is set to the player number
	{
	    if(isOwned == false)     // Validates the isOwned status
	    						 // if false, set to true and set the owner
	    						 // otherwise set to false and the owner is the bank
	    {
	        isOwned = true;
	        owner = playerNumber;
	    }
	    else
	    {
	        isOwned = false;
	        owner = 0;
	    }
	}
	
    public int rentDue(Player owner, int die1, int die2)
    {
        return 0;
    }
        
    public int rentDue(Player owner)
    {
        return 0;
    }
        
    public int rentDue()
    {
        return 0;
    }
        
	@Override
	public String[] getPossibleActions(Player player)
	// PRE: player is initialized
	// POST: FCTVAL = array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		String[] possibilities;
		if(this.isOwned == false && player.getBalance() >= this.buyPrice)
		// The player can buy the property if its owner is the bank
		// and the player has enough money
		{
			possibilities = new String[1];
			possibilities[0] = "Buy property";
			return possibilities;
		}
		else // Otherwise, the game must proceed
		{
			return super.getPossibleActions(player);
		}
	}
	
	@Override
	public String toString()
	// POST: Returns a string representation of the
	//		    name, location, type,
	//			price, isOwned and owner of this Property
	{
	    return super.toString() + "Price: $" + buyPrice + "\n"
	                            + (isOwned? "Is owned" : "Not owned") + "\n"
	                            + "Owner: " + owner + "\n";
	}
}
