import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Select {

    private final String url = "jdbc:postgresql://134.209.61.5:5432/dbhotel2";
    private final String user = "shobhit";
    private final String password = "hoteladmin";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<Booking> getBookings(LocalDate currDate){
        currDate = LocalDate.now();

        List<Booking> bookings = new ArrayList<>();


        //Booking b = new Booking();


        String SQL = "SELECT b.* from  booking b where b.to_date::text <= ? order by b.bid;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            // display actor information

            stmt.setString(1, String.valueOf(currDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

                Booking b = new Booking();

                b.setBookingId(rs.getInt("bid"));
                b.setFrom(rs.getDate("from_date"));
                b.setTo(rs.getDate("to_date"));
                b.setRno(rs.getInt("roomno"));
                b.setAmount(rs.getInt("total_amt"));
                b.setGuestId(rs.getInt("gid"));

                bookings.add(b);

            }



            //displayBookings(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bookings;


    }
//
    public List<Guest> getGuests(){


        List<Guest> guests = new ArrayList<>();


        //Booking b = new Booking();


        String SQL = "SELECT * from  guest ;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {



            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

                Guest g = new Guest();

                g.setGid(rs.getInt("gid"));
                g.setF_name(rs.getString("f_name"));
                g.setL_name(rs.getString("l_name"));
                g.setAddr(rs.getString("addr"));
                g.setPhone(rs.getString("phone"));
                g.setCard(rs.getString("credit_card"));
                g.setCountry(rs.getString("country"));

                guests.add(g);

            }



            //displayBookings(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return guests;


    }

    public Guest getGuestForId(int id){

        Guest g = new Guest();
        Booking b = new Booking();
        //id = b.getGuestId();

        String SQL = "SELECT g.gid,g.f_name,g.l_name,g.addr,g.phone,g.credit_card, g.country from  guest g inner join booking b on b.gid=g.gid where b.gid = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){





                g.setGid(rs.getInt("gid"));
                g.setF_name(rs.getString("f_name"));
                g.setL_name(rs.getString("l_name"));
                g.setAddr(rs.getString("addr"));
                g.setPhone(rs.getString("phone"));
                g.setCard(rs.getString("credit_card"));
                g.setCountry(rs.getString("country"));

            }



            //displayBookings(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return g;

    }

    public void deleteByIdFromDb(int bookingId){



        //Booking b = new Booking();


        String SQL = "DELETE FROM booking where bid = ?";

        int deletedRows = 0;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            // display actor information

            stmt.setInt(1, bookingId);
//            ResultSet rs = stmt.executeQuery();
            deletedRows = stmt.executeUpdate();




            //displayBookings(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }






    }




}
