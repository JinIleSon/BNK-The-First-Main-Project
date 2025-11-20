package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.product.SlfcertDTO;
import kr.co.bnkfirst.entity.product.Slfcert;
import kr.co.bnkfirst.mapper.SlfcertMapper;
import kr.co.bnkfirst.repository.product.SlfcertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlfcertService {
    private final SlfcertMapper slfcertMapper;
    private final SlfcertRepository slfcertRepository;

    public SlfcertDTO saveSlfcert(SlfcertDTO slfcertDTO){
        Slfcert savedSlfcert = slfcertRepository.save(slfcertDTO.toEntity());
        if (savedSlfcert.getId() == null){
            throw new IllegalArgumentException("SLFCERT ID가 생성되지 않았습니다.");
        }

        return savedSlfcert.toDTO();
    }
}
