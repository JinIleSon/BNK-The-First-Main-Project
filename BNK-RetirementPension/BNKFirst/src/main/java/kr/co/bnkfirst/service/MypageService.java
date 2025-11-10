package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.DealDTO;
import kr.co.bnkfirst.dto.UsersDTO;
import kr.co.bnkfirst.entity.Users;
import kr.co.bnkfirst.mapper.MypageMapper;
import kr.co.bnkfirst.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageService{

    final UsersRepository usersRepository;
    final MypageMapper mypageMapper;

    public UsersDTO findById(String mId) {
        return mypageMapper.findById(mId);
    }

    public DealDTO findByDeal(String mId) {
        return mypageMapper.findByDeal(mId);
    }
}
