package com.tung.reddit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String text;

    @Column(name="img")
    private String img;

    @ManyToOne()
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private AppPost post;
    private Instant createdDate;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private AppUser user;
}