package th.mfu;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testGetAllEmployees() {
        List<Employee> reaponse = repository.findAll();
        assertEquals(10, reaponse.size());
    }
}
