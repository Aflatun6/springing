package app.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class XUserDetails implements UserDetails {

    private final int id;
    private final String name;
    private final String password;
    private final String[] roles;

    public XUserDetails(int id, String name, String password, String[] roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles)
                .map(s->"ROLE_" + s)
                .map(s-> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("XUserDetails[%d:'%s':'%s':{%s}]", id, name, password, Arrays.toString(roles));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
