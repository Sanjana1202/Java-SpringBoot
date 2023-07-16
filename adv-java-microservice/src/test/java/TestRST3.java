import org.example.SpringBootMicroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import services.FacilityDTO;

import javax.inject.Inject;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringJUnitConfig(SpringBootMicroService.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRST3 {

    @Inject
    DataSource dataSource;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddFacility() {
        String baseUrl = "http://localhost:" + port + "/facilities";

        FacilityDTO facilityDTO = new FacilityDTO(123, "Test Facility", 10.0, 15.0, 100.0, 5.0);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FacilityDTO> request = new HttpEntity<>(facilityDTO, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFacilities() {
        String baseUrl = "http://localhost:" + port + "/facilities";

        ResponseEntity<FacilityDTO[]> response = restTemplate.getForEntity("/facilities", FacilityDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        FacilityDTO[] facilities = response.getBody();

        assertEquals(9, facilities.length);

    }
}

