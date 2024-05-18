import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class ReservationSystem {
    private ArrayList<Accommodation> accommodations;

    // Requirement 1: Load data
    public ReservationSystem(String accPath, String roomPath, String roomOfAccPath) {
        this.accommodations = loadAccommodations(accPath, roomPath, roomOfAccPath);
    }

    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    // Requirement 1
    public ArrayList<Accommodation> loadAccommodations(String accPath, String roomPath, String roomOfAccPath) {

        ArrayList<Accommodation> accomodationList = new ArrayList<>();
        Map<Integer, Accommodation> accomodationMap = new HashMap<>();
        Map<Integer, Room> roomMap = new HashMap<>();

        String line;

        // read accomodation.csv file
        try {
            BufferedReader accReader = new BufferedReader(new FileReader(accPath));
            Accommodation acc = null;
            while ((line = accReader.readLine()) != null) {
                String[] accParts = line.split(",");

                int accomodationID = Integer.parseInt(accParts[0]);
                String accomodationName = accParts[1];
                String accomodationAddress = accParts[2];
                String city = accParts[3];

                // consider the object based on the length
                switch (accParts.length) {
                    case 5:
                        acc = new Homestay(accomodationID, accomodationName, accomodationAddress, city,
                                Float.parseFloat(accParts[4]));
                        break;

                    case 6:
                        acc = new Hotel(accomodationID, accomodationName, accomodationAddress, city,
                                Float.parseFloat(accParts[5]),
                                Integer.parseInt(accParts[4]));
                        break;

                    case 7:
                        acc = new Resort(accomodationID, accomodationName, accomodationAddress, city,
                                Float.parseFloat(accParts[6]),
                                Integer.parseInt(accParts[4]),
                                Boolean.valueOf(accParts[5].equals("yes") ? "true" : "false"));
                        break;

                    case 10:
                        acc = new Villa(accomodationID, accomodationName, accomodationAddress, city,
                                Boolean.valueOf(accParts[4].equals("yes") ? "true" : "false"),
                                Boolean.valueOf(accParts[5].equals("yes") ? "true" : "false"),
                                Boolean.valueOf(accParts[6].equals("yes") ? "true" : "false"),
                                Boolean.valueOf(accParts[7].equals("yes") ? "true" : "false"),
                                Integer.parseInt(accParts[8]), Integer.parseInt(accParts[9]));
                        break;

                    case 11:
                        acc = new CruiseShip(accomodationID, accomodationName, accomodationAddress, city,
                                Boolean.valueOf(accParts[4].equals("yes") ? "true" : "false"),
                                Boolean.valueOf(accParts[5].equals("yes") ? "true" : "false"),
                                Boolean.valueOf(accParts[6].equals("yes") ? "true" : "false"),
                                Boolean.valueOf(accParts[7].equals("yes") ? "true" : "false"),
                                Integer.parseInt(accParts[9]), Integer.parseInt(accParts[10]),
                                Boolean.valueOf(accParts[8].equals("yes") ? "true" : "false"));
                        break;

                    default:
                        throw new IllegalArgumentException("Unexpected lengths in csv file: " + accParts.length);
                }

                // Ensure the room list is initialized
                if (acc instanceof CommonAccommodation) {
                    ((CommonAccommodation) acc).setRoomList(new ArrayList<>());
                }

                if (acc != null) {
                    accomodationList.add(acc);
                    accomodationMap.put(accomodationID, acc);
                }
            }

            accReader.close();

        } catch (IOException e) {
            System.out.println("ALERT: An error occured in acoomodation.csv file reading!!!!");
            e.printStackTrace();
            return null;
        }

        // read Room.csv file
        try {
            BufferedReader roomReader = new BufferedReader(new FileReader(roomPath));
            while ((line = roomReader.readLine()) != null) {
                String[] roomParts = line.split(",");

                Room ro = new Room(Integer.parseInt(roomParts[0]), roomParts[1], Integer.parseInt(roomParts[2]),
                        roomParts[3],
                        Integer.parseInt(roomParts[4]), Integer.parseInt(roomParts[5]),
                        Double.parseDouble(roomParts[6]),
                        Double.parseDouble(roomParts[7]));

                roomMap.put(ro.getRoomId(), ro);
            }

            roomReader.close();

        } catch (IOException e) {
            System.out.println("ALERT: An error occured in room.csv file reading!!!!");
            e.printStackTrace();
            return null;
        }

        // read room_in_acc.csv file
        try {
            BufferedReader roomOfAccReader = new BufferedReader(new FileReader(roomOfAccPath));

            while ((line = roomOfAccReader.readLine()) != null) {
                String[] roomOfAccParts = line.split(",");
                int accomoId = Integer.parseInt(roomOfAccParts[0]);
                int roomID = Integer.parseInt(roomOfAccParts[1]);

                // retrieve the room and accomodation objects from the map
                Accommodation accommodation = accomodationMap.get(accomoId);
                Room room = roomMap.get(roomID);

                if (accommodation != null && room != null) {
                    if (accommodation instanceof CommonAccommodation) {
                        ((CommonAccommodation) accommodation).getRoomList().add(room);
                    }
                }
            }
            roomOfAccReader.close();
        } catch (IOException e) {
            System.out.println("ALERT: An error occured in room_in_acc.csv file reading!!!!");
            e.printStackTrace();
            return null;
        }

        return accomodationList;
    }

    // Requirement 2
    public ArrayList<Accommodation> searchForRoom(String city, int numOfPeople) {
        ArrayList<Accommodation> resultAccommodations = new ArrayList<>();
        ArrayList<Accommodation> luxuryAccomodations = new ArrayList<>();
        ArrayList<Accommodation> commonAccommodations = new ArrayList<>();

        // filter and collect accomodations
        for (Accommodation acc : accommodations) {
            if (acc.getCity().equals(city)) {
                // it's luxury accomodation??
                if (acc instanceof LuxuryAccommodation) {
                    if (((LuxuryAccommodation) acc).getMaxPeople() >= numOfPeople) {
                        luxuryAccomodations.add(acc);
                    }
                }

                // it's common accomodation??
                else if (acc instanceof CommonAccommodation) {
                    // check each room in the common accomodation
                    for (Room room : ((CommonAccommodation) acc).getRoomList()) {
                        if (room.getMaxPeople() >= numOfPeople) {
                            commonAccommodations.add(acc);
                        }
                    }
                }
            }
        }

        // Sorting luxuary accomodations
        int size = luxuryAccomodations.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (luxuryAccomodations.get(j).getAccomodationName()
                        .compareToIgnoreCase(luxuryAccomodations.get(j + 1).getAccomodationName()) > 0) {
                    // swapping
                    Accommodation temp = luxuryAccomodations.get(j);
                    luxuryAccomodations.set(j, luxuryAccomodations.get(j + 1));
                    luxuryAccomodations.set(j + 1, temp);
                }
            }
        }

        // sorting for Common Accomodations
        int csize = commonAccommodations.size();
        for (int i = 0; i < csize - 1; i++) {
            for (int j = 0; j < csize - i - 1; j++) {
                if (commonAccommodations.get(j).getAccomodationName()
                        .compareToIgnoreCase(commonAccommodations.get(j + 1).getAccomodationName()) > 0) {
                    // swapping
                    Accommodation temp = commonAccommodations.get(j);
                    commonAccommodations.set(j, commonAccommodations.get(j + 1));
                    commonAccommodations.set(j + 1, temp);
                }
            }
        }

        // adding sorted accomodation lists to result (use addAll to append all
        // elements)
        resultAccommodations.addAll(luxuryAccomodations);
        resultAccommodations.addAll(commonAccommodations);

        return resultAccommodations;
    }

    // Requirement 3
    public ArrayList<Accommodation> searchForRoomByRange(String reservationPath, double priceFrom, double priceTo,
            Date checkin, Date checkout, String city, int numOfPeople) {

        ArrayList<Accommodation> resultAccommodations = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        // load reservation_3.csv
        try {
            BufferedReader reservationReader = new BufferedReader(new FileReader(reservationPath));
            String line;

            Reservation reserve = null;

            while ((line = reservationReader.readLine()) != null) {
                String[] reserveParts = line.split(",");
                int reservationID = Integer.parseInt(reserveParts[0]);
                int accomodationID = Integer.parseInt(reserveParts[1]);

                long checkIn, checkOut;
                Date checkInTime, checkOutTime;
                int roomID;

                switch (reserveParts.length) {
                    case 4:
                        checkIn = Long.parseLong(reserveParts[2]);
                        checkOut = Long.parseLong(reserveParts[3]);
                        checkInTime = new Date(checkIn);
                        checkOutTime = new Date(checkOut);

                        reserve = new Reservation(reservationID, accomodationID, -1, checkInTime, checkOutTime);
                        break;

                    case 5:
                        roomID = Integer.parseInt(reserveParts[2]);
                        checkIn = Long.parseLong(reserveParts[3]);
                        checkOut = Long.parseLong(reserveParts[4]);
                        checkInTime = new Date(checkIn);
                        checkOutTime = new Date(checkOut);

                        reserve = new Reservation(reservationID, accomodationID, roomID, checkInTime, checkOutTime);
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Unexpected lengths in csv file:  " + reserveParts.length);
                }
            }

            if (reserve != null) {
                reservations.add(reserve);
            }
            reservationReader.close();

        } catch (IOException e) {
            System.out.println("ALERT: An error occured in reading reservation_3.csv file!!!!");
            e.printStackTrace();
            return null;
        }

        // filter and collect accomodations
        double pricePerNight = 0;
        boolean isRoomAvailable = true;

        for (Accommodation acc : accommodations) {
            if (acc.getCity().equals(city)) {
                // it's luxury accomodation???
                if (acc instanceof LuxuryAccommodation) {
                    pricePerNight = ((LuxuryAccommodation) acc).getFeePerNight();
                    // withing the price range or not??
                    if (pricePerNight >= priceFrom && pricePerNight <= priceTo) {
                        // no more than 2 people and same as the number of people user input
                        if (((LuxuryAccommodation) acc).getMaxPeople() <= 2
                                || ((LuxuryAccommodation) acc).getMaxPeople() == numOfPeople) {
                            isRoomAvailable = true;

                            // check reservation availability within the time range (overlap? => can't
                            // reserve, if not=> ok)
                            for (Reservation reservation : reservations) {
                                if (reservation.getAccId() == acc.getAccomodationID()) {
                                    if (checkin.before(reservation.getCheckout())
                                            && checkout.after(reservation.getCheckin())) {
                                        isRoomAvailable = false;
                                        break;
                                    }
                                }
                            }
                            if (isRoomAvailable) {
                                resultAccommodations.add(acc);
                            }
                        }
                    }
                }

                // it's common accomodation???
                else if (acc instanceof CommonAccommodation) {
                    for (Room room : ((CommonAccommodation) acc).getRoomList()) {
                        pricePerNight = room.getFeePerNight();
                        // withing the price range or not??
                        if (pricePerNight >= priceFrom && pricePerNight <= priceTo) {
                            // no more than 2 people and same as the number of people user input
                            if (room.getMaxPeople() <= 2 || room.getMaxPeople() == numOfPeople) {
                                isRoomAvailable = true;

                                // check reservation availability within the time range
                                for (Reservation reservation : reservations) {
                                    if (reservation.getRoomId() == room.getRoomId()
                                            && reservation.getAccId() == acc.getAccomodationID()) {
                                        if (checkin.before(reservation.getCheckout())
                                                && checkout.after(reservation.getCheckin())) {
                                            isRoomAvailable = false;
                                            break;
                                        }
                                    }
                                }

                                if (isRoomAvailable) {
                                    resultAccommodations.add(acc);
                                }
                            }
                        }
                    }
                }
            }
        }

        return resultAccommodations;
    }

    // Requirement 4
    public ArrayList<Accommodation> searchInAdvance(String city, Integer numOfPeople, String roomType,
            Boolean privatePool, Integer starQuality, Boolean freeBreakfast, Boolean privateBar) {
        ArrayList<Accommodation> resultAccommodations = new ArrayList<>();

        for (Accommodation acc : accommodations) {
            if (acc.getCity().equals(city)) {
                // luxury accomodation?? (Note: city and people can't be null, the rest can be
                // null)(CruiseShip => barcheck)
                if (acc instanceof LuxuryAccommodation) {
                    LuxuryAccommodation luxuryAccommodation = (LuxuryAccommodation) acc;
                    if (numOfPeople != null && luxuryAccommodation.getMaxPeople() >= numOfPeople) {
                        if (privatePool == null || luxuryAccommodation.getPrivatePool() == privatePool) {
                            if (freeBreakfast == null || luxuryAccommodation.getFreeBreakfast() == freeBreakfast) {
                                if (privateBar == null || !(acc instanceof CruiseShip)
                                        || ((CruiseShip) acc).getPrivateBar() == privateBar) {
                                    if (starQuality == null && roomType == null) {
                                        resultAccommodations.add(acc);
                                    }
                                }
                            }
                        }
                    }
                }

                // common accomodation??(Note: city and people can't be null, the rest can be
                // null)(Hotel, Resort => star check, Resort => pool check)
                else if (acc instanceof CommonAccommodation) {
                    CommonAccommodation commonAccomo = (CommonAccommodation) acc;
                    for (Room room : commonAccomo.getRoomList()) {
                        if (numOfPeople != null && room.getMaxPeople() >= numOfPeople) {
                            if (roomType == null || room.getRoomType().equals(roomType)) {
                                if (freeBreakfast == null || privateBar == null) {
                                    if (starQuality == null
                                            || (acc instanceof Hotel && ((Hotel) acc).getRate() == starQuality)
                                            || (acc instanceof Resort && ((Resort) acc).getRate() == starQuality)) {
                                        if (privatePool == null || !(acc instanceof Resort)
                                                || ((Resort) acc).getPrivatePool() == privatePool) {
                                            resultAccommodations.add(acc);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        return resultAccommodations;
    }

    // Requirement 5
    public double performReservation(String reservationPath, Accommodation acc, Room room, Date checkin, Date checkout)
            throws Exception {

        double totalFee = 0.0;
        boolean roomBooked = false;

        // read reservation5.csv file
        try {
            BufferedReader reservationReader = new BufferedReader(new FileReader(reservationPath));
            String line;

            while ((line = reservationReader.readLine()) != null) {
                String[] reserveParts = line.split(",");
                int reservationAccomoID = Integer.parseInt(reserveParts[1]);
                long timecheckIn, timecheckOut;

                // luxury accomo??
                if (acc instanceof LuxuryAccommodation) {
                    if (reservationAccomoID == acc.getAccomodationID()) {
                        timecheckIn = Long.parseLong(reserveParts[2]);
                        timecheckOut = Long.parseLong(reserveParts[3]);

                        // room availability check
                        if ((checkin.getTime() < timecheckOut) && (checkout.getTime() > timecheckIn)) {
                            roomBooked = true;
                            break;
                        }
                    }
                }

                // common accomod??
                else if (acc instanceof CommonAccommodation) {
                    int roomID = Integer.parseInt(reserveParts[2]);
                    if (reservationAccomoID == acc.getAccomodationID() && roomID == room.getRoomId()) {
                        timecheckIn = Long.parseLong(reserveParts[3]);
                        timecheckOut = Long.parseLong(reserveParts[4]);

                        // room availability check
                        if ((checkin.getTime() < timecheckOut) && (checkout.getTime() > timecheckIn)) {
                            roomBooked = true;
                            break;
                        }
                    }
                }
            }
            reservationReader.close();

        } catch (IOException e) {
            System.out.println("ALERT: An error occured in reservation5.csv file reading!!!");
            e.printStackTrace();
        }

        if (roomBooked) {
            throw new Exception("The room has already been booked during this time period");
        } else {
            long diffInDays = diffBetweenDays(checkin.getTime(), checkout.getTime());

            // at least one day before
            if (diffInDays < 1)
                diffInDays = 1;

            // if luxury, use fee of luxury accomos
            if (acc instanceof LuxuryAccommodation) {
                totalFee = ((LuxuryAccommodation) acc).getFeePerNight() * diffInDays;
            } else {
                totalFee = room.getFeePerNight() * diffInDays; // use fee of room
            }
            // add tax 8%
            totalFee += totalFee * 0.08;
        }

        return totalFee;
    }

    // Helper functions for req 5
    public long diffBetweenDays(long dateStart, long dateEnd) {
        Date date = new Date(dateStart * 1000);
        Date date1 = new Date(dateEnd * 1000);

        date = removeTime(date);
        date1 = removeTime(date1);

        long diff = Math.abs(date1.getTime() - date.getTime());
        long numOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return numOfDays;
    }

    private Date removeTime(Date date) {
        long time = date.getTime();
        long timeWithoutTime = time - (time % (24 * 60 * 60 * 1000));
        return new Date(timeWithoutTime);
    }
}
