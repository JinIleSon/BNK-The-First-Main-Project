package kr.co.bnkfirst.service;

import kr.co.bnkfirst.domain.Contribution;
import kr.co.bnkfirst.mapper.ContributionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributionServiceImpl implements ContributionService {

    private final ContributionMapper contributionMapper;

    @Override
    public List<Contribution> getContributionByEmpId(Long empId) {
        return contributionMapper.findByEmpId(empId);
    }

    @Override
    public Long getCurrentBalance(Long empId) {
        return contributionMapper.findCurrentBalance(empId);
    }

    @Override
    public Long getTotalAccumulatedAmount() {
        return contributionMapper.getTotalAccumulatedAmount();
    }

    // ⬇⬇⬇ 반드시 추가해야 하는 부분
    @Override
    public Long getExpectedContributionForMonth() {
        return contributionMapper.getExpectedContributionForMonth();
    }
}
