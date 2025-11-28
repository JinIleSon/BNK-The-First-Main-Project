package kr.co.bnkfirst.service;

import kr.co.bnkfirst.domain.Contribution;
import java.util.List;

public interface ContributionService {

    List<Contribution> getContributionByEmpId(Long empId);

    Long getCurrentBalance(Long empId);

    Long getTotalAccumulatedAmount();

    // ⬇⬇⬇ 반드시 추가해야 하는 부분
    Long getExpectedContributionForMonth();
}
