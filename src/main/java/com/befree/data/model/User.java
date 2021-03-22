package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "likeReceived"})
public class User implements UserDetails, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_name", unique = true)
    @Size(min = 3)
    private String userName;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "userSendLike", orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonBackReference
    private List<Like> likesSended;

    @OneToMany(mappedBy = "userLiked", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Like> likeReceived;

    @ManyToMany(targetEntity = Graduation.class,
            fetch = FetchType.LAZY
            /*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "graduation_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "graduation_id", referencedColumnName = "graduation_id")})
    @JsonIgnoreProperties({"users"})
    private List<Graduation> userGraduations;

    @Enumerated(EnumType.ORDINAL)
    private Usertype usertype;

    @Column(name = "birthday")
    private String birthDay;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permissions", joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")})
    private List<Permission> permissions;

    @OneToMany(mappedBy = "you")
    private List<Match> matches;

    @Transient
    private String token;

    public User() {
    }

    public User(String id, @Size(min = 3) String userName, String firstName,
                String lastName, String gender, List<Like> likesSended,
                List<Like> likeReceived, List<Graduation> userGraduations,
                List<Match> matches,
                String email, Usertype usertype, String birthDay,
                String password, String token) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.likesSended = likesSended;
        this.likeReceived = likeReceived;
        this.userGraduations = userGraduations;
        this.email = email;
        this.usertype = usertype;
        this.birthDay = birthDay;
        this.password = password;
        this.matches = matches;
        this.token = token;
    }


    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Like> getLikesSended() {
        return likesSended;
    }

    public void setLikesSended(List<Like> likesSended) {
        this.likesSended = likesSended;
    }

    public List<Like> getLikeReceived() {
        return likeReceived;
    }

    public void setLikeReceived(List<Like> likeReceived) {
        this.likeReceived = likeReceived;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    public List<Graduation> getUserGraduations() {
        return userGraduations;
    }

    public void setUserGraduations(List<Graduation> userGraduations) {
        this.userGraduations = userGraduations;
    }

    public String getBirthDay() {
//        LocalDate localDateTime =  LocalDate.now();
//        DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        localDateTime.format(dateFormat);
        // DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // int birthdayYear = LocalDate.parse(age, dateTimeFormatter).getYear();
        // int yearNow = LocalDate.now().getYear();

        // int data = (yearNow - birthdayYear);
        // return String.valueOf(data);
        return birthDay;
    }

    //pegando as funcoes dos usuários
    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : this.permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    public void setBirthday(String birthDay) {
        this.birthDay = birthDay;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
