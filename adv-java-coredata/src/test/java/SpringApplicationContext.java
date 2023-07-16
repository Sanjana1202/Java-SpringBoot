import org.example.services.impl.jpa.dataservice.BookingRepository;
import org.example.services.BookingDAO;
import org.example.services.FacilityDAO;
import org.example.services.MemberDAO;
import org.example.services.impl.jpa.BookingJPADAO;
import org.example.services.impl.jpa.FacilityJPADAO;
import org.example.services.impl.jpa.MemberJPADAO;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class SpringApplicationContext {


    @Bean("firstBean")
    public String stringHello(){
        return "test from spring!";
    }


    @Bean
    public DataSource mainDatasource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource ds) throws SQLException, IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(ds);
        localSessionFactoryBean.setPackagesToScan("org.example.datamodel");
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }
    @Bean
    public BookingRepository bookingRepository(FacilityDAO facilityDAO, MemberDAO memberDAO, BookingDAO bookingDAO,SessionFactory sessionFactory) {
        return new BookingRepository(facilityDAO, memberDAO, bookingDAO,sessionFactory);
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public MemberDAO memberDAObean(SessionFactory sf)  {
        return new MemberJPADAO(sf);
    }
    @Bean
    public FacilityDAO facilityDAO(SessionFactory sf)  {
        return new FacilityJPADAO(sf);
    }
    @Bean
    public BookingDAO bookingDAO(SessionFactory sf)  {
        return new BookingJPADAO(sf);
    }
}