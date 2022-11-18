package hp.rest.webservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class EmployeeManager {

    private static Staff staff = new Staff();

    public Staff getStaff() {
        return staff;
    }

    public EmployeeManager() {
        staff.addEmployee(new Employee("1", "Amha", "Kindu", "amha@gmail.com","Engineer"));
        staff.addEmployee(new Employee("2", "Kaleab", "Kindu", "kaleab@gmail.com","Engineer"));
        staff.addEmployee(new Employee("3", "Gadisa", "Amenu", "gadisa@gmail.com","Engineer"));
    }
}
