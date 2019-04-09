import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class Main {



    public int doMigration(){

        LocalDate currDate = LocalDate.now();
        Select select = new Select();
        //get bookings
        List<Booking> bookingsToMigrate= select.getBookings(currDate);
        List<Integer> ids = new LinkedList();
        bookingsToMigrate.forEach((e) -> ids.add(e.getBookingId()));
        //List<Guest> guests = select.getGuests();


        List<FactWarehouse> factWAndDimensions = createFactWAndDimensions(bookingsToMigrate);

        Warehouse wh = new Warehouse();
        wh.insertIntoWareHouse(factWAndDimensions);

        for(Booking booking: bookingsToMigrate){
            int id = booking.getBookingId();

            select.deleteByIdFromDb(id);

        }



        return factWAndDimensions.size();

    }

    private BookingW createBookingW(Booking booking) {
        BookingW bw = new BookingW();
        String bookingId = "2" + booking.getBookingId();
        bw.setBookingId(Integer.parseInt(bookingId));
        bw.setFromDate(booking.getFrom());
        bw.setToDate(booking.getTo());

        return bw;
    }

    private CustomerW createCustomerW(Guest guest) {
        CustomerW customerW = new CustomerW();
        String customerId = "2" + guest.getGid();
        customerW.setCustId(Integer.parseInt(customerId));
        customerW.setName(guest.getF_name() + " " + guest.getL_name());
        if(guest.getCountry() == null) {
            System.out.println("null");
        }
        customerW.setNationality(guest.getCountry().toLowerCase());
        return customerW;
    }



    private List<FactWarehouse> createFactWAndDimensions(List<Booking> bookingsToMigrate) {

        LocationW lw = new LocationW();
        Select select = new Select();
        Guest g = new Guest();
        List<FactWarehouse> warehouseEntries = new ArrayList<>();
        for(Booking booking : bookingsToMigrate) {

            FactWarehouse fw = new FactWarehouse();
            fw.setLocation(lw);
            TimeW timeW = new TimeW(booking.getTo());
            fw.setTimew(timeW);
            BookingW bookingW = createBookingW(booking);
            fw.setBooking(bookingW);
            int guestid = booking.getGuestId();
            g =  select.getGuestForId(guestid);
            if(g.getCountry() == null) {
                System.out.println("null");
            }
            CustomerW customerW = createCustomerW(g);
            fw.setCustomer(customerW);
//            long numOfDays = (booking.getTo().getTime() - booking.getArrivalTime().getTime())  / (24 * 60 * 60 * 1000);
//            // price * num of days * tax
//            double totalAmount = booking.getRoom().getType().getBasePrice() * (numOfDays) * 1.13;
            fw.setTotalAmount((long) booking.getAmount());


            warehouseEntries.add(fw);
        }
        return warehouseEntries;
    }


    public static void main(String[] args) {
        Main main = new Main();
        LocalDate date = LocalDate.now();
        int n;
        n=main.doMigration();

        System.out.println(n);




    }




}










