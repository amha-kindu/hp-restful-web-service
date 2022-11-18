package hp.rest.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Staff {
    private HashMap<String, Employee> employees;

    public Staff() {
        this.employees = new HashMap<>();
    }

    public List<Employee> getAllEmployees(){
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(String emp){
        return employees.get(emp);
    }

    public Employee addEmployee(Employee emp){
        String id = employees.size()+1+"";
        emp.setId(id);
        employees.put(id, emp);
        return employees.get(id);
    }
    public boolean removeEmployee(String id){
        return employees.remove(id)!=null;
    }
    public Employee updateEmployee(Employee emp){
        employees.put(emp.getId(), emp);
        return emp;
    }
}
