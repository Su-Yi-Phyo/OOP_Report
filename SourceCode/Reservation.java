import java.util.*;

public class Reservation {
    private int reservationId;
    private int accId;
    private int roomId;
    private Date checkin;
    private Date checkout;

    public Reservation(int reservationId, int accId, int roomId, Date checkin, Date checkout) {
        this.reservationId = reservationId;
        this.accId = accId;
        this.roomId = roomId;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public int getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Override
    public String toString() {
        return String.format("Reservation [%s, %s, %s, %s, %s]", reservationId, accId, roomId, checkin, checkout);
    }
}
