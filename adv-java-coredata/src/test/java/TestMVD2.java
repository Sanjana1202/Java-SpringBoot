import org.example.datamodel.Facility;
import org.example.datamodel.Member;

import java.sql.Timestamp;

public class TestMVD2 {
    public static void main(String[] args) {

        Facility facility = new Facility(0, "Tennis Court 1", 5, 25, 10000, 200);

        Timestamp joinDate = Timestamp.valueOf("2012-07-02 12:02:05");
        Member member = new Member(1, "Smith", "Darren", "8 Bloomsbury Close, Boston", 4321, "555-555-5555", 1, joinDate);


        System.out.println("Facility Details:");
        System.out.println("Facility ID: " + facility.getFacid());
        System.out.println("Name: " + facility.getName());
        System.out.println("Member Cost: " + facility.getMemberCost());
        System.out.println("Guest Cost: " + facility.getGuestCost());
        System.out.println("Initial Outlay: " + facility.getInitialOutlay());
        System.out.println("Monthly Maintenance: " + facility.getMonthlyMaintenance());

        System.out.println();


        System.out.println("Member Details:");
        System.out.println("Member ID: " + member.getMemid());
        System.out.println("Surname: " + member.getSurname());
        System.out.println("First Name: " + member.getFirstname());
        System.out.println("Address: " + member.getAddress());
        System.out.println("Zipcode: " + member.getZipcode());
        System.out.println("Telephone: " + member.getTelephone());
        System.out.println("Recommended By: " + member.getRecommendedBy());
        System.out.println("Join Date: " + member.getJoinDate());
    }
}


