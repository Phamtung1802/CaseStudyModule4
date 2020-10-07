package com.tung.reddit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "app_roles")
public class AppRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = AppUser.class,cascade = {CascadeType.ALL})
    private List<AppUser> appUsers;

    @Override
    public String getAuthority() {
        return this.getName();
    }
}