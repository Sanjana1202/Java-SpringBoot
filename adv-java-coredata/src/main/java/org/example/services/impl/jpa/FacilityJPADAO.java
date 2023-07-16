package org.example.services.impl.jpa;
import org.example.datamodel.Booking;
import org.example.datamodel.Facility;
import org.example.services.BookingDAO;
import org.example.services.FacilityDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class FacilityJPADAO extends AbstractJPADAO<Facility> implements FacilityDAO

{
    public FacilityJPADAO(SessionFactory sf) {
        super(sf,Facility.class);
    }
    @Override
    public List<Facility> search(Facility instance) {
        return null;
    }

    @Override
    public Facility getById(int id) {
        return null;
    }

        @Override
        public List<Facility> getAll() {
            return super.getAll();
        }
}
