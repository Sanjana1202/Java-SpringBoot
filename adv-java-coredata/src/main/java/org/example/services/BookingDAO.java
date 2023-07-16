package org.example.services;

import org.example.datamodel.Booking;
import org.example.services.impl.jpa.GenericDAO;

public interface BookingDAO extends GenericDAO<Booking> {
    // Add additional methods specific to BookingDAO here
}
