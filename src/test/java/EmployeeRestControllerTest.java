import hp.rest.webservice.Employee;
import hp.rest.webservice.EmployeeManager;
import hp.rest.webservice.RestServiceApplication;
import hp.rest.webservice.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes=RestServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeRestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeManager manager;

    @Test
    public void getAllEmployees() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/staff",
                List.class).size()).isEqualTo(3);
    }

    @Test
    public void getEmployee() throws Exception {
        Staff st = manager.getStaff();
        int id = (int)(Math.random()*4+1);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/employee?id="+id,
                Employee.class)).isEqualTo(st.getEmployee(id+""));
    }

    @Test
    public void addEmployeeTest() throws Exception{
        int prev = manager.getStaff().getAllEmployees().size();
        Employee newEmployee = new Employee("1", "Tony", "Stark", "ironman@gmail.com","IronMan");
        this.restTemplate.postForObject("http://localhost:" + port + "/employee",
                newEmployee, Employee.class);
        assertThat(prev+1).isEqualTo(manager.getStaff().getAllEmployees().size());
    }

    @Test
    public void removeEmployeeTest() throws Exception{
        Staff staff = manager.getStaff();
        int id = (int)(Math.random()*4+1);
        this.restTemplate.delete("http://localhost:" + port + "/employee?id="+id);
        assertThat(staff.getEmployee(id+"")).isNull();
    }

    @Test
    public void updateEmployeeTest() throws Exception{
        Staff staff = manager.getStaff();
        Employee emp = staff.getAllEmployees().get(0);
        String new_first_name = "John";
        Employee newData = new Employee(emp.getId(), new_first_name, emp.getLast_name(), emp.getEmail(), emp.getTitle());
        this.restTemplate.put("http://localhost:" + port + "/employee?id="+emp.getId(), newData);
        assertThat(staff.getEmployee(emp.getId()).getFirst_name()).isEqualTo(new_first_name);
    }
}
