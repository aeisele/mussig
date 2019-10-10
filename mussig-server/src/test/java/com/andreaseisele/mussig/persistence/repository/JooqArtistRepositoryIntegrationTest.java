package com.andreaseisele.mussig.persistence.repository;

import com.andreaseisele.mussig.persistence.model.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class JooqArtistRepositoryIntegrationTest {

    @Autowired
    private JooqArtistRepository repository;

    @Test
    public void testSaveNewRecordAndRetrieveIt() {
        Artist artist = newArtist();

        final Artist saved = repository.saveOrUpdate(artist);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(artist.getName());

        final Optional<Artist> maybeFound = repository.findById(saved.getId());
        assertThat(maybeFound).isPresent();
        final Artist found = maybeFound.get();
        assertThat(found).isEqualTo(saved);
    }

    @Test
    public void testSaveNewRecordAndUpdateIt() {
        Artist artist = newArtist();

        final Artist saved = repository.saveOrUpdate(artist);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(artist.getName());

        saved.setName("updated artist");
        final Artist updated = repository.saveOrUpdate(saved);

        assertThat(updated).isEqualTo(saved);
    }

    private Artist newArtist() {
        Artist artist = new Artist();
        artist.setName("test name");
        return artist;
    }

}