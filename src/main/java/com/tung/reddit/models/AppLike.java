package com.tung.reddit.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "likes")
public class AppLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppPost post;

    @ManyToOne
    private AppUser appUser;
}