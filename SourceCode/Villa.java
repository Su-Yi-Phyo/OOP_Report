public class Villa extends LuxuryAccommodation {
    /* Code here */

    public Villa(int accomodationID, String accomodationName, String accomodationAddress, String city,
            boolean privatePoolAvailable, boolean welcomeDrinkAvailable, boolean freeBreakfastAvailable,
            boolean gymServiceAvailable, int maxPeople, int feePerNight) {
        super(accomodationID, accomodationName, accomodationAddress, city, privatePoolAvailable, welcomeDrinkAvailable,
                freeBreakfastAvailable, gymServiceAvailable, maxPeople, feePerNight);
    }

    @Override
    public String toString() {
        return "Villa [" + accomodationID + ", " + accomodationName + ", " + accomodationAddress + ", " + city + ", "
                + privatePoolAvailable + ", " + welcomeDrinkAvailable + ", " + freeBreakfastAvailable + ", "
                + gymServiceAvailable + ", " + maxPeople + ", " + feePerNight + "]";
    }
}
