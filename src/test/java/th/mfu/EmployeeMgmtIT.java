package th.mfu;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeMgmtIT {
    private static Client client;

    private static String WEB_SERVICE_URI = "http://localhost:8080/employees/";

    @BeforeClass
    public static void createClient() {
        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void closeClient() {
        client.close();
    }

    // test create
    @Test
    public void testCreate() {
        // Act
        Employee emp = new Employee();
        emp.setFirstname("David");
        emp.setLastname("Miller");
        emp.setBirthday(new Date());
        emp.setSalary(90000);
        Account account = new Account();
        account.setUsername("david");
        account.setPassword("1234");
        emp.setAccount(account);

        // prepare invocation to the employee service
        Builder builder = client.target(WEB_SERVICE_URI).request().accept(MediaType.APPLICATION_JSON);
        // send the request
        Response response = builder.post(Entity.json(emp));

        int responseCode = response.getStatus();
        // Assert if response code is 200 OK
        assertEquals(Response.Status.OK.getStatusCode(), responseCode);
    }

    // test query all

    // test delete

    // test partial update

}
