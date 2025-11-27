package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.corporate.employee.EmployeeListDto;
import kr.co.bnkfirst.dto.corporate.employee.EmployeeDetailDto;
import kr.co.bnkfirst.dto.corporate.employee.EmployeeContributionDto;
import kr.co.bnkfirst.dto.corporate.employee.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeListDto> getEmployeeList();

    EmployeeDetailDto getEmployeeDetail(Long empId);

    List<EmployeeContributionDto> getEmployeeContributions(Long empId);

    int getTotalEmployees();

    List<EmployeeListDto> search(String keyword, String planType);

    Long getEmployeeCurrentBalance(Long empId);

    void updateEmployee(EmployeeUpdateDto dto);

    void deleteEmployee(Long empId);

    // ⭐ 자동완성 기능 추가
    List<EmployeeListDto> autocomplete(String keyword);
}
