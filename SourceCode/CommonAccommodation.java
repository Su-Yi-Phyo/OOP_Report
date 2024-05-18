import java.util.ArrayList;

public class CommonAccommodation extends Accommodation {
   /* Code here */
   protected ArrayList<Room> roomList;
   protected float rate;

   public CommonAccommodation(int accomodationID, String accomodationName, String accomodationAddress, String city,
         ArrayList<Room> roomList, float rate) {
      super(accomodationID, accomodationName, accomodationAddress, city);
      this.roomList = roomList;
      this.rate = rate;
   }

   // no room list
   public CommonAccommodation(int accomodationID, String accomodationName, String accomodationAddress, String city,
         float rate) {
      super(accomodationID, accomodationName, accomodationAddress, city);
      this.rate = rate;
   }

   public ArrayList<Room> getRoomList() {
      return this.roomList;
   }

   public float getRate() {
      return this.rate;
   }

   public void setRoomList(ArrayList<Room> roomList) {
      this.roomList = roomList;
   }

   public void setRate(float rate) {
      this.rate = rate;
   }

}
