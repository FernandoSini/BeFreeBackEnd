package com.befree.data.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégia de geração dos ids
    @Column(name = "id_permission")
    private Long id;

    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        //retornar description
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
