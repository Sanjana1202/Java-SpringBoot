import org.example.datamodel.Facility;
import org.example.datamodel.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJUN2 {
    private Connection connection;

    @BeforeEach
    public void setUp() {

        // Establish a connection to the in-memory database
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

            // Create tables and insert data from SQL files
            executeSqlScript(connection, "src/test/resources/create-facilities.sql");
            executeSqlScript(connection, "src/test/resources/insert-facilities.sql");
            executeSqlScript(connection, "src/test/resources/create-members.sql");
            executeSqlScript(connection, "src/test/resources/insert-members.sql");
        } catch (Exception e) {
            System.out.println("Error occurred while setting up the database.");
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Close the connection and clean up the database
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while cleaning up the database.");
            e.printStackTrace();
        }
    }

    private void executeSqlScript(Connection connection, String fileName) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
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

    private List<Member> getMembers() {
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
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving members from the database.");
            e.printStackTrace();
        }

        return members;
    }

    private List<Facility> getFacilities() {
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
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving facilities from the database.");
            e.printStackTrace();
        }

        return facilities;
    }

    @Test
    public void testDatabaseFetch() {
        //given
        List<Member> members = getMembers();
        List<Facility> facilities = getFacilities();


        // Assert that the size of the list is correct
        assertEquals(30, members.size());
        Assertions.assertEquals(9, facilities.size());


    }
}
