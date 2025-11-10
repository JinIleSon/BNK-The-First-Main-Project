package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.mypage.DealDTO;
import kr.co.bnkfirst.dto.UsersDTO;
import kr.co.bnkfirst.dto.product.PcontractDTO;
import kr.co.bnkfirst.mapper.MypageMapper;
import kr.co.bnkfirst.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService{

    final UsersRepository usersRepository;
    final MypageMapper mypageMapper;

    public UsersDTO findById(String mid) {
        return mypageMapper.findById(mid);
    }

    public DealDTO findByDeal(String mid) {
        return mypageMapper.findByDeal(mid);
    }

    public List<PcontractDTO> findByContract(String mid) {
        return mypageMapper.findByContract(mid);
    }

    public int findByBalance(String mid) {
        return mypageMapper.findByBalance(mid);
    }
}
