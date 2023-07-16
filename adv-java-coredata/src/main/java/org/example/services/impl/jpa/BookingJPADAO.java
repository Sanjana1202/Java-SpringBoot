package org.example.services.impl.jpa;
import org.example.datamodel.Booking;
import org.example.services.BookingDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class BookingJPADAO extends AbstractJPADAO<Booking> implements BookingDAO {

    public BookingJPADAO(SessionFactory sf) {
        super(sf, Booking.class);
    }

    @Override
    public List<Booking> search(Booking instance) {
        // Implement the search logic specific to Booking
        return null;
    }

    @Override
    public Booking getById(int id) {
        // Implement the getById logic specific to Booking
        return null;
    }

    @Override
    public List<Booking> getAll() {
        return super.getAll();
    }
}
