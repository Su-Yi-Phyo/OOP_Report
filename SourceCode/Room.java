public class Room {
    /* Code here */
    protected int roomID;
    protected String roomName;
    protected int noOfFloor;
    protected String roomType;
    protected int bedNum;
    protected int maxPeople;
    protected double floorArea;
    protected double feePerNight;

    public Room(int roomID, String roomName, int noOfFloor, String roomType, int bedNum, int maxPeople,
            double floorArea, double feePerNight) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.noOfFloor = noOfFloor;
        this.roomType = roomType;
        this.bedNum = bedNum;
        this.maxPeople = maxPeople;
        this.floorArea = floorArea;
        this.feePerNight = feePerNight;
    }

    @Override
    public String toString() {
        return "Room [" + roomID + ", " + roomName + ", " + noOfFloor + ", " + roomType + ", " + bedNum + ", "
                + maxPeople + ", " + floorArea + ", " + feePerNight + "]";
    }

    public int getRoomId() {
        return this.roomID;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public int getNumofFloor() {
        return this.noOfFloor;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public int getBedNums() {
        return this.bedNum;
    }

    public int getMaxPeople() {
        return this.maxPeople;
    }

    public double getArea() {
        return this.floorArea;
    }

    public double getFeePerNight() {
        return this.feePerNight;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNumofFloor(int noOfFloor) {
        this.noOfFloor = noOfFloor;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setArea(double floorArea) {
        this.floorArea = floorArea;
    }

    public void setFeePerNight(double feePerNight) {
        this.feePerNight = feePerNight;
    }
}
