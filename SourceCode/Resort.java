import java.util.ArrayList;

public class Resort extends CommonAccommodation {
    /* Code here */
    protected int star;
    protected boolean privatePoolAvailable;

    public Resort(int accomodationID, String accomodationName, String accomodationAddress, String city,
            ArrayList<Room> roomList, float rate, int star, boolean privatePoolAvailable) {
        super(accomodationID, accomodationName, accomodationAddress, city, roomList, rate);
        this.star = star;
        this.privatePoolAvailable = privatePoolAvailable;
    }

    // no room list
    public Resort(int accomodationID, String accomodationName, String accomodationAddress, String city,
            float rate, int star, boolean privatePoolAvailable) {
        super(accomodationID, accomodationName, accomodationAddress, city, rate);
        this.star = star;
        this.privatePoolAvailable = privatePoolAvailable;
    }

    public String toString() {
        return "Resort [" + accomodationID + ", " + accomodationName + ", " + star + ", " + accomodationAddress + ", "
                + privatePoolAvailable + ", " + city + "]";
    }

    public int getStar() {
        return this.star;
    }

    public boolean getPrivatePool() {
        return this.privatePoolAvailable;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setPrivatePool(boolean privatePoolAvailable) {
        this.privatePoolAvailable = privatePoolAvailable;
    }
}
