abstract class Accommodation {
    /* Code here */
    protected int accomodationID;
    protected String accomodationName;
    protected String accomodationAddress;
    protected String city;

    public Accommodation(int accomodationID, String accomodationName, String accomodationAddress, String city) {
        this.accomodationID = accomodationID;
        this.accomodationName = accomodationName;
        this.accomodationAddress = accomodationAddress;
        this.city = city;
    }

    public int getAccomodationID() {
        return this.accomodationID;
    }

    public String getAccomodationName() {
        return this.accomodationName;
    }

    public String getAccomodationAddress() {
        return this.accomodationAddress;
    }

    public String getCity() {
        return this.city;
    }

    public void setAccomodationID(int accomodationID) {
        this.accomodationID = accomodationID;
    }

    public void setAccomodationName(String accomodationName) {
        this.accomodationName = accomodationName;
    }

    public void setAccomodationAddress(String accomodationAddress) {
        this.accomodationAddress = accomodationAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
