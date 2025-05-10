package co.edu.javeriana.caravana_medieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval.dto.JwtAuthenticationResponse;
import co.edu.javeriana.caravana_medieval.dto.LoginDTO;
import co.edu.javeriana.caravana_medieval.model.Jugador;
import co.edu.javeriana.caravana_medieval.repository.JugadorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthenticationService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Jugador jugador = jugadorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(jugador.getUsername());
        return new JwtAuthenticationResponse(jwt, jugador.getEmail(), jugador.getRole());
    }

}
