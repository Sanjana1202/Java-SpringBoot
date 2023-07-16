package org.example.services.impl.jpa.dataservice;

import org.example.datamodel.Booking;
import org.example.datamodel.Facility;
import org.example.datamodel.Member;
import org.example.services.BookingDAO;
import org.example.services.FacilityDAO;
import org.example.services.MemberDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Timestamp;

public class BookingRepository {

    SessionFactory sf;
    FacilityDAO facilityDAO;
    MemberDAO memberDAO;
    BookingDAO bookingDAO;

    public BookingRepository(FacilityDAO facilityDAO, MemberDAO memberDAO, BookingDAO bookingDAO, SessionFactory sf) {
        this.facilityDAO = facilityDAO;
        this.memberDAO = memberDAO;
        this.bookingDAO = bookingDAO;
        this.sf=sf;
    }

    public void createBooking(Member member, Facility facility, Timestamp bookingDateTime, int slotsTaken) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Booking booking = new Booking();
        booking.setMember(member);
        booking.setFacility(facility);
        booking.setStartTime(bookingDateTime);
        booking.setSlots(slotsTaken);
        bookingDAO.create(booking);
        transaction.commit();
        session.close();
    }
}


