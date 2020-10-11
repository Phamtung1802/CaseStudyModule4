package com.tung.reddit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;;
import java.util.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String content;
    private Long loveCount;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private AppPost post;

    private Date timeComment;
}