package ru.panic.authservice.template.service;

import ru.panic.authservice.template.dto.SignInRequest;
import ru.panic.authservice.template.dto.SignInResponse;
import ru.panic.authservice.template.dto.SignUpRequest;
import ru.panic.authservice.template.entity.Client;

public interface AuthService {
    SignInResponse handleSignIn(SignInRequest signInRequest);
    SignInResponse handleSignUp(SignUpRequest signUpRequest);
    Client getInfoByJwt(String jwtToken);
}
