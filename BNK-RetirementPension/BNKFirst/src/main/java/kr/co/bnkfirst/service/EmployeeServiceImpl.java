package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.corporate.employee.*;
import kr.co.bnkfirst.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeListDto> getEmployeeList() {
        return employeeMapper.findAllEmployees();
    }

    @Override
    public EmployeeDetailDto getEmployeeDetail(Long empId) {
        return employeeMapper.findEmployeeById(empId);
    }

    @Override
    public List<EmployeeContributionDto> getEmployeeContributions(Long empId) {
        return employeeMapper.findContributionsByEmpId(empId);
    }

    @Override
    public int getTotalEmployees() {
        return employeeMapper.getTotalEmployees();
    }

    @Override
    public List<EmployeeListDto> search(String keyword, String planType) {
        if (planType == null || planType.isEmpty()) {
            planType = "ALL";
        }
        return employeeMapper.search(keyword, planType);
    }

    @Transactional
    @Override
    public void updateEmployee(EmployeeUpdateDto dto) {
        employeeMapper.updateEmployee(dto);
    }

    @Transactional
    @Override
    public void deleteEmployee(Long empId) {
        employeeMapper.deleteEmployee(empId);
    }

    @Override
    public Long getEmployeeCurrentBalance(Long empId) {
        return employeeMapper.getCurrentBalance(empId);
    }

    @Override
    public List<EmployeeAutoDto> autocomplete(String keyword) {
        return employeeMapper.autocomplete(keyword);
    }


    /*******************************************
     * ⭐ 추가해야 하는 부분 (상태 변경 + 퇴사 처리)
     *******************************************/

    /** 직원 재직/휴직 상태 변경 */
    @Transactional
    @Override
    public void updateStatus(Long empId, String status) {
        employeeMapper.updateStatus(empId, status);
    }

    /** 직원 퇴사 처리 (퇴사일 포함) */
    @Transactional
    @Override
    public void retire(Long empId, String status, String retireDate) {
        employeeMapper.updateRetire(empId, status, retireDate);
    }

}
