package com.vadims.books.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Getter
    @NonNull
    private Long id;

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;
}