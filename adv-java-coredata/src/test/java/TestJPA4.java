import org.example.services.impl.jpa.dataservice.BookingRepository;
import org.example.datamodel.Facility;
import org.example.datamodel.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class TestJPA4 {

    @Inject
    BookingRepository bookingRepository;

    @Test
    @Transactional
    public void testCreateBooking() {

        Member member = new Member(1,"raparthi","sanjana","paris",94110,"14777555",1,Timestamp.valueOf(LocalDateTime.now()));
        Facility facility = new Facility(4,"sanjana",50.0,12.1,88.8,966.4);


        LocalDateTime bookingDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(bookingDateTime);

        bookingRepository.createBooking(member, facility, timestamp,5 );

    }
}
