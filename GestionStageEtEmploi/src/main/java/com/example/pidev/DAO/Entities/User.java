package com.example.pidev.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;

    String username;
    String mail;
    String password;
    @Transient
    String verifpassword;
    @Temporal(TemporalType.DATE)
    Date dateCreation;
    @ManyToOne
    Role roles;
    @Transient
    @Nullable
    String roleName;
    private String verificationCode;
    private String PasswordToken;
    private Boolean locked = false;
    private Boolean enabled = false;

    public String getRoleName() {
        return (roles != null) ? roles.getName() : null;
    }

    public Role getRole() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    @PrePersist
    public void setDateCreation() {
        this.dateCreation = new Date();
    }
}
