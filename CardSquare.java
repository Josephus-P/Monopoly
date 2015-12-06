import java.util.Random;

public class CardSquare extends BoardLocation
{
    private final static int CARDAMOUNTLIMIT = 200;   // The money amount limit that a card
                                                      // can give or take from a player
    
	public CardSquare()
	// POST: A new CardSquare object is created with name = "",
	//       location = 0, and locationType = "CardSquare"
	{
		super();
		this.locationType = "CardSquare";
	}
	
	public CardSquare(String name, int location)
	// PRE: name and location are initialized
	// POST: A new CardSquare object is created with
	//       this.name = name, this.location = location, and locationType = "CardSquare"
	{
	    super(name, location);
	    this.locationType = "Card Square";
	}
	
	public int obtainCardMoneyAmount()
	// POST: FCTVAL = a random money ammount in $ given by the selected card.
	//       The value can be positive or negative.
	{
		Random random;
		int randomNum;
		
		random = new Random();
		randomNum = random.nextInt(2*CARDAMOUNTLIMIT) - CARDAMOUNTLIMIT;
		
	    return randomNum;
	}
	
	@Override
	public String[] getPossibleActions(Player player)
	// PRE: player is initialized
	// POST: FCTVAL = array of options player has upon landing on
	//       this space, to be used in a menu in a user interface.
	// 		 No other possibility than get or loss some money.
	{
		String[] possibilities = new String[1];
		possibilities[0] = "Proceed with the chance result";
		return possibilities;
	}
	
	@Override
	public String toString()
	// POST: FCTVAL = a String representing the object status
	{
		return super.toString() + "Card square amonut limit: " + CARDAMOUNTLIMIT + "\n";
	}
}
