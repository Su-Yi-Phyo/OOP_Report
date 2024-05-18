public class LuxuryAccommodation extends Accommodation {
    /* Code here */
    protected boolean privatePoolAvailable;
    protected boolean welcomeDrinkAvailable;
    protected boolean freeBreakfastAvailable;
    protected boolean gymServiceAvailable;
    protected int maxPeople;
    protected int feePerNight;

    public LuxuryAccommodation(int accomodationID, String accomodationName, String accomodationAddress, String city) {
        super(accomodationID, accomodationName, accomodationAddress, city);
    }

    public LuxuryAccommodation(int accomodationID, String accomodationName, String accomodationAddress, String city,
            boolean privatePoolAvailable, boolean welcomeDrinkAvailable, boolean freeBreakfastAvailable,
            boolean gymServiceAvailable, int maxPeople, int feePerNight) {
        super(accomodationID, accomodationName, accomodationAddress, city);
        this.privatePoolAvailable = privatePoolAvailable;
        this.welcomeDrinkAvailable = welcomeDrinkAvailable;
        this.freeBreakfastAvailable = freeBreakfastAvailable;
        this.gymServiceAvailable = gymServiceAvailable;
        this.maxPeople = maxPeople;
        this.feePerNight = feePerNight;
    }

    public boolean getPrivatePool() {
        return this.privatePoolAvailable;
    }

    public boolean getWelcomeDrink() {
        return this.welcomeDrinkAvailable;
    }

    public boolean getFreeBreakfast() {
        return this.freeBreakfastAvailable;
    }

    public boolean getGymService() {
        return this.gymServiceAvailable;
    }

    public int getMaxPeople() {
        return this.maxPeople;
    }

    public int getFeePerNight() {
        return this.feePerNight;
    }

    public void setPrivatePool(boolean privatePoolAvailable) {
        this.privatePoolAvailable = privatePoolAvailable;
    }

    public void setWelcomeDrink(boolean welcomeDrinkAvailable) {
        this.welcomeDrinkAvailable = welcomeDrinkAvailable;
    }

    public void setFreeBreakfast(boolean freeBreakfastAvailable) {
        this.freeBreakfastAvailable = freeBreakfastAvailable;
    }

    public void setGymService(boolean gymServiceAvailable) {
        this.gymServiceAvailable = gymServiceAvailable;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setFeePerNight(int feePerNight) {
        this.feePerNight = feePerNight;
    }
}
