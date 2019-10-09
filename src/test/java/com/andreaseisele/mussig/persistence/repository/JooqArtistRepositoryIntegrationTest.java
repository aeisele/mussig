package com.andreaseisele.mussig.persistence.repository;

import com.andreaseisele.mussig.persistence.model.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.truth.Truth.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class JooqArtistRepositoryIntegrationTest {

    @Autowired
    private JooqArtistRepository repository;

    @Test
    public void testSaveNewRecord() {
        Artist artist = new Artist();
        artist.setName("test name");

        final Artist saved = repository.saveOrUpdate(artist);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(artist.getName());
    }

}