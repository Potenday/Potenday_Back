package com.example.potenday.dto.security;

import com.example.potenday.domain.constant.Role;
import com.example.potenday.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record PotendayPrincipal (
        String email,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String fullname,
        String nickname,
        Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

    /** OAuth2.0 인증을 안한 경우 */
    public static PotendayPrincipal of(String email, String password, Set<Role> roles, String fullname, String nickname) {
        return PotendayPrincipal.of(email, password, roles, fullname, nickname, Map.of());
    }

    /** OAuth2.0 인증한 경우 */
    public static PotendayPrincipal of(String email, String password, Set<Role> roles, String fullname, String nickname, Map<String, Object> oAuth2Attributes) {
        return new PotendayPrincipal(
                email,
                password,
                roles.stream()
                        .map(Role::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                fullname,
                nickname,
                oAuth2Attributes
        );
    }

    public static PotendayPrincipal from(User user) {
        return PotendayPrincipal.of(
                user.getEmail(),
                user.getPassword(),
                user.getRoles(),
                user.getFullname(),
                user.getNickname()
        );
    }

    @Override public String getUsername() { return email; }
    @Override public String getPassword() { return password; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    @Override public String getName() { return email; }
    @Override public Map<String, Object> getAttributes() { return oAuth2Attributes; }
}
