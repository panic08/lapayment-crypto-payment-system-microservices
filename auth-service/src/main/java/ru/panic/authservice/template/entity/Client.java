package ru.panic.authservice.template.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Data
public class Client implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Double btc_balance;
    private Double eth_balance;
    private Double ltc_balance;
    private Double trx_balance;
    private Double ton_balance;
    private Double matic_balance;
    private Double xrp_balance;
    private Double tetherERC20_balance;
    private Boolean isAccountNonLocked;
    private Long registeredAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
