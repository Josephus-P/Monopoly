public class BoardLocation
{
	protected String name;        	// The name of the square
	protected int location;       	// The location counting spaces from GO
	protected String locationType; 	// The type of the location
	
	public BoardLocation()
	// POST: A new BoardLocation object is created with name = "" and location = 0
	// 		 and locationType = ""
	{
		name = "";
		location = 0;
		locationType = "";
	}
	
	public BoardLocation(String name, int location)
	// PRE: name is initialized and location >= 0
	// POST: A new BoardLocation object is created with
	//       this.name = name and this.location = location
	//       and locationType = ""
	{
	    this.name = name;
	    this.location = location;
	    locationType = "";
	}
	
	public String getName()
	// POST: FCTVAL = name
	{
	    return name;
	}
	
	public int getLocation()
	// POST: FCTVAL = location
	{
	    return location;
	}
	
	public String getLocationType()
	// POST: FCTVAL = locationtype
	{
		return locationType;
	}
	
	public void setLocationType(String locationType)
	// PRE: locationType is initialized
	// POST: this.locationType is set to locationType
	{
		this.locationType = locationType;
	}
	
	public String[] getPossibleActions(Player player)
	// PRE: player is initialized
	// POST: FCTVAL = array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		String[] possibilities = new String[1];
		possibilities[0] = "Proceed";
		return possibilities;
	}
	
	@Override
	public String toString()
	// POST: FCTVAL = a String representing the object status
	{
	    return name + "\nLocation: " + location + "\n" +
	    		"Location type: " + locationType + "\n";
	}
}
