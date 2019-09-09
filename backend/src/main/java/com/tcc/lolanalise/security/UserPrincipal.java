package com.tcc.lolanalise.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.lolanalise.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonIgnore
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Boolean valido;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(Usuario user) {
        List<GrantedAuthority> authorities = user.getPermicoesCollection().stream().map(permicao ->
                new SimpleGrantedAuthority(permicao.getNome())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getValido(),
                authorities
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
