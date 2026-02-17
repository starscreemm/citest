package org.example.repository;

import org.example.entity.Album;
import org.example.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Iterable<Album> findBySinger(Singer singer);

    Iterable<Album> findByReleaseDateGreaterThan(LocalDate rd);

    @Query("select a from Album a where a.title like %:title%")
    Iterable<Album> findByTitle(@Param("title") String t);
}
