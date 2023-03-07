package com.portfolioarg.ec.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolioarg.ec.security.entity.Rol;
import com.portfolioarg.ec.security.enums.RolName;
import com.portfolioarg.ec.security.repository.iRolRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;

    public Optional<Rol> getByRolName(RolName rolName) {
        return irolRepository.findByRolName(rolName);
    }

    public void save(Rol rol) {
        irolRepository.save(rol);
    }
}
