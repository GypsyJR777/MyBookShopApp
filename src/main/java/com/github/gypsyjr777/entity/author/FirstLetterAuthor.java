package com.github.gypsyjr777.entity.author;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FirstLetterAuthor {
    private String firstLetter;
    private List<Author> authors;
}
