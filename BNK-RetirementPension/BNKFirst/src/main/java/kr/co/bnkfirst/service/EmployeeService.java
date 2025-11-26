package kr.co.bnkfirst.service;

import kr.co.bnkfirst.domain.Employee;
import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long empId);
    List<Employee> getAllEmployees();
}
