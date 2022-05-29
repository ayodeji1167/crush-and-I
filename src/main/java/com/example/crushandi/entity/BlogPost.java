package com.example.crushandi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Lob
    private String content;

    private String imageName;

    @NotBlank
    private String category;

    private int view;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = {"role", "email", "password"})
    private AppUser appUser;

    private String createdDate;

    private String updatedDate;

}
