public class CruiseShip extends LuxuryAccommodation {
    /* Code here */
    protected boolean privateBarAvailable;

    public CruiseShip(int accomodationID, String accomodationName, String accomodationAddress, String city,
            boolean privatePoolAvailable, boolean welcomeDrinkAvailable, boolean freeBreakfastAvailable,
            boolean gymServiceAvailable, int maxPeople, int feePerNight, boolean privateBarAvailable) {
        super(accomodationID, accomodationName, accomodationAddress, city, privatePoolAvailable, welcomeDrinkAvailable,
                freeBreakfastAvailable, gymServiceAvailable, maxPeople, feePerNight);
        this.privateBarAvailable = privateBarAvailable;
    }

    @Override
    public String toString() {
        return "CruiseShip [" + accomodationID + ", " + accomodationName + ", " + accomodationAddress + ", " + city
                + ", " + privateBarAvailable + ", " + privatePoolAvailable + ", " + welcomeDrinkAvailable + ", "
                + freeBreakfastAvailable + "]";
    }

    public boolean getPrivateBar() {
        return this.privateBarAvailable;
    }

    public void setPrivateBar(boolean privateBarAvailable) {
        this.privateBarAvailable = privateBarAvailable;
    }
}
