package org.example.service;

import org.example.entity.Singer;
import org.example.repository.SingerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SingerService {

    private final SingerRepository singerRepository;

    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Singer updateFirstName(Long id, String firstName) {
        singerRepository.findById(id)
                .ifPresent(singer -> singer.setFirstName(firstName));
        return singerRepository.findById(id).orElse(null);
    }
}
