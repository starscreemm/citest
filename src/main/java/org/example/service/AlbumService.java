package org.example.service;

import org.example.entity.Album;
import org.example.entity.Singer;
import org.example.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Stream<Album> findBySinger(Singer singer) {
        return StreamSupport.stream(albumRepository.findBySinger(singer).spliterator(), false);
    }

    public Stream<Album> findWithReleaseDateGreaterThan(LocalDate rd) {
        return StreamSupport.stream(albumRepository.findByReleaseDateGreaterThan(rd).spliterator(), false);
    }

    public Stream<Album> findByTitle(String title) {
        return StreamSupport.stream(albumRepository.findByTitle(title).spliterator(), false);
    }


}
