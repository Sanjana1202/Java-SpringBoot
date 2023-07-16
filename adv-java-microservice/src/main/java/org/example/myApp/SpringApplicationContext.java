package org.example.myApp;

import org.example.datamodel.Facility;
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
import services.FacilityDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class SpringApplicationContext {
    @Bean
    public DataSource mainDatasource() {
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) throws SQLException, IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("org.example.datamodel");
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }

    @Bean
    public FacilityDAO getFacilityDAO(SessionFactory sf) {
        return new FacilityJPADAO(sf);
    }

    @Bean
    public FacilityJPADAO getFacilty(SessionFactory sf) {
        return new FacilityJPADAO(sf);
    }

    @Bean("getFacilityDAO")
    public BookingDAO getBookingDAO(SessionFactory sf) {
        return new BookingJPADAO(sf);
    }

    @Bean
    public MemberDAO getMemberDAO(SessionFactory sf) {
        return new MemberJPADAO(sf);
    }

    private Facility convertToEntity(FacilityDTO facilityDTO) {
        Facility facility = new Facility();
        facility.setFacid(facilityDTO.getFacid());
        facility.setName(facilityDTO.getName());
        facility.setMemberCost(facilityDTO.getMemberCost());
        facility.setGuestCost(facilityDTO.getGuestCost());
        facility.setInitialOutlay(facilityDTO.getInitialOutlay());
        facility.setMonthlyMaintenance(facilityDTO.getMonthlyMaintenance());
        return facility;
    }

}