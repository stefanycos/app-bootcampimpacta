package br.com.impacta.moedinhas.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String password;

    private String email;

    private Boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime birthday;

    @ManyToOne
    private User parent;

    @OneToMany(mappedBy = "parent")
    private Collection<User> children;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return this.name.trim();
    }

    public Boolean getEnabled() {
        return enabled == null ? true : enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }
}
