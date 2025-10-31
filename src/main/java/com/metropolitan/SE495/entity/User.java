package com.metropolitan.SE495.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metropolitan.SE495.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 190)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)              // <-- STRING, ne ORDINAL!
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    @JsonIgnore
    public void prePersist() {
        if (role == null) role = Role.USER;
    }

    @JsonIgnore
    private static final Map<String, List<String>> rolePermissions = Map.of(
            "USER", Arrays.asList("READ_ONLY"),
            "ADMIN", Arrays.asList("FULL_ACCESS")
    );

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> permissions = rolePermissions.getOrDefault(role, List.of());
        List<GrantedAuthority> authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }



    @JsonIgnore
    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }
}