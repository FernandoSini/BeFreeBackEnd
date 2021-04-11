package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "EventOwner")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventOwner implements Serializable, UserDetails {

    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "event_owner_id",unique = true)
    private String id;


    @Column(name = "event_owner_name",unique = true)
    private String ownerName;
    //this can be user document number, or company number
    @Column(name = "document_number")
    private int documentNumber;

    @OneToMany(orphanRemoval = true)
    private List<Event> events;

    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_owner_permissions", joinColumns = {@JoinColumn(name = "id_event_owner", referencedColumnName = "event_owner_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")})
    private List<Permission> permissions;

    //pegando as funcoes dos usuários
    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : this.permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getUsername() {
        return this.ownerName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


}
