package com.github.gypsyjr777.service;

import com.github.gypsyjr777.entity.genre.GenreEntity;
import com.github.gypsyjr777.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> getAllGenres() {
        return genreRepository.findAll();
    }

    public GenreEntity getGenreById(Integer genreId) {
        return genreRepository.findById(genreId).get();
    }
}
