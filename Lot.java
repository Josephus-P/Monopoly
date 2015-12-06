public class Lot extends Property
{
    private String color;       // Color group of the lot
    private int numHouses;      // Number of houses on the lot
    private int numHotels;      // Number of hotels on the lot
    private int rentFee;        // Base rent fee of the lot
    private boolean isImproved; // The improvement status
    private int improveFee;     // Cost to improve the property
    private int rentHotel;      // Rent with 1 hotel
    private int rent1House;	    // Rent with 1 house
    private int rent2House;	    // Rent with 2 houses
    private int rent3House;     // Rent with 3 houses
    private int rent4House;     // Rent with 4 houses
    
    public Lot()
    // POST: A new Lot object is created with name = "", location = 0, buyPrice = 0
    // color = "No Color", numHouses = 0, numHotels = 0,  rentFee = 0,
    // isImproved = false, improveFee = 0, rentHouse (1-4)= 0, 
	// rentHotel = 0, and locationType = "Lot"
    {
        super();
        color = "No Color";
        numHouses = 0;
        numHotels = 0;	
        rentFee = 0;
        improveFee = 0;
        isImproved = false;
        rentHotel = 0;
        rent1House = 0;
        rent2House = 0;
        rent3House = 0;
        rent4House = 0;
        this.locationType = "Lot";
    }
	
    public Lot(String name, int location, int buyPrice, String color)
    // PRE: name and color are initialized, location >= 0 and buyPrice >= 0 
    // POST: A Lot object has been constructed with data members name, location,
	// buyPrice, and color set to the values passed in the corresponding parameters
	// and numHouses = 0, numHotels = 0.
	// baseRent, rentHouse(1-4), rentHotel, and improveFee are set to the
	// respectives renthouses, rentHotel, and improveFee
    {
        super(name, location, buyPrice);
        this.color = color;
        numHouses = 0;
        numHotels = 0;
        isImproved = false;
        this.locationType = "Lot";

        switch(location) // Each branch represents a different configuration of the fee rates
        {
            case 1: setFeeRates(2, 10, 30, 90, 160, 230, 50);
                break;
            case 3: setFeeRates(4, 20, 60, 180, 320, 450, 50);
                break;
            case 6: setFeeRates(6, 30, 90, 270, 400, 550, 50);
                break;
            case 8: setFeeRates(6, 30, 90, 270, 400, 550, 50);
                break;
            case 9: setFeeRates(8, 40, 100, 300, 450, 600, 50);
                break;
            case 11: setFeeRates(10, 50, 150, 450, 625, 750, 100);
                break;
            case 13: setFeeRates(10, 50, 150, 450, 625, 750, 100);
                break;
            case 14: setFeeRates(12, 60, 180, 500, 700, 900, 100);
                break;
            case 16: setFeeRates(14, 70, 200, 550, 750, 950, 100);
                break;
            case 18: setFeeRates(14, 70, 200, 550, 750, 950, 100);
                break;
            case 19: setFeeRates(16, 80, 220, 600, 800, 1000, 100);
                break;
            case 21: setFeeRates(18, 90, 250, 700, 875, 1050, 150);
                break;
            case 23: setFeeRates(18, 90, 250, 700, 875, 1050, 150);
                break;
            case 24: setFeeRates(20, 100, 300, 750, 925, 1100, 150);
                break;
            case 26: setFeeRates(22, 110, 330, 800, 975, 1150, 150);
                break;
            case 27: setFeeRates(22, 110, 330, 800, 975, 1150, 150);
                break;
            case 29: setFeeRates(24, 120, 360, 850, 1025, 1200, 150);
                break;
            case 31: setFeeRates(26, 130, 390, 900, 1100, 1275, 200);
                break;
            case 32: setFeeRates(26, 130, 390, 900, 1100, 1275, 200);
                break;
            case 34: setFeeRates(28, 150, 450, 1000, 1200, 1400, 200);
                break;
            case 37: setFeeRates(35, 175, 500, 1100, 1300, 1500, 200);
                break;
            case 39: setFeeRates(50, 200, 600, 1400, 1700, 2000, 200);
                break;
            default:
                break;		
        }
    }
	
	public void setFeeRates(int baseRent, int rent1, int rent2, int rent3,
	    int rent4, int rentHotel, int improveFee)
	// PRE: baseRent, rentHouse(1-4), rentHotel, and improveFee are non-negative int
	// POST: Sets the data members to the respective renthouses
	// rentHotel and improveFee
	{
	    rentFee = baseRent;
	    rent1House = rent1;
	    rent2House = rent2;
	    rent3House = rent3;
	    rent4House = rent4;
	    this.rentHotel = rentHotel;
	    this.improveFee = improveFee;
	}
	
	public void setNumHouses(int numHouses)
	// PRE: 0 <= numHouses <= 4
	// POST: Sets class member numHouses to numHouses
	{
	    this.numHouses = numHouses;
	}
	
	public int getNumHouses()
	// POST: Returns the number of houses a lot has on it
	{
	    return numHouses;
	}
	
	public void setNumHotels(int numHotels)
	{
	    this.numHotels = numHotels;
	}
	
	public int getNumHotels()
	{
	    return numHotels;
	}
	
        @Override
	public int rentDue()
	// POST: FCTVAL = a rent for unimproved lots or a separate rent 
	// for having 1 house, 2 houses, etc.
	{
	    if(isImproved == false) // Returns the rent fee for unimproved lots
	    {
	        return rentFee;
	    }
	    else
	    {
	        switch(numHouses) // Returns the rent fee for lots with houses or hotel
	        {
	            case 1: return rent1House;
	            case 2: return rent2House;
	            case 3: return rent3House;
	            case 4: return rent4House;
	            default: return rentHotel;
	        }
	    }
	}
	
    public int getImproveFee()
    {
        return this.improveFee;
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
			possibilities[0] = "Pay lot rent";
			return possibilities;
		}
        else if(this.owner == player.getPlayerNumber() && this.numHouses < 4 && this.numHouses > 0)
        {
            possibilities = new String[2];
            possibilities[0] = "Build house";
            possibilities[1] = "Sell house";
            return possibilities;
        }
        else if(this.owner == player.getPlayerNumber() && this.numHouses < 4 && this.numHotels == 0)
        {
            possibilities = new String[1];
            possibilities[0] = "Build house";
            return possibilities;
        }
        else if(this.owner == player.getPlayerNumber() && this.numHouses == 4)
        {
            possibilities = new String[2];
            possibilities[0] = "Build hotel";
            possibilities[1] = "Sell house";
            return possibilities;
        }
        else if(this.owner == player.getPlayerNumber() && this.numHotels == 1)
        {
            possibilities = new String[1];
            possibilities[0] = "Sell hotel";
            return possibilities;
        }
		else			// Otherwise, the game must proceed
		{
			return super.getPossibleActions(player);
		}
	}
	
	@Override
	public String toString()
	// POST: Returns a string representation of the description of this Lot
	// (color,rentFee,numHouses,numHotels,rent1-4,
	// current rent a player has to pay, and the cost
	//		    of improving)
	{
	    return super.toString() + "Color: " + color + "\n"
	                            + "Base Rent: $" + rentFee + "\n"
	                            + "Contains " + numHouses + " houses\n"
	                            + "Contains " + numHotels + " hotels\n"
	                            + "Rent: $" + rentDue() + "\n"
	                            + (isImproved?"Improved":"Not improved") + "\n"
	                            + " Improve Cost: $" + improveFee + "\n";
	}
}
