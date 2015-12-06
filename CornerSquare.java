public class CornerSquare extends BoardLocation
{
	private static final int JAILFINE = 500;	// The amount of $ that a player must pay
												// if want to be free by paying
	
	public CornerSquare()
	// POST: A new CornerSquare object is created with
	//       name = "", location = 0, and locationType = "Corner"
	{
		super();
		this.locationType = "Corner";
	}
	
	public CornerSquare(String name, int location)
	// PRE: name is initialized and location >= 0
	// POST: A new CornerSquare object is created with
	//       this.name = name, this.location = location,
	//		 and locationType = "Corner"
	{
	    super(name, location);
	    this.locationType = "Corner";
	}
	
	public int getJailFee()
	// POST: FCTVAL = JAILFEE
	{
		return JAILFINE;
	}
	
	@Override
	public String[] getPossibleActions(Player player)
	// PRE: player is initialized
	// POST: FCTVAL = array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		String[] possibilities;
		// Here "proceede" means that the player will
		// roll the dice or the next player will play.
		// It will be decided in the game logic.
		
		if(this.name.equals("JAIL/JUST VISITING") && player.isInJail()) // If landing in jail and really in jail,
														  // the player must roll doubles or pay the fine
		{
			possibilities = new String[2];
			possibilities[0] = "Roll doubles";
                        possibilities[1] = "Pay the jail fine";
			return possibilities;
		}
		else if(this.name.equals("JAIL/JUST VISITING") && !player.isInJail())	// If landing in jail but not in jail,
														  		// the player must proceed
		{
			return super.getPossibleActions(player);
		}
		else if(this.name.equals("GO TO JAIL"))		// The player just go to jail
		{
			possibilities = new String[1];
			possibilities[0] = "Go to jail";
			return possibilities;
		}
		else if(this.name.equals("FREE PARKING"))	// The player must proceed
		{
			return super.getPossibleActions(player);
		}
		else if(this.name.equals("GO"))				// The player must proceed
		{
			return super.getPossibleActions(player);
		}
		else
		{
			return super.getPossibleActions(player);
		}
	}
	
	@Override
	public String toString()
	// POST: FCTVAL = a String with the object description
	{
		return super.toString() + "Jail fine: " + JAILFINE + "\n";
	}
}
