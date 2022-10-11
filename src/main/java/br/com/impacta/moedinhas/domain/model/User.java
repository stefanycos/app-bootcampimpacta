package br.com.impacta.moedinhas.domain.model;

import br.com.impacta.moedinhas.domain.model.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter()
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", nullable = true)
    private User parent;

    @OneToMany(mappedBy = "id", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Goal> goals;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

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

    public Optional<Account> getAccount() {
        if (this.getRole().equals(Role.RESPONSIBLE)) {
            return this.getParent().isPresent() ? this.parent.getAccount() : Optional.empty();
        }

        return Optional.ofNullable(this.account);
    }

    public Optional<User> getParent() {
        return Optional.ofNullable(this.parent);
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
