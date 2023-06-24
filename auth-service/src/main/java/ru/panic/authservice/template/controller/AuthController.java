package ru.panic.authservice.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.authservice.template.dto.SignInRequest;
import ru.panic.authservice.template.dto.SignInResponse;
import ru.panic.authservice.template.dto.SignUpRequest;
import ru.panic.authservice.template.entity.Client;
import ru.panic.authservice.template.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }
    private final AuthServiceImpl authService;
    @PostMapping("/signIn")
    private SignInResponse signIn(@RequestBody SignInRequest signInRequest){
        return authService.handleSignIn(signInRequest);
    }
    @PostMapping("/signUp")
    private SignInResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authService.handleSignUp(signUpRequest);
    }
    @PostMapping("/getInfoByJwt")
    private Client getInfoByJwt(@RequestParam(name = "jwtToken") String jwtToken){
        return authService.getInfoByJwt(jwtToken);
    }
}
