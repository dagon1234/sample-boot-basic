package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    // creat hashmap for employee
    private HashMap<Long, Employee> employeesDB = new HashMap<Long, Employee>();

    // select all employee
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployee() {
        return employeesDB.values();
    }

    // select employee by id
    @GetMapping("employees/{id}")
    public ResponseEntity getEmployeeById(@PathVariable long id) {
        // check if id exists in db
        if (!employeesDB.containsKey(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        return ResponseEntity.ok(employeesDB.get(id));
    }

    // creat new employee
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        // check if id exists
        if (employeesDB.containsKey(employee.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee id already exists");
        }

        // add employee to hashmap
        employeesDB.put(employee.getId(), employee);

        // return craet success message
        return ResponseEntity.ok("Employee had created");
    }

    // update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {

        // update employee
        // Check if the employee ID exists in the database or any other validation
        if (!employeesDB.containsKey(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // Apply the partial updates to the existing employee
        Employee existingEmployee = employeesDB.get(id);
        if (employee.getFirstname() != null) {
            existingEmployee.setFirstname(employee.getFirstname());
        }
        if (employee.getLastname() != null) {
            existingEmployee.setLastname(employee.getLastname());
        }
        if (employee.getSalary() != 0) {
            existingEmployee.setSalary(employee.getSalary());
        }
        if (employee.getBirthdate() != null) {
            existingEmployee.setBirthdate(employee.getBirthdate());
        }

        // Save the updated employee back to the database
        employeesDB.put(id, existingEmployee);

        // Return success message
        return ResponseEntity.ok("Employee updated");
    }

    // delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        if (!employeesDB.containsKey(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // delete employee
        employeesDB.remove(id);

        // return success message
        return ResponseEntity.ok("Employee deleted");
    }
}