package ru.panic.authservice.template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {
    private String jwtToken;
    private Long timestamp;
}
