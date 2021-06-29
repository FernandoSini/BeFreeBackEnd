package com.befree.data.model.vo;

import com.befree.data.model.Permission;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"event_owner_id", "event_owner_name", "avatar_profile_owner",
        "document_number", "event_owner_email", "createdAt"})
@JsonIgnoreProperties({"links", "roles", "authorities", "permissions",
        "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "username"})
public class EventOwnerVO extends RepresentationModel implements UserDetails, Serializable {

    @Mapping("id")
    @JsonProperty(value = "event_owner_id")
    private String id;
    @JsonProperty(value = "event_owner_name")
    private String ownerName;
    @JsonProperty(value = "document_number")
    private Integer documentNumber;
    @JsonProperty(value="event_owner_email")
    private String email;
    @JsonIgnoreProperties({"links"})
    @ToString.Exclude
    private List<EventVO> events;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    @JsonProperty(value="avatar_profile_owner")
    private EventOwnerAvatarVO avatarProfile;
    private Boolean enabled;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private List<Permission> permissions;
    @JsonProperty(value = "event_owner_token", access = JsonProperty.Access.READ_ONLY)
    private String token;
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
        return this.accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
