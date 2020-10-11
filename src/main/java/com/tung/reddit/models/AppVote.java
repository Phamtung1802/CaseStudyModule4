package com.tung.reddit.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "votes")
public class AppVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppPost post;

    @ManyToOne
    private AppUser appUser;

    private Long value;
}