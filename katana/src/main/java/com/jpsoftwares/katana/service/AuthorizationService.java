package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    ProfissionalRepository profissionalRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profissionalRepository.findByEmail(username);
    }
}
