package es.cifpcm.SpringRestMarrero.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer usuid;
    @Column(name = "user_name", nullable = false)
    private String usuname;
    @Column(name = "password", nullable = false)
    private String usupass;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Integer getUsuid() {
        return usuid;
    }

    public void setUsuid(Integer usuid) {
        this.usuid = usuid;
    }

    public String getUsuname() {
        return usuname;
    }

    public void setUsuname(String usuname) {
        this.usuname = usuname;
    }

    public String getUsupass() {
        return usupass;
    }

    public void setUsupass(String usupass) {
        this.usupass = usupass;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return usupass;
    }

    @Override
    public String getUsername() {
        return usuname;
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