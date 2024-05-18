import java.util.ArrayList;

public class Homestay extends CommonAccommodation {
    /* Code here */
    public Homestay(int accomodationID, String accomodationName, String accomodationAddress, String city,
            ArrayList<Room> roomList, float rate) {
        super(accomodationID, accomodationName, accomodationAddress, city, roomList, rate);
    }

    public Homestay(int accomodationID, String accomodationName, String accomodationAddress, String city,
            float rate) {
        super(accomodationID, accomodationName, accomodationAddress, city, rate);
    }

    @Override
    public String toString() {
        return "HomeStay [" + accomodationID + ", " + accomodationName + ", " + accomodationAddress + ", " + rate + ", "
                + city + "]";
    }
}
