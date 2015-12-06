public class TaxSquare extends BoardLocation {

	public TaxSquare()
	// POST: A new TaxSquare object is created with
	//       name = "", location = 0, and the other properties set accordingly
	//		 to the default super() constructor
	{
		super();
		this.locationType = "Tax Square";
	}
	
	public TaxSquare(String name, int location)
	// PRE: name is initialized and location >= 0
	// POST: TaxSquare object is created and uses the super class 
	// from BoardLocation class. setlocation is set to Tax Square
	{
	    super(name, location);
	    this.locationType = "Tax Square";
	}
	
	public int taxDue()
	// POST: FCTVAL = the tax due the kind of tax square location
	{
		if(this.name.equals("INCOME TAX"))	// Returns the respective tax
											// due to the square type
			return 200;
		else if(this.name.equals("LUXURY TAX"))
			return 75;
		else
			return 0;
	}
	
	@Override
	public String[] getPossibleActions(Player player)
	// PRE: player is initialized
	// POST: FCTVAL = array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		String[] possibilities;
		// The player must pay the tax
		possibilities = new String[1];
		possibilities[0] = "Pay the tax";
		return possibilities;
	}
	
	@Override
	public String toString()
	// POST: FCTVAL = a String representing the TaxSquare properties
	//		 The description of the datamembers specified on super classes
	//		 are given by the super.toString() method
	{
		return super.toString() + "Tax due: " + taxDue() + "\n";
	}
}
