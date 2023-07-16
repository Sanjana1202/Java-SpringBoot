import org.example.datamodel.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class TestJPA1 {

    @Inject
    SessionFactory sessionFactory;

    @Test
    public void test() {
        Session session = sessionFactory.openSession();
        Member member = new Member(1, "Raparthi", "Sanjana", "Paris", 500,
                "12344", 2, Timestamp.valueOf(LocalDateTime.now()));
        Transaction transaction = session.beginTransaction();
        session.persist(member);
        session.flush();
        transaction.commit();
        List<Member> list = session.createQuery("from Member u where u.surname = 'Raparthi'", Member.class).list();
        Assertions.assertEquals(list.get(0).getSurname(), "Raparthi");
    }
}
