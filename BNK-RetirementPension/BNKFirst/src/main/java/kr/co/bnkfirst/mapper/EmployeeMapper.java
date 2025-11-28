package kr.co.bnkfirst.mapper;

import kr.co.bnkfirst.dto.corporate.employee.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeListDto> findAllEmployees();

    EmployeeDetailDto findEmployeeById(@Param("empId") Long empId);

    List<EmployeeContributionDto> findContributionsByEmpId(@Param("empId") Long empId);

    int getTotalEmployees();

    List<EmployeeListDto> search(@Param("keyword") String keyword,
                                 @Param("planType") String planType);

    void updateEmployee(EmployeeUpdateDto dto);

    void deleteEmployee(@Param("empId") Long empId);

    Long getCurrentBalance(@Param("empId") Long empId);

    // ⭐ 자동완성 (EmployeeAutoDto로 타입 변경)
    List<EmployeeAutoDto> autocomplete(@Param("keyword") String keyword);


    /*********************************************
     * ⭐ 여기 아래 2개가 반드시 추가되어야 함!!
     *********************************************/

    /** 직원 재직 상태 변경 (재직/휴직) */
    void updateStatus(@Param("empId") Long empId,
                      @Param("status") String status);

    /** 직원 퇴사 처리 (퇴사일 + 상태 변경 포함) */
    void updateRetire(@Param("empId") Long empId,
                      @Param("status") String status,
                      @Param("retireDate") String retireDate);

}
