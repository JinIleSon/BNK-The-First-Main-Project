package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    Employee findById(Long empId);

    List<Employee> findAll();
}
