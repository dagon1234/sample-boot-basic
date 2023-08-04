package th.mfu;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public List<Employee> findAll();

    public List<Employee> findByFirstname(String firstname);

    public List<Employee> findByFirstnameStartingWith(String prefix);
}
