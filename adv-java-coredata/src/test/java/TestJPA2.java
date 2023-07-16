import org.example.datamodel.Facility;
import org.example.datamodel.Member;
import org.example.services.FacilityDAO;
import org.example.services.MemberDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class TestJPA2 {
    @Inject
    SessionFactory sessionFactory;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private FacilityDAO facilityDAO;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp() throws Exception {
        executeSqlScript("insert-facilities.sql");
        executeSqlScript("insert-members.sql");
    }

    private void executeSqlScript(String scriptFileName) throws Exception {
        ClassPathResource resource = new ClassPathResource(scriptFileName);
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
    }

    @Test
    public void fetchMembers() {
        // Fetch all data from the Members table
        Session session = sessionFactory.openSession();
        List<Member> members = memberDAO.getAll();
        System.out.println("Members:");
        for (Member member : members) {
            System.out.println(member);
        }
        Assertions.assertEquals(30, members.size());
    }

    @Test
    public void fetchFacilities() {
        // Fetch all data from the Facilities table
        List<Facility> facilities = facilityDAO.getAll();
        System.out.println("Facilities:");
        for (Facility facility : facilities) {
            System.out.println(facility);
        }
        Assertions.assertEquals(9, facilities.size());
    }
}
