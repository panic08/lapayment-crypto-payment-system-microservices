package ru.panic.authservice.template.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.panic.authservice.security.jwt.JwtUtil;
import ru.panic.authservice.template.dto.SignInRequest;
import ru.panic.authservice.template.dto.SignInResponse;
import ru.panic.authservice.template.dto.SignUpRequest;
import ru.panic.authservice.template.entity.Client;
import ru.panic.authservice.template.exception.InvalidCredentialsException;
import ru.panic.authservice.template.repository.impl.ClientRepositoryImpl;
import ru.panic.authservice.template.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {
    public AuthServiceImpl(ClientRepositoryImpl clientRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    private final ClientRepositoryImpl clientRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public SignInResponse handleSignIn(SignInRequest signInRequest) {
        Client client = clientRepository.findByUsername(signInRequest.getUsername());

        if (client == null || !passwordEncoder.matches(client.getPassword(), signInRequest.getPassword())){
            throw new InvalidCredentialsException("Wrong login or password");
        }

        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setJwtToken(jwtUtil.generateToken(client));
        signInResponse.setTimestamp(System.currentTimeMillis());

        return signInResponse;
    }

    @Override
    public SignInResponse handleSignUp(SignUpRequest signUpRequest) {
        Client client = clientRepository.findByUsername(signUpRequest.getUsername());

        if (client != null){
            throw new InvalidCredentialsException("This user already exists");
        }

        Client client1 = new Client();
        client1.setUsername(signUpRequest.getUsername());
        client1.setEmail(signUpRequest.getEmail());
        client1.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        client1.setBtc_balance(0d);
        client1.setEth_balance(0d);
        client1.setLtc_balance(0d);
        client1.setTrx_balance(0d);
        client1.setTon_balance(0d);
        client1.setMatic_balance(0d);
        client1.setXrp_balance(0d);
        client1.setTetherERC20_balance(0d);
        client1.setRegisteredAt(System.currentTimeMillis());

        clientRepository.save(client1);

        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setJwtToken(jwtUtil.generateToken(client1));
        signInResponse.setTimestamp(client1.getRegisteredAt());

        return signInResponse;
    }

    @Override
    public Client getInfoByJwt(String jwtToken) {
        if (!jwtUtil.isJwtValid(jwtToken) || jwtUtil.isTokenExpired(jwtToken)){
            throw new InvalidCredentialsException("Invalid JWT token");
        }

        return clientRepository.findByUsername(jwtUtil.extractUsername(jwtToken));
    }
}
