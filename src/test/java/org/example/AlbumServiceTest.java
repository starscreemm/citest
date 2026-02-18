package org.example;

import org.example.config.BaseCfg;
import org.example.config.DataJpaCfg;
import org.example.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {BaseCfg.class, DataJpaCfg.class})
@Sql(scripts = {"classpath:testcontainers/create-schema.sql", "classpath:testcontainers/add-nina.sql", "classpath:testcontainers/add-chuck.sql"})
public class AlbumServiceTest extends TestContainersBase {
    private static final Logger logger = LoggerFactory.getLogger(AlbumServiceTest.class);


    @Autowired
    private AlbumService albumService;

    @Test
    void testFindWithReleaseDateGreaterThan() {
        var albums = albumService.findWithReleaseDateGreaterThan(LocalDate.of(2000, 1, 1))
                .peek(a -> logger.info(a.toString())).toList();
        assertEquals(3, albums.size());
    }

}
