public class Utility extends Property 
{
	public Utility()
	// POST: A new Utility object is created with buyPrice = 0, name = "",
	//       and location = 0
	{
		super();
		this.locationType = "Utility";
	}

	public Utility(String name, int location, int buyPrice)
	// PRE: name is initialized and location >= 0 and buyPrice=0
	// POST: Utility object is created and uses the super class 
	// from Property class. Then setlocation is to Utility
	{
	    super(name, location, buyPrice);
	    this.locationType = "Utility";
	}
	
        @Override
	public int rentDue(Player owner, int die1, int die2)
	// PRE: owner is initialized, 1 <= die1 <= 6, 1 <= die2 <= 6
	// POST: If the player who owns the utility owns one utility, the rent
	// is 4 times the amount shown on the dice. If the player who owns the 
	// utility owns both utilities, the rent is 10 times the amount
	// shown on the dice.
	// FCTVAL = the rent due the dice result and the number of utilities the player has
	{
    	if(owner.getNumOfUtilities() == 1)		// Returns the rent due to the number of utilities
    		return (die1+die2)*4;
    	else
    		return (die1+die2)*10;
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
			possibilities[0] = "Pay utility rent";
			return possibilities;
		}
		else											// Otherwise, the game must proceed
		{
			return super.getPossibleActions(player);
		}
	}
	
	@Override
	public String toString()
	// POST: FCTVAL = a String representing the Utility properties
	//		 The description of the datamembers specified on super classes
	//		 are given by the super.toString() method
	{
		return super.toString();
	}
}
