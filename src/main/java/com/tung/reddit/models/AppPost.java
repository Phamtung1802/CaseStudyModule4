package com.tung.reddit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class AppPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String photoName;

    @Transient
    private MultipartFile photo;

    private long likeCount;
    private long commentCount;
    private long voteCount;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private AppStatus status;

    @Column(name = "date_Upload")
    private Date dateUpload;

}