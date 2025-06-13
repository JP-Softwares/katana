package com.jpsoftwares.katana.service;
import com.jpsoftwares.katana.modelo.Cliente;
import com.jpsoftwares.katana.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public ClienteDetailsService(ClienteRepository clienteRepository, PasswordEncoder encoder) {
        this.clienteRepository = clienteRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = (Cliente) clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado: " + email));

        return User.builder()
                .username(cliente.getEmail())
                .password(cliente.getSenha())
                .roles("CLIENTE")
                .build();
    }

    public Cliente register(Cliente dto) {
        dto.setSenha(encoder.encode(dto.getSenha()));
        return clienteRepository.save(dto);
    }
}
