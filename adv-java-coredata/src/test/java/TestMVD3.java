import org.example.datamodel.Facility;
import org.example.datamodel.Member;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestMVD3 {
    public static void main(String[] args) {
        // Establish a connection to the in-memory database
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            // Create tables and insert data from SQL files
            executeSqlScript(connection, "create-facilities.sql");
            executeSqlScript(connection, "insert-facilities.sql");
            executeSqlScript(connection, "create-members.sql");
            executeSqlScript(connection, "insert-members.sql");

            // Retrieve members from the database
            List<Member> members = getMembers(connection);

            // Display the retrieved members
            System.out.println("Members:");
            for (Member member : members) {
                System.out.println(member);
            }

            // Retrieve facilities from the database
            List<Facility> facilities = getFacilities(connection);

            // Display the retrieved facilities
            System.out.println("\nFacilities:");
            for (Facility facility : facilities) {
                System.out.println(facility);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while interacting with the database.");
            e.printStackTrace();
        }
    }

    private static void executeSqlScript(Connection connection, String fileName) throws Exception {
        try (InputStream inputStream = TestMVD3.class.getResourceAsStream("/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Statement statement = connection.createStatement()) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                if (line.endsWith(";")) {
                    // Remove the trailing semicolon
                    sb.deleteCharAt(sb.length() - 1);

                    // Execute the SQL statement
                    statement.execute(sb.toString());

                    // Clear the StringBuilder for the next statement
                    sb.setLength(0);
                }
            }
        }
    }

    private static List<Member> getMembers(Connection connection) throws Exception {
        List<Member> members = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM members")) {

            while (resultSet.next()) {
                int memid = resultSet.getInt("memid");
                String surname = resultSet.getString("surname");
                String firstname = resultSet.getString("firstname");
                String address = resultSet.getString("address");
                int zipcode = resultSet.getInt("zipcode");
                String telephone = resultSet.getString("telephone");
                Integer recommendedby = resultSet.getInt("recommendedby");
                Timestamp joindate = resultSet.getTimestamp("joindate");

                Member member = new Member(memid, surname, firstname, address, zipcode, telephone, recommendedby, joindate);
                members.add(member);
            }
        }

        return members;
    }

    private static List<Facility> getFacilities(Connection connection) throws Exception {
        List<Facility> facilities = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM facilities")) {

            while (resultSet.next()) {
                int facid = resultSet.getInt("facid");
                String name = resultSet.getString("name");
                double membercost = resultSet.getDouble("membercost");
                double guestcost = resultSet.getDouble("guestcost");
                double initialoutlay = resultSet.getDouble("initialoutlay");
                double monthlymaintenance = resultSet.getDouble("monthlymaintenance");

                Facility facility = new Facility(facid, name, membercost, guestcost, initialoutlay, monthlymaintenance);
                facilities.add(facility);
            }
        }

        return facilities;
    }
}
