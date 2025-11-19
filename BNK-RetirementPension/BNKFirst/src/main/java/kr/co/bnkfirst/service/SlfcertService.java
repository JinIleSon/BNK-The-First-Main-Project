package kr.co.bnkfirst.service;

import kr.co.bnkfirst.dto.product.SlfcertDTO;
import kr.co.bnkfirst.mapper.SlfcertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlfcertService {
    private final SlfcertMapper slfcertMapper;

    public Optional<SlfcertDTO> saveSlfcert(SlfcertDTO slfcertDTO){
        if (slfcertDTO.getNatcd().equals("US"))
            slfcertDTO.setFtype("W9");
        else {
            slfcertDTO.setFtype("W8");
        }
        return slfcertMapper.saveSlfcert(slfcertDTO);
    }
}
