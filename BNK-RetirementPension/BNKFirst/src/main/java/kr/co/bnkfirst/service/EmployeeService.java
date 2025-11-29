package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.corporate.employee.*;
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

    // ⭐ 자동완성 (전용 DTO)
    List<EmployeeAutoDto> autocomplete(String keyword);

    // ⭐ 직원 재직 상태 변경 (재직/휴직)
    void updateStatus(Long empId, String status);

    // ⭐ 퇴사 처리 (퇴사일 포함)
    void retire(Long empId, String status, String retireDate);

    // ⭐ 페이지네이션: 직원 목록 (검색 + 페이징)
    List<EmployeeListDto> getEmployeePage(String keyword, String planType, int offset, int size);

    // ⭐ 페이지네이션: 총 직원 수 조회 (검색 조건 포함)
    int getEmployeeTotalCount(String keyword, String planType);

}