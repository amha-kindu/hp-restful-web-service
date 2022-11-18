package hp.rest.webservice;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeRestController {
    private final Map<String, Employee> employees = new HashMap<>();

    private final EmployeeManager manager;

    public EmployeeRestController(EmployeeManager manager){
        this.manager = manager;
    }
    @GetMapping("/staff")
    List<Employee> getAllEmployees(){
        Staff staff = manager.getStaff();
        return staff.getAllEmployees();
    }
    @GetMapping("/employee")
    Employee getEmployee(@RequestParam String id){
        Staff staff = manager.getStaff();
        return staff.getEmployee(id);
    }

    @PostMapping("/employee")
    Employee register(@RequestBody Employee employee){
        Staff staff = manager.getStaff();
        return staff.addEmployee(employee);
    }

    @PutMapping("/employee")
    Employee updateEmployeeInfo(@RequestBody Employee employee){
        Staff staff = manager.getStaff();
        return staff.updateEmployee(employee);
    }

    @DeleteMapping("/employee")
    boolean removeEmployee(@RequestParam String id){
        Staff staff = manager.getStaff();
        return staff.removeEmployee(id);
    }

}
