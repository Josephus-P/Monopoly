public class Railroad extends Property
{
	public Railroad()
	// POST: A new Railroad object is created with buyPrice = 0, name = "",
	//       location = 0, and locationType = "Railroad"
	{
		super();
		this.locationType = "Railroad";
	}
	
	public Railroad(String name, int location, int buyPrice)
	// PRE: name is initialized and location >= 0 and buyPrice >= 0
	// POST: Railroad object is created and uses the super class 
	// from Property class. Then set location is to Railroad
	{
	    super(name, location, buyPrice);
	    this.locationType = "Railroad";
	}
	
        @Override
	public int rentDue(Player owner)
	// POST: If the player who owns the railroad only owns that railroad, 
	// the rent is $25. If the player owns two railroads, the rent is $50. 
	// If the player owns three railroads, the rent is $100. If the player owns 
	// all four railroads, the rent is $200.
	// 		FCTVAL = the rent due the players' number of railroads
	{
	    switch (owner.getNumOfRailRoads())
	    {
	        case 0: return 0; 
	        case 1: return 25;
    	    case 2: return 50;
	        case 3: return 100;
		    case 4: return 200;
    	    default: return 0;
        }
    }
    
    @Override
	public String[] getPossibleActions(Player player)
	// PRE: player is initialized
	// POST: FCTVAL = array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		String[] possibilities;
		if(this.owner != player.getPlayerNumber() && this.owner != 0)		// The player must pay the rent if is not the owner
		{
			possibilities = new String[1];
			possibilities[0] = "Pay railroad rent";
			return possibilities;
		}
		else											// Otherwise, the game must proceed
		{
			return super.getPossibleActions(player);
		}
	}
    
    @Override
    public String toString()
    // POST: Returns a string representation of the
	//		 name, location, type, price, isOwned and owner of this Property
    {
    	return super.toString();
    }
}
