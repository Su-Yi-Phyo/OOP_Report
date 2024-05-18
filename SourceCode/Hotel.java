import java.util.ArrayList;

public class Hotel extends CommonAccommodation {
    /* Code here */
    protected int serviceStar;

    public Hotel(int accomodationID, String accomodationName, String accomodationAddress, String city,
            ArrayList<Room> roomList, float rate, int serviceStar) {
        super(accomodationID, accomodationName, accomodationAddress, city, roomList, rate);
        this.serviceStar = serviceStar;
    }

    public Hotel(int accomodationID, String accomodationName, String accomodationAddress, String city,
            float rate, int serviceStar) {
        super(accomodationID, accomodationName, accomodationAddress, city, rate);
        this.serviceStar = serviceStar;
    }

    @Override
    public String toString() {
        return "Hotel [" + accomodationID + ", " + serviceStar + ", " + accomodationName + ", " + accomodationAddress
                + ", " + city + "]";
    }

    public int getServiceStar() {
        return this.serviceStar;
    }

    public void setServiceStar(int serviceStar) {
        this.serviceStar = serviceStar;
    }
}
