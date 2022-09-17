package br.com.impacta.moedinhas.domain.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
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

    private LocalDate birthday;

    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private User parent;

    @OneToMany(mappedBy = "id", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Goal> goals;

    private String resetToken;

    @Transient
    private String parentEmail;

    public String getName() {
        return this.name.trim();
    }

    public Boolean getEnabled() {
        return this.enabled == null || this.enabled;
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
