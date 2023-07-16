package org.example.myApp;

import org.example.datamodel.Facility;
import org.example.services.SqlReader;
import org.example.services.impl.jpa.FacilityJPADAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.FacilityDTO;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("facilities")
public class FacilitiesRestController {

    @Inject
    SessionFactory sessionFactory;
    @Inject
    DataSource dataSource;
    @Autowired
    FacilityJPADAO facilityJPADAO;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacilityDTO> addFacility(@RequestBody FacilityDTO facilityDTO) {
        Facility facility = new Facility(
                facilityDTO.getFacid(),
                facilityDTO.getName(),
                facilityDTO.getMemberCost(),
                facilityDTO.getGuestCost(),
                facilityDTO.getInitialOutlay(),
                facilityDTO.getMonthlyMaintenance()
        );
        facilityJPADAO.create(facility);

        FacilityDTO createdFacilityDTO = new FacilityDTO(
                facility.getFacid(),
                facility.getName(),
                facility.getMemberCost(),
                facility.getGuestCost(),
                facility.getInitialOutlay(),
                facility.getMonthlyMaintenance()
        );

        return ResponseEntity.ok(createdFacilityDTO);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FacilityDTO>> getFacilities() {
        try {
            String basePath = "src/test/Resources/";

            // Execute the SQL scripts using a native query
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(SqlReader.readAll(basePath + "create-facilities.sql")).executeUpdate();
            session.createNativeQuery(SqlReader.readAll(basePath + "insert-facilities.sql")).executeUpdate();
            transaction.commit();
            session.close();

            // Retrieve the facilities from the database
            List<Facility> facilities = facilityJPADAO.getAll();

            // Convert the Facility objects to FacilityDTO objects
            List<FacilityDTO> facilityDTOs = facilities.stream()
                    .map(facility -> new FacilityDTO(
                            facility.getFacid(),
                            facility.getName(),
                            facility.getMemberCost(),
                            facility.getGuestCost(),
                            facility.getInitialOutlay(),
                            facility.getMonthlyMaintenance()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(facilityDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
