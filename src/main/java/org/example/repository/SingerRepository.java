package org.example.repository;

import org.example.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
    Iterable<Singer> findByFirstName(String firstName);

    Iterable<Singer> findByFirstNameAndLastName(String firstName, String lastName);

    @Modifying(clearAutomatically = true)
    @Query("update Singer s set s.firstName = :firstName where s.id = :id")
    int setFirstNameFor(@Param("firstName") String firstName, @Param("id") Long id);
}
