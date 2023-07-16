import org.example.datamodel.Facility;
import org.example.datamodel.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestJUN1 {
    private static void executeSqlScript(Connection connection, String fileName) throws Exception {
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
    @Test
    public void testDatabaseFetch() {
        // Establish a connection to the in-memory database
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            // given
            executeSqlScript(connection, "src/test/resources/create-facilities.sql");
            executeSqlScript(connection, "src/test/resources/insert-facilities.sql");
            executeSqlScript(connection, "src/test/resources/create-members.sql");
            executeSqlScript(connection, "src/test/resources/insert-members.sql");

         // when
            List<Member> members = getMembers(connection);
            List<Facility> facilities = getFacilities(connection);

            // then
            Assertions.assertEquals(30, members.size());
            Assertions.assertEquals(9, facilities.size());

        } catch (Exception e) {
            System.out.println("Error occurred while interacting with the database.");
            e.printStackTrace();
        }
    }
}
