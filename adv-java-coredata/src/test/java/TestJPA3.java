import org.example.datamodel.Booking;
import org.example.services.BookingDAO;
import org.example.services.SqlReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
@Transactional
public class TestJPA3 {
    @Autowired
    private DataSource dataSource;
    @Inject
    SessionFactory sessionFactory;

    @Autowired
    private BookingDAO bookingDAO;


    @Test
    public void testFetchAllBookings() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String basePath = "src/test/resources/";

        session.createNativeQuery(SqlReader.readAll(basePath + "create-members.sql")).executeUpdate();
        session.createNativeQuery(SqlReader.readAll(basePath + "create-facilities.sql")).executeUpdate();
        session.createNativeQuery(SqlReader.readAll(basePath + "insert-members.sql")).executeUpdate();
        session.createNativeQuery(SqlReader.readAll(basePath + "insert-facilities.sql")).executeUpdate();
        session.createNativeQuery(SqlReader.readAll(basePath + "create-bookings.sql")).executeUpdate();
        session.createNativeQuery(SqlReader.readAll(basePath + "insert-bookings.sql")).executeUpdate();

        transaction.commit();

        List<Booking> bookings = bookingDAO.getAll();
        Assertions.assertEquals(4044, bookings.size());
    }

}
