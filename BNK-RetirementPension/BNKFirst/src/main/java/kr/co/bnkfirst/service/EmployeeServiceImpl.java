package kr.co.bnkfirst.service;

import kr.co.bnkfirst.domain.Employee;
import kr.co.bnkfirst.mapper.EmployeeMapper;   // ★ 반드시 있어야 한다
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Override
    public Employee getEmployeeById(Long empId) {
        return employeeMapper.findById(empId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.findAll();
    }
}
